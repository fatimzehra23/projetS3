package interventions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InterventionDaoImpl implements InterventionDAO {
    private DAOFactory daoFactory;

    public InterventionDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public List<InterventionBean> getAllInterventions() throws DAOException {
        List<InterventionBean> interventions = new ArrayList<>();
        String query = "SELECT * FROM intervention";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Query executed: " + query);

            while (resultSet.next()) {
                InterventionBean intervention = new InterventionBean();
                intervention.setPriority(resultSet.getString("priority"));
                intervention.setIdInterv(resultSet.getInt("idInterv"));
                intervention.setDescription(resultSet.getString("description"));
                intervention.setDateCreation(resultSet.getDate("dateCreation"));
                intervention.setDateTerminaison(resultSet.getDate("dateTerminaison"));
                intervention.setBatiment(resultSet.getString("batiment"));
                intervention.setEtage(resultSet.getString("etage"));
                intervention.setNumChambre(resultSet.getInt("numChambre"));
                intervention.setNomTech(resultSet.getString("nomTech"));
                intervention.setStatut(resultSet.getString("statut"));
                intervention.setEvaluation(resultSet.getInt("evaluation"));
                intervention.setCommentaire(resultSet.getString("commentaire"));



                System.out.println("Intervention added: " + intervention);

                interventions.add(intervention);
            }

        } catch (SQLException e) {
            throw new DAOException("Error retrieving all interventions", e);
        }

        System.out.println("Total interventions: " + interventions.size());
        return interventions;
    }


    @Override
    public int addIntervention(InterventionBean intervention) {
        int generatedId = -1; // Valeur par défaut si aucune ligne n'est insérée
        String sql = "INSERT INTO intervention (description, dateCreation, dateTerminaison, nomTech, batiment, etage, numChambre, email, statut, priority) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Définir les paramètres de la requête dans le bon ordre
            statement.setString(1, intervention.getDescription());
            statement.setDate(2, new java.sql.Date(intervention.getDateCreation().getTime()));

            if (intervention.getDateTerminaison() != null) {
                statement.setDate(3, new java.sql.Date(intervention.getDateTerminaison().getTime()));
            } else {
                statement.setNull(3, java.sql.Types.DATE);
            }

            statement.setString(4, intervention.getNomTech());
            statement.setString(5, intervention.getBatiment());
            statement.setString(6, intervention.getEtage()); // Envoyez une chaîne
            statement.setInt(7, intervention.getNumChambre());
            statement.setString(8, intervention.getEmail());
            statement.setString(9, intervention.getStatut());
            statement.setString(10, intervention.getPriority());

            

            // Exécuter la requête
            int rowsInserted = statement.executeUpdate();
            System.out.println("Lignes insérées : " + rowsInserted);

            // Vérifier si une ligne a été insérée et récupérer l'ID généré
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1); // Récupérer l'ID généré
                        System.out.println("ID généré : " + generatedId);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
            throw new DAOException("Erreur lors de l'ajout de l'intervention", e);
        }

        return generatedId; // Retourner l'ID généré
    }
    
    @Override
    public boolean assignTechnicianToIntervention(int idInterv, String nomTech) {
        boolean success = false;
        String query = "UPDATE intervention SET nomTech = ? WHERE idInterv = ?";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nomTech);
            ps.setInt(2, idInterv);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
                System.out.println("row updated !");
                
            }else {
            	System.out.println("error !");            	
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
    
    @Override
    public TechnicienBean getTechnicienById(int idTech) {
        TechnicienBean technicien = null;
        String query = "SELECT * FROM technicien WHERE idTech = ?";
        
        try (Connection conn = daoFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idTech);

            // Exécution de la requête
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Récupérer les données de la ligne et créer un objet TechnicienBean
                    technicien = new TechnicienBean(
                        rs.getInt("idTech"),
                        rs.getString("nomTech"),
                        rs.getString("specialite"),
                        rs.getString("disponibilite"),
                        rs.getString("email"),
                        rs.getString("telephone")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        }

        return technicien;
    }

    @Override
    public void updateIntervention(InterventionBean intervention) throws DAOException {
        String sql = "UPDATE intervention SET priority = ?, description = ?, dateCreation = ?, dateTerminaison = ?, nomTech = ?, statut = ?, batiment = ?, etage = ?, numChambre = ? WHERE idInterv = ?";
        
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, intervention.getDescription());
            statement.setDate(2, new java.sql.Date(intervention.getDateCreation().getTime()));  // Date de création
            if (intervention.getDateTerminaison() != null) {
                statement.setDate(3, new java.sql.Date(intervention.getDateTerminaison().getTime())); // Date de terminaison
            } else {
                statement.setNull(3, Types.DATE);
            }
            statement.setString(4, intervention.getNomTech());
            statement.setString(5, intervention.getStatut());
            statement.setString(6, intervention.getBatiment());
            statement.setString(7, intervention.getEtage());
            statement.setInt(8, intervention.getNumChambre());
            statement.setInt(9, intervention.getIdInterv());
            statement.setString(10, intervention.getPriority());


            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error updating intervention", e);
        }
    }
   

    @Override
    public InterventionBean getInterventionById(int idInterv) throws DAOException {
        String sql = "SELECT * FROM intervention WHERE idInterv = ?";
        InterventionBean intervention = null;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, idInterv);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                intervention = new InterventionBean(
                    resultSet.getString("priority"),                		
                    resultSet.getInt("idInterv"),
                    resultSet.getString("description"),
                    resultSet.getDate("dateCreation"),
                    resultSet.getDate("dateTerminaison"),
                    resultSet.getString("nomTech"),
                    resultSet.getString("statut"),
                    resultSet.getString("batiment"),
                    resultSet.getString("etage"),
                    resultSet.getInt("numChambre"),
                    resultSet.getString("email"),
                    resultSet.getInt("evaluation"),
                    resultSet.getString("commentaire")

                );
            }
        } catch (SQLException e) {
            throw new DAOException("Error finding intervention by id", e);
        }

        return intervention;
    }

    @Override
    public void deleteIntervention(int idInterv) throws DAOException {
        String sql = "DELETE FROM intervention WHERE idInterv = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, idInterv);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error deleting intervention", e);
        }
    }
    
    public List<InterventionBean> getTerminatedInterventions() throws Exception {
        List<InterventionBean> terminatedInterventions = new ArrayList<>();
        String query = "SELECT * FROM intervention WHERE statut = 'terminée'";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                InterventionBean intervention = new InterventionBean();
                intervention.setIdInterv(resultSet.getInt("idInterv"));
                intervention.setDescription(resultSet.getString("description"));
                intervention.setDateCreation(resultSet.getDate("dateCreation"));
                intervention.setDateTerminaison(resultSet.getDate("dateTerminaison"));
                intervention.setNomTech(resultSet.getString("nomTech"));
                intervention.setPriority(resultSet.getString("priority"));
                intervention.setBatiment(resultSet.getString("batiment"));
                intervention.setEtage(resultSet.getString("etage"));
                intervention.setNumChambre(resultSet.getInt("numChambre"));
                intervention.setEmail(resultSet.getString("email"));
                intervention.setStatut(resultSet.getString("statut"));
                intervention.setEvaluation(resultSet.getInt("evaluation"));
                intervention.setCommentaire(resultSet.getString("commentaire"));


                

                terminatedInterventions.add(intervention);
            }
        }
        return terminatedInterventions;
    }

}


