package com.jt.blog.utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author : 戴瑞
 * @Description :
 * @create : 2017-12-12 16:42
 **/

public class SendMailUtils {

    public static boolean send_qqmail(String strMail, String strTitle, String strText){
        boolean bret = false;
        try
        {
            final Properties props = new Properties();

            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.qq.com");
            //你自己的邮箱
            props.put("mail.user", "q1996716@vip.qq.com");
            //你开启pop3/smtp时的验证码
            props.put("mail.password", "legztdyyegdhbbai");
            props.put("mail.smtp.port", "25");
            // 这里必须设置STARTTLS 模式
            props.put("mail.smtp.starttls.enable", "true");

            Authenticator authenticator = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            InternetAddress to = new InternetAddress(strMail);
            message.setRecipient(MimeMessage.RecipientType.TO, to);

            // 设置邮件标题
            message.setSubject(strTitle);

            // 设置邮件的内容体
            message.setContent(strText, "text/html;charset=UTF-8");

            // 发送邮件
            Transport.send(message);
            bret = true;
        }
        catch (AddressException e) {
            e.printStackTrace();
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bret;
    }
}
