package com.joao.victor.random.user.v1.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "timezone_location")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public @Data class TimezoneEntity extends AbstractEntity<Long> implements Serializable {

    @Column(name = "off_set")
    private String offSet;

    private String description;
}
