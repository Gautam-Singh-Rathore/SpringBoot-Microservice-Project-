package com.gautam.customer_service.customer;

import com.gautam.customer_service.exceptions.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = customerRepo.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepo.findById(request.id())
                .orElseThrow(()-> new CustomerNotFoundException("No customer found with Customer ID :: "+request.id()));
        mergeCustomer(customer, request);
        customerRepo.save(customer);
    }
    private void mergeCustomer(Customer customer , CustomerRequest request){
        if(StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepo.findAll()
                .stream()
                .map(mapper::toCustomerResponse)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return customerRepo.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findCustomerById(String customerId) {
        return customerRepo.findById(customerId)
                .map(mapper::toCustomerResponse)
                .orElseThrow(()-> new CustomerNotFoundException("No customer found with id : "+customerId));
    }

    public void deleteCustomer(String customerId) {
        customerRepo.deleteById(customerId);
    }
}
