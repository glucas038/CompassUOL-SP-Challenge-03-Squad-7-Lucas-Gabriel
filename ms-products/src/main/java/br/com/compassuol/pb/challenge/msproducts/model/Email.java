package br.com.compassuol.pb.challenge.msproducts.model;

import br.com.compassuol.pb.challenge.msproducts.enums.StatusEmail;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Email implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID emailId;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;
}
