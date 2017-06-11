package com.shyammalani.demo.restapi.controller;

import com.shyammalani.demo.restapi.model.Criteria;
import com.shyammalani.demo.restapi.model.User;
import com.shyammalani.demo.restapi.mongo.MongoServicesDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private MongoServicesDB mongoServices;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> filterData(@Valid @RequestBody Criteria criteria) {
        List<User> users = mongoServices.filterUser(criteria);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
