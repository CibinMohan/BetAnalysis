/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.BetDTO;
import dto.SingletonDTO;
import dto.UserDTO;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.ScheduleExpression;
import pogo.BetCart;

/**
 *
 * @author cibin
 */
@Remote
public interface BetuserFacadeRemote {

    void create(Betuser betuser);

    void edit(Betuser betuser);

    void remove(Betuser betuser);

    Betuser find(Object id);

    List<Betuser> findAll();

    List<Betuser> findRange(int[] range);

    int count();

    public UserDTO findBetUser(UserDTO userDTO);

    public UserDTO rechargeUser(UserDTO userDTO);

    public UserDTO confitmBet(List<BetCart> cartList,UserDTO userDTO );

    public List<BetDTO> removeBetList(List<BetDTO> userBet, BetDTO betId, UserDTO userDTO);

    public List<BetDTO> editBetList(List<BetDTO> userBet, BetDTO betId, UserDTO userDTO);

    public List<BetDTO> claimUserReward(List<BetDTO> userBet, UserDTO userDTO);

    public String checkUserPassword(UserDTO userDTO);

    public String forgotUserPass(String userEmail);

    public void seceduleUserMail(SingletonDTO singletonDTO);

    public List<ScheduleExpression> getScheduleList();



    

    
}
