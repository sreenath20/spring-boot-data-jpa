package com.paytm.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public Wallet registerNewWallet(Wallet newWallet) {

        return this.walletRepository.save(newWallet); // create new resource
    }

    @Override
    public Collection<Wallet> getAllWallets() {
        return this.walletRepository.findAll();
    }

    @Override
    public Double depositFundsToWalletById(Integer walletId, Double amount) throws WalletException {
        // check if wallet exists
        Optional<Wallet> foundWalletOpt = this.walletRepository.findById(walletId);
        if (foundWalletOpt.isEmpty())
            throw new WalletException("Wallet not Found:id:" + walletId);
        Wallet wallet = foundWalletOpt.get();
        Double currentBalance = wallet.getBalance();
        wallet.setBalance(currentBalance + amount);
        // coz id already present wallet will get updated
        this.walletRepository.save(wallet); // save the changes to DB
        return wallet.getBalance();
    }

    @Override
    public Wallet getWalletByEmailId(String email) throws WalletException {
        //Optional<Wallet> foundWalletOpt = this.walletRepository.findByEmail(email);
//        Optional<Wallet> foundWalletOpt = this.walletRepository.findByEmailCustomQuery(email);
        Optional<Wallet> foundWalletOpt = this.walletRepository.findByEmailUsingCustomNativeQuery(email);


        if (foundWalletOpt.isEmpty())
            throw new WalletException("Wallet account not found for email:" + email);
        return foundWalletOpt.get();
    }

    @Override
    public Collection<Wallet> getAllwalletsHavingBalanceBetween(Double fromAmount, Double toAmount) {
        // Solution using Stream API
//        Collection<Wallet> wallets = this.walletRepository.findAll();
//
//
//        return wallets.stream()
//                .filter(w->w.getBalance() >=fromAmount && w.getBalance() <=toAmount )
//                .toList();
        return this.walletRepository.findByBalanceBetween(fromAmount, toAmount);
    }

    @Override
    public Wallet deleteWalletByEmailId(String emailId) throws WalletException {
        Optional<Wallet> walletOpt = this.walletRepository.findByEmail(emailId);
        //check if wallet exists

        if (walletOpt.isEmpty())
            throw new WalletException("Wallet account not found");
        this.walletRepository.delete(walletOpt.get());
        return walletOpt.get();
    }

    @Override
    public Wallet inactivateWalletUserByEmailId(String email) throws WalletException {
        Optional<Wallet> walletOpt = this.walletRepository.findByEmail(email);
        //check if wallet exists

        if (walletOpt.isEmpty())
            throw new WalletException("Wallet account not found");
        Wallet wallet =walletOpt.get();
        wallet.setActive(false);
       wallet =  this.walletRepository.save(wallet);
        return wallet;
    }
}
