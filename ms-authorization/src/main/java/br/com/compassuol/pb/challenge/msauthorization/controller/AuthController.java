package br.com.compassuol.pb.challenge.msauthorization.controller;

import br.com.compassuol.pb.challenge.msauthorization.dto.JWTAuthResponse;
import br.com.compassuol.pb.challenge.msauthorization.dto.LoginDTO;
import br.com.compassuol.pb.challenge.msauthorization.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Build Login REST API
    @PostMapping()
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO loginDTO){
        System.out.println("Primeiro");
        String token = authService.login(loginDTO);

        System.out.println("Aqui");

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse );
    }



}

