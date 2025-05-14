package com.gautam.customer_service.customer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepo extends MongoRepository<Customer  , String> {
}
