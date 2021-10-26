package com.joao.victor.random.user.v1.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "dob_user")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public @Data class DobEntity extends AbstractEntity<Long> implements Serializable {

    private String date;

    private String age;
}
