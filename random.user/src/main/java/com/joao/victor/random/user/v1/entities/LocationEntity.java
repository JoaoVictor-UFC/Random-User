package com.joao.victor.random.user.v1.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "location_user")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public @Data class LocationEntity extends AbstractEntity<Long> implements Serializable {

    private String city;

    private String state;

    private String country;

    private String postcode;

    @OneToOne
    @JoinColumn(name ="id_street", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_street"))
    private StreetEntity street;

    @OneToOne
    @JoinColumn(name ="id_coordinates", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_coordinates"))
    private CoordinatesEntity coordinates;

    @OneToOne
    @JoinColumn(name ="id_timezone", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_timezone"))
    private TimezoneEntity timezone;

}
