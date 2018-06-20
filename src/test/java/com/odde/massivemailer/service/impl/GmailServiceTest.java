package com.odde.massivemailer.service.impl;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import com.odde.TestWithDB;
import com.odde.massivemailer.exception.EmailException;
import com.odde.massivemailer.model.Mail;
import com.odde.massivemailer.service.GMailService;
import com.odde.massivemailer.service.SMTPConfiguration;
import com.odde.massivemailer.util.NotificationUtil;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.mail.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(TestWithDB.class)
@Ignore
//TODO remove ignore
public class GmailServiceTest {

	private static String[] RECIPIENTS = new String[] { "myodde@gmail.com",
			"kit.sumate@gmail.com" };

    private Session session;

    @Before
    public void setup() {
//        session = mock(Session.class);
    }

	private Mail createEmail() {
		Mail email = new Mail();
		email.setContent("Hi Dude");
		email.setSubject("test subject");
		email.setReceipts(Arrays.asList(RECIPIENTS));
		NotificationUtil.addSentMail(email);
		return email;
	}
	
	private GMailService getGmailService(final Transport transport) {
		SMTPConfiguration config = new SMTPConfiguration("fakeUser@gmail.com", "fakeUserPassword", "smtp.gmail.com", 587);
        GMailService gmailService = new GMailService(config, session) {
			@Override
			protected Transport getTransport() {
				return transport;
			} 
		};

		return gmailService;
	}
   
	
	@Test
	public void testSend_multipleRecipients() throws EmailException, MessagingException {
		final Transport transport = mock(Transport.class);
        GMailService emailService = this.getGmailService(transport);
		Mail email = createEmail();
		email.setReceipts(Arrays.asList(RECIPIENTS));
		emailService.send(email);
		verify(transport, times(1)).connect(anyString(), anyInt(), anyString(), anyString());
		verify(transport, times(RECIPIENTS.length)).sendMessage(any(Message.class), any(Address[].class));
		verify(transport, times(1)).close();

	}
	@Test(expected = EmailException.class)
	public void testSend_failed() throws Exception {
		Transport transport = null;
        GMailService emailService = this.getGmailService(transport);
		Mail email = createEmail();
		emailService.send(email);
	}

	@Test
	public void sendEmailViaGreenMailSMTP() throws EmailException, UnknownHostException {
		//Arrange
		GreenMail greenMail = new GreenMail(new ServerSetup(3025, null, "smtp"));
		greenMail.start();
		SMTPConfiguration config = new SMTPConfiguration("fake@greenmail.com", "*******", "localhost", 3025);

        GMailService mailService = new GMailService(config, session);


        //Act
		Mail mail = createEmail();
		mailService.send(mail);

		//Assert
		assertEquals("<html><body>Hi Dude<img height=\"42\" width=\"42\" src=\"http://"+InetAddress.getLocalHost().getHostAddress()+":8070/massive_mailer/resources/images/qrcode.png?token="+mail.getSentMail().getSentMailVisits().get(0).getId()+"\"></img></body></html>", GreenMailUtil.getBody(greenMail.getReceivedMessages()[0]));
		assertEquals("<html><body>Hi Dude<img height=\"42\" width=\"42\" src=\"http://"+InetAddress.getLocalHost().getHostAddress()+":8070/massive_mailer/resources/images/qrcode.png?token="+mail.getSentMail().getSentMailVisits().get(1).getId()+"\"></img></body></html>", GreenMailUtil.getBody(greenMail.getReceivedMessages()[1]));
		greenMail.stop();
	}

    @Test
	@Ignore
    public void getEmailTest() {
        final List<Mail> mockUnreadEmails = new ArrayList<>();
        final List<Mail> mockReadEmails = new ArrayList<>();
        final List<Mail> mockEmails = new ArrayList<>();
        mockEmails.addAll(mockReadEmails);
        mockEmails.addAll(mockUnreadEmails);

        final Transport transport = mock(Transport.class);

//        when(session.get)

        GMailService emailService = this.getGmailService(transport);

        final Set<Mail> emails = emailService.getUnreadEmails();
        assertThat(emails).containsAll(mockUnreadEmails);
        assertThat(emails).doesNotContainAnyElementsOf(mockReadEmails);
    }

}
