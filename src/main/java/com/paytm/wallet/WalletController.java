package com.paytm.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("v1/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;


    @GetMapping
    public String info(){
        return "Wallet Application.";
    }

    //GET:http:/domainname:8080/v1/wallet
    @PostMapping("/{company}")
    public Wallet registerNewWalletUser(@RequestBody Wallet newWallet, @PathVariable("company") String companyName)throws WalletException{
      return this.walletService.registerNewWalletToCompany(newWallet,companyName);

    }

    @GetMapping("/all")
    public Collection<Wallet> getAllWallets(){
        return this.walletService.getAllWallets();
    }
    // deposit funds
    @PatchMapping
    public Double addFundsToWallet(@RequestBody WalletDTO walletDTO)throws WalletException{
        return this.walletService.depositFundsToWalletById(walletDTO.getEmail(),walletDTO.getAmount());
    }

    @GetMapping("/{email}")
    public Wallet getWalletByEmail(@PathVariable String email) throws WalletException{
        return this.walletService.getWalletByEmailId(email);
    }
    // Admin API to get all wallets having low balance (between)
    @GetMapping("from/{fromAmount}/to/{toAmount}")
    public Collection<Wallet> getWalletsHavingBalanceBetween(@PathVariable Double fromAmount,@PathVariable Double toAmount){
     return this.walletService.getAllwalletsHavingBalanceBetween(fromAmount,toAmount);
    }

    @DeleteMapping("/{email}")
    public Wallet deleteWalletByEmailId(@PathVariable("email") String emailId) throws WalletException {
        return this.walletService.deleteWalletByEmailId(emailId);
    }

    @DeleteMapping("soft/{email}")
    public Wallet inactivateWalletByEmailId(@PathVariable String email)throws WalletException{
        return this.walletService.inactivateWalletUserByEmailId(email);

    }
    // Admin API
    // Get all active users
    // Get all inactive users

}
