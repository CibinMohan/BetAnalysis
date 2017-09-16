/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.BetDTO;
import dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pogo.BetCart;

/**
 *
 * @author cibin
 */
@Stateful
public class BetFacade extends AbstractFacade<Bet> implements ejb.BetFacadeRemote {

  
    @PersistenceContext(unitName = "EAP-ejbPU")
    private EntityManager em;
    private BetDTO betDTO = new BetDTO();
    private List<BetDTO> listBet = new ArrayList<>();
    private List<BetCart> cartList = new ArrayList<>();
    private List<BetDTO> betByUser = new ArrayList<>();

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BetFacade() {
        super(Bet.class);
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<BetDTO> listBets()
    {
        List<Bet> bet = new ArrayList<>();
        bet = findAll();
        listBet = new ArrayList<>();
        for (Bet b : bet)
        {
            betDTO = new BetDTO();
            betDTO.setBetid(b.getBetid());
            betDTO.setDetails(b.getDetails());
            betDTO.setDate(b.getDate());
            betDTO.setTime(b.getTime());
            betDTO.setTeam(b.getTeam());
            betDTO.setVsteam(b.getVsteam());
            betDTO.setBetamo(b.getBetamo());
            betDTO.setStatus(b.getStatus());
            listBet.add(betDTO);
        }
        return listBet;
    }

    /**
     *
     * @param betDTO
     * @return
     */
    @Override
    public List<BetCart> addToCart(BetDTO betDTO)
    {
        Bet bet = find(betDTO.getBetid());
        BetCart newCart= new BetCart();
        newCart.setBetid(bet.getBetid());
        newCart.setBetamo(bet.getBetamo());
        newCart.setDetails(bet.getDetails());
        newCart.setDate(bet.getDate());
        newCart.setTime(bet.getTime());
        newCart.setTeam(bet.getTeam());
        newCart.setVsteam(bet.getVsteam());
        newCart.setBetamo(bet.getBetamo());
        newCart.setStatus(bet.getStatus());
        newCart.setStake(betDTO.getMoney());
        cartList.add(newCart);
        return cartList;
    }
    @Override
    public List<BetCart> removeBetList( String betId)
    {
        List<BetCart> cartListNew = new ArrayList<>();
        try {
            
        for(BetCart b: cartList)
        {
            if(b.getBetid().equals(betId))
            {
                cartList.remove(b);
                return cartList;
            }
            else
              cartListNew.add(b);
        }
        
        } catch (EJBException e) {
        }
        
        return cartListNew;
    }
    
    @Override
    public List<BetCart> removeAllLogout()
    {
        List<BetCart> cartListNew = new ArrayList<>();
        cartList.clear();
        return cartListNew;
    }
    
    
    @Override
    public List<BetDTO> findUserBet(UserDTO userDTO)
     {
         List<Bet> localBet= new ArrayList<>();
         betByUser = new ArrayList<>();
         localBet = findAll();
         try {
         if(userDTO.getUsername().startsWith("Guest"))
         {
             return betByUser;
         }
         else{
        
         for(Bet b : localBet)
         {
             if(userDTO.getBet1().equals(b.getBetid()))
             {
                 BetDTO dto = new BetDTO();
                 dto.setBetid(b.getBetid());
                 dto.setBetamo(b.getBetamo());
                 dto.setDate(b.getDate());
                 dto.setTime(b.getTime());
                 dto.setDetails(b.getDetails());
                 dto.setTeam(b.getTeam());
                 dto.setVsteam(b.getVsteam());
                 dto.setStatus(b.getStatus());
                 dto.setMoney(userDTO.getBet1st());
                 betByUser.add(dto);
             }
             if(userDTO.getBet2().equals(b.getBetid()))
             {
                 BetDTO dto = new BetDTO();
                 dto.setBetid(b.getBetid());
                 dto.setBetamo(b.getBetamo());
                 dto.setDate(b.getDate());
                 dto.setTime(b.getTime());
                 dto.setDetails(b.getDetails());
                 dto.setTeam(b.getTeam());
                 dto.setVsteam(b.getVsteam());
                 dto.setStatus(b.getStatus());
                 dto.setMoney(userDTO.getBet2st());
                 betByUser.add(dto);
             }if(userDTO.getBet3().equals(b.getBetid()))
             {
                 BetDTO dto = new BetDTO();
                 dto.setBetid(b.getBetid());
                 dto.setBetamo(b.getBetamo());
                 dto.setDate(b.getDate());
                 dto.setTime(b.getTime());
                 dto.setDetails(b.getDetails());
                 dto.setTeam(b.getTeam());
                 dto.setVsteam(b.getVsteam());
                 dto.setStatus(b.getStatus());
                 dto.setMoney(userDTO.getBet3st());
                 betByUser.add(dto);
             }
         }
         }
         } catch (EJBException e) {
         }
     
         return betByUser;
     }
    
    @RolesAllowed({"admin"})
    @Override
    public void updateStatus(BetDTO adminBetDTO)
    {
        try {
            
                Bet adBet = find(adminBetDTO.getBetid());
                adBet.setStatus(adminBetDTO.getStatus());
                em.persist(adBet);
        
        } catch (EJBException e) {
        }
    }
    
    
    @RolesAllowed({"admin"})
    @Override
        public void addNewBet(BetDTO adminBetDTO)
        {
            
            try {
                
            Bet adminBet = new Bet();
            adminBet.setBetamo(adminBetDTO.getBetamo());
            adminBet.setBetid(adminBetDTO.getBetid());
            adminBet.setDate(adminBetDTO.getDate());
            adminBet.setDetails(adminBetDTO.getDetails());
            adminBet.setTeam(adminBetDTO.getTeam());
            adminBet.setTime(adminBetDTO.getTime());
            adminBet.setVsteam(adminBetDTO.getVsteam());
            adminBet.setStatus(0);
            create(adminBet);
            
            } catch (EJBException e) {
            }
        }

}
