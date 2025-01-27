package reclamations;

import java.util.List;

public interface InterventionDAO {
    List<InterventionBean> getAllInterventions(String email);

}
