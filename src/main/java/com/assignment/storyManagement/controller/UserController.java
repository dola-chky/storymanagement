package com.assignment.storyManagement.controller;

import com.assignment.storyManagement.model.ApplicationUser;
import com.assignment.storyManagement.repository.ApplicationUserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
public class UserController {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<ApplicationUser> createUser(@RequestBody ApplicationUser appUser) {
        if (applicationUserRepository.findByUsername(appUser.getUsername()) != null) {
            throw new RuntimeException("Username already exist");
        }

        /*removed password encoding part as I want to insert some data manually*/
        /*appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));*/

        return new ResponseEntity<ApplicationUser>(applicationUserRepository.save(appUser), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> userMap) throws IOException {
        String username = userMap.get("username");
        String password = userMap.get("password");

        String token = null;
        ApplicationUser appUser = applicationUserRepository.findByUsername(username);
        Map<String, Object> tokenMap = new HashMap<String, Object>();

        if (appUser != null && appUser.getPassword().equals(password)) {
            token = Jwts.builder().setSubject(username).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
            tokenMap.put("token", token);
            tokenMap.put("user", appUser);
            return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.OK);
        } else {
            tokenMap.put("token", null);
            return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.UNAUTHORIZED);
        }

    }
}
