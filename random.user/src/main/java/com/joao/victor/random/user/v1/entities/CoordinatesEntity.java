package com.joao.victor.random.user.v1.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "coordinates_location")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public @Data class CoordinatesEntity extends AbstractEntity<Long> implements Serializable {

    private String latitude;

    private String longitude;
}
