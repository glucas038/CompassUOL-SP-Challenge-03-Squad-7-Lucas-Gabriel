package br.com.compassuol.pb.challenge.msauthorization.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<RoleDTO> roles;
}
