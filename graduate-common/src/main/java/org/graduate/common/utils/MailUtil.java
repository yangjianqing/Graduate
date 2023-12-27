package org.graduate.common.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

/**
 * @description: 邮箱工具类
 */
public class MailUtil {

    // 发送人
    public static final String FROM_ARRDESS = "877533957@qq.com";
    // qq邮箱授权码
    public static final String AUTH_CODE = "senshlejfapnbbch";

    /**
     * 发送单封邮件
     * @param title 邮件标题
     * @param content 邮件内容
     * @param receiveAddress 收件人邮箱地址
     * @throws MessagingException
     */
    public static void sendTextMail (String title, String content, String receiveAddress) throws MessagingException {
        //把收件人放入对象中
        Session session = createSession();
        Message message = createMessage(session, title, content);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveAddress));
        sendMail(session, message);
    }

    /**
     * 批量发送邮件，只发送qq邮箱
     * @param title 邮件标题
     * @param content 邮件内容
     * @param addressList 收件人邮箱地址数组
     * @throws MessagingException
     */
    public static void batchSendTextMail(String title, String content, List<String> addressList) throws MessagingException {
        Session session = createSession();
        Message message = createMessage(session, title, content);
        for (String address : addressList) {
            if (!address.matches(".+@qq\\.com")) { // 判断是否为qq邮箱
                continue; // 如果不是，跳过本次循环，发送下一个邮箱
            }
            InternetAddress recipient = new InternetAddress(address);
            message.setRecipient(Message.RecipientType.TO, recipient);
            sendMail(session, message);
        }
    }


    /**
     * 产生链接
     * @return
     */
    public static Session createSession () {
        Properties properties = new Properties();
        // 连接协议
        properties.put("mail.transport.protocol", "smtp");
        // 主机名
        properties.put("mail.smtp.host", "smtp.qq.com");
        // 端口号
        properties.put("mail.smtp.port", 465);
        properties.put("mail.smtp.auth", "true");
        // 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.smtp.ssl.enable", "true");
        // 设置是否显示debug信息 true 会在控制台显示相关信息
        properties.put("mail.debug", "true");
        // 得到回话对象
        return Session.getInstance(properties);
    }

    /**
     *  创建message对象
     * @param session
     * @param title
     * @param content
     * @return
     * @throws MessagingException
     */
    public static Message createMessage (Session session, String title, String content) throws MessagingException {
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress(FROM_ARRDESS));
        // 设置邮件标题
        message.setSubject(title);
        // 设置邮件内容
        message.setContent(content, "text/html;charset=gb2312");
        return message;
    }

    /**
     *  发送邮件
     * @param session
     * @param message
     * @throws MessagingException
     */
    public static void sendMail(Session session, Message message) throws MessagingException {
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        // 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        transport.connect(FROM_ARRDESS, AUTH_CODE);
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
