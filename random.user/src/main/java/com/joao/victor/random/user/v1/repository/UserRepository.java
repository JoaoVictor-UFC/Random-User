package com.joao.victor.random.user.v1.repository;

import com.joao.victor.random.user.v1.dtos.UserResponse;
import com.joao.victor.random.user.v1.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.login.username = ?1")
    Optional<UserEntity> findByUserName(String username);

    Optional<UserEntity> findById(Long id);

    Page<UserEntity> findAllBy(Pageable pg);
}
