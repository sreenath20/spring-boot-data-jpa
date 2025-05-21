package com.paytm.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public  class CompanyServiceImpl implements CompanyService{


    private final CompanyRepository companyRepository;
    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company createNewCompany(Company newCompany) {
        return this.companyRepository.save(newCompany);
    }

    @Override
    public Company getComapanyByName(String name) {
        return this.companyRepository.findByName(name).orElseGet(Company::new);
    }

    @Override
    public Company deleteCompanyByName(String name) {
        // handle exception
        Optional<Company> companyOpt = this.companyRepository.findByName(name);
        Company company = companyOpt.get();
        this.companyRepository.delete(company);
        return company;
    }
}
