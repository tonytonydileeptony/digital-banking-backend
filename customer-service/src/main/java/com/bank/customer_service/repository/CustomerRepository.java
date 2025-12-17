package com.bank.customer_service.repository;

import com.bank.customer_service.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;




public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmail(String email);
/*
* 3️⃣ Search APIs (Business Requirement)
❓ Why search?

Banks rarely fetch all customers.

Real use cases:

Search by email

Search by mobile

Search by name
Search APIs improve usability and meet real business needs.
* * */
    Optional<Customer> findByEmail(String email);

    /*
    * Pagination (Performance & Scalability)
    ❓ Why we add pagination

    Returning all records kills performance.

    ❌ Problem without pagination

    High memory usage

    Slow responses

    DB overload

    ✅ What it does

    Returns data in chunks

    Improves performance

    Required in real systems

    Pagination improves performance and scalability for large datasets.
    Why did you implement pagination instead of fetching all records?
A:

Pagination prevents performance issues and supports scalable data access for large datasets.
* * **/
    Page<Customer> findAll(Pageable pageable);
}


