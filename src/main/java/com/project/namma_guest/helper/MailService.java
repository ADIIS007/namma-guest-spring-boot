package com.project.namma_guest.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class MailService {
    private final JavaMailSender javaMailSender;
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendMail(String to, String subject, String text) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("akilanathithyaithayan@gmail.com");
        mail.setTo(to.trim());
        mail.setSubject(subject);
        mail.setText(text);
        log.info("Mail sent to: "+ mail.getTo());
        log.info("Mail sent to: "+ mail.getFrom());
        log.info("Mail sent to: "+ mail.getSentDate());
        javaMailSender.send(mail);
    }
}