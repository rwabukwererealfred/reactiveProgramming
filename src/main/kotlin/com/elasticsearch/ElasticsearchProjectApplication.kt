package com.elasticsearch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ElasticsearchProjectApplication

fun main(args: Array<String>) {
	runApplication<ElasticsearchProjectApplication>(*args)
}
