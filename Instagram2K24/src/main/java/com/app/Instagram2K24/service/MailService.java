package com.app.Instagram2K24.service;

import com.app.Instagram2K24.dto.NotificationEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender javaMailSender;

    @Async
    public void sendMail(NotificationEmail notificationEmail)  {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("sundhar8346@gmail.com");
            mimeMessageHelper.setTo(notificationEmail.getRecipient());
            mimeMessageHelper.setSubject(notificationEmail.getSubject());
            mimeMessageHelper.setText(notificationEmail.getBody());
        };
        try {
            javaMailSender.send(mimeMessagePreparator);
            log.info("Activation mail send");
        }catch (MailException e){
            log.error("Exception occurred when sending mail "+e);
            throw new RuntimeException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
        }
    }


}
