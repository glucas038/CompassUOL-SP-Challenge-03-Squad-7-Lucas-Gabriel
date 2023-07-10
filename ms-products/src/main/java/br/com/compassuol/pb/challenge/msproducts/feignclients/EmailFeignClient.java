package br.com.compassuol.pb.challenge.msproducts.feignclients;


import br.com.compassuol.pb.challenge.msproducts.model.Email;
import br.com.compassuol.pb.challenge.msproducts.payload.EmailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Component
@FeignClient(name = "ms-notification", path = "")
public interface EmailFeignClient {

    @PostMapping("/sending-email")
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDTO emailDto);

}

