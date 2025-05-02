package com.gonzalodev.saiyajinstore.backend.infrastructure.rest;

import com.gonzalodev.saiyajinstore.backend.application.UserService;
import com.gonzalodev.saiyajinstore.backend.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin/users")
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> findAll(){
        log.info("Users found.");
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        log.info("User with id: {} created.", user.getId());
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id){
        log.info("User with id: {} found.", id);
        return ResponseEntity.ok(userService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUserById (@PathVariable Integer id){
        log.warn("User with id: {} deleted", id);
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity updateUserById (@RequestParam Integer id, @RequestParam String userType){
        userService.updateUserById(id, userType);
        return ResponseEntity.ok().build();
    }
}
