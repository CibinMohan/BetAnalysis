/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.SingletonDTO;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.ScheduleExpression;

/**
 *
 * @author cibin
 */
@Remote
public interface MailSingletonRemote {


   
    public void addSchedule(String email, String team);

    public String seceduleUserMail(SingletonDTO singletonDTO);
    
}
