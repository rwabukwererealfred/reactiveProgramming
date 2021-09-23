package search

import com.elasticsearch.document.Person
import org.apache.lucene.util.CollectionUtil
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.cluster.routing.allocation.decider.Decision
import org.elasticsearch.index.query.MultiMatchQueryBuilder
import org.elasticsearch.index.query.Operator
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.search.builder.SearchSourceBuilder
import org.springframework.util.CollectionUtils
import java.lang.reflect.Executable

object SearchUtil {

    private fun queryBuilder(dto:SearchDto):QueryBuilder{
        if(dto == null){
            println("dto is ---------------------------")
            return null!!;
        }
        val fields:List<String> = dto.fields;
        if(CollectionUtils.isEmpty(fields)){
            println("collection is empty---------------------------")
            return null!!;
        }

        if(fields.size >1){
            val queryBuilder: MultiMatchQueryBuilder = MultiMatchQueryBuilder(dto.searchItem).
            type(MultiMatchQueryBuilder.Type.CROSS_FIELDS).operator(Operator.AND);
            fields.forEach(queryBuilder::field);
            return queryBuilder
        }
        return fields.stream().findFirst().map { field -> QueryBuilders.matchQuery(field, dto.searchItem).
        operator(Operator.AND) }.orElse(null);

    }
     fun buildSearchRequest(indexName:String, dto: SearchDto): SearchRequest {
        try {
            val builder: SearchSourceBuilder = SearchSourceBuilder().postFilter(SearchUtil.queryBuilder(dto));
            val request: SearchRequest = SearchRequest(indexName);
            request.source(builder);
            return request;
        }catch (ex:Exception){
            ex.printStackTrace();
            println("not found error------------------------------------")
            return null!!;
        }
    }



}