package reclamations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EvaluationDaoImpl implements EvaluationDAO {
    private static final Logger LOGGER = Logger.getLogger(EvaluationDaoImpl.class.getName());
    private DAOFactory daoFactory;

    public EvaluationDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void enregistrerEvaluation(int idInterv, int evaluation, String commentaire) throws SQLException {
        String sql = "UPDATE intervention SET evaluation = ?, commentaire = ? WHERE idInterv = ?";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, evaluation);
            stmt.setString(2, commentaire);
            stmt.setInt(3, idInterv);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 0) {
                LOGGER.warning("Aucune ligne mise à jour. Vérifiez l'ID d'intervention : " + idInterv);
                throw new SQLException("Mise à jour échouée, aucune ligne affectée.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'enregistrement de l'évaluation", e);
            throw e;
        }
    }
    public void enregistrerEvaluation1(int idInterv, int evaluation, String commentaire) throws SQLException {
        String sql = "UPDATE intervention SET evaluation = ?, commentaire = ? WHERE idInterv = ?";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, evaluation);
            stmt.setString(2, commentaire);
            stmt.setInt(3, idInterv);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Aucune ligne mise à jour. Vérifiez que l'ID d'intervention est correct : " + idInterv);
            }
        }
    }


    @Override
    public InterventionBean getInterventionById(int idInterv) throws SQLException {
        String sql = "SELECT * FROM intervention WHERE idInterv = ?";
        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idInterv);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new InterventionBean(
                    );
                }
            }
        }
        return null;
    }
}