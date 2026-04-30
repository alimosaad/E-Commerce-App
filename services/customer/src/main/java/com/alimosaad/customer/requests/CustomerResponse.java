package com.alimosaad.customer.requests;

import com.alimosaad.customer.entites.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerResponse(
        String id ,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
