package com.bank.customer_service.controller;

import com.bank.customer_service.dto.ApiResponse;
import com.bank.customer_service.dto.CustomerRequestDTO;
import com.bank.customer_service.dto.CustomerResponseDTO;
import com.bank.customer_service.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer APIs")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @Operation(summary = "Create customer")
    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> createCustomer(
            @Valid @RequestBody CustomerRequestDTO dto) {

        CustomerResponseDTO response = service.createCustomer(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Customer created", response));
    }

    @Operation(summary = "Get all customers with pagination")
    //Combining pagination and search supports scalable data retrieval.
    @GetMapping
    public ResponseEntity<ApiResponse<Page<CustomerResponseDTO>>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<CustomerResponseDTO> customers =
                service.getAllCustomers(page, size);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Customers fetched", customers)
        );
    }

    @Operation(summary = "Get customer by email")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> getCustomerByEmail(
            @RequestParam String email) {

        CustomerResponseDTO customer =
                service.getCustomerByEmail(email);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Customer found", customer)
        );
    }
}


