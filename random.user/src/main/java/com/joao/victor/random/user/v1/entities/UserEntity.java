package com.joao.victor.random.user.v1.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "random_user")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public @Data class UserEntity extends AbstractEntity<Long> implements Serializable {

    private String gender;

    private String email;

    private String phone;

    private String cell;

    private String nat;

    @OneToOne
    @JoinColumn(name ="id_name", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_name"))
    private NameEntity name;

    @OneToOne
    @JoinColumn(name ="id_location", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_location"))
    private LocationEntity location;

    @OneToOne
    @JoinColumn(name ="id_login", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_login"))
    private LoginEntity login;

    @OneToOne
    @JoinColumn(name ="id_dob", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_dob"))
    private DobEntity dob;

    @OneToOne
    @JoinColumn(name ="id_registered", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_registered"))
    private RegisteredEntity registered;

    @OneToOne
    @JoinColumn(name ="id_idEntity", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_idEntity"))
    private IdEntity idEntity;

    @OneToOne
    @JoinColumn(name ="id_picture", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_picture"))
    private PictureEntity picture;

}
