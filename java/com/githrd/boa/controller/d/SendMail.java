package com.githrd.boa.controller.d;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class SendMail {
	   /*
	   public SendMail() {
	      gmailSend();
	   }
	   */
	   public void gmailSend(String mail, int inum, String id) {
	        String user = "ehdtn0209@gmail.com"; // gmail 계정 입력
	        String password = "rmflsvjscl!9194";    // gmail 패스워드 입력

	        // SMTP 서버 정보를 설정한다.
	        Properties prop = new Properties();
	        prop.put("mail.smtp.starttls.enable", "true");
	        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
	        prop.put("mail.smtp.host", "smtp.gmail.com"); 
	        prop.put("mail.smtp.port", 587); 
	        prop.put("mail.smtp.auth", "true"); 
	      //prop.put("mail.smtp.ssl.enable", "true");
	      prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	       
	        
	        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(user, password);
	            }
	        });

	        try {
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(user));
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));//수신자메일주소

	            // Subject
	            message.setSubject("인증메일"); //메일 제목을 입력

	            // Text
	            message.setText("http://localhost/boara/member/cert.boa?code="+inum+"&id="+id);    //메일 내용을 입력

	            // send the message
	            Transport.send(message); ////전송
	            System.out.println("message sent successfully...");
	        } catch (AddressException e) {
	            e.printStackTrace();
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
	   
	   /*
	   public static void naverMailSend() {
	        String host = "smtp.naver.com"; 
	        String user = "";   // 네이버 계정
	        String password = "";   // 네이버 패스워드

	        // SMTP 서버 정보를 설정한다.
	        Properties props = new Properties();
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.port", 587);
	        props.put("mail.smtp.auth", "true");
	        
	        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(user, password);
	            }
	        });

	        try {
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(user));
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress("ktko@ktko.com"));

	            // 메일 제목
	            message.setSubject("KTKO SMTP TEST1111");

	            // 메일 내용
	            message.setText("KTKO Success!!");

	            // send the message
	            Transport.send(message);
	            System.out.println("Success Message Send");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
	   public static void main(String[] args) {
	      new SendMail();
	   }
	   */

	}