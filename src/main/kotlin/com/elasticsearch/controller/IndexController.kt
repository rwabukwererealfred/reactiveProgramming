package com.elasticsearch.controller

import com.elasticsearch.service.IndexService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/index")
class IndexController {

    @Autowired
    lateinit var indexService: IndexService;

    @PostMapping("/recreate")
    fun recreatedAllIndices(){
        indexService.recreateIndices(true);
    }
}