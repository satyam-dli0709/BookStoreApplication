package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.model.CustomerDetails;

public interface CustomerService {
    CustomerDetails addCustomerDetails(CustomerDetails customerDetails);

    CustomerDetails updateCustomerDetails(Long userId, CustomerDetails customerDetails);

    boolean DeleteCustomerDetails(Long id);
}