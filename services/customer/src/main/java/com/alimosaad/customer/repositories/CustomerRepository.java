package com.alimosaad.customer.repositories;

import com.alimosaad.customer.entites.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity,String> {
}
