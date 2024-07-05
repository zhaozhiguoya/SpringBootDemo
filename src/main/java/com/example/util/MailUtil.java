package com.example.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.*;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

/**
 * @Author：zhaozg
 * @Date：2024/4/19 13:55
 * @Desc：
 */
public class MailUtil {

    public static boolean sendMail(String sentToEmail,String ccMail,String content) throws MessagingException, GeneralSecurityException {
        Properties props = new Properties();
        // 初始化props
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.163.com");

        // 如果是QQ邮箱，还要设置SSL加密，加上以下代码即可
//        MailSSLSocketFactory sf = new MailSSLSocketFactory();
//        sf.setTrustAllHosts(true);
//        props.put("mail.smtp.ssl.enable", "true");
//        props.put("mail.smtp.ssl.socketFactory", sf);

//        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl.enable", true);


        final String username = "zhaozg1219@163.com";// gmail 邮箱
        final String password = "GIXZWUFKNVFUAERF";// Google应用专用密码

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            //身份认证
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);//fromUser(), fromUserPassword
            }
        });

        MimeMessage msg=new MimeMessage(session);
        //2.设置发件人地址
        msg.setFrom(new InternetAddress(username));
        // 指定收件人
        //设置收件人地址，以逗号隔开
//        InternetAddress[] sendTo = InternetAddress.parse(sentToEmail);
        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(sentToEmail));
        //抄送
        msg.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(ccMail));
        //4.设置邮件主题
        msg.setSubject("测试邮件发送!");
        //6.创建文本"节点"
        MimeBodyPart text = new MimeBodyPart();
        //这里添加图片的方式是将整个图片包含到邮件内容中，实际上也可以以 http 链接的形式添加网络图片
        text.setContent(content,"text/html;charset=UTF-8");
        //7.(文本+图片)设置 文本 和 图片"节点"的关系(将 文本 和 图片"节点"合成一个混合"节点")
        MimeMultipart mm_text_image = new MimeMultipart();
        mm_text_image.addBodyPart(text);
        mm_text_image.setSubType("related");//关联关系
        // 11. 设置整个邮件的关系(将最终的混合"节点"作为邮件的内容添加到邮件对象)
        msg.setContent(mm_text_image);
        //设置邮件的发送时间,默认立即发送
        msg.setSentDate(new Date());
        Transport.send(msg);

        return true;
    }

}
