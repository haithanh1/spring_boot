package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "staff", indexes = {
        @Index(name = "idx_fk_store_id", columnList = "store_id"),
        @Index(name = "idx_fk_address_id", columnList = "address_id")
})
@Entity
@Data
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id", nullable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "picture")
    private byte[] picture;

    @Column(name = "email", length = 50)
    private String email;

    @ManyToOne(optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToOne(optional = false)
    @JoinColumn(name = "staff_id", nullable = false)
    private Role role;

    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;


}