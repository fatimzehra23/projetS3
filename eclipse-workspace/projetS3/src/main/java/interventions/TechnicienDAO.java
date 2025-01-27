package interventions;

import java.util.List;

public interface TechnicienDAO {
    // Récupérer tous les techniciens
    List<TechnicienBean> getAllTechniciens() throws DAOException;

    // Récupérer les techniciens par spécialité
    List<TechnicienBean> getTechniciensBySpecialite(String specialite) throws DAOException;

    // Ajouter d'autres méthodes si nécessaire
}
