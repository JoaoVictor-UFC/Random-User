package com.joao.victor.random.user.v1.service;

import com.joao.victor.random.user.v1.dtos.CreateLoginRequest;
import com.joao.victor.random.user.v1.entities.LoginEntity;
import com.joao.victor.random.user.v1.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService {

    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired private LoginRepository loginRepository;

    public LoginEntity saveLogin(CreateLoginRequest request){
        LoginEntity entity = new LoginEntity();
        entity.setUuid(request.getUuid());
        entity.setUsername(request.getUsername());
        entity.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        entity.setSalt(request.getSalt());
        entity.setMd5(request.getMd5());
        entity.setSha1(request.getSha1());
        entity.setSha256(request.getSha256());
        return loginRepository.save(entity);
    }
}
