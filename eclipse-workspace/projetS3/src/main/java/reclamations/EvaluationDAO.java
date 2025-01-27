package reclamations;

import java.sql.SQLException;

public interface EvaluationDAO {
    void enregistrerEvaluation(int idInterv, int evaluation, String commentaire) throws SQLException;

    InterventionBean getInterventionById(int idInterv) throws SQLException;
}
