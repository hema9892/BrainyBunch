package com.ULB.service.controller;

import com.ULB.service.dto.UserDTO;
import com.ULB.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
  @GetMapping("/{username}/loaned-books")
    public List<String> getLoanedBooks(@PathVariable String username){
     return userService.getLoanedBooks(username);

  }

  @PostMapping("/{username}/loan-book")
    public void loanBook(@PathVariable String username, @RequestBody String bookTitle){
      userService.loanBook(username, bookTitle);
  }

  @PostMapping("/{username}/return-book")
  public void returnBook(@PathVariable String username, @RequestBody String bookTitle){
      userService.returnBook(username, bookTitle);
  }


}
