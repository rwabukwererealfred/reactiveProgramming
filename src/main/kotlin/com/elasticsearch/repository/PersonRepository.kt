package com.elasticsearch.repository

import com.elasticsearch.document.Person
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface PersonRepository : ElasticsearchRepository<Person, String>{

}