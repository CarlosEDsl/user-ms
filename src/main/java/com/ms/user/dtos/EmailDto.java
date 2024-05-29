package com.ms.user.dtos;

import lombok.Data;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.UUID;

@Data
public class EmailDto {

    private UUID userId;
    private String emailTo;
    private String subject;
    private String text;

}
