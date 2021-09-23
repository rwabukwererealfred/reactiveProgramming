package com.elasticsearch.repository

import com.elasticsearch.document.Customer
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface CustomerRepository: ElasticsearchRepository<Customer,String> {
}