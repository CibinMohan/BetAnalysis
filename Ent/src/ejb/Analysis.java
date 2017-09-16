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
@Table(name = "ANALYSIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analysis.findAll", query = "SELECT a FROM Analysis a"),
    @NamedQuery(name = "Analysis.findByAnalysisid", query = "SELECT a FROM Analysis a WHERE a.analysisid = :analysisid"),
    @NamedQuery(name = "Analysis.findByTeam", query = "SELECT a FROM Analysis a WHERE a.team = :team"),
    @NamedQuery(name = "Analysis.findByVsteam", query = "SELECT a FROM Analysis a WHERE a.vsteam = :vsteam"),
    @NamedQuery(name = "Analysis.findByTime", query = "SELECT a FROM Analysis a WHERE a.time = :time"),
    @NamedQuery(name = "Analysis.findByDate", query = "SELECT a FROM Analysis a WHERE a.date = :date"),
    @NamedQuery(name = "Analysis.findByPreviousresult", query = "SELECT a FROM Analysis a WHERE a.previousresult = :previousresult")})
public class Analysis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ANALYSISID")
    private String analysisid;
    @Column(name = "TEAM")
    private String team;
    @Column(name = "VSTEAM")
    private String vsteam;
    @Column(name = "TIME")
    private String time;
    @Column(name = "DATE")
    private String date;
    @Column(name = "PREVIOUSRESULT")
    private String previousresult;

    public Analysis() {
    }

    public Analysis(String analysisid) {
        this.analysisid = analysisid;
    }

    public String getAnalysisid() {
        return analysisid;
    }

    public void setAnalysisid(String analysisid) {
        this.analysisid = analysisid;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPreviousresult() {
        return previousresult;
    }

    public void setPreviousresult(String previousresult) {
        this.previousresult = previousresult;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (analysisid != null ? analysisid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Analysis)) {
            return false;
        }
        Analysis other = (Analysis) object;
        if ((this.analysisid == null && other.analysisid != null) || (this.analysisid != null && !this.analysisid.equals(other.analysisid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Analysis[ analysisid=" + analysisid + " ]";
    }
    
}
