/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.UserDTO;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cibin
 */
@Stateless
public class StatelessBetuserFacade extends AbstractFacade<Betuser> implements ejb.StatelessBetuserFacadeRemote {

    @PersistenceContext(unitName = "EAP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatelessBetuserFacade() {
        super(Betuser.class);
    }
    
    @Override
    public String addUser(UserDTO signupDTO)
    {
        
        String newdat="";
        try {
        List<Betuser> userList= findAll();
          {
            String userid= signupDTO.getUserid();
            Betuser user = new Betuser(userid);
            user.setUsername(signupDTO.getUsername());
            user.setPhone(signupDTO.getPhone());
            String newPass = signupDTO.getPassword();
            user.setPassword(newPass);
            user.setEmail(signupDTO.getEmail());
            user.setCardid(signupDTO.getCardid());
            user.setCarddetails(signupDTO.getCarddetails());
            user.setBet1("null");
            user.setBet1st(0.0);
            user.setBet2("null");
            user.setBet2st(0.0);
            user.setBet3("null");
            user.setBet3st(0.0);
            user.setLocalac(5.0);
            user.setMessagestat("0");
            create(user);
            newdat="signupSuccess";
           
          }
        
        } catch (EJBException e) {
            return "Values Not Correct";
        }
        return newdat;
    }
        @Override
    public String checkAvailability(String name)
    {
        List<Betuser> userList= findAll();
        for(Betuser b: userList)
        {
            if(b.getUsername().toLowerCase().startsWith(name.toLowerCase()))
            {
                return "Not available";
            }
            
        }
        return "available";
    }

}
