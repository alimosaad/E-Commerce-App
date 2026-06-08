package com.alimosaad.ecommerce.Dto;

import com.alimosaad.ecommerce.entites.CustomerEntity;
import com.alimosaad.ecommerce.requests.CustomerRequest;
import com.alimosaad.ecommerce.requests.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public CustomerEntity toCustomer(CustomerRequest request) {
        if (request==null){
            return null;
        }
        return CustomerEntity
                .builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse  toResponse(CustomerEntity customerEntity) {
        return new CustomerResponse(
          customerEntity.getId(),
          customerEntity.getFirstName(),
          customerEntity.getLastName(),
          customerEntity.getEmail(),
          customerEntity.getAddress()
        );
    }
}
