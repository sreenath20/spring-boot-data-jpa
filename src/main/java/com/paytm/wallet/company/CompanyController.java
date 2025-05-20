package com.paytm.wallet.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @PostMapping
    public Company addCompany(@RequestBody Company company){
    return this.companyService.createNewCompany(company);

    }
}
