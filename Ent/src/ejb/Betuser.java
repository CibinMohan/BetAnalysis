/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cibin
 */
@Entity
@Table(name = "BETUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Betuser.findAll", query = "SELECT b FROM Betuser b"),
    @NamedQuery(name = "Betuser.findByUserid", query = "SELECT b FROM Betuser b WHERE b.userid = :userid"),
    @NamedQuery(name = "Betuser.findByUsername", query = "SELECT b FROM Betuser b WHERE b.username = :username"),
    @NamedQuery(name = "Betuser.findByPassword", query = "SELECT b FROM Betuser b WHERE b.password = :password"),
    @NamedQuery(name = "Betuser.findByBet1", query = "SELECT b FROM Betuser b WHERE b.bet1 = :bet1"),
    @NamedQuery(name = "Betuser.findByBet2", query = "SELECT b FROM Betuser b WHERE b.bet2 = :bet2"),
    @NamedQuery(name = "Betuser.findByBet3", query = "SELECT b FROM Betuser b WHERE b.bet3 = :bet3"),
    @NamedQuery(name = "Betuser.findByEmail", query = "SELECT b FROM Betuser b WHERE b.email = :email"),
    @NamedQuery(name = "Betuser.findByPhone", query = "SELECT b FROM Betuser b WHERE b.phone = :phone"),
    @NamedQuery(name = "Betuser.findByCardid", query = "SELECT b FROM Betuser b WHERE b.cardid = :cardid"),
    @NamedQuery(name = "Betuser.findByCarddetails", query = "SELECT b FROM Betuser b WHERE b.carddetails = :carddetails"),
    @NamedQuery(name = "Betuser.findByBet3st", query = "SELECT b FROM Betuser b WHERE b.bet3st = :bet3st"),
    @NamedQuery(name = "Betuser.findByBet1st", query = "SELECT b FROM Betuser b WHERE b.bet1st = :bet1st"),
    @NamedQuery(name = "Betuser.findByBet2st", query = "SELECT b FROM Betuser b WHERE b.bet2st = :bet2st"),
    @NamedQuery(name = "Betuser.findByLocalac", query = "SELECT b FROM Betuser b WHERE b.localac = :localac")})
public class Betuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USERID")
    private String userid;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "BET1")
    private String bet1;
    @Column(name = "BET2")
    private String bet2;
    @Column(name = "BET3")
    private String bet3;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "MESSAGESTAT")
    private String messagestat;
    @Column(name = "PHONE")
    private Integer phone;
    @Column(name = "CARDID")
    private String cardid;
    @Column(name = "CARDDETAILS")
    private String carddetails;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BET3ST")
    private Double bet3st;
    @Column(name = "BET1ST")
    private Double bet1st;
    @Column(name = "BET2ST")
    private Double bet2st;
    @Column(name = "LOCALAC")
    private Double localac;
    
    public Betuser() {
    }

    public Betuser(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMessagestat() {
        return messagestat;
    }

    public void setMessagestat(String messagestat) {
        this.messagestat = messagestat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBet1() {
        return bet1;
    }

    public void setBet1(String bet1) {
        this.bet1 = bet1;
    }

    public String getBet2() {
        return bet2;
    }

    public void setBet2(String bet2) {
        this.bet2 = bet2;
    }

    public String getBet3() {
        return bet3;
    }

    public void setBet3(String bet3) {
        this.bet3 = bet3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getCarddetails() {
        return carddetails;
    }

    public void setCarddetails(String carddetails) {
        this.carddetails = carddetails;
    }

    public Double getBet3st() {
        return bet3st;
    }

    public void setBet3st(Double bet3st) {
        this.bet3st = bet3st;
    }

    public Double getBet1st() {
        return bet1st;
    }

    public void setBet1st(Double bet1st) {
        this.bet1st = bet1st;
    }

    public Double getBet2st() {
        return bet2st;
    }

    public void setBet2st(Double bet2st) {
        this.bet2st = bet2st;
    }

    public Double getLocalac() {
        return localac;
    }

    public void setLocalac(Double localac) {
        this.localac = localac;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Betuser)) {
            return false;
        }
        Betuser other = (Betuser) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Betuser[ userid=" + userid + " ]";
    }
    
}
