package com.gautam.customer_service.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {
        if(request==null){
            return null;
        }
        return Customer.builder()
                .id(request.id())
                .email(request.email())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .address(request.address())
                .build();
        }

    public CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
