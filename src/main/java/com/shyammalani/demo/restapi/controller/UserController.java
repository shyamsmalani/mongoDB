package com.shyammalani.demo.restapi.controller;

import com.shyammalani.demo.restapi.model.User;
import com.shyammalani.demo.restapi.mongo.MongoServicesDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/users")
public class UserController {
    @Autowired
    private MongoServicesDB mongoServices;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User response = mongoServices.addUser(user);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        final User user = mongoServices.getUser(id);

        if (null == user) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        boolean response = mongoServices.deleteUser(id);

        if (!response) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {

        if (!mongoServices.updateUser(id, user)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
