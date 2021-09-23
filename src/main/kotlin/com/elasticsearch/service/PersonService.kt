package com.elasticsearch.service


import com.elasticsearch.document.Person
import com.elasticsearch.repository.PersonRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.JSONPObject
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.search.SearchHit
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import search.SearchDto
import search.SearchUtil
import java.util.*


@Service
class PersonService {
    private var LOG: Logger = LoggerFactory.getLogger(PersonService::class.java);
    private var mapper: ObjectMapper = ObjectMapper()

    @Autowired
    lateinit var personRepository: PersonRepository;

    @Autowired
    lateinit var  client: RestHighLevelClient;

    fun savePerson(person:Person){
        personRepository.save(person);
    }

    fun findById(id:String):Person{
        return personRepository.findById(id).get();
    }

    fun search(dto: SearchDto):MutableList<Person>{
        var request: SearchRequest = SearchUtil.buildSearchRequest("person",dto);
        if( request == null ){
           LOG.error("fail to build search request")
            return Collections.emptyList();
        }
        try {
            var response:SearchResponse =client.search(request, RequestOptions.DEFAULT);
            var searchHits:Array<SearchHit> =response.hits.hits;
                println("hit size ${searchHits.size}---------------")
            var persons: MutableList<Person> = ArrayList<Person>();

            for( hit in searchHits){
                println("result---------------")
                    var li:List<String> = hit.sourceAsString.split(",");
                    var per:String= hit.sourceAsString.removeRange(1,li.get(0).length+1);

                println("out put ${per} --------------------------- ");
                persons.add(mapper.readValue(per, Person::class.java))
            }


            return persons
        }catch (ex:Exception){
            ex.printStackTrace();
            LOG.error(ex.message,ex);
            println("not found-------------------------------")
            return Collections.emptyList();
        }

    }





}