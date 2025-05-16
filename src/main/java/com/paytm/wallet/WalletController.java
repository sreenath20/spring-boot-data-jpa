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
    @PostMapping
    public Wallet registerNewWalletUser(@RequestBody Wallet newWallet){
        return this.walletService.registerNewWallet(newWallet);
    }

    @GetMapping("/all")
    public Collection<Wallet> getAllWallets(){
        return this.walletService.getAllWallets();
    }
    @PatchMapping
    public Double addFundsToWallet(@RequestBody WalletDTO walletDTO)throws WalletException{
        return this.walletService.depositFundsToWalletById(walletDTO.getId(),walletDTO.getAmount());
    }


}
