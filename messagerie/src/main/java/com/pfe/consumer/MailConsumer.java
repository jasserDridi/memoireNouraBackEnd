package com.pfe.consumer;


import com.pfe.Service.EmailService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
@ApplicationScoped
public class MailConsumer {

 /*   @Inject
    EmailService emailService;
    @Incoming("mail-in")
    public void sendEmail(EmailDto emailDto)
    {
        System.out.println("inside sendEmail");
        emailService.sendMail(emailDto.email, emailDto.message, "NOTIFICATION");
    }*/
}
