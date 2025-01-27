package reclamations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InterventionDaoImpl implements InterventionDAO {
    private DAOFactory daoFactory;

    public InterventionDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public List<InterventionBean> getAllInterventions(String email) {
        List<InterventionBean> interventions = new ArrayList<>();
        String sql = "SELECT * FROM intervention WHERE email = ?";
        
        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                InterventionBean intervention = new InterventionBean();
                intervention.setPriority(rs.getString("priority"));
                intervention.setIdInterv(rs.getInt("idInterv"));
                intervention.setDescription(rs.getString("description"));
                intervention.setDateCreation(rs.getDate("dateCreation"));
                intervention.setDateTerminaison(rs.getDate("dateTerminaison"));
                intervention.setNomTech(rs.getString("nomTech"));
                intervention.setStatut(rs.getString("statut"));
                intervention.setBatiment(rs.getString("batiment"));
                intervention.setEtage(rs.getInt("etage"));
                intervention.setNumChambre(rs.getInt("numChambre"));
                intervention.setEmail(rs.getString("email"));
                intervention.setEvaluation(rs.getInt("evaluation"));
                intervention.setCommentaire(rs.getString("commentaire"));

                interventions.add(intervention);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return interventions;
    }
}


