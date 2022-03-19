package com.softtech.softtechspringboot.Entity;

import com.softtech.softtechspringboot.Entity.BaseEntity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "USERS")
public class User extends BaseEntity {

    @Id
    @SequenceGenerator(name = "User",sequenceName = "USER_ID_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "User")
    private Long id;

    @Column(name = "USERNAME" ,length = 60 ,nullable = false,unique = true)
    private String userName;

    @Column(name = "PASSWORD" ,length = 60 ,nullable = false)
    private String password;

    @Column(name = "NAME" ,length = 60, nullable = false)
    private String name;

    @Column(name = "SURNAME" ,length = 60 ,nullable = false)
    private String surname;


}
