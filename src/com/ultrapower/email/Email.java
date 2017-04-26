package com.ultrapower.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ultrapower.common.util.PropertyUtil;

public class Email { 

	private static Log log = LogFactory.getLog(Email.class);
	private static PropertyUtil instance = PropertyUtil.getInstance();
	private static String host=instance.getProperty("email.host").trim();
	private static int port= Integer.parseInt(instance.getProperty("email.port").trim());
	private static String protocol=instance.getProperty("mail.transport.protocol").trim();
	private static String from=instance.getProperty("email.from").trim();
	private static String username=instance.getProperty("username").trim();
	private static String password=instance.getProperty("password").trim();
	//发送邮件
    public void sendEmail(String dept,String subject,String content) {  
        Properties props = new Properties();  
        // 开启debug调试  
        props.setProperty("mail.debug", "true");  
        // 发送服务器需要身份验证  
        props.setProperty("mail.smtp.auth", "true");  
        // 设置邮件服务器主机名  
        props.setProperty("mail.smtp.host", host);        
        //mail.ultrapower.com.cn,smtp.163.com,smtp.qq.com,yuanlulu@ultrapower.com.cn
        //设置邮件服务器端口  
        props.setProperty("mail.smtp.port",String.valueOf(port) );
        // 发送邮件协议名称  
        props.setProperty("mail.transport.protocol", protocol);            
        // 设置环境信息  
        Session session = Session.getInstance(props);          
        // 创建邮件对象  
        Message msg = new MimeMessage(session);  
        try {
	    msg.setSubject(subject);		 
        // 设置邮件内容   
        msg.setContent(content,"text/html;charset=UTF-8");
        msg.setSentDate(new Date());
        // 设置发件人  
        msg.setFrom(new InternetAddress(from));  
        msg.saveChanges();         
        Transport transport = session.getTransport();  
        // 连接邮件服务器  
        transport.connect(username, password);  
        // 发送邮件  
        transport.sendMessage(msg, new Address[] {new InternetAddress(dept)});  
        // 关闭连接  
        transport.close(); 
        } catch (MessagingException e) {
			e.printStackTrace();
		} 
    }  
    public static void main(String[] args) {
    	Email email=new Email();
    	email.sendEmail("wangshenghui@ultrapower.com.cn", "邮件测试","不要脸" );
	}
} 