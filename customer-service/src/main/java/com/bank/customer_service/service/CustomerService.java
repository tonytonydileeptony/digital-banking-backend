package com.bank.customer_service.service;

import com.bank.customer_service.dto.CustomerRequestDTO;
import com.bank.customer_service.dto.CustomerResponseDTO;
import org.springframework.data.domain.Page;

public interface CustomerService {

    CustomerResponseDTO createCustomer(CustomerRequestDTO dto);

    Page<CustomerResponseDTO> getAllCustomers(int page, int size);

    CustomerResponseDTO getCustomerByEmail(String email);
}


