package technicien_fct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisponibiliteDAOImpl implements DisponibiliteDAO {
    private DAOFactory daoFactory;

    public DisponibiliteDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<TechnicienFctBean> getDisponibilites() throws DAOException {
        List<TechnicienFctBean> techniciens = new ArrayList<>();
        String query = "SELECT * FROM technicien";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                TechnicienFctBean technicien = new TechnicienFctBean();
                technicien.setIdTech(resultSet.getInt("idTech"));
                technicien.setNomTech(resultSet.getString("nomTech"));
                technicien.setSpecialite(resultSet.getString("specialite"));
                technicien.setEmail(resultSet.getString("email"));
                technicien.setPassword(resultSet.getString("password"));
                technicien.setDisponibilite(Disponibilite.valueOf(resultSet.getString("disponibilite")));
                techniciens.add(technicien);
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des disponibilités.", e);
        }

        return techniciens;
    }

    @Override
    public void updateDisponibilite(int idTech, Disponibilite disponibilite) throws DAOException {
        String query = "UPDATE technicien SET disponibilite = ? WHERE idTech = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, disponibilite.name());
            preparedStatement.setInt(2, idTech);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la mise à jour de la disponibilité.", e);
        }
    }

    

    @Override
    public Disponibilite getDisponibiliteByTechId(int idTech) throws DAOException {
        String query = "SELECT disponibilite FROM technicien WHERE idTech = ?";
        Disponibilite disponibilite = null;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idTech);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    disponibilite = Disponibilite.valueOf(resultSet.getString("disponibilite"));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération de la disponibilité.", e);
        }

        return disponibilite;
    }

    @Override
    public Disponibilite getDisponibiliteByEmail(String email) throws DAOException {
        String query = "SELECT disponibilite FROM technicien WHERE email = ?";
        Disponibilite disponibilite = null;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String disponibiliteStr = resultSet.getString("disponibilite");
                    if (disponibiliteStr != null) {
                        try {
                            // Mapping "INDISPONIBLE" à "NON_DISPONIBLE"
                            if ("INDISPONIBLE".equals(disponibiliteStr)) {
                                disponibilite = Disponibilite.INDISPONIBLE;
                            } else {
                                disponibilite = Disponibilite.valueOf(disponibiliteStr);
                            }
                        } catch (IllegalArgumentException e) {
                            // Gestion des erreurs
                            disponibilite = Disponibilite.INDISPONIBLE;
                        }
                    }
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération de la disponibilité du technicien par email.", e);
        }

        return disponibilite;
    }

    @Override
    public void updateDisponibiliteByEmail(String email, Disponibilite disponibilite) throws DAOException {
        String query = "UPDATE technicien SET disponibilite = ? WHERE email = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, disponibilite.name());
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la mise à jour de la disponibilité du technicien par email.", e);
        }
    }

    
    
    
    
    
    
    
    
    @Override
    public TechnicienFctBean getTechnicienByEmail(String email) throws DAOException {
        String query = "SELECT * FROM technicien WHERE email = ?";
        TechnicienFctBean technicien = null;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    technicien = new TechnicienFctBean();
                    technicien.setIdTech(resultSet.getInt("idTech"));
                    technicien.setNomTech(resultSet.getString("nomTech"));
                    technicien.setSpecialite(resultSet.getString("specialite"));
                    technicien.setEmail(resultSet.getString("email"));
                    technicien.setPassword(resultSet.getString("password"));
                    technicien.setDisponibilite(Disponibilite.valueOf(resultSet.getString("disponibilite")));
                } else {
                    throw new DAOException("Aucun technicien trouvé avec l'email : " + email);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération du technicien par email.", e);
        }

        return technicien;
    }
}