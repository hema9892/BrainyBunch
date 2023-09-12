package com.ULB.service.controller;

import com.ULB.service.dto.UserDTO;
import com.ULB.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

  @PostMapping
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
      try{
          userService.RegisterUser(userDTO);
          return new ResponseEntity<>(HttpStatus.CREATED);
      }
      catch (Exception e){
          return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
      }
  }



}
