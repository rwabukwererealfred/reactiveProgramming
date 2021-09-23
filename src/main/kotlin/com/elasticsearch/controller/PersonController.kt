package com.elasticsearch.controller

import com.elasticsearch.document.Person
import com.elasticsearch.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import search.SearchDto

@RestController
@RequestMapping("/api/person")
class PersonController {

    @Autowired
    lateinit var personService: PersonService;

    @PostMapping()
    fun savePerson(@RequestBody person:Person){
        personService.savePerson(person);
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id:String):Person{
        return personService.findById(id);
    }

    @PostMapping("/search")
    fun persons(@RequestBody dto:SearchDto):List<Person>{
       return personService.search(dto);
    }
}