package net.mooko.common.utils;

import org.apache.axis.transport.mail.MailServer;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

/**
 * @author puras <he@puras.me>
 * @since 15/8/20  下午9:29
 */
public class MailSender {
    public static boolean sendTextMail(MailInfo mailInfo) {
        Session session = getSession(mailInfo);
        try {
            final Message message = new MimeMessage(session);
            setMessage(message, mailInfo);
            message.setText(mailInfo.getContent());
            Transport.send(message);
            return true;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean sendHtmlMail(MailInfo mailInfo) {
        Session session = getSession(mailInfo);
        try {
            final Message message = new MimeMessage(session);
            setMessage(message, mailInfo);
            Multipart part = new MimeMultipart();
            BodyPart html = new MimeBodyPart();
            html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
            part.addBodyPart(html);
            message.setContent(part);
            Transport.send(message);

            return true;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private static Session getSession(MailInfo mailInfo) {
        Authenticator authenticator = null;
        Properties props = mailInfo.getProperties();
        if (mailInfo.isValidate()) {
            authenticator = new MailAuthenticator(mailInfo.getUsername(), mailInfo.getPassword());
        }
        return Session.getInstance(props, authenticator);
    }

    private static void setMessage(final Message message, MailInfo mailInfo) throws Exception {
        Address from = new InternetAddress(mailInfo.getFromAddress());
        message.setFrom(from);
        Address to = new InternetAddress(mailInfo.getToAddress());
        message.setRecipient(Message.RecipientType.TO, to);
//        String subject = new String(Base64Utils.encode(mailInfo.getSubject()).getBytes("UTF-8"));
//        message.setSubject(MimeUtility.encodeText(mailInfo.getSubject(), "UTF-8", "B"));
        message.setSubject(mailInfo.getSubject());
        message.setSentDate(new Date());
    }

    public static void main(String[] args) {
        MailInfo info = new MailInfo();
        info.setServerHost("smtp.163.com");
        info.setUsername("mk_master@163.com");
        info.setPassword("neu2006");
        info.setFromAddress("mk_master@163.com");
        info.setToAddress("he@puras.me");
        info.setSubject("测试测试呗");
        info.setContent("<div style='color: red;'>Helloworld</div>");
        boolean result = MailSender.sendTextMail(info);
        System.out.println(result ? "发送成功" : "发送失败");
        result = MailSender.sendHtmlMail(info);
        System.out.println(result ? "发送成功" : "发送失败");
    }

    public static class MailInfo {
        private String serverHost;
        private String serverPort = "25";
        private String username;
        private String password;
        private String fromAddress;
        private String toAddress;
        private boolean validate = true;
        private String subject;
        private String content;
        private String[] attachFileNames;

        public Properties getProperties() {
            Properties props = new Properties();
            props.put("mail.smtp.host", this.serverHost);
            props.put("mail.smtp.port", this.serverPort);
            props.put("mail.smtp.auth", validate ? "true" : "false");
            return props;
        }

        public String[] getAttachFileNames() {
            return attachFileNames;
        }

        public void setAttachFileNames(String[] attachFileNames) {
            this.attachFileNames = attachFileNames;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFromAddress() {
            return fromAddress;
        }

        public void setFromAddress(String fromAddress) {
            this.fromAddress = fromAddress;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getServerHost() {
            return serverHost;
        }

        public void setServerHost(String serverHost) {
            this.serverHost = serverHost;
        }

        public String getServerPort() {
            return serverPort;
        }

        public void setServerPort(String serverPort) {
            this.serverPort = serverPort;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getToAddress() {
            return toAddress;
        }

        public void setToAddress(String toAddress) {
            this.toAddress = toAddress;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public boolean isValidate() {
            return validate;
        }

        public void setValidate(boolean validate) {
            this.validate = validate;
        }
    }
}

class MailAuthenticator extends Authenticator {
    private String username;
    private String password;

    public MailAuthenticator() {
    }

    public MailAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
