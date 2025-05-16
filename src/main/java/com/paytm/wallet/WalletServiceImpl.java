package com.paytm.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public Wallet registerNewWallet(Wallet newWallet) {
        return this.walletRepository.save(newWallet);
    }

    @Override
    public Collection<Wallet> getAllWallets() {
        return this.walletRepository.findAll();
    }

    @Override
    public Double depositFundsToWalletById(Integer walletId, Double amount)throws WalletException {
        // check if wallet exists
        Optional<Wallet> foundWalletOpt = this.walletRepository.findById(walletId);
        if(foundWalletOpt.isEmpty())
            throw new WalletException("Wallet not Found:id:"+walletId);
        Wallet wallet = foundWalletOpt.get();
        Double currentBalance = wallet.getBalance();
        wallet.setBalance(currentBalance+amount);
        this.walletRepository.save(wallet); // save the changes to DB
        return wallet.getBalance();
    }
}
