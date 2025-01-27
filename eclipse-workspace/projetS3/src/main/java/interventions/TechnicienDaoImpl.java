package interventions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TechnicienDaoImpl implements TechnicienDAO {
    private DAOFactory daoFactory;

    public TechnicienDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<TechnicienBean> getAllTechniciens() throws DAOException {
        List<TechnicienBean> techniciens = new ArrayList<>();
        String query = "SELECT * FROM technicien";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                TechnicienBean technicien = new TechnicienBean();
                technicien.setIdTech(resultSet.getInt("idTech"));
                technicien.setNomTech(resultSet.getString("nomTech"));
                technicien.setSpecialite(resultSet.getString("specialite"));
                technicien.setDisponibilite(resultSet.getString("disponibilite"));
                technicien.setEmail(resultSet.getString("email"));
                technicien.setTelephone(resultSet.getString("telephone"));
                techniciens.add(technicien);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des techniciens.", e);
        }
        return techniciens;
    }

    @Override
    public List<TechnicienBean> getTechniciensBySpecialite(String specialite) throws DAOException {
        List<TechnicienBean> techniciens = new ArrayList<>();
        String query = "SELECT * FROM technicien WHERE specialite = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, specialite);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TechnicienBean technicien = new TechnicienBean();
                    technicien.setIdTech(resultSet.getInt("idTech"));
                    technicien.setNomTech(resultSet.getString("nomTech"));
                    technicien.setSpecialite(resultSet.getString("specialite"));
                    technicien.setDisponibilite(resultSet.getString("disponibilite"));
                    technicien.setEmail(resultSet.getString("email"));
                    technicien.setTelephone(resultSet.getString("telephone"));
                    techniciens.add(technicien);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des techniciens par spécialité.", e);
        }
        return techniciens;
   
    }
}

