package com.bank.customer_service.dto;

import lombok.Data;

import java.time.LocalDateTime;
/*1️⃣ Response Wrapper (ApiResponse)
❓ Why we add this

Different APIs returning different response formats = bad client experience.

❌ Problem without it
"Customer created"


or

{
  "id": 1,
  "name": "Dileep"
}


Inconsistent ❌

✅ What this class does

Standardizes API responses

Makes frontend & mobile integration easier

Adds metadata (status, message, timestamp)


Response wrapper ensures consistent API contract across services.*/
@Data
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    // getters & setters
}

