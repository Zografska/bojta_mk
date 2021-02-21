package com.example.bojta_mk.service.impl;

import com.example.bojta_mk.model.User;
import com.example.bojta_mk.service.MailService;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
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
            Message message = this.createMessage("jakovvmitrovski@gmail.com", fileName);

            //send message
            Transport.send(message);

            file = new File(fileName);

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        } finally {
            file.delete();
        }
    }

    private Message createContactMessage(String mailTo, String body) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(mailTo));
        message.setSubject("Прашање");
        message.setText(body);
        return message;
    }

    @Override
    public void sendQuestion(String body) {

        try {
            //set config and create session
            setProperties();

            //create message
            Message message = this.createContactMessage("aleksndra2@gmail.com", body);

            //send message
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Message createRegisterMessage(String mailTo,String name,String surname) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(mailTo));
        message.setSubject("Успешна регистрација!");
        message.setText("Почитувани"+name+surname+"\n"+
                "Успешно се регистриравте на сајтот bojta-ing.com.mk");
        return message;
    }

    @Override
    public void sendRegisterConfirmation(String username,String name,String surname) {
        try {
            //set config and create session
            setProperties();

            //create message
            Message message = this.createRegisterMessage(username,name,surname);

            //send message
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
