package org.sid.ebankservice.web;

import org.sid.ebankservice.entities.Customer;
import org.sid.ebankservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CustomerGraphQLController {
    @Autowired
    private CustomerRepository customerRepository;

    @QueryMapping
    public List<Customer> customersList(){
        return customerRepository.findAll();
    }

    @QueryMapping
    public Customer customer(@Argument Long id){
        return customerRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Customer %d not found " ,id)));
    }

}
