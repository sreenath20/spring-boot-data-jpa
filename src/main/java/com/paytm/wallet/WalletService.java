package com.paytm.wallet;

import java.util.Collection;

public interface WalletService {
    Wallet registerNewWallet(Wallet newWallet);

    Collection<Wallet> getAllWallets();

    Double depositFundsToWalletById(Integer walletId, Double amount)throws WalletException;

    Wallet getWalletByEmailId(String email)throws WalletException;

    Collection<Wallet> getAllwalletsHavingBalanceBetween(Double fromAmount, Double toAmount);

    Wallet deleteWalletByEmailId(String emailId) throws WalletException;

    Wallet inactivateWalletUserByEmailId(String email)throws WalletException;
}
