package com.bank.customer_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
/*4️⃣ Database Indexing (VERY IMPORTANT)
❓ Why index?

Search on large tables without index = slow full table scan.

❌ Without index
O(n) scan

✅ With index
O(log n) lookup

📌 Entity
@Column(unique = true)
private String email;


or

@Table(
  indexes = {
    @Index(name = "idx_email", columnList = "email")
  })
  Indexing improves query performance on frequently searched columns.
  */
@Table(
        name = "customers",
        indexes = {
                @Index(name = "idx_email", columnList = "email"),
                @Index(name = "idx_mobile", columnList = "mobile")
        }
)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String mobile;

    // getters & setters
}
