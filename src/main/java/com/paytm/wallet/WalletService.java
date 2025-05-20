package com.paytm.wallet;

import java.util.Collection;

public interface WalletService {
    Wallet registerNewWallet(Wallet newWallet)throws WalletException;

    Collection<Wallet> getAllWallets();

    Double depositFundsToWalletById(String walletEmailId, Double amount)throws WalletException;

    Wallet getWalletByEmailId(String email)throws WalletException;

    Collection<Wallet> getAllwalletsHavingBalanceBetween(Double fromAmount, Double toAmount);

    Wallet deleteWalletByEmailId(String emailId) throws WalletException;

    Wallet inactivateWalletUserByEmailId(String email)throws WalletException;

    Wallet registerNewWalletToCompany(Wallet newWallet, String companyName) throws WalletException;
}
