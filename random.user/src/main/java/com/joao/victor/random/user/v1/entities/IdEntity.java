package com.joao.victor.random.user.v1.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "id_user")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public @Data class IdEntity extends AbstractEntity<Long> implements Serializable {

    private String name;

    private String value;
}
