/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taxicalls.driver.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author romulo
 */
@Entity
@NamedQuery(name = "Driver.findAll", query = "SELECT d FROM Driver d")
public class Driver implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private Long atualLatitude;
    private Long atualLongitude;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAtualLatitude() {
        return atualLatitude;
    }

    public void setAtualLatitude(Long atualLatitude) {
        this.atualLatitude = atualLatitude;
    }

    public Long getAtualLongitude() {
        return atualLongitude;
    }

    public void setAtualLongitude(Long atualLongitude) {
        this.atualLongitude = atualLongitude;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Driver)) {
            return false;
        }
        Driver other = (Driver) obj;
        return getId().equals(other.getId());
    }

}
