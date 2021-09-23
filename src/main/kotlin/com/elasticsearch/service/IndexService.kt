package com.elasticsearch.service

import com.elasticsearch.helper.Utils
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.client.indices.CreateIndexRequest
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
 class IndexService {

    private val LOG: Logger = LoggerFactory.getLogger(IndexService::class.java);
    private val INDICES:List<String> = listOf("person");

    @Autowired
    lateinit var  client: RestHighLevelClient;

    fun recreateIndices(deleteExisting:Boolean){

       val settings:String = Utils.loadAsString(("static/es-settings.json"));

        if(settings == null){
            LOG.error("failed to load index settings")
        }
        for(indexName:String in INDICES){
            val indexExists:Boolean = client.indices().exists(GetIndexRequest(indexName),RequestOptions.DEFAULT);

            if(indexExists){
                if(!deleteExisting){
                    continue;
                }
                client.indices().delete(DeleteIndexRequest(indexName), RequestOptions.DEFAULT);
            }
            val createdIndexRequest: CreateIndexRequest = CreateIndexRequest(indexName);
            val mappings:String = loadMapping(indexName);
            if(mappings != null){
                createdIndexRequest.mapping(mappings,XContentType.JSON);
            }

            client.indices().create(createdIndexRequest, RequestOptions.DEFAULT);
        }
    }
     fun loadMapping(indexName:String):String{
         val mappings:String = Utils.loadAsString(("static/es-settings.json"));
         if(mappings == null){
             LOG.error("Failed to load mappings for index with name '{}'", indexName)
             return "";
         }
         return mappings;
     }
}