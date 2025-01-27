package technicien;

import java.util.List;

public interface TechnicienDAO {

    // Ajouter un technicien
    void addTechnicien(TechnicienBean technicien) throws DAOException;

    // Récupérer la liste de tous les techniciens
    List<TechnicienBean> getAllTechniciens() throws DAOException;

    // Récupérer un technicien par son ID
    TechnicienBean getTechnicienById(int idTech) throws DAOException;

    // Mettre à jour un technicien existant
    void updateTechnicien(TechnicienBean technicien) throws DAOException;

    // Supprimer un technicien par son ID
    void deleteTechnicien(int idTech) throws DAOException;
}
