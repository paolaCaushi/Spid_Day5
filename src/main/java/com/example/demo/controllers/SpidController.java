package com.example.demo.controllers;


import com.example.demo.model.Spid;
import com.example.demo.model.User;
import com.example.demo.service.SpidService;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/api")
public class SpidController {

    UserService userService;
    SpidService spidService;

    SpidController(UserService userService, SpidService spidService) {
        this.userService = userService;
        this.spidService = spidService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setId(-1);
        return new ResponseEntity(userService.createUser(user), HttpStatus.OK);
    }
    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user, @RequestParam long id) throws Exception {
        return new ResponseEntity(userService.updateUser(user, id), HttpStatus.OK);
    }

    @GetMapping("/spid")
    public ResponseEntity<List<Spid>> getAllSpids() {
        return new ResponseEntity(spidService.retrieveAllSpids(), HttpStatus.OK);
    }

    @GetMapping("/spid/{id}")
    public ResponseEntity<Spid> getSpid(@PathVariable(name = "id") long id) throws Exception {
        return new ResponseEntity(spidService.findSpidById(id), HttpStatus.OK);
    }

    @PostMapping("/spid")
    public ResponseEntity<Spid> createSpid(@RequestBody Spid spid) throws Exception {
        return new ResponseEntity(spidService.createSpid(spid), HttpStatus.OK);
    }

    @PutMapping("/spid/{id}")
    public ResponseEntity<Spid> changeSpidStatus(@PathVariable(name = "id") long id) throws Exception {
        return new ResponseEntity(spidService.changeStatus(id), HttpStatus.OK);
    }

    @DeleteMapping("/spid/{id}")
    public ResponseEntity<Map<String, String>> deleteSpid(@PathVariable(name = "id") long id) throws Exception {
        spidService.deleteSpid(id);

        Map<String, String> res = new HashMap();
        res.put("message", "Spid is deleted successfully");
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
