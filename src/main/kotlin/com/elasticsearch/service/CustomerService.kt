package com.elasticsearch.service

import com.elasticsearch.document.Customer
import com.elasticsearch.document.Person
import com.elasticsearch.repository.CustomerRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerService {

    private var LOG: Logger = LoggerFactory.getLogger(PersonService::class.java);
    private var mapper: ObjectMapper = ObjectMapper()

    @Autowired
    lateinit var customerRepository:CustomerRepository;

    fun saveCustomer(customer:Customer){
        customerRepository.save(customer);
    }

    fun findById(id:String): Customer {
        return customerRepository.findById(id).get();
    }
}