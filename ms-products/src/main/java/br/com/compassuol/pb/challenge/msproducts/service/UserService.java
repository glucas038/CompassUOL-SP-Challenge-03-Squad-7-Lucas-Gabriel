package br.com.compassuol.pb.challenge.msproducts.service;

import br.com.compassuol.pb.challenge.msproducts.payload.UserDTO;

import java.util.Optional;

public interface UserService {

    Optional<UserDTO> findByEmail(String email);
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long userId);
    UserDTO updateUser(Long userId, UserDTO userDTO);

}
