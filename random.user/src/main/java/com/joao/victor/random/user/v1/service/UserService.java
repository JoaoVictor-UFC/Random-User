package com.joao.victor.random.user.v1.service;

import com.joao.victor.random.user.errorExceptions.ResourceBadRequestException;
import com.joao.victor.random.user.errorExceptions.ResourceNotFoundException;
import com.joao.victor.random.user.v1.dtos.*;
import com.joao.victor.random.user.v1.entities.*;
import com.joao.victor.random.user.v1.repository.*;
import com.joao.victor.random.user.v1.utils.ImportDateRandomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired private UserRepository userRepository;
    @Autowired private NameRepository nameRepository;
    @Autowired private DobRepository dobRepository;
    @Autowired private IdRepository idRepository;
    @Autowired private RegisteredRepository registeredRepository;
    @Autowired private LocationService locationService;
    @Autowired private LoginService loginService;
    @Autowired private PictureService pictureService;

    public UserEntity findUserByUserName(String userName) {
        return userRepository.findByUserName(userName).get();
    }

    public void importForApiRandomUser(Long qtdImports) throws Exception {
        List<CreateUserRequest> list = ImportDateRandomUser.importDateRandomUser(qtdImports);
        try {
            list.stream().forEach((nve) -> {
                createUser(nve);
            });
        }catch (Exception e){
            throw new ResourceNotFoundException("ERRO: " + e);
        }
    }

    public UserEntity createUser(CreateUserRequest request){
        UserEntity user = new UserEntity();
        user.setImportedAt(LocalDateTime.now());
        user.setGender(request.getGender());
        user.setName(saveName(request.getName()));
        user.setLocation(locationService.saveLocation(request.getLocation()));
        user.setEmail(request.getEmail());
        user.setLogin(loginService.saveLogin(request.getLogin()));
        user.setDob(saveDob(request.getDob()));
        user.setRegistered(saveRegistered(request.getRegistered()));
        user.setPhone(request.getPhone());
        user.setCell(request.getCell());
        user.setIdEntity(saveId(request.getId()));
        user.setPicture(pictureService.savePicture(request.getPicture()));
        user.setNat(request.getNat());
        return userRepository.save(user);
    }

    public UserEntity updateUser(Long id, CreateUserRequest request){
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()){
            user.get().setUpdatedAt(LocalDateTime.now());
            user.get().setGender(request.getGender());
            user.get().setName(saveName(request.getName()));
            user.get().setLocation(locationService.saveLocation(request.getLocation()));
            user.get().setEmail(request.getEmail());
            user.get().setLogin(loginService.saveLogin(request.getLogin()));
            user.get().setDob(saveDob(request.getDob()));
            user.get().setRegistered(saveRegistered(request.getRegistered()));
            user.get().setPhone(request.getPhone());
            user.get().setCell(request.getCell());
            user.get().setIdEntity(saveId(request.getId()));
            user.get().setPicture(pictureService.savePicture(request.getPicture()));
            user.get().setNat(request.getNat());
            return userRepository.save(user.get());
        }
        throw new ResourceBadRequestException("User not found!");
    }

    public void deleteUser(Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.delete(user.get());
        }
        throw new ResourceBadRequestException("User not found!");
    }

    public UserResponse findUserById (Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()){
            return fromUserEntityToUserResponse(user.get());
        }
        throw new ResourceBadRequestException("User not found!");
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<UserEntity> findAllUsers(Pageable pageable) {
        return userRepository.findAllBy(pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUserName(userName);
        if (!user.isPresent())
            throw new UsernameNotFoundException("User not found, please check your credentials.");
        return new User(user.get().getLogin().getUsername(), user.get().getLogin().getPassword(),
                true, true, true, true, new ArrayList<>());
    }

    public UserResponse fromUserEntityToUserResponse(@Valid @NotBlank UserEntity user) {
        UserResponse res = new UserResponse();
        res.setGender(user.getGender());
        res.setName(user.getName());
        res.setLocation(user.getLocation());
        res.setEmail(user.getEmail());
        res.setLogin(user.getLogin());
        res.setDob(user.getDob());
        res.setRegistered(user.getRegistered());
        res.setPhone(user.getPhone());
        res.setCell(user.getCell());
        res.setId(user.getIdEntity());
        res.setPicture(user.getPicture());
        res.setNat(user.getNat());
        return res;
    }

    public NameEntity saveName(CreateNameRequest request){
        NameEntity entity = new NameEntity();
        entity.setTitle(request.getTitle());
        entity.setFirst(request.getFirst());
        entity.setLast(request.getLast());
        return nameRepository.save(entity);
    }

    public DobEntity saveDob(DobRequest request){
        DobEntity entity = new DobEntity();
        entity.setDate(request.getDate());
        entity.setAge(request.getAge());
        return dobRepository.save(entity);
    }

    public RegisteredEntity saveRegistered(RegisteredRequest request){
        RegisteredEntity entity = new RegisteredEntity();
        entity.setDate(request.getDate());
        entity.setAge(request.getAge());
        return registeredRepository.save(entity);
    }

    public IdEntity saveId(IdRequest request){
        IdEntity entity = new IdEntity();
        entity.setName(request.getName());
        entity.setValue(request.getValue());
        return idRepository.save(entity);
    }
}
