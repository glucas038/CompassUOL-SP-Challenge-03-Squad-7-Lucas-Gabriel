package br.com.compassuol.pb.challenge.msauthorization.service;

import br.com.compassuol.pb.challenge.msauthorization.dto.LoginDTO;
import br.com.compassuol.pb.challenge.msauthorization.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthService(AuthenticationManager authenticationManager,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String login(LoginDTO loginDTO) {
        System.out.println(loginDTO.getEmail());
        System.out.println(loginDTO.getPassword());


        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDTO.getEmail(), loginDTO.getPassword()
            ));

            System.out.println("Opa");

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);

            return token;

            // Restante do código após a autenticação bem-sucedida
        } catch (AuthenticationException e) {
            // Captura a exceção de autenticação
            System.out.println("Erro na autenticação: " + e.getMessage());
            // Ou use um logger para registrar a exceção, se preferir
        }


        return "Tudo certo";
    }


}
