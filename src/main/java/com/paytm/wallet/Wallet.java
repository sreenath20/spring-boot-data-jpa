package com.paytm.wallet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paytm.company.Company;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Wallets")
public class Wallet { // POJO

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Double balance;
    private String email;
    private String password;
    private String address;
    private String city;
    private Boolean isActive = true;
    // Wallet HAS-A dependency relation to userProfile []
    @OneToOne
    private UserProfile userProfile;

    @OneToMany
//    @Column(name = "transaction")
    private Set<Transaction> TransactionsSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Transaction> getTransactionsSet() {
        return TransactionsSet;
    }

    public void setTransactionsSet(Set<Transaction> transactionsSet) {
        TransactionsSet = transactionsSet;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Wallet() {
    }

    public Wallet( String name, Double balance, String email, String password) {

        this.name = name;
        this.balance = balance;
        this.email = email;
        this.password = password;
        this.isActive = true;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(email, wallet.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
