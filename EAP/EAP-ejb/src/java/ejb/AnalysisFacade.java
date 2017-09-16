/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.AnalysisDTO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cibin
 */
@Stateless
public class AnalysisFacade extends AbstractFacade<Analysis> implements ejb.AnalysisFacadeRemote {

    @PersistenceContext(unitName = "EAP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnalysisFacade() {
        super(Analysis.class);
    }
    
    @Override
    public List<AnalysisDTO> findAllBet()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date Ndate = new Date();
        String date = dateFormat.format(Ndate);
        int year =Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day =Integer.parseInt(date.substring(8, 10));
        int Time = Integer.parseInt(date.substring(11, 13));
       
        List<AnalysisDTO> anDTO = new ArrayList<>();
            List<Analysis> analysis = new ArrayList<>();
            analysis = findAll();
            for(Analysis b : analysis)
            {
               int gameDay=Integer.parseInt(b.getDate().substring(0, 2));
                int gameMonth=Integer.parseInt(b.getDate().substring(3, 5));
                int gameYear=Integer.parseInt(b.getDate().substring(6));
                int gameTime=Integer.parseInt(b.getTime());
                if(gameYear>year){
                    anDTO.add(analysiscalc(b));
                    }
                else if(gameYear==year && gameMonth>month)
                {
                    anDTO.add(analysiscalc(b));
                }
                else if(gameYear==year && gameMonth==month && gameDay>day)
                {
                    anDTO.add(analysiscalc(b));
                }
                else if(gameYear==year && gameMonth==month && gameDay==day && gameTime>=Time)
                {
                    
                    anDTO.add(analysiscalc(b));
                }
            }
            return anDTO;
        }
    
    @Override
    public List<AnalysisDTO> findPrevBet()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date Ndate = new Date();
        String date = dateFormat.format(Ndate);
        int year =Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day =Integer.parseInt(date.substring(8, 10));
        int Time = Integer.parseInt(date.substring(11, 13));
      
        List<AnalysisDTO> anDTO = new ArrayList<>();
            List<Analysis> analysis = new ArrayList<>();
            analysis = findAll();
            for(Analysis b : analysis)
            {
               int gameDay=Integer.parseInt(b.getDate().substring(0, 2));
                int gameMonth=Integer.parseInt(b.getDate().substring(3, 5));
                int gameYear=Integer.parseInt(b.getDate().substring(6));
                int gameTime=Integer.parseInt(b.getTime());
                if(gameYear<year){
                    anDTO.add(analysiscalc(b));
                    }
                else if( gameYear==year  && gameMonth<month )
                {
                    anDTO.add(analysiscalc(b));
                }
                else if(gameYear==year && gameMonth==month&&gameDay<day )
                {
                    anDTO.add(analysiscalc(b));
                }
                else if(gameYear==year && gameMonth==month && gameDay==day && gameTime<=Time )
                {
                    
                    anDTO.add(analysiscalc(b));
                }
            }
            return anDTO;
        }
    private AnalysisDTO analysiscalc(Analysis b)
    {
                    AnalysisDTO dto = new AnalysisDTO();
                    dto.setAnalysisid(b.getAnalysisid());
                    dto.setDate(b.getDate());
                    dto.setPreviousresult(b.getPreviousresult());
                    dto.setTeam(b.getTeam());
                    dto.setTime(b.getTime());
                    dto.setVsteam(b.getVsteam());
                    return dto;
    }
    
    @Override
        public List<AnalysisDTO> filterByTeam(List<AnalysisDTO> analysisDTO, AnalysisDTO dto)
        {
            List<AnalysisDTO> teamDTO = new ArrayList<>();
            if(dto.getTeam()!=null)
            {
            for(AnalysisDTO b: analysisDTO)
            if(dto.getTeam().toLowerCase().equals(b.getTeam().toLowerCase()))
            {
                teamDTO.add(b);
            }
            else if(dto.getTeam().toLowerCase().equals(b.getVsteam().toLowerCase())){
                teamDTO.add(b);
            }
            }
            return teamDTO;
        }
}
