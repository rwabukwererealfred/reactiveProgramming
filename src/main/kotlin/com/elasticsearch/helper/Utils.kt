package com.elasticsearch.helper

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import java.io.File;
import java.nio.file.Files;

object Utils {
    private val LOG: Logger = LoggerFactory.getLogger(Utils::class.java);

    fun loadAsString(path:String):String{
        try {
            val resource: File = ClassPathResource(path).file
            return String(Files.readAllBytes(resource.toPath()));
        }catch (ex: Exception){
            LOG.error(ex.message)
            return "";
        }
    }
}