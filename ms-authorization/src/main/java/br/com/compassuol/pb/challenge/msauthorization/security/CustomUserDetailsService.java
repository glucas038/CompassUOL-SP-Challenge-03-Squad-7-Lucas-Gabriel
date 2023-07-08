package br.com.compassuol.pb.challenge.msauthorization.security;

import br.com.compassuol.pb.challenge.msauthorization.dto.UserDTO;
import br.com.compassuol.pb.challenge.msauthorization.feignclients.UserFeignClient;
import br.com.compassuol.pb.challenge.msauthorization.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserFeignClient userFeignClient;
    private UserService userService;

    public CustomUserDetailsService(UserFeignClient userFeignClient, UserService userService) {
        this.userFeignClient = userFeignClient;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDTO userDTO = userService.findByEmail(email);

        Set<SimpleGrantedAuthority> authorities = userDTO
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(userDTO.getEmail(),
                userDTO.getPassword(),
                authorities);
    }


}
