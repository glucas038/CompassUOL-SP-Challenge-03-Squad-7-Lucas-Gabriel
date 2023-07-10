package br.com.compassuol.pb.challenge.msproducts.service.impl;

import br.com.compassuol.pb.challenge.msproducts.feignclients.EmailFeignClient;
import br.com.compassuol.pb.challenge.msproducts.model.Email;
import br.com.compassuol.pb.challenge.msproducts.payload.EmailDTO;
import br.com.compassuol.pb.challenge.msproducts.payload.ProductDTO;
import br.com.compassuol.pb.challenge.msproducts.payload.UserDTO;
import br.com.compassuol.pb.challenge.msproducts.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailFeignClient emailFeignClient;

    @Override
    public ResponseEntity<Email> sendingEmail(UserDTO userDTO, ProductDTO productDTO) {
        EmailDTO emailDTO = new EmailDTO();

        emailDTO.setOwnerRef(userDTO.getFirstName()+ " " + userDTO.getLastName());
        emailDTO.setEmailFrom("pbuolcompass@gmail.com");
        emailDTO.setEmailTo(userDTO.getEmail());
        emailDTO.setSubject("Product created");
        emailDTO.setText("Product " + productDTO.getName() + " was successfully created");

        Email email = emailFeignClient.sendingEmail(emailDTO).getBody();

        return ResponseEntity.ok(email);
    }
}


