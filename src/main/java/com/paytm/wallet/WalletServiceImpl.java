package com.paytm.wallet;

import com.paytm.company.Company;
import com.paytm.company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Wallet registerNewWallet(Wallet newWallet) throws WalletException {
        // company logic
        Optional<Wallet> walletopt = this.walletRepository.findByEmail(newWallet.getEmail());
        if (walletopt.isPresent())
            throw new WalletException("Email id already present");
        // profile logic
//        UserProfile userProfile = this.userProfileRepository.save(newWallet.getUserProfile());
//        newWallet.setUserProfile(userProfile);
        return this.walletRepository.save(newWallet); // create new resource
    }

    @Override
    public Collection<Wallet> getAllWallets() {
        return this.walletRepository.findAll();
    }

    @Override
    @Transactional
    public Double depositFundsToWalletById(String walletEmailId, Double amount) throws WalletException {
        // check if wallet exists
        Optional<Wallet> foundWalletOpt = this.walletRepository.findByEmail(walletEmailId);
        if (foundWalletOpt.isEmpty())
            throw new WalletException("Wallet not Found:id:" + walletEmailId);
        Wallet wallet = foundWalletOpt.get();

        Double currentBalance = wallet.getBalance();
        wallet.setBalance(currentBalance + amount);
        // create transaction
        Transaction newTransaction = new Transaction(LocalDate.now(), "CREDIT", amount);
        newTransaction = this.transactionRepository.save(newTransaction);
        //assign new transaction to wallet
        wallet.getTransactionsSet().add(newTransaction);
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
        Wallet wallet = walletOpt.get();
        wallet.setActive(false);
        wallet = this.walletRepository.save(wallet);
        return wallet;
    }

    @Override
    public Wallet registerNewWalletToCompany(Wallet newWallet, String companyName) throws WalletException {
        // company logic
        Optional<Company> companyOpt = this.companyRepository.findByName(companyName);
        if (companyOpt.isEmpty())
            throw new WalletException("Company not found");
        Company company = companyOpt.get();
        Optional<Wallet> walletopt = this.walletRepository.findByEmail(newWallet.getEmail());
        if (walletopt.isPresent())
            throw new WalletException("Email id already present");
        newWallet.setCompany(company);  // associate company to new wallet
        // profile logic
        UserProfile userProfile = this.userProfileRepository.save(newWallet.getUserProfile());
        newWallet.setUserProfile(userProfile);

        return this.walletRepository.save(newWallet); // create new resource

    }
}
