/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.BetDTO;
import dto.SingletonDTO;
import dto.UserDTO;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pogo.BetCart;

/**
 *
 * @author cibin
 */
@Stateful
public class BetuserFacade extends AbstractFacade<Betuser> implements ejb.BetuserFacadeRemote {

    @EJB
    private MailSingletonRemote mailSingleton;

    

    @PersistenceContext(unitName = "EAP-ejbPU")
    private EntityManager em;
    private static String mailId = "cibinmohan@gmail.com"; 
    private static String password = "221beminem"; //GMail password 
    private Betuser user = new Betuser();
    private Betuser confirmUser= new Betuser();
    private UserDTO userDTOLoc= new UserDTO();
    List<ScheduleExpression>scheduleList = new ArrayList<>();

    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BetuserFacade() {
        super(Betuser.class);
    }

    /**
     *
     * @param userDTO
     * @return
     */
    @Override
    public UserDTO findBetUser(UserDTO userDTO)
    {
            List<Betuser> betUser =new ArrayList<>();
            betUser = findAll();
            //Betuser user = new Betuser();
            try {
            
            for(Betuser user : betUser)
            {
                if(user.getUsername().toLowerCase().startsWith(userDTO.getUsername().toLowerCase()))
                {
                    userDTOLoc.setUserid(user.getUserid());
                    userDTOLoc.setUsername(user.getUsername());
                    if(userDTO.getUsername().equals("Guest"))
                    {
                        return userDTOLoc;
                    }
                    userDTOLoc.setPhone(user.getPhone());
                    userDTOLoc.setBet1(user.getBet1());
                    userDTOLoc.setBet1st(user.getBet1st());
                    userDTOLoc.setBet2(user.getBet2());
                    userDTOLoc.setBet2st(user.getBet2st());
                    userDTOLoc.setBet3(user.getBet3());
                    userDTOLoc.setBet3st(user.getBet3st());
                    userDTOLoc.setCarddetails(user.getCarddetails());
                    userDTOLoc.setCardid(user.getCardid());
                    userDTOLoc.setEmail(user.getEmail());
                    userDTOLoc.setLocalac(user.getLocalac());
                    userDTOLoc.setMessagestat(user.getMessagestat());
            
                }
            }
            
        } catch (EJBException e) {
        }
            return userDTOLoc;
    }
    
    
    @Override
    public void seceduleUserMail(SingletonDTO singletonDTO)
 {
     int newTime = Integer.parseInt(singletonDTO.getTime())-2;
     String time=Integer.toString(newTime);
     String dt=singletonDTO.getDate();
     System.out.println(dt);
     String[] shapes = dt.split("/");
     String email =singletonDTO.getEmail();
     String day=shapes[0];
     String month=shapes[1];
     String year=shapes[2];  
     String team = singletonDTO.getYear();
     ScheduleExpression schedule = new ScheduleExpression();
     schedule.month(month).dayOfMonth(day);
     schedule.hour(time).minute("*").second("9,15,45");
     scheduleList.add(schedule);
     mailSingleton.addSchedule(email, team);
     
 }

    @Override
    public List<ScheduleExpression> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<ScheduleExpression> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @Override
     public UserDTO rechargeUser(UserDTO userDTO)
     {
            try {
             
            if(userDTO.getUsername().startsWith("Guest"))
            {
             userDTO.setLocalac(0.0);
             return userDTO;
            }
            else{
            user =find(userDTO.getUserid());
            double amount = userDTO.getLocalac();
            double balance = user.getLocalac();
            user.setLocalac(amount+balance);
            userDTO.setLocalac(amount+balance);
            return userDTO;
            }
            
         } catch (EJBException e) {
         }
         return userDTO;
     }
     
