package test;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CopyOfSendEmail163Test {
	public static void main(String[] args) throws MessagingException {
		Properties properties = new Properties();
		properties.setProperty("mail.host", "smtp.163.com");
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
		//1.创建Session
		Session session = Session.getInstance(properties);
		//2.开启session的debug模式，方便查看发送email的运行状态
		session.setDebug(true);
		//3.通过session得到transport对象
		Transport transport = session.getTransport();
		//4.使用用户名密码连接上邮箱服务器,此处的密码需要到邮箱开启服务设置
		transport.connect("smtp.163.com", "m13378696977", "serverxhsh123");
		//5.创建邮件
		Message message = createSimpleMail(session);
		//6.发送邮件
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}

	private static Message createSimpleMail(Session session) throws AddressException, MessagingException {
		//创建邮件对象
		MimeMessage message = new MimeMessage(session);
		//指明邮件的发件人
		message.setFrom(new InternetAddress("m13378696977@163.com"));
		//指明邮件的收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("1642483368@qq.com"));
		//邮件的标题
		message.setSubject("邮件发送测试");
		//邮件的内容
		message.setContent("你好，欢迎来到Javaee学习课堂", "text/html;charset=UTF-8");
		//返回
		return message;
	}
	
	
}
