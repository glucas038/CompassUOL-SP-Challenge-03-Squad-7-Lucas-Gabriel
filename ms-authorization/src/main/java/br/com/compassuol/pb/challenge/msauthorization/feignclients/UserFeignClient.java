package br.com.compassuol.pb.challenge.msauthorization.feignclients;

import br.com.compassuol.pb.challenge.msauthorization.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Component
@FeignClient(name = "ms-products", path = "/api/users")
public interface UserFeignClient {

    @GetMapping
    ResponseEntity<UserDTO> status();

    @GetMapping("oauth")
    ResponseEntity<Optional<UserDTO>> findByEmail(@RequestParam String email);

    @GetMapping("/{userId}")
    ResponseEntity<UserDTO> getUserById(@PathVariable(value = "userId") Long userId);
}
