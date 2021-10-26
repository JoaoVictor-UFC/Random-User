package com.joao.victor.random.user.v1.service;

import com.joao.victor.random.user.v1.entities.UserEntity;
import com.joao.victor.random.user.v1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByLogin(login);
        if (!user.isPresent())
            throw new UsernameNotFoundException("User not found, please check your credentials.");
        return new User(user.get().getLogin().getUsername(), user.get().getLogin().getPassword(),
                true, true, true, true, new ArrayList<>());
    }
}
