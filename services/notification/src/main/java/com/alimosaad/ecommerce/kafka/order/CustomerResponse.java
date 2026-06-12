package com.alimosaad.ecommerce.kafka.order;

import org.springframework.validation.annotation.Validated;

@Validated
public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
