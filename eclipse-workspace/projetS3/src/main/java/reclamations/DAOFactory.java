package reclamations;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {
    private static final String FICHIER_PROPERTIES = "reclamations/dao.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";

    private String url;
    private String username;
    private String password;

    private DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;

        try (InputStream fichierProperties = Thread.currentThread().getContextClassLoader().getResourceAsStream(FICHIER_PROPERTIES)) {
            if (fichierProperties == null) {
                throw new DAOConfigurationException("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");
            }
            properties.load(fichierProperties);
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            nomUtilisateur = properties.getProperty(PROPERTY_NOM_UTILISATEUR);
            motDePasse = properties.getProperty(PROPERTY_MOT_DE_PASSE);

            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOConfigurationException("Erreur lors du chargement de la configuration", e);
        }

        return new DAOFactory(url, nomUtilisateur, motDePasse);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public ReclamationDAO getReclamationDao() {
        return new ReclamationDaoImpl(this);
    }
    
    public InterventionDAO getInterventionDao() {
        return new InterventionDaoImpl(this);
    }
    
    public EvaluationDAO getEvaluationDao() {
        return new EvaluationDaoImpl(this);
    }
}