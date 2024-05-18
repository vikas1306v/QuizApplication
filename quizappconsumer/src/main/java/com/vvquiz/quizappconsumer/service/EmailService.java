package com.vvquiz.quizappconsumer.service;

import com.vvquiz.quizappconsumer.utils.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}
