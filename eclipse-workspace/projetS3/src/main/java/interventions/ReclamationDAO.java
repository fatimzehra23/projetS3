package interventions;

import java.util.List;

public interface ReclamationDAO {
    
    List<ReclamationBean> searchReclamations(String searchQuery) throws DAOException;
    
    void deleteReclamation(int idRecl) throws DAOException;  // Updated with boolean parameter
    
    ReclamationBean getReclamationById(int idRecl) throws DAOException;
    
    List<ReclamationBean> getAllReclamations() throws DAOException;
    
    ReclamationBean getReclamationDetailsForIntervention(int idRecl) throws DAOException;

    void createIntervention(InterventionBean intervention) throws DAOException;


}
