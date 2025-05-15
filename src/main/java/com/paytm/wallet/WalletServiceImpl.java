package com.paytm.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public Wallet registerNewWallet(Wallet newWallet) {
        return this.walletRepository.save(newWallet);
    }
}
