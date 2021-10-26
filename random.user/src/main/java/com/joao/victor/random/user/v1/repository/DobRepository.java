package com.joao.victor.random.user.v1.repository;

import com.joao.victor.random.user.v1.entities.DobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DobRepository extends JpaRepository<DobEntity, Long> {
}