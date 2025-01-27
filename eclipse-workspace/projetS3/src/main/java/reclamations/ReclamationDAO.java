package reclamations;

import java.util.List;

public interface ReclamationDAO {

    void addReclamation(ReclamationBean reclamation) throws DAOException;
    List<ReclamationBean> searchReclamations(String searchQuery) throws DAOException;
    void deleteReclamation(int idRecl) throws DAOException; 
    void updateReclamation(ReclamationBean reclamation) throws DAOException;
    ReclamationBean getReclamationById(int idRecl) throws DAOException;
    List<ReclamationBean> getAllReclamations(String email);
    

}