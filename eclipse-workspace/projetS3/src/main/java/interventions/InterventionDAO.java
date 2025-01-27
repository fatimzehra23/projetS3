package interventions;

import java.util.List;

public interface InterventionDAO {

    int addIntervention(InterventionBean intervention) throws DAOException;
    void deleteIntervention(int idInterv) throws DAOException; 
    void updateIntervention(InterventionBean intervention) throws DAOException;
    InterventionBean getInterventionById(int idInterv) throws DAOException;
    List<InterventionBean> getAllInterventions() throws DAOException;
    boolean assignTechnicianToIntervention(int idInterv, String nomTech);
    TechnicienBean getTechnicienById(int idTech);
    public List<InterventionBean> getTerminatedInterventions() throws Exception ;


}
