package com.joao.victor.random.user.v1.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "login_user")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public @Data class LoginEntity extends AbstractEntity<Long> implements Serializable {

    private String uuid;

    private String username;

    private String password;

    private String salt;

    private String md5;

    private String sha1;

    private String sha256;
}
