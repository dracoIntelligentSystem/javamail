import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Test {

	public void test(String sender, String receiver, String subject, String text){
    Properties props = System.getProperties();
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    props.put("mail.smtp.starttls.enable", true); // added this line
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.user", "username");
    props.put("mail.smtp.password", "password");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", true);



    Session session = Session.getInstance(props,null);
    MimeMessage message = new MimeMessage(session);

    System.out.println("Port: "+ session.getProperty("mail.smtp.port"));

    // Create the email addresses involved
    try {
        InternetAddress from = new InternetAddress(sender);
        message.setSubject(subject);
        message.setFrom(from);
        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));

        // Create a multi-part to combine the parts
        Multipart multipart = new MimeMultipart("alternative");

//        // Create your text message part
        BodyPart messageBodyPart = new MimeBodyPart();
//        messageBodyPart.setText(t);
//
//        // Add the text part to the multipart
//        multipart.addBodyPart(messageBodyPart);

        // Create the html part
        messageBodyPart = new MimeBodyPart();
//        String htmlMessage = "Our html text";
        messageBodyPart.setContent(text, "text/html");


        // Add html part to multi part
        multipart.addBodyPart(messageBodyPart);

        // Associate multi-part with message
        message.setContent(multipart);

        // Send message
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.Xxxx.com", "username@domani.xx", "passwordmail");
        System.out.println("Transport: "+transport.toString());
        transport.sendMessage(message, message.getAllRecipients());


    } catch (AddressException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (MessagingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
	
	public static void main(String args[]) {
		Test n = new Test();
		n.test("usernameSENDER@domani.xx", 
				"usernameRECEIVER@domani.xx", 
				"primo messaggio astratto", 
				"Questo è il secondo messaggio mail che mando. "
				);
	}
}