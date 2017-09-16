/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Properties;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author cibin
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue9"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class forMail implements MessageListener {

    @EJB
    private BetuserFacadeRemote betuserFacade;
    private static String mailId = "cibinmohan@gmail.com"; 
    private static String password = "221beminem"; //GMail password 
    
    public forMail() {
    }
    
    @Override
    public void onMessage(Message message) {
        TextMessage textmsg  = (TextMessage)message;
        try {
            String userEmail = textmsg.getText();
            String pass = "cibin";
            //pass = betuserFacade.forgotUserPass(userEmail);
            String[] rec = {userEmail};
            sendMail(mailId,password,rec,"New pass",pass);
        
        } catch (Exception e) {
            
        }
        
        
    }
    
      private static void sendMail(String mailId, String password, String[] rece, String text, String random) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", mailId);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session bean = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(bean);

        try {
            msg.setFrom(new InternetAddress(mailId));
            InternetAddress[] toAddress = new InternetAddress[rece.length];

            for (int i = 0; i < rece.length; i++) {
                toAddress[i] = new InternetAddress(rece[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                msg.addRecipient(javax.mail.Message.RecipientType.TO, toAddress[i]);
            }

            msg.setSubject(text);
            msg.setText(random);
            Transport transport = bean.getTransport("smtp");
            transport.connect(host, mailId, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
        } catch (MessagingException me) {
        }
        }
}
