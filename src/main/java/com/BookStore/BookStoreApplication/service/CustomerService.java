package com.BookStore.BookStoreApplication.service;

import com.BookStore.BookStoreApplication.model.CustomerDetails;

public interface CustomerService {
    CustomerDetails addCustomerDetails(CustomerDetails customerDetails);

    CustomerDetails updateCustomerDetails(Long customerId, CustomerDetails customerDetails);

    boolean DeleteCustomerDetails(Long customerId);
}