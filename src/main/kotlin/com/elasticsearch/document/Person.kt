package com.elasticsearch.document

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import org.springframework.data.elasticsearch.annotations.Setting

@JsonIgnoreProperties
@Document(indexName = "person")
//@Setting(settingPath = "static/es-settings.json")
class Person(
) {
    @JsonProperty @Field(type = FieldType.Text) lateinit var id:String;
    @JsonProperty @Field(type = FieldType.Text) lateinit var name: String;
    constructor(id:String, name:String):this() {
        this.id = id;
        this.name = name;
    }

}