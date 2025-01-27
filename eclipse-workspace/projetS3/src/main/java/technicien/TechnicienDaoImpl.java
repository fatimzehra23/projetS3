package technicien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TechnicienDaoImpl implements TechnicienDAO {
    private DAOFactory daoFactory;

    public TechnicienDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }


    @Override
    public void addTechnicien(TechnicienBean technicien) {
        String sql = "INSERT INTO technicien (nomTech, specialite, disponibilite, email, telephone) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            System.out.println("Connexion établie pour ajouter un technicien");
            
            // Définir les paramètres de la requête
            statement.setString(1, technicien.getNomTech());
            statement.setString(2, technicien.getSpecialite());
            statement.setString(3, technicien.getDisponibilite());
            statement.setString(4, technicien.getEmail());
            statement.setString(5, technicien.getTelephone());

            System.out.println("Requête SQL : " + statement);

            // Exécuter la requête
            int rowsInserted = statement.executeUpdate();
            System.out.println("Lignes insérées : " + rowsInserted);

        } catch (SQLException e) {
            // Log de l'erreur
            System.err.println("Erreur SQL lors de l'ajout du technicien : " + e.getMessage());
            e.printStackTrace();
            throw new DAOException("Erreur lors de l'ajout du technicien : " + e.getMessage(), e);
        }
    }


    @Override
    public List<TechnicienBean> getAllTechniciens() throws DAOException {
        List<TechnicienBean> techniciens = new ArrayList<>();
        String sql = "SELECT * FROM technicien";
        try (Connection connection = daoFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                TechnicienBean technicien = new TechnicienBean();
                technicien.setIdTech(rs.getInt("idTech"));
                technicien.setNomTech(rs.getString("nomTech"));
                technicien.setSpecialite(rs.getString("specialite"));
                technicien.setDisponibilite(rs.getString("disponibilite"));
                technicien.setEmail(rs.getString("email"));
                technicien.setTelephone(rs.getString("telephone"));
                techniciens.add(technicien);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des techniciens : " + e.getMessage(), e);
        }
        return techniciens;
    }

    @Override
    public TechnicienBean getTechnicienById(int idTech) throws DAOException {
        String sql = "SELECT * FROM technicien WHERE idTech = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idTech);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TechnicienBean technicien = new TechnicienBean();
                    technicien.setIdTech(rs.getInt("idTech"));
                    technicien.setNomTech(rs.getString("nomTech"));
                    technicien.setSpecialite(rs.getString("specialite"));
                    technicien.setDisponibilite(rs.getString("disponibilite"));
                    technicien.setEmail(rs.getString("email"));
                    technicien.setTelephone(rs.getString("telephone"));
                    return technicien;
                } else {
                    throw new DAOException("Technicien non trouvé avec l'ID : " + idTech);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération du technicien : " + e.getMessage(), e);
        }
    }

    @Override
    public void updateTechnicien(TechnicienBean technicien) throws DAOException {
        String sql = "UPDATE technicien SET nomTech = ?, specialite = ?, disponibilite = ?, email = ?, telephone = ? WHERE idTech = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, technicien.getNomTech());
            ps.setString(2, technicien.getSpecialite());
            ps.setString(3, technicien.getDisponibilite());
            ps.setString(4, technicien.getEmail());
            ps.setString(5, technicien.getTelephone());
            ps.setInt(6, technicien.getIdTech());
            if (ps.executeUpdate() == 0) {
                throw new DAOException("Échec de la mise à jour : aucun technicien avec l'ID spécifié.");
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la mise à jour du technicien : " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteTechnicien(int idTech) throws DAOException {
        String sql = "DELETE FROM technicien WHERE idTech = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idTech);
            if (ps.executeUpdate() == 0) {
                throw new DAOException("Échec de la suppression : aucun technicien avec l'ID spécifié.");
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la suppression du technicien : " + e.getMessage(), e);
        }
    }
}
