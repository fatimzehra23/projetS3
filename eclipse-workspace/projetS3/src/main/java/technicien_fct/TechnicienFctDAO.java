package technicien_fct;

import java.sql.SQLException;
import java.util.List;

import interventions.InterventionBean;

public interface TechnicienFctDAO {
    List<InterventionBean> getInterventionsByTechnician(String email);
    void updateIntervention(InterventionBean intervention) throws SQLException;

}
