package com.paytm.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @PostMapping
    public Company addCompany(@RequestBody Company company){
    return this.companyService.createNewCompany(company);

    }
    @GetMapping("{name}")
    public Company getCompanyByName(@PathVariable String name){
        return this.companyService.getComapanyByName(name);
    }
    @DeleteMapping("{name}")
    public Company deleteComanyByName(@PathVariable String name) {
        return this.companyService.deleteCompanyByName(name);
    }
}
