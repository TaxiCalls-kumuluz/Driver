/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taxicalls.driver.model;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author romulo
 */
public class Trip implements Serializable {

    private Long id;
    private Driver driver;
    private Address addressFrom;
    private Address addressTo;
    private Set<Passenger> passengers;

    protected Trip() {
    }

    public Trip(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public Address getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(Address addressFrom) {
        this.addressFrom = addressFrom;
    }

    public Address getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(Address addressTo) {
        this.addressTo = addressTo;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

}
