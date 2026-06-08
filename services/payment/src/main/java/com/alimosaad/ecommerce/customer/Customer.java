package com.alimosaad.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated // when use @valid we should make the all the attribute validated
public record Customer(
        String id,
        @NotNull(message = "First Name Is required")
        String firstName,
        @NotNull(message = "Last Name Is required")
        String lastName,
        @NotNull(message = "Email Is required")
        @Email(message = "The Email is not correctly formatted")
        String email
) {
}
