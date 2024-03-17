package utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String to, String subject, String body) {
        Properties props = new Properties();
        InputStream input = EmailSender.class.getClassLoader().getResourceAsStream("email-api-config.properties");
        try {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String username = props.getProperty("email.username");
        final String password = props.getProperty("email.password");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

