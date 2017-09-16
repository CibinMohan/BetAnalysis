/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import dto.AnalysisDTO;
import dto.BetDTO;
import dto.SingletonDTO;
import dto.UserDTO;
import ejb.AnalysisFacadeRemote;
import ejb.BetFacadeRemote;
import ejb.BetuserFacadeRemote;
import ejb.MailSingletonRemote;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import ejb.StatelessBetuserFacadeRemote;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import pogo.BetCart;

/**
 *
 * @author cibin
 */
@Named(value = "myBean")

@SessionScoped

public class MyBean implements Serializable {

    @Resource(mappedName = "jms/queue9")
    private Queue queue9;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    @EJB
    private MailSingletonRemote mailSingleton;

    @EJB
    private AnalysisFacadeRemote analysisFacade;

    @EJB
    private BetuserFacadeRemote betuserFacade;

    @EJB
    private BetFacadeRemote betFacade;

    @EJB
    private StatelessBetuserFacadeRemote statelessBetuserFacade;
    

    private String loginName;
    private String pass;
    private String betAmount;
    private String analysisName;
    private String frgEmail;
    private String availability;
    private String availabilityName;
    private int sNumber=0;
    private int count;
    private double betmoney=0.0;
    private double rechargeAmount;
    private BetDTO betDTO = new BetDTO();
    private UserDTO userDTO = new UserDTO();
    private UserDTO signupDTO = new UserDTO();
    private UserDTO checkSignupDTO = new UserDTO();
    private List<BetDTO> listBetDTO = new ArrayList<>();
    private List<BetDTO> userBetDTO = new ArrayList<>();
    private BetDTO adminBetDTO = new BetDTO();
    private List<BetCart> cartList = new ArrayList<>();
    private List<AnalysisDTO> analysisDTO = new ArrayList<>();
    private List<AnalysisDTO> previousDTO = new ArrayList<>();
    private SingletonDTO singletonDTO = new SingletonDTO();
    
    public void countDB()
    {
        count = betFacade.count();
    }
    public String guestEntry()
    {
    loginName = "Guest";
    findUser();
    listBetItems();
    return "Menu";
    }
    public void findUser()
    {
        userDTO.setUsername(loginName);
        userDTO= betuserFacade.findBetUser(userDTO);
            
    }
    
    public void listBetItems()
    {
        listBetDTO= new ArrayList<>();
        listBetDTO = betFacade.listBets();
        findUser();
    }
    public void rechargeUserAccount()
    {
        userDTO.setLocalac(rechargeAmount);
        userDTO = betuserFacade.rechargeUser(userDTO);
    }
    
    public void addUserBet(String betId)
    {
        betDTO = new BetDTO();
        betDTO.setBetid(betId);
        for(BetDTO b : listBetDTO)
        {
            if(betId.equals(b.getBetid()))
            {
                betDTO.setMoney(b.getMoney());
            }
        }    
        
        cartList = betFacade.addToCart(betDTO);
        betmoney=0.0;
    }
    
    public void confirmBet()
    {
        userDTO = betuserFacade.confitmBet(cartList,userDTO);
        scheduleMail(cartList, userDTO);
        cartList=new ArrayList<>();
         betFacade.removeAllLogout();
        String conf =userDTO.getCarddetails();
    }
    
    public void scheduleMail(List<BetCart> mailCartList,UserDTO mailUserDTO)
    {

        for(BetCart b: mailCartList)
        {
            singletonDTO.setDate(b.getDate());
            singletonDTO.setTime(Integer.toString((b.getTime())));
            singletonDTO.setEmail(mailUserDTO.getEmail());
            singletonDTO.setYear(b.getTeam());
            int num = (Integer.parseInt(b.getBetid()));
            singletonDTO.setNumber(num);
            mailSingleton.seceduleUserMail(singletonDTO);
            sNumber++;
        }
    }
    public void removeFromList(String betId)
    {
        try {
                 
                 cartList = betFacade.removeBetList(betId);    
                 
        } catch (EJBException e) {
        }
        
    }
    
    public String signup()
    {
        String data = statelessBetuserFacade.addUser(signupDTO);
        return data;
    }
    
    public void checkAvail()
    {
        availability = statelessBetuserFacade.checkAvailability(checkSignupDTO.getUsername());
    }

    
    public void findUserBet()
    {
        userBetDTO = betFacade.findUserBet(userDTO);
    }
    
    public void claimReward()
    {
        findUserBet();
        userBetDTO = betuserFacade.claimUserReward(userBetDTO,userDTO);
        findUserBet();
    }
    
    
    public void removeUsersBet(String betId)
    {
        try {
            BetDTO dto = new BetDTO();
            dto.setBetid(betId);
            userBetDTO = betuserFacade.removeBetList(userBetDTO,dto,userDTO);    
        } catch (EJBException e) {
        }
        
    }
    
    public void editUsersBet(String betId)
    {
        try {
            
            BetDTO dto = new BetDTO();
            dto.setBetid(betId);
            userBetDTO = betuserFacade.editBetList(userBetDTO,dto,userDTO);    
        } catch (EJBException e) {
        }
        
    }
    