    /**
     *
     * @param cartList
     * @param userDTO
     * @return
     */
    @Override
     public UserDTO confitmBet(List<BetCart> cartList, UserDTO userDTO)
     {
         user =new Betuser();
         user= find(userDTO.getUserid());
         try {
             
         if(userDTO.getUsername().startsWith("Guest"))
         {
             userDTOLoc.setCarddetails("yoyAreGuest");
         }
         else{
         for(BetCart b : cartList)
         {
             if(user.getLocalac()>= b.getStake())
             {
             if ("null".equals(user.getBet1())){
                 user.setBet1(b.getBetid());;
                 user.setBet1st(b.getStake());
                 user.setLocalac((user.getLocalac())-(b.getStake()));
                 userDTOLoc.setBet1(b.getBetid());;
                 userDTOLoc.setBet1st(b.getStake());
                 userDTOLoc.setLocalac((user.getLocalac())-(b.getStake()));
             }
          
         
             else if ("null".equals(user.getBet2()))
             {
                 user.setBet2(b.getBetid());
                 user.setBet2st(b.getStake());
                 user.setLocalac((user.getLocalac())-(b.getStake()));
                 userDTOLoc.setBet2(b.getBetid());;
                 userDTOLoc.setBet2st(b.getStake());
                 userDTOLoc.setLocalac((user.getLocalac())-(b.getStake()));
             }
             
             else if ("null".equals(user.getBet3()))
             {
                 user.setBet3(b.getBetid());
                 user.setBet3st(b.getStake());
                 user.setLocalac((user.getLocalac())-(b.getStake()));
                 userDTOLoc.setBet3(b.getBetid());;
                 userDTOLoc.setBet3st(b.getStake());
                 userDTOLoc.setLocalac((user.getLocalac())-(b.getStake()));
             }
             
             else 
             {
                 userDTOLoc.setCarddetails("NoSpace");
             }
             }
             else
             {
                 userDTOLoc.setCarddetails("NoBalance");
             }
             em.merge(user);
             String uNam = user.getEmail();
         }
         confirmUser= user;
     userDTOLoc.setCarddetails("betSuccess");
     }
         } catch (Exception e) {
         }
     return userDTOLoc;
     }
     
     public Betuser confirmedBet()
     {
         return confirmUser;
     }
     
    /**
     *
     * @param userBet
     * @param bDTO
     * @param betId
     * @param userDTO
     * @return
     */
    @Override
     public List<BetDTO> removeBetList(List<BetDTO> userBet, BetDTO bDTO, UserDTO userDTO)
     {
        user = new Betuser(); 
        user = find(userDTO.getUserid());
        String betId= bDTO.getBetid();
        List<BetDTO> newDTO= new ArrayList<>();
        try {         
        for(BetDTO b: userBet)
        {
            
            if(b.getBetid().equals(betId))
            {
                if(betId.equals(user.getBet1()))
                {
                    user.setBet1("null");
                    double balance =user.getBet1st()+user.getLocalac();
                    user.setLocalac(balance);
                    user.setBet1st(0.0);
                    edit(user);
                }
                else if(betId.equals(user.getBet2()))
                {
                    user.setBet2("null");
                    user.setLocalac(user.getBet2st()+user.getLocalac());
                    user.setBet2st(0.0);
                    edit(user);
                }
                else if(betId.equals(user.getBet3()))
                {
                    user.setBet3("null");
                    user.setLocalac(user.getBet3st()+user.getLocalac());
                    user.setBet3st(0.0);
                    edit(user);
                }
                
            }
            
                else
                {
                    newDTO.add(b);
                }
                
        }
             em.merge(user);
             
         } catch (EJBException e) {
         } 
        return newDTO;
     }
     
