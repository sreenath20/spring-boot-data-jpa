package com.paytm.company;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.paytm.wallet.Wallet;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Company {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @OneToMany (mappedBy = "company",fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    private Set<Wallet> walletSet = new HashSet<>();

    public Set<Wallet> getWalletSet() {
        return walletSet;
    }

    public void setWalletSet(Set<Wallet> walletSet) {
        this.walletSet = walletSet;
    }

    public Company() {
    }

    public Company(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
