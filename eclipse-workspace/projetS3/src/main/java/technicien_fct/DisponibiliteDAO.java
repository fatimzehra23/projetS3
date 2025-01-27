package technicien_fct;

import java.util.List;

public interface DisponibiliteDAO {
    // Récupérer la liste des disponibilités des techniciens
    List<TechnicienFctBean> getDisponibilites();

    // Mettre à jour la disponibilité d'un technicien
    void updateDisponibilite(int idTech, Disponibilite disponibilite) throws DAOException;

    
    // Récupérer la disponibilité d'un technicien par ID
    Disponibilite getDisponibiliteByTechId(int idTech) throws DAOException;
    
    TechnicienFctBean getTechnicienByEmail(String email) throws Exception;
    Disponibilite getDisponibiliteByEmail(String email) throws Exception;
    void updateDisponibiliteByEmail(String email, Disponibilite disponibilite) throws Exception;
}