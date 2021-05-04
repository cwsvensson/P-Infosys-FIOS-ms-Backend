package com.verizon.admin.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @ToString
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String username;
    private String password;
}
