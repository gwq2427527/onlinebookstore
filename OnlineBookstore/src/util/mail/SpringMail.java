/**
 * Copyright (c) 2012 by nrp 
 * All rights reserved.
 */
package util.mail;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;


public class SpringMail {

	private String type;

	
	public SpringMail() {

	}


	public SpringMail(String type) {
		this.type = type;
	}



	public void sendMimeMail(final File file, final Mail mailBean) throws IOException {
		MailConfigBean config = new MailConfigBean(type);
		System.out.println(config);
		final String userName = config.getUserName();
		String password = config.getPassword();
		String host = config.getHost();
		final String from = javax.mail.internet.MimeUtility.encodeText(config.getFrom());
		JavaMailSenderImpl jmail = new JavaMailSenderImpl();
		jmail.setHost(host);
		jmail.setUsername(userName);
		jmail.setPassword(password);

		Properties pp = new Properties();
		pp.setProperty("mail.smtp.auth", "true");
		jmail.setJavaMailProperties(pp);
		MimeMessagePreparator mimeMail = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMess) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMess, true, "GBK");

				message.setTo(mailBean.getTo());
				message.setSubject(mailBean.getTitle());
				message.setFrom(new InternetAddress(from + " <" + userName + ">"));
				message.setText(mailBean.getContext(), true);
				if (file != null && file.exists()) {
					message.addAttachment(mailBean.getAttName(), file);
				}
				mimeMess.setSentDate(new Date());
			}
		};
		jmail.send(mimeMail);
	}

	public void sendMimeMail(final File[] files, final String[] fileNames, final Mail mailBean) throws IOException {
		MailConfigBean config = new MailConfigBean(type);
		final String userName = config.getUserName();
		String password = config.getPassword();
		String host = config.getHost();
		final String from = javax.mail.internet.MimeUtility.encodeText(config.getFrom());
		JavaMailSenderImpl jmail = new JavaMailSenderImpl();
		jmail.setHost(host);
		jmail.setUsername(userName);
		jmail.setPassword(password);
		Properties pp = new Properties();
		pp.setProperty("mail.smtp.auth", "true");
		jmail.setJavaMailProperties(pp);
		MimeMessagePreparator mimeMail = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMess) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMess, true, "GBK");

				message.setTo(mailBean.getTo());
				message.setSubject(mailBean.getTitle());
				message.setFrom(new InternetAddress(from + " <" + userName + ">"));
				message.setText(mailBean.getContext(), true);
				if (mailBean.getBcc() != null) {
					message.setBcc(mailBean.getBcc());
				}
				String fileName = null;
				for (int i = 0; i < files.length; i++) {
					File file = files[i];
					if (file == null || !file.exists())
						continue;
					if (fileNames == null || fileNames[i] == null) {
						fileName = file.getName();
					} else {
						fileName = fileNames[i];
					}
					message.addAttachment(fileName, file);
				}
				mimeMess.setSentDate(new Date());

			}
		};
		jmail.send(mimeMail);
	}


	public void sendMimeMail(final Mail mailBean) throws IOException {
		MailConfigBean config = new MailConfigBean(type);
		final String userName = config.getUserName();
		String password = config.getPassword();
		String host = config.getHost();
		final String from = javax.mail.internet.MimeUtility.encodeText(config.getFrom());
		JavaMailSenderImpl jmail = new JavaMailSenderImpl();
		jmail.setHost(host);
		jmail.setUsername(userName);
		jmail.setPassword(password);
		Properties pp = new Properties();
		pp.setProperty("mail.smtp.auth", "true");
		jmail.setJavaMailProperties(pp);
		MimeMessagePreparator mimeMail = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMess) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMess, true, "GBK");

				message.setTo(mailBean.getTo());
				message.setSubject(mailBean.getTitle());
				message.setFrom(new InternetAddress(from + " <" + userName + ">"));
				message.setText(mailBean.getContext(), true);
				if (mailBean.getBcc() != null) {
					message.setBcc(mailBean.getBcc());
				}
				mimeMess.setSentDate(new Date());
			}
		};
		jmail.send(mimeMail);
	}

	public static void main(String[] args) {
		try {
			SpringMail smail = new SpringMail();

			Mail mail = new Mail();
			mail.setContext("Hello");
			mail.setTitle("Title");
			mail.setTo("guoweiqi0504@outlook.com");

			smail.sendMimeMail(null, mail);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}