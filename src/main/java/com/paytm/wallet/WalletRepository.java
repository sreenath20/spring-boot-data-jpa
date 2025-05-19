package com.paytm.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Integer> {

    // custom method by name
    // SELECT wallet FROM Wallet wallet WHERE wallet.email = ?1
    Optional<Wallet> findByEmail(String email);

    @Query("SELECT w FROM Wallet w WHERE w.email = ?1")
    Optional<Wallet> findByEmailUsingCustomQuery(String email);

    @Query(value = "SELECT * FROM Wallets WHERE email = ?1",nativeQuery = true)
    Optional<Wallet> findByEmailUsingCustomNativeQuery(String email);


    Collection<Wallet> findByBalanceBetween(Double fromAmount,Double toAmount);
}
