package reclamations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReclamationDaoImpl implements ReclamationDAO {
    private DAOFactory daoFactory;

    public ReclamationDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<ReclamationBean> getAllReclamations(String email) throws DAOException {
        List<ReclamationBean> reclamations = new ArrayList<>();
        String query = "SELECT idRecl, sujet, description, dateCreation, email, batiment, etage, numChambre FROM reclamation WHERE email = ?";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ReclamationBean reclamation = new ReclamationBean(
                    rs.getInt("idRecl"),
                    rs.getString("sujet"),
                    rs.getString("description"),
                    rs.getDate("dateCreation"),
                    email,
                    rs.getString("batiment"),
                    rs.getString("etage"),
                    rs.getInt("numChambre")
                );
                reclamations.add(reclamation);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des réclamations.", e);
        }
        return reclamations;
    }

    @Override
    public void addReclamation(ReclamationBean reclamation) {
        String sql = "INSERT INTO reclamation (sujet, description, dateCreation, email, batiment, etage, numChambre) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = daoFactory.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reclamation.getSujet());
            stmt.setString(2, reclamation.getDescription());
            stmt.setDate(3, new java.sql.Date(reclamation.getDateCreation().getTime()));
            stmt.setString(4, reclamation.getEmail());
            stmt.setString(5, reclamation.getBatiment());
            stmt.setString(6, reclamation.getEtage());
            stmt.setInt(7, reclamation.getNumChambre());
            
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Réclamation ajoutée, lignes affectées : " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();  // Afficher l'exception SQL pour le débogage
        }
    }


    @Override
    public List<ReclamationBean> searchReclamations(String searchQuery) throws DAOException {
        List<ReclamationBean> reclamations = new ArrayList<>();
        String query = "SELECT idRecl, sujet, description, dateCreation, email, batiment, etage, numChambre FROM reclamation WHERE sujet LIKE ? OR description LIKE ?";

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
                        rs.getString("email"),
                        rs.getString("batiment"),
                        rs.getString("etage"),
                        rs.getInt("numChambre")
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
        String query = "DELETE FROM reclamation WHERE idRecl = ?";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idRecl);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la suppression de la réclamation.", e);
        }
    }

    @Override
    public void updateReclamation(ReclamationBean reclamation) throws DAOException {
        String query = "UPDATE reclamation SET sujet = ?, description = ?, dateCreation = ?, email = ?, batiment = ?, etage = ?, numChambre = ? WHERE idRecl = ?";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, reclamation.getSujet());
            stmt.setString(2, reclamation.getDescription());
            stmt.setDate(3, new java.sql.Date(reclamation.getDateCreation().getTime()));
            stmt.setString(4, reclamation.getEmail());
            stmt.setString(5, reclamation.getBatiment());
            stmt.setString(6, reclamation.getEtage());
            stmt.setInt(7, reclamation.getNumChambre());
            stmt.setInt(8, reclamation.getIdRecl());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  // Afficher l'exception SQL pour le débogage
            throw new DAOException("Erreur lors de la mise à jour de la réclamation.", e);
        }
    }


    @Override
    public ReclamationBean getReclamationById(int idRecl) {
        ReclamationBean reclamation = null;
        String query = "SELECT idRecl, sujet, description, dateCreation, email, batiment, etage, numChambre FROM reclamation WHERE idRecl = ?";

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
                        rs.getString("email"),
                        rs.getString("batiment"),
                        rs.getString("etage"),
                        rs.getInt("numChambre")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reclamation;
    }
}
