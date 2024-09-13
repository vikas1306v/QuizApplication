package com.vvquiz.quizappconsumer;

import com.vvquiz.quizappconsumer.service.impl.EmailServiceImpl;
import com.vvquiz.quizappconsumer.utils.EmailDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class QuizappconsumerApplicationTests {
	@Autowired
	EmailServiceImpl emailService;

	@Test
	void contextLoads() {
		EmailDetails details = new EmailDetails();
		details.setRecipient("vikas1306vv@gmail.com");
		details.setSubject("Test Subject");
		details.setMsgBody("Test Body");
		emailService.sendSimpleMail(details);

	}

}