    public String analysisBet()
    {
        analysisDTO= analysisFacade.findAllBet();
        analysisResult();
        return "Analysis";
    }

    public void analysisResult()
    {
        previousDTO= analysisFacade.findPrevBet();
    }
    
    public void filterByTeam()
    {
        AnalysisDTO dto = new AnalysisDTO();
        dto.setTeam(analysisName);
        analysisDTO= analysisFacade.filterByTeam(analysisDTO,dto);
        previousDTO= analysisFacade.filterByTeam(previousDTO,dto);
    }
    
    public String loginCheck()
    {
        userDTO.setUsername(loginName);
        userDTO.setPassword(pass);
        String st = betuserFacade.checkUserPassword(userDTO);
        findUser();
        listBetItems();
        return st;
    }
    
    
    public void forgotPassword()
    {
        if(frgEmail != null){
            sendJMSMessageToQueue9(frgEmail);
        }
    }
    
    
    public String logout()
    {
        this.loginName= null;
        this.pass=null;
        this.betAmount=null;
        this.analysisName=null;
        this.frgEmail=null;
        this.sNumber=0;
        this.count=0;
        this.betmoney=0.0;
        this.rechargeAmount=0.0;
        this.betDTO = new BetDTO();
        this.userDTO = new UserDTO();
        this.signupDTO = new UserDTO();
        this.listBetDTO = new ArrayList<>();
        this.userBetDTO = new ArrayList<>();
        this.cartList = new ArrayList<>();
        betFacade.removeAllLogout();
        this.analysisDTO = new ArrayList<>();
        this.previousDTO = new ArrayList<>();
        this.singletonDTO = new SingletonDTO();
        // FacesContext context;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("MyBean", null);
        return "Home";
    }
    
   
    public void betStatusUpdate()
    {
        betFacade.updateStatus(adminBetDTO);
    }
    
    public void addNewBet()
    {
        betFacade.addNewBet(adminBetDTO);
    }
    public List<AnalysisDTO> getPreviousDTO() {
        return previousDTO;
    }

    public void setPreviousDTO(List<AnalysisDTO> previousDTO) {
        this.previousDTO = previousDTO;
    }
    
   
    public AnalysisFacadeRemote getAnalysisFacade() {
        return analysisFacade;
    }

    public void setAnalysisFacade(AnalysisFacadeRemote analysisFacade) {
        this.analysisFacade = analysisFacade;
    }
    
    public String getBetAmount() {
        return betAmount;
    }

    public String getAvailabilityName() {
        return availabilityName;
    }

    public void setAvailabilityName(String availabilityName) {
        this.availabilityName = availabilityName;
    }

    public List<BetCart> getCartList() {
        return cartList;
    }

    public List<BetDTO> getUserBet() {
        return userBetDTO;
    }

    public void setUserBet(List<BetDTO> userBet) {
        this.userBetDTO = userBet;
    }

    public void setCartList(List<BetCart> cartList) {
        this.cartList = cartList;
    }

    public void setBetAmount(String betAmount) {
        this.betAmount = betAmount;
    }

   
    

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int getCount() {
        return count;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BetDTO getBetDTO() {
        return betDTO;
    }

    public void setBetDTO(BetDTO betDTO) {
        this.betDTO = betDTO;
    }

    public List<AnalysisDTO> getAnalysisDTO() {
        return analysisDTO;
    }

    public void setAnalysisDTO(List<AnalysisDTO> analysisDTO) {
        this.analysisDTO = analysisDTO;
    }

    public List<BetDTO> getListBet() {
        return listBetDTO;
    }

    public void setListBet(List<BetDTO> listBet) {
        this.listBetDTO = listBet;
    }

    public double getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(double rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public double getBetmoney() {
        return betmoney;
    }

    public void setBetmoney(double betmoney) {
        this.betmoney = betmoney;
    }

    public String getAnalysisName() {
        return analysisName;
    }


    public void setAnalysisName(String analysisName) {
        this.analysisName = analysisName;
    }

    public UserDTO getSignupDTO() {
        return signupDTO;
    }

    public void setSignupDTO(UserDTO signupDTO) {
        this.signupDTO = signupDTO;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    private void sendJMSMessageToQueue9(String messageData) {
        context.createProducer().send(queue9, messageData);
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getFrgEmail() {
        return frgEmail;
    }

    public void setFrgEmail(String frgEmail) {
        this.frgEmail = frgEmail;
    }

    public BetDTO getAdminBetDTO() {
        return adminBetDTO;
    }

    public void setAdminBetDTO(BetDTO adminBetDTO) {
        this.adminBetDTO = adminBetDTO;
    }

    public UserDTO getCheckSignupDTO() {
        return checkSignupDTO;
    }

    public void setCheckSignupDTO(UserDTO checkSignupDTO) {
        this.checkSignupDTO = checkSignupDTO;
    }

   

}
