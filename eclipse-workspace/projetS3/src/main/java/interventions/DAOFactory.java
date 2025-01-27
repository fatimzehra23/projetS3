package interventions;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {
    private static final String PROPERTIES_FILE = "interventions/dao.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USERNAME = "nomutilisateur";
    private static final String PROPERTY_PASSWORD = "motdepasse";

    private final String url;
    private final String username;
    private final String password;

    private DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String username;
        String password;

        try (InputStream propertiesFile = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (propertiesFile == null) {
                throw new DAOConfigurationException("The properties file " + PROPERTIES_FILE + " could not be found.");
            }
            properties.load(propertiesFile);
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            username = properties.getProperty(PROPERTY_USERNAME);
            password = properties.getProperty(PROPERTY_PASSWORD);

            // Load JDBC driver dynamically
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOConfigurationException("Error loading configuration", e);
        }

        return new DAOFactory(url, username, password);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public InterventionDAO getInterventionDao() {
        return new InterventionDaoImpl(this);
    }
    
    public TechnicienDAO getTechnicienDao() {
        return new TechnicienDaoImpl(this);
    }
}
