/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.UserDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author cibin
 */
@Remote
public interface StatelessBetuserFacadeRemote {

    void create(Betuser betuser);

    void edit(Betuser betuser);

    void remove(Betuser betuser);

    Betuser find(Object id);

    List<Betuser> findAll();

    List<Betuser> findRange(int[] range);

    int count();

    public String addUser(UserDTO signupDTO);

    public String checkAvailability(String name);
    
}
