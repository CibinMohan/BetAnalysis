/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.SingletonDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.mail.Message;
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
@Singleton
@Startup
public class MailSingleton implements MailSingletonRemote {

    @EJB
    private BetuserFacadeRemote betuserFacade1;


 @Resource
    private TimerService timerService;
    private Timer[] timer = new Timer[100];
    private List<TimerService> tsL = new ArrayList<>();
    private static String mailId = "cibinmohan@gmail.com"; 
    private static String password = "221beminem"; //GMail password
    private String time;
    private String day;
    private String date;
    private String month;
    private String year;
    private String email;
    private String hour;
    private String minute;
    private String team;
    private Timer timer1;
    private Timer timer2;
    private int n =0;
    private List<TimerConfig> tC= new ArrayList<>();
    private List<ScheduleExpression> scheduleList = new ArrayList<>();

    /**
     *
     * @param singletonDTO
     * @return
     */
 @Override
 public String seceduleUserMail(SingletonDTO singletonDTO)
 {
     time=singletonDTO.getTime();
     if(time.length()==4)
     {
     hour = time.substring(0, 2);
     minute = time.substring(2);
         System.err.println("mccsadasdsasd"+hour);
     }
     else
     {
     hour = time.substring(0, 1);
     minute = time.substring(1);
         System.err.println("mccsadasdsasd"+hour);
     }
     String dt=singletonDTO.getDate();
     System.out.println(dt);
     String[] shapes = dt.split("/");
     email =singletonDTO.getEmail();
     day=shapes[0];
     month=shapes[1];
     year=shapes[2];  
     team = singletonDTO.getYear();
     n= singletonDTO.getNumber();
     init();
     return "mailForward";
     
 }

 @Override
 public void addSchedule(String email, String team)
 {
     scheduleList = betuserFacade1.getScheduleList();
     time ="1";
     mailId = email;
     this.team = team;
     init();
 }
 @PostConstruct
    private void init() {
        if(time!=null){
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo("CalendarProgTimerDemo_Info");
        ScheduleExpression schedule = new ScheduleExpression();
        schedule.month(month).dayOfMonth(day);
        schedule.hour(hour).minute(minute).second("1");
        tC.add(timerConfig);
        scheduleList.add(schedule);    
         timer[n]= timerService.createCalendarTimer(schedule);
      
    }}
 
    @Timeout
    public void execute(Timer timer) {
        System.err.println("*****");
        String[] rec = {mailId};
        sendMail(mailId,password,rec,"WoW","Its the final hour for make change on Your BET!!!!");
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
                msg.addRecipient(Message.RecipientType.TO, toAddress[i]);
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
