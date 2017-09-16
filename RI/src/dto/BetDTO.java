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
public class BetDTO {
    private String betid;
    private String details;
    private String date;
    private Integer time;
    private String team;
    private String vsteam;
    private String betamo;
    private Integer status;
    private double money;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getBetid() {
        return betid;
    }

    public void setBetid(String betid) {
        this.betid = betid;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getVsteam() {
        return vsteam;
    }

    public void setVsteam(String vsteam) {
        this.vsteam = vsteam;
    }

    public String getBetamo() {
        return betamo;
    }

    public void setBetamo(String betamo) {
        this.betamo = betamo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
