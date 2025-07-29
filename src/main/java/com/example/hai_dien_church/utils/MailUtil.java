package com.example.hai_dien_church.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailUtil {

    JavaMailSender javaMailSender;
    public void sendEmailFormat(String userEmail, String subject, String header, String content, String footer) {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,"utf-8");
            mimeMessageHelper.setFrom("tringuyenquoc15102004@gmail.com");
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setSubject(subject);
            String text = "<!DOCTYPE html>\n" +
                    "<html lang=\"vi\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "</head>\n" +
                    "<body style=\"margin: 0; padding: 0; background-color: #fff7ed; font-family: Arial, sans-serif; color: #1a1a1a;\">\n" +
                    "    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                    "        <tr>\n" +
                    "            <td align=\"center\">\n" +
                    "                <table width=\"640\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background: #ffffff; border-radius: 16px;\">\n" +
                    "                    <!-- Header -->\n" +
                    header +
                    "                    <!-- Content -->\n" +
                    content +
                    "                    <!-- Footer -->\n" +
                    footer +
                    "                </table>\n" +
                    "            </td>\n" +
                    "        </tr>\n" +
                    "    </table>\n" +
                    "</body>\n" +
                    "</html>";
            mimeMessageHelper.setText(text,true);
            mimeMessageHelper.setTo(userEmail);
            javaMailSender.send(mimeMessage);
        }catch (MailException | MessagingException e){
            throw new MailSendException("failed to send email");
        }

    }

}
