package technicien_fct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interventions.InterventionBean;
import reclamations.DAOException;

public class TechnicienFctDaoImpl implements TechnicienFctDAO {
    private DAOFactory daoFactory;

    // Constructeur pour initialiser le DAOFactory
    public TechnicienFctDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<InterventionBean> getInterventionsByTechnician(String email) {
        List<InterventionBean> interventions = new ArrayList<>();
        

        // Requête pour récupérer le nom du technicien en utilisant l'email
        String queryNomTech = "SELECT nomTech FROM technicien WHERE email = ?";
        
        String nomTech = null;
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatementNomTech = connection.prepareStatement(queryNomTech)) {
            
            preparedStatementNomTech.setString(1, email);  // Utilisation de l'email du technicien
            ResultSet resultSetNomTech = preparedStatementNomTech.executeQuery();
            
            if (resultSetNomTech.next()) {
                nomTech = resultSetNomTech.getString("nomTech");  // Récupérer le nom du technicien
                
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération du nom du technicien.", e);
        }
        
        // Si le nom du technicien a été trouvé, récupérer ses interventions
        if (nomTech != null) {
            String queryInterventions = "SELECT * FROM intervention WHERE nomTech = ?";
            
            try (Connection connection = daoFactory.getConnection();
                 PreparedStatement preparedStatementInterventions = connection.prepareStatement(queryInterventions)) {
                
                preparedStatementInterventions.setString(1, nomTech);  // Utilisation du nom du technicien récupéré
                ResultSet resultSet = preparedStatementInterventions.executeQuery();
                
                while (resultSet.next()) {
                    InterventionBean intervention = new InterventionBean();
                    intervention.setPriority(resultSet.getString("priority"));
                    intervention.setIdInterv(resultSet.getInt("idInterv"));
                    intervention.setNomTech(resultSet.getString("nomTech"));
                    intervention.setDescription(resultSet.getString("description"));
                    intervention.setDateCreation(resultSet.getDate("dateCreation"));
                    intervention.setDateTerminaison(resultSet.getDate("dateTerminaison"));
                    intervention.setStatut(resultSet.getString("statut"));
                    interventions.add(intervention);
                }
            } catch (SQLException e) {
                throw new DAOException("Erreur lors de la récupération des interventions.", e);
            }
        }
        
        return interventions;
    }
    
    @Override
    public void updateIntervention(InterventionBean intervention) throws SQLException {
    	
    	
    	String sql = "UPDATE intervention SET dateTerminaison = ?, statut = ? WHERE idInterv = ?";
    	try (Connection conn = daoFactory.getConnection();
    	     PreparedStatement stmt = conn.prepareStatement(sql)) {
    	    stmt.setDate(1, new java.sql.Date(intervention.getDateTerminaison().getTime())); // dateTerminaison à l'index 1
    	    stmt.setString(2, intervention.getStatut()); // statut à l'index 2
    	    stmt.setInt(3, intervention.getIdInterv()); // idInterv à l'index 3
    	    stmt.executeUpdate();
    	} catch (SQLException e) {
    	    e.printStackTrace();  // Afficher l'exception SQL pour le débogage
    	    throw new DAOException("Erreur lors de la mise à jour de l'intervention.", e);
    	}

    }}