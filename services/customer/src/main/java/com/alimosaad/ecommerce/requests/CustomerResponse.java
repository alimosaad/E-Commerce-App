package com.alimosaad.ecommerce.requests;

import com.alimosaad.ecommerce.entites.Address;

public record CustomerResponse(
        String id ,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
