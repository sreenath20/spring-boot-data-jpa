package com.paytm.wallet;

import java.util.Collection;

public interface WalletService {
    Wallet registerNewWallet(Wallet newWallet);

    Collection<Wallet> getAllWallets();

    Double depositFundsToWalletById(Integer walletId, Double amount)throws WalletException;
}
