package com.paytm.wallet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserProfile {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String aadharNumber;

    public UserProfile() {
    }

    public UserProfile(Integer id, String name, String aadharNumber) {
        this.id = id;
        this.name = name;
        this.aadharNumber = aadharNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
