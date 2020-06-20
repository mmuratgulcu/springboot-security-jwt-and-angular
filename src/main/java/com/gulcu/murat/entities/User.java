package com.gulcu.murat.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @SequenceGenerator(name = "userGen",sequenceName = "user_seq",allocationSize = 1)
    @GeneratedValue(generator = "userGen",strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "name",length = 100)
    private String name;
    @Column(name = "surname",length = 100)
    private String surname;
    @Column(name = "username",length = 100,unique = true)
    private String username;
    @Column(name = "password",length = 100)
    private String password;
    @Column(name = "email",length =100 )
    private String email;

}