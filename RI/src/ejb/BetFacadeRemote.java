/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.BetDTO;
import dto.UserDTO;
import java.util.List;
import javax.ejb.Remote;
import pogo.BetCart;

/**
 *
 * @author cibin
 */
@Remote
public interface BetFacadeRemote {

    void create(Bet bet);

    void edit(Bet bet);

    void remove(Bet bet);

    Bet find(Object id);

    List<Bet> findAll();

    List<Bet> findRange(int[] range);

    int count();
    
    List<BetDTO> listBets();

    public List<BetCart> addToCart(BetDTO betDTO);

    public List<BetCart> removeBetList(String betId);

    public List<BetDTO> findUserBet(UserDTO userDTO);

    public List<BetCart> removeAllLogout();

    public void updateStatus(BetDTO adminBetDTO);

    public void addNewBet(BetDTO adminBetDTO);
    
}
