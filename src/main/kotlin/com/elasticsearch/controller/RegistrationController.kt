package com.elasticsearch.controller

import com.elasticsearch.document.Customer
import com.elasticsearch.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/elasticsearch/")
class RegistrationController {

    @Autowired
    lateinit var customerService: CustomerService;


    @PostMapping("registerCustomer")
    fun registerCustomer(@RequestBody customer:Customer){
        println("well saved---------------------");
        customerService.saveCustomer(customer);
    }

    @GetMapping("findCustomerById/{id}")
    fun findCustomerById(@PathVariable id:String):Customer{
        return customerService.findById(id);
    }


}