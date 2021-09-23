//package rw.project.bkaccount.configuration
//
//import org.elasticsearch.client.RestHighLevelClient
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.ComponentScan
//import org.springframework.context.annotation.Configuration
//import org.springframework.data.elasticsearch.client.ClientConfiguration
//import org.springframework.data.elasticsearch.client.RestClients
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
//
//@Configuration
//@EnableElasticsearchRepositories(basePackages = ["rw.project.bkaccount.elasticRepository"])
//@ComponentScan(basePackages = ["rw.project.bkaccount"])
//class Config: AbstractElasticsearchConfiguration() {
//
////    @Value("\${elasticsearch.url}")
////    lateinit var elasticsarch:String;
//
//    @Bean
//    override fun elasticsearchClient(): RestHighLevelClient {
//        val config:ClientConfiguration = ClientConfiguration.builder().connectedTo("localhost:9200").build();
//        return  RestClients.create(config).rest();
//    }
//
//}