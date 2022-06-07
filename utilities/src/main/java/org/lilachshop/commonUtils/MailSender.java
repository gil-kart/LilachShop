package org.lilachshop.commonUtils;
import org.lilachshop.entities.Customer;
import org.lilachshop.entities.Order;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    public static void sendMailToCustomer(Order order) {

        final String username = "lilachshopemail@gmail.com";
        final String password = "kubblajprgjdjoni";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(order.getCustomer().getEmail())
            );
            message.setSubject("ההזמנה הגיעה ליעד");
            message.setText(order.getCustomer() +
                    " שלום,"+
                    "\n" +
                    "הזמנה מספר " +
                    String.valueOf(order.getId()) +
                    " הגיעה ליעד." +
                    "\n" +
                    " תודה שבחרת בלילך!"
            );

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}