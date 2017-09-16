/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.AnalysisDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author cibin
 */
@Remote
public interface AnalysisFacadeRemote {

    void create(Analysis analysis);

    void edit(Analysis analysis);

    void remove(Analysis analysis);

    Analysis find(Object id);

    List<Analysis> findAll();

    List<Analysis> findRange(int[] range);

    int count();

    public List<AnalysisDTO> findAllBet();

    public List<AnalysisDTO> findPrevBet();


    public List<AnalysisDTO> filterByTeam(List<AnalysisDTO> analysisDTO, AnalysisDTO dto);
    
}
