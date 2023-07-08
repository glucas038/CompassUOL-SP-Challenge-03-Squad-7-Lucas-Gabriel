package br.com.compassuol.pb.challenge.msauthorization.service;

import br.com.compassuol.pb.challenge.msauthorization.dto.UserDTO;
import br.com.compassuol.pb.challenge.msauthorization.feignclients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    public ResponseEntity<UserDTO> getStatus(){
        return userFeignClient.status();
    }

    public UserDTO findById(Long id){
        UserDTO userDTO = userFeignClient.getUserById(id).getBody();
        if(userDTO == null){
            throw new IllegalArgumentException("User not found");
        }
        //System.out.println(userDTO.getEmail());
        logger.info("User found: " + id);
        return userDTO;
    }

    public UserDTO findByEmail(String email){
        UserDTO userDTO = userFeignClient.findByEmail(email).getBody().get();
        if(userDTO == null){
            throw new IllegalArgumentException("User email not found");
        }
        //System.out.println(userDTO.getEmail());
        logger.info("User found: " + email);
        logger.info("User found: " + userDTO.getPassword());
        return userDTO;
    }
}
