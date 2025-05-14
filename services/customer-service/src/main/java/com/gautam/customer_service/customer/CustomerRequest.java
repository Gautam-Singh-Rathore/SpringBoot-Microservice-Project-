package com.gautam.customer_service.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer firstname is required")
        String firstname,
        @NotNull(message = "Customer lastname is required")
        String lastname,
        @NotNull(message = "Email address is required")
        @Email(message = "Invalid email address")
        String email,

        Address address
) {
}
