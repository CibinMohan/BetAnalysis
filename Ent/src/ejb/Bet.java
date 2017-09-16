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
@Table(name = "BET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bet.findAll", query = "SELECT b FROM Bet b"),
    @NamedQuery(name = "Bet.findByBetid", query = "SELECT b FROM Bet b WHERE b.betid = :betid"),
    @NamedQuery(name = "Bet.findByDetails", query = "SELECT b FROM Bet b WHERE b.details = :details"),
    @NamedQuery(name = "Bet.findByDate", query = "SELECT b FROM Bet b WHERE b.date = :date"),
    @NamedQuery(name = "Bet.findByTime", query = "SELECT b FROM Bet b WHERE b.time = :time"),
    @NamedQuery(name = "Bet.findByTeam", query = "SELECT b FROM Bet b WHERE b.team = :team"),
    @NamedQuery(name = "Bet.findByVsteam", query = "SELECT b FROM Bet b WHERE b.vsteam = :vsteam"),
    @NamedQuery(name = "Bet.findByBetamo", query = "SELECT b FROM Bet b WHERE b.betamo = :betamo"),
    @NamedQuery(name = "Bet.findByStatus", query = "SELECT b FROM Bet b WHERE b.status = :status")})
public class Bet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BETID")
    private String betid;
    @Column(name = "DETAILS")
    private String details;
    @Column(name = "DATE")
    private String date;
    @Column(name = "TIME")
    private Integer time;
    @Column(name = "TEAM")
    private String team;
    @Column(name = "VSTEAM")
    private String vsteam;
    @Column(name = "BETAMO")
    private String betamo;
    @Column(name = "STATUS")
    private Integer status;

    public Bet() {
    }

    public Bet(String betid) {
        this.betid = betid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (betid != null ? betid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bet)) {
            return false;
        }
        Bet other = (Bet) object;
        if ((this.betid == null && other.betid != null) || (this.betid != null && !this.betid.equals(other.betid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Bet[ betid=" + betid + " ]";
    }
    
}
