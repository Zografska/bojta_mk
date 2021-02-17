package com.example.bojta_mk.service.impl;

import com.example.bojta_mk.service.MailService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;


@Service
public class MailServiceImpl implements MailService {

    private static final String username = "order.bojtaing@gmail.com";
    private static final String password = "pk35^#!9jbAHxU+ux2zR";
    private Session session;
    private File file;

    private void setProperties()
    {
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.EnableSSL.enable","true");
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    private Message createMessage(String mailTo, String fileName) throws MessagingException, IOException {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(mailTo));
        message.setSubject("Нарачка");
        message.setText("Во прилог добивате .pdf датотека со нова нарачка.");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();
        DataSource source = new FileDataSource(fileName);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        return message;
    }

    @Override
    public void sendOrderMail(String name, Long id) {

        try {
            //set config and create session
            setProperties();

            //create message
            String fileName = name + "Order" + id + ".pdf";
            Message message = this.createMessage("aleksndra2@gmail.com", fileName);

            //send message
            Transport.send(message);

            file = new File(fileName);

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        } finally {
            file.delete();
        }
    }
}