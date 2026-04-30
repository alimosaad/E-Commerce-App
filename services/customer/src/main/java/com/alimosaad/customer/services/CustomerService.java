package com.alimosaad.customer.services;

import com.alimosaad.customer.Dto.CustomerMapper;
import com.alimosaad.customer.entites.CustomerEntity;
import com.alimosaad.customer.exceptions.CustomException;
import com.alimosaad.customer.repositories.CustomerRepository;
import com.alimosaad.customer.requests.CustomerRequest;
import com.alimosaad.customer.requests.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer= repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer=repository.findById(request.id())
                .orElseThrow(()-> new CustomException(
                        format("cannot update customer :: No customer found with the provided ID :: %s ",request.id())
                ));
        mergeCustomer(customer ,request);
        repository.save(customer);
    }

    private void mergeCustomer(CustomerEntity customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if (request.address()!=null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }
    public CustomerResponse findById(String id) {
        return this.repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new CustomException(String.format("No customer found with the provided ID: %s", id)));
    }

    public boolean existsById(String id) {
        return this.repository.findById(id)
                .isPresent();
    }

    public void deleteCustomer(String customerId) {
        this.repository.deleteById(customerId);
    }
}
