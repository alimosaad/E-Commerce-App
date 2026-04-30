package com.alimosaad.customer.Dto;

import com.alimosaad.customer.entites.CustomerEntity;
import com.alimosaad.customer.requests.CustomerRequest;
import com.alimosaad.customer.requests.CustomerResponse;
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
