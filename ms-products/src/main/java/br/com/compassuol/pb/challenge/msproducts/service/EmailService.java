package br.com.compassuol.pb.challenge.msproducts.service;

import br.com.compassuol.pb.challenge.msproducts.payload.ProductDTO;
import br.com.compassuol.pb.challenge.msproducts.payload.UserDTO;
import br.com.compassuol.pb.challenge.msproducts.model.Email;
import org.springframework.http.ResponseEntity;

public interface EmailService {
    ResponseEntity<Email> sendingEmail(UserDTO userDTO, ProductDTO productDTO);

}
