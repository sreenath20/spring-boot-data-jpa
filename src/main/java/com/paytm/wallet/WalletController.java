package com.paytm.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;


    @GetMapping
    public String info(){
        return "Wallet Application.";
    }

    @PostMapping
    public Wallet registerNewWalletUser(@RequestBody Wallet newWallet){
        return this.walletService.registerNewWallet(newWallet);
    }

}
