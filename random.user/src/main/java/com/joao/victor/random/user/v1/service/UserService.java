package com.joao.victor.random.user.v1.service;

import com.joao.victor.random.user.v1.dtos.*;
import com.joao.victor.random.user.v1.entities.*;
import com.joao.victor.random.user.v1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public UserEntity importForApiRandomUser(Long qtdImports){

        return null;
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

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUserName(userName);
        if (!user.isPresent())
            throw new UsernameNotFoundException("User not found, please check your credentials.");
        return new User(user.get().getLogin().getUsername(), user.get().getLogin().getPassword(),
                true, true, true, true, new ArrayList<>());
    }

    public UserResponse fromUserEntityToUserResponse(@Valid @NotBlank UserEntity user) {
        userRepository.findById(user.getId());
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
        res.setIdEntity(user.getIdEntity());
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
