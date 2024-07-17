package com.project.others;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.project.model.RecoverInfo;
import com.project.model.SendingMail;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class SendMail {

	private static SendMail instance = null;

	public static SendMail getInstance() {
		if (instance == null) {
			instance = new SendMail();
		}
		return instance;
	}

	private JavaMailSender mailSender;
	private SpringTemplateEngine templateEngine;

	/**
	 * 
	 * @author MaiTran
	 */

	public void SendMail(String inputMail, String inputSubject, String inputContent)
			throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("maitranbt2003@gmail.com", "giumuqwcnkiruype");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("maitranbt2003@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(inputMail));
		msg.setSubject(inputSubject);
		msg.setSentDate(new java.util.Date());
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(inputContent, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		msg.setContent(multipart);
		Transport.send(msg);
	}

	/**
	 * @author Vinh
	 */
	public void sendMailForgotPassword(SendingMail mailSend, RecoverInfo recoverInfo) {
		try {
			mailSender = declareStaticValue();
			templateEngine = new SpringTemplateEngine();
			//
			ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
			templateResolver.setPrefix("/templates/");
			templateResolver.setSuffix(".html");
			templateResolver.setTemplateMode(TemplateMode.HTML);
			templateResolver.setCharacterEncoding("UTF-8");
			templateResolver.setOrder(0);
			templateEngine.setTemplateResolver(templateResolver);
			templateEngine.addTemplateResolver(templateResolver);
			//
			Message mimeMessage = mailSender.createMimeMessage();
			mimeMessage.setFrom(new InternetAddress("ngtrqvinh1810@gmail.com",false));
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailSend.getSentTo()));
			mimeMessage.setSubject(mailSend.getMailSubject());
			mimeMessage.setSentDate(new java.util.Date());
			//
			MimeBodyPart bodyMail = new MimeBodyPart();
			/**
			 * @Context
			 */
			Context cThyme = new Context();
			cThyme.setVariable("linkRecover","http://localhost:8080/Recover/VerifyRecoverPass?codeRecover=" + recoverInfo.getLinkRecover());
			cThyme.setVariable("timeExpiered", recoverInfo.getTimeExpired());
			//			
			String htmlContent = templateEngine.process("EmailSending/ResetPassword.html", cThyme);
			bodyMail.setContent(htmlContent,"text/html");
			//
			Multipart mContentPart = new MimeMultipart();
			mContentPart.addBodyPart(bodyMail);
			//
			mimeMessage.setContent(mContentPart);
			Transport.send(mimeMessage);
		} catch (Exception e) {
			System.out.println("ERROR sendMailForgotPassword");
			System.out.println(e.getMessage());
		}
	}
	
	public void sendChangeMailRequest(SendingMail mailSend, RecoverInfo recoverInfo) {
		try {
			mailSender = declareStaticValue();
			templateEngine = new SpringTemplateEngine();
			//
			ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
			templateResolver.setPrefix("/templates/");
			templateResolver.setSuffix(".html");
			templateResolver.setTemplateMode(TemplateMode.HTML);
			templateResolver.setCharacterEncoding("UTF-8");
			templateResolver.setOrder(0);
			templateEngine.setTemplateResolver(templateResolver);
			templateEngine.addTemplateResolver(templateResolver);
			//
			Message mimeMessage = mailSender.createMimeMessage();
			mimeMessage.setFrom(new InternetAddress("ngtrqvinh1810@gmail.com",false));
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailSend.getSentTo()));
			mimeMessage.setSubject(mailSend.getMailSubject());
			mimeMessage.setSentDate(new java.util.Date());
			//
			MimeBodyPart bodyMail = new MimeBodyPart();
			/**
			 * @Context
			 */
			Context cThyme = new Context();
			cThyme.setVariable("linkVerify","http://localhost:8080/Email/VerifyNewEmail?codeLink=" + recoverInfo.getLinkRecover());
			cThyme.setVariable("timeExpiered", recoverInfo.getTimeExpired());
			//			
			String htmlContent = templateEngine.process("EmailSending/RequestChangeMail.html", cThyme);
			bodyMail.setContent(htmlContent,"text/html");
			//
			Multipart mContentPart = new MimeMultipart();
			mContentPart.addBodyPart(bodyMail);
			//
			mimeMessage.setContent(mContentPart);
			Transport.send(mimeMessage);
		} catch (Exception e) {
			System.out.println("ERROR sendChangeMailRequest");
			System.out.println(e.getMessage());
		}
	}
	
	public void sendMailRegisterVerification(SendingMail mailSend, RecoverInfo recoverInfo) {
		try {
			mailSender = declareStaticValue();
			templateEngine = new SpringTemplateEngine();
			//
			ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
			templateResolver.setPrefix("/templates/");
			templateResolver.setSuffix(".html");
			templateResolver.setTemplateMode(TemplateMode.HTML);
			templateResolver.setCharacterEncoding("UTF-8");
			templateResolver.setOrder(0);
			templateEngine.setTemplateResolver(templateResolver);
			templateEngine.addTemplateResolver(templateResolver);
			//
			Message mimeMessage = mailSender.createMimeMessage();
			mimeMessage.setFrom(new InternetAddress("ngtrqvinh1810@gmail.com",false));
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailSend.getSentTo()));
			mimeMessage.setSubject(mailSend.getMailSubject());
			mimeMessage.setSentDate(new java.util.Date());
			//
			MimeBodyPart bodyMail = new MimeBodyPart();
			/**
			 * @Context
			 */
			Context cThyme = new Context();
			cThyme.setVariable("linkVerify","http://localhost:8080/Register/verifyNewAccount?codeLink=" + recoverInfo.getLinkRecover());
			cThyme.setVariable("timeExpiered", recoverInfo.getTimeExpired());
			//			
			String htmlContent = templateEngine.process("EmailSending/RegisterAccount.html", cThyme);
			bodyMail.setContent(htmlContent,"text/html");
			//
			Multipart mContentPart = new MimeMultipart();
			mContentPart.addBodyPart(bodyMail);
			//
			mimeMessage.setContent(mContentPart);
			Transport.send(mimeMessage);
		} catch (Exception e) {
			System.out.println("ERROR sendMailRegisterVerification");
			System.out.println(e.getMessage());
		}
	}
	
	public void supplierSendMailVerify(SendingMail mailSend, RecoverInfo recoverInfo) {
		try {
			mailSender = declareStaticValue();
			templateEngine = new SpringTemplateEngine();
			//
			ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
			templateResolver.setPrefix("/templates/");
			templateResolver.setSuffix(".html");
			templateResolver.setTemplateMode(TemplateMode.HTML);
			templateResolver.setCharacterEncoding("UTF-8");
			templateResolver.setOrder(0);
			templateEngine.setTemplateResolver(templateResolver);
			templateEngine.addTemplateResolver(templateResolver);
			//
			Message mimeMessage = mailSender.createMimeMessage();
			mimeMessage.setFrom(new InternetAddress("ngtrqvinh1810@gmail.com",false));
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailSend.getSentTo()));
			mimeMessage.setSubject(mailSend.getMailSubject());
			mimeMessage.setSentDate(new java.util.Date());
			//
			MimeBodyPart bodyMail = new MimeBodyPart();
			/**
			 * @Context
			 */
			Context cThyme = new Context();
			cThyme.setVariable("linkVerify","http://localhost:8080/Supplier/Register/verifyNewAccount?codeLink=" + recoverInfo.getLinkRecover());
			cThyme.setVariable("timeExpiered", recoverInfo.getTimeExpired());
			//			
			String htmlContent = templateEngine.process("EmailSending/RegisterAccount.html", cThyme);
			bodyMail.setContent(htmlContent,"text/html");
			//
			Multipart mContentPart = new MimeMultipart();
			mContentPart.addBodyPart(bodyMail);
			//
			mimeMessage.setContent(mContentPart);
			Transport.send(mimeMessage);
		} catch (Exception e) {
			System.out.println("ERROR supplierSendMailVerify");
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * @author Thuan
	 */
	public void T_SendChangeMailRequest(SendingMail mailSend, RecoverInfo recoverInfo) {
		try {
			mailSender = declareStaticValue();
			templateEngine = new SpringTemplateEngine();
			//
			ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
			templateResolver.setPrefix("/templates/");
			templateResolver.setSuffix(".html");
			templateResolver.setTemplateMode(TemplateMode.HTML);
			templateResolver.setCharacterEncoding("UTF-8");
			templateResolver.setOrder(0);
			templateEngine.setTemplateResolver(templateResolver);
			templateEngine.addTemplateResolver(templateResolver);
			//
			Message mimeMessage = mailSender.createMimeMessage();
			mimeMessage.setFrom(new InternetAddress("thuan181898@gmail.com",false));
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailSend.getSentTo()));
			mimeMessage.setSubject(mailSend.getMailSubject());
			mimeMessage.setSentDate(new java.util.Date());
			//
			MimeBodyPart bodyMail = new MimeBodyPart();
			/**
			 * @Context
			 */
			Context cThyme = new Context();
			cThyme.setVariable("linkVerify","http://localhost:8080/Supplier/VerifyNewEmail?codeLink=" + recoverInfo.getLinkRecover());
			cThyme.setVariable("timeExpiered", recoverInfo.getTimeExpired());
			//			
			String htmlContent = templateEngine.process("EmailSending/RequestChangeMail.html", cThyme);
			bodyMail.setContent(htmlContent,"text/html");
			//
			Multipart mContentPart = new MimeMultipart();
			mContentPart.addBodyPart(bodyMail);
			//
			mimeMessage.setContent(mContentPart);
			Transport.send(mimeMessage);
		} catch (Exception e) {
			System.out.println("ERROR sendChangeMailRequest");
			System.out.println(e.getMessage());
		}
	}
	
	public void T_SendMailForgotPassword(SendingMail mailSend, RecoverInfo recoverInfo) {
		try {
			mailSender = declareStaticValue();
			templateEngine = new SpringTemplateEngine();
			//
			ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
			templateResolver.setPrefix("/templates/");
			templateResolver.setSuffix(".html");
			templateResolver.setTemplateMode(TemplateMode.HTML);
			templateResolver.setCharacterEncoding("UTF-8");
			templateResolver.setOrder(0);
			templateEngine.setTemplateResolver(templateResolver);
			templateEngine.addTemplateResolver(templateResolver);
			//
			Message mimeMessage = mailSender.createMimeMessage();
			mimeMessage.setFrom(new InternetAddress("ngtrqvinh1810@gmail.com",false));
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailSend.getSentTo()));
			mimeMessage.setSubject(mailSend.getMailSubject());
			mimeMessage.setSentDate(new java.util.Date());
			//
			MimeBodyPart bodyMail = new MimeBodyPart();
			/**
			 * @Context
			 */
			Context cThyme = new Context();
			cThyme.setVariable("linkRecover","http://localhost:8080/Supplier/Recover/VerifyRecoverPass?codeRecover=" + recoverInfo.getLinkRecover());
			cThyme.setVariable("timeExpiered", recoverInfo.getTimeExpired());
			//			
			String htmlContent = templateEngine.process("EmailSending/ResetPassword.html", cThyme);
			bodyMail.setContent(htmlContent,"text/html");
			//
			Multipart mContentPart = new MimeMultipart();
			mContentPart.addBodyPart(bodyMail);
			//
			mimeMessage.setContent(mContentPart);
			Transport.send(mimeMessage);
		} catch (Exception e) {
			System.out.println("ERROR sendMailForgotPassword");
			System.out.println(e.getMessage());
		}
	}

	private JavaMailSender declareStaticValue() {
		JavaMailSender msDeclare = new JavaMailSender() {

			@Override
			public void send(SimpleMailMessage... simpleMessages) throws MailException {
				// TODO Auto-generated method stub

			}

			@Override
			public void send(SimpleMailMessage simpleMessage) throws MailException {
				// TODO Auto-generated method stub

			}

			@Override
			public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {
				// TODO Auto-generated method stub

			}

			@Override
			public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
				// TODO Auto-generated method stub

			}

			@Override
			public void send(MimeMessage... mimeMessages) throws MailException {
				// TODO Auto-generated method stub

			}

			@Override
			public void send(MimeMessage mimeMessage) throws MailException {
				// TODO Auto-generated method stub

			}

			@Override
			public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
				return null;
			}

			@Override
			public MimeMessage createMimeMessage() {
				// SetUp Properties
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");
				//
				Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("ngtrqvinh1810@gmail.com", "qwfyzvdutrurdmqh");
					}
				});
				MimeMessage msgReturn = new MimeMessage(session);
				return msgReturn;
			}
		};
		return msDeclare;

	}
}
