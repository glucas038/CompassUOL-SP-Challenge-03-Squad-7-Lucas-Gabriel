package br.com.compassuol.pb.challenge.msauthorization.resources;

import br.com.compassuol.pb.challenge.msauthorization.dto.UserDTO;
import br.com.compassuol.pb.challenge.msauthorization.service.UserService;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserDTO> status(){
        return userService.getStatus();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "userId") Long userId){
        try {
            System.out.println("Id -> " + userId);
            System.out.println("1");
            UserDTO userDTO = userService.findById(userId);
            System.out.println("2");
            return ResponseEntity.ok(userDTO);
        }
        catch (IllegalArgumentException e){

            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        }

    }

    @GetMapping("/email")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email){
        try {
            System.out.println("email -> " + email);
            System.out.println("1");
            UserDTO userDTO = userService.findByEmail(email);
            System.out.println("2");
            return ResponseEntity.ok(userDTO);
        }
        catch (IllegalArgumentException e){

            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        }

    }
}
