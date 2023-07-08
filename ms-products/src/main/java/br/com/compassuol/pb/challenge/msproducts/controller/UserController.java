package br.com.compassuol.pb.challenge.msproducts.controller;

import br.com.compassuol.pb.challenge.msproducts.dto.UserDTO;
import br.com.compassuol.pb.challenge.msproducts.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDTO> status() {
        return new ResponseEntity<>(userService.getUserById(1l), HttpStatus.OK);
    }

    @GetMapping("/oauth")
    public ResponseEntity<Optional<UserDTO>> findByEmail(@RequestParam String email) {
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "userId") long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "userId") long userId,
                                              @Valid @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateUser(userId, userDTO));
    }
}
