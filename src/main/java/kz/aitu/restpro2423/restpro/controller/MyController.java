package kz.aitu.restpro2423.restpro.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import kz.aitu.restpro2423.restpro.enteties.*;
@RestController
public class MyController {

    private ObjectMapper oMapper = new ObjectMapper();

    @GetMapping("/main")
    public String myListener() {
        return "Hello World";
    }

    @GetMapping("/main/student")
    public String myStudentListener() {
        Student st1=new Student("Marzhan",17);
        String jsonData=null;
        try{
            jsonData=oMapper.writeValueAsString(st1);

        }catch (JsonProcessingException e){
            System.out.println("Error system"+e.getMessage());

        }

        return jsonData;
    }
}