    /**
     *
     * @param userBet
     * @param bDTO
     * @param betId
     * @param userDTO
     * @return
     */
    @Override
     public List<BetDTO> editBetList(List<BetDTO> userBet, BetDTO bDTO, UserDTO userDTO)
     {
        Betuser user = find(userDTO.getUserid());
        String betId= bDTO.getBetid();
        BetDTO newB = new BetDTO();
         try {
             
        for(BetDTO b: userBet)
        {
            if(b.getBetid().equals(betId))
            {
                if(betId.equals(user.getBet1()))
                {
                    user.setLocalac(user.getBet1st()+user.getLocalac());
                    user.setBet1st(b.getMoney());
                    user.setLocalac(user.getLocalac()-b.getMoney());
                    edit(user);  
                }
                else if(betId.equals(user.getBet2()))
                {
                    user.setLocalac(user.getBet2st()+user.getLocalac());
                    user.setBet2st(b.getMoney());
                    user.setLocalac(user.getLocalac()-b.getMoney());
                    edit(user);
                }
                if(betId.equals(user.getBet3()))
                {
                    user.setLocalac(user.getBet3st()+user.getLocalac());
                    user.setBet3st(b.getMoney());
                    user.setLocalac(user.getLocalac()-b.getMoney());
                    edit(user);
                }
                
            }
            edit(user);
        }
        
         } catch (EJBException e) {
         }
        return userBet;
      
     }
     
    /**
     *
     * @param userBet
     * @param userDTO
     * @return
     */
    @Override
        public List<BetDTO> claimUserReward(List<BetDTO> userBet, UserDTO userDTO)
        {
            List<BetDTO> retBet = new ArrayList<>();
            try {
                
            for(BetDTO b: userBet)
            {
                
                if(b.getStatus()==1)
                {
                    String[] amount = b.getBetamo().split(":");
                    double stake1= Double.parseDouble(amount[0]);
                    double stake2= Double.parseDouble(amount[1]);
                    double stake=b.getMoney();
                    double winAmount = (stake2/stake1)*stake;
                    Betuser claimUser= find(userDTO.getUserid());
                    String betId=b.getBetid();
                    if(betId.equals(claimUser.getBet1()))
                    {
                    claimUser.setLocalac(winAmount+claimUser.getLocalac());
                    claimUser.setBet1("null");
                    claimUser.setBet1st(0.0);
                    
                    }
                     else if(betId.equals(claimUser.getBet2()))
                    {
                    claimUser.setLocalac(winAmount+claimUser.getLocalac());
                    claimUser.setBet2("null");
                    claimUser.setBet2st(0.0);

                    }
                    else if(betId.equals(claimUser.getBet3()))
                    {
                    claimUser.setLocalac(winAmount+claimUser.getLocalac());
                    claimUser.setBet3("null");
                    claimUser.setBet3st(0.0);
                    
}
                   
                }
                else
                    retBet.add(b);
            }
            
            } catch (EJBException e) {
            }
            return retBet;
        }

        /**
     *
     * @param userDTO
     * @return
     */
    @Override
        public String checkUserPassword(UserDTO userDTO)
            {
                try {
               String status;
               List<Betuser> passCheckUser = new ArrayList<>();
               passCheckUser = findAll();
               for(Betuser b: passCheckUser)
               {
                   if(b.getUsername().toLowerCase().startsWith(userDTO.getUsername().toLowerCase()))
                   {
                       if(userDTO.getPassword().equals(b.getPassword()))
                       {
                           return "Menu";
                       }
                   
                   }
               }
               
               return "wrongPass";
               } catch (EJBException e) {
                   return "wrongPass";
               }
            }
        
    /**
     *
     * @param userDTO
     * @return
     */
      
    /**
     *
     * @param userEmail
     */
    @Override
        public String forgotUserPass(String userEmail) {
            String newPass = null;
            List<Betuser> forgUser = new ArrayList<>();
            forgUser = findAll();
            //Betuser user = new Betuser();
            UserDTO userDTOLoc= new UserDTO();
            try {
            
            for(Betuser user : forgUser)
            {
                if(user.getEmail().equals(userEmail))
                {
                    newPass = user.getEmail();
                    
                }
            }
            return newPass;
        } catch (EJBException e) {
        }
            return newPass;
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
                
 
