/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author cibin
 */
@Remote
public interface MyuserFacadeRemote {

    void create(ejb.Myuser myuser);

    void edit(ejb.Myuser myuser);

    void remove(ejb.Myuser myuser);

    ejb.Myuser find(Object id);

    List<Myuser> findAll();

    List<Myuser> findRange(int[] range);

    int count();

    public String changePassword(String userId, String seqAns);

    public Boolean checkNumber(String number);

    public String generateRandomNumber();

    public String randomNumber();

    public Boolean getSeqAns(String userId, String ans);

    public String getSeq(String userId);

    public Myuser getUserQ(String userid);
    
}
