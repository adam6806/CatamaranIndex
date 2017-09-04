package com.github.adam6806.catamaranindex.scraper.email;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Component
@PropertySource(value = {"classpath:application.properties"})
public class EmailSender {

    @Inject
    private Environment environment;

    public void sendEmail(String emailMessage) {
        sendEmail(emailMessage, environment.getProperty("email.list"));
    }

    public void sendEmail(String emailMessage, String commaSeparatedAddresses) {

        try {
            final String fromEmail = environment.getProperty("email.address"); //requires valid gmail id
            final String password = environment.getProperty("email.password"); // correct password for gmail id

            System.out.println("TLSEmail Start");
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(props, auth);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            List<String> emails = Arrays.asList(commaSeparatedAddresses.split("\\s*,\\s*"));
            for (String emailAddress : emails) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
            }

            System.out.println("Mail Check 2");

            message.setSubject("New Catamarans From Adam's Catamaran Scraper");
            message.setContent(emailMessage, "text/html");

            System.out.println("Mail Check 3");

            Transport.send(message);
            System.out.println("Mail Sent");
        } catch (Exception ex) {
            System.out.println("Mail fail");
            System.out.println(ex);
        }
    }
}