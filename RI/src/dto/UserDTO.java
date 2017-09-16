/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author cibin
 */
public class UserDTO {
    private static final long serialVersionUID = 1L;
    
    private String userid;
    private String username;
    private String password;
    private String bet1;
    private String bet2;
    private String bet3;
    private String email;
    private String messagestat;
    private Integer phone;
    private String cardid;
    private String carddetails;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double bet3st;
    private Double bet1st;
    private Double bet2st;
    private Double localac;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getMessagestat() {
        return messagestat;
    }

    public void setMessagestat(String messagestat) {
        this.messagestat = messagestat;
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
    
}
