package com.vvquiz.quizappconsumer.controller;

import com.vvquiz.quizappconsumer.service.impl.EmailServiceImpl;
import com.vvquiz.quizappconsumer.utils.EmailDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailServiceImpl emailService;
    @PostMapping("/send-email")
    public String sendEmail() {
        EmailDetails   emailDetails = new EmailDetails();
        emailDetails.setRecipient("vikas1306vv@gmail.com");
        emailDetails.setSubject("Test Subject");
        emailDetails.setMsgBody("Test Body");
       return  emailService.sendSimpleMail(emailDetails);
    }
}
