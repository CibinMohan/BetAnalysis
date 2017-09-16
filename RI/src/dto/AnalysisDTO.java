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
public class AnalysisDTO {
    
    private String analysisid;
    private String team;
    private String vsteam;
    private String time;
    private String date;
    private String previousresult;

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

    
}
