package br.com.compassuol.pb.challenge.msproducts.service;

import br.com.compassuol.pb.challenge.msproducts.dto.EmailDTO;
import br.com.compassuol.pb.challenge.msproducts.dto.ProductDTO;
import br.com.compassuol.pb.challenge.msproducts.dto.UserDTO;
import br.com.compassuol.pb.challenge.msproducts.entity.User;
import br.com.compassuol.pb.challenge.msproducts.model.Email;
import org.springframework.http.ResponseEntity;

public interface EmailService {
    ResponseEntity<Email> sendingEmail(UserDTO userDTO, ProductDTO productDTO);

}
