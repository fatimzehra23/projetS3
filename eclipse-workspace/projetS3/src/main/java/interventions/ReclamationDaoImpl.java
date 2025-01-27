package interventions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReclamationDaoImpl implements ReclamationDAO {
    private DAOFactory daoFactory;

    public ReclamationDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<ReclamationBean> getAllReclamations() throws DAOException {
        List<ReclamationBean> reclamations = new ArrayList<>();
        String query = "SELECT idRecl, sujet, description, dateCreation, batiment, etage, numChambre, email FROM reclamation";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ReclamationBean reclamation = new ReclamationBean(
                        rs.getInt("idRecl"),
                        rs.getString("sujet"),
                        rs.getString("description"),
                        rs.getDate("dateCreation"),
                        rs.getString("batiment"),
                        rs.getInt("etage"),
                        rs.getInt("numChambre"),
                        rs.getString("email")

                );
                reclamations.add(reclamation);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des réclamations.", e);
        }
        return reclamations;
    }

    @Override
    public List<ReclamationBean> searchReclamations(String searchQuery) throws DAOException {
        List<ReclamationBean> reclamations = new ArrayList<>();
        String query = "SELECT idRecl, sujet, description, dateCreation, batiment, etage, numChambre, email FROM reclamation WHERE sujet LIKE ? OR description LIKE ?";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + searchQuery + "%");
            pstmt.setString(2, "%" + searchQuery + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ReclamationBean reclamation = new ReclamationBean(
                        rs.getInt("idRecl"),
                        rs.getString("sujet"),
                        rs.getString("description"),
                        rs.getDate("dateCreation"),
                        rs.getString("batiment"),
                        rs.getInt("etage"),
                        rs.getInt("numChambre"),
                        rs.getString("email")
                        );

                reclamations.add(reclamation);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la recherche de réclamations.", e);
        }
        return reclamations;
    }

    @Override
    public void deleteReclamation(int idRecl) throws DAOException {
        // Optionally create an intervention based on this reclamation before deleting
        

        // Delete the reclamation
        String query = "DELETE FROM reclamation WHERE idRecl = ?";
        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idRecl);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la suppression de la réclamation.", e);
        }
    }

    public ReclamationBean getReclamationDetailsForIntervention(int idRecl) throws DAOException {
        try (Connection connection = daoFactory.getConnection()) {
            String query = "SELECT * FROM reclamation WHERE idRecl = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, idRecl);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        ReclamationBean reclamation = new ReclamationBean();
                        reclamation.setIdRecl(rs.getInt("idRecl"));
                        reclamation.setDescription(rs.getString("description"));
                        reclamation.setBatiment(rs.getString("batiment"));
                        reclamation.setEtage(rs.getInt("etage"));
                        reclamation.setNumChambre(rs.getInt("numChambre"));
                        reclamation.setDateCreation(rs.getDate("dateCreation"));
                        reclamation.setEmail(rs.getString("email"));
                        return reclamation;
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des détails de la réclamation.", e);
        }
        return null;
    }
    
    @Override
    public void createIntervention(InterventionBean intervention) throws DAOException {
        try (Connection connection = daoFactory.getConnection()) {
            String insertInterventionSQL = "INSERT INTO intervention (priority, description, dateCreation, nomTech, batiment, etage, numChambre, statut, email) " +
                                           "VALUES (?, ?, NOW(), ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertInterventionSQL)) {
                stmt.setString(1, intervention.getPriority());
            	stmt.setString(2, intervention.getDescription());
                stmt.setString(3, intervention.getNomTech());
                stmt.setString(4, intervention.getBatiment());
                stmt.setString(5, intervention.getEtage());
                stmt.setInt(6, intervention.getNumChambre());
                stmt.setString(7, intervention.getStatut());
                stmt.setString(8, intervention.getEmail());
                
                stmt.executeUpdate();
                
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la création de l'intervention.", e);
        }
    }


    @Override
    public ReclamationBean getReclamationById(int idRecl) {
        ReclamationBean reclamation = null;
        String query = "SELECT idRecl, sujet, description, dateCreation, batiment, etage, numChambre, email FROM reclamation WHERE idRecl = ?";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idRecl);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                reclamation = new ReclamationBean(
                    rs.getInt("idRecl"),
                    rs.getString("sujet"),
                    rs.getString("description"),
                    rs.getDate("dateCreation"),
                    rs.getString("batiment"),
                    rs.getInt("etage"),
                    rs.getInt("numChambre"),
                    rs.getString("email")
                    
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reclamation;
    }
}
