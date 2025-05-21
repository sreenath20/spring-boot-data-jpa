package com.paytm.company;

public interface CompanyService {

    Company createNewCompany(Company newCompany);

    Company getComapanyByName(String name);

    Company deleteCompanyByName(String name);
}
