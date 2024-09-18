package com.ticketcounter.spring_boot_library.service;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String content, byte[] imageData, String imageFileName) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(content, true); // Set to true to indicate that the content is HTML

        if (imageData != null) {
            MimeMultipart multipart = new MimeMultipart("related");
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(content, "text/html; charset=UTF-8");
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart imagePart = new MimeBodyPart();
            DataSource source = new ByteArrayDataSource(imageData, "image/jpeg");
            imagePart.setDataHandler(new DataHandler(source));
            imagePart.setHeader("Content-ID", "<movieImage>");
            multipart.addBodyPart(imagePart);

            mimeMessage.setContent(multipart);
        } else {
            mimeMessage.setContent(content, "text/html; charset=UTF-8");
        }

        mailSender.send(mimeMessage);
    }
}
