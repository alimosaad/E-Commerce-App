package com.alimosaad.ecommerce.repositories;

import com.alimosaad.ecommerce.entites.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity,String> {
}
