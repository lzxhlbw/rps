package com.hdu.rps.service;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

/**
 * Created by SJH on 2017/11/16.
 */
@Service
public class MailServiceImpl implements MailService{
    private Logger logger = Logger.getLogger(String.valueOf(MailServiceImpl.this));

    @Value("${spring.mail.username}")
    private String from;
    
    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String username;
    
    @Value("${spring.mail.password}")
    private String password;
    
    @Value("${spring.mail.properties.mail.smtp.auth}")//
    private String auth;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", auth);
        properties.setProperty("mail.user", username);
        properties.setProperty("mail.password", password);
        Session session = Session.getDefaultInstance(properties);
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch(MessagingException mex){
            mex.printStackTrace();
        }
        
    }
}
