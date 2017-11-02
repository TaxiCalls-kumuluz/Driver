/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taxicalls.driver.model;

import java.io.Serializable;
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

    public String getPassword() {
        return password;
    }

    public Long getAtualLatitude() {
        return atualLatitude;
    }

    public Long getAtualLongitude() {
        return atualLongitude;
    }

    public Status getStatus() {
        return status;
    }

    public Coordinate getAtualCoordinate() {
        return new Coordinate(atualLongitude, atualLatitude);
    }

}
