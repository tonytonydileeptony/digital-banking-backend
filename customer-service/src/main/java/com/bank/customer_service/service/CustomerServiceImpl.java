package com.bank.customer_service.service;

import com.bank.customer_service.dto.CustomerRequestDTO;
import com.bank.customer_service.dto.CustomerResponseDTO;
import com.bank.customer_service.entity.Customer;
import com.bank.customer_service.exception.BusinessException;
import com.bank.customer_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {

        if (repository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Email already exists");
        }

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setMobile(dto.getMobile());

        Customer saved = repository.save(customer);
        return mapToResponse(saved);
    }

    @Override
    public Page<CustomerResponseDTO> getAllCustomers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customers = repository.findAll(pageable);

        return customers.map(this::mapToResponse);
    }

    @Override
    public CustomerResponseDTO getCustomerByEmail(String email) {

        Customer customer = repository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Customer not found"));

        return mapToResponse(customer);
    }

    private CustomerResponseDTO mapToResponse(Customer customer) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setMobile(customer.getMobile());
        return dto;
    }
}
