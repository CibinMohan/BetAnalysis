/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.SingletonDTO;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cibin
 */
@Stateful
public class MyuserFacade extends AbstractFacade<Myuser> implements MyuserFacadeRemote {

    @PersistenceContext(unitName = "EAP-ejbPU")
    private EntityManager em;
    private String randomNo;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MyuserFacade() {
        super(Myuser.class);
    }
    
        @Override
    public Myuser getUserQ(String userid) {
        try {
           
            return find(userid);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     *
     * @param userId
     * @return
     */
    @Override
    public String getSeq(String userId) {
        try {
            
        Myuser user = find(userId);
        String seq = user.getSecqn();
        return seq;
        
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public Boolean getSeqAns(String userId,String ans) {
        try {
            
        Myuser user = find(userId);
        String seq = user.getSecans();
        if(seq.equals(ans))
        return true;
        else
        return false;    
        
        } catch (Exception e) {
            return null;
        }
    }

   
    /**
     *
     * @return
     */
    @Override
    public String randomNumber() {
        int length =8;
        String characters="sadsfgwt53uyrjsrtjsrrwrwtetawtwaetwetwetgwag";
        SecureRandom rng = new SecureRandom();
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        int random = (int )(Math.random() * 50 + 1);
        String random2 = new String(text);
        String random3 = random + random2;
        return random3;
    }
    @Override
    public String generateRandomNumber()
    {
        String rndNm = randomNumber();
        randomNo=rndNm;
        return rndNm;
    }

    @Override
    public Boolean checkNumber(String number) {
        if (randomNo.equals(number)) {
            return true;
            
        } else {
            return false;
        }
       
    }

    @Override
    public String changePassword(String userId, String seqAns) {
        {
        try{
        Myuser user = find(userId);
        user.setPassword(seqAns);
        edit(user);
        
        return"SUCCESS";
        }catch(Exception e){
        return "FAIL";
        }
    }

}
}