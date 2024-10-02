package ma.projet.connexion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe de connexion à la base de données.
 */
public class Connexion {
    private static Connection connection;

    // Bloc statique pour initialiser la connexion
    static {
        try {
            // Charger les propriétés depuis le fichier config.properties
            FileInputStream f = new FileInputStream("config.properties");
            Properties p = new Properties();
            p.load(f);
            
            // Récupérer les informations de connexion depuis les propriétés
            String url = p.getProperty("jdbc.url");
            String login = p.getProperty("jdbc.username");
            String password = p.getProperty("jdbc.password");
            String driver = p.getProperty("jdbc.driver");

            // Charger le driver JDBC
            Class.forName(driver);

            // Établir la connexion avec la base de données
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Connexion réussie à la base de données.");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, "Fichier config.properties non trouvé.", ex);
        } catch (IOException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, "Erreur lors du chargement des propriétés.", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, "Driver JDBC non trouvé.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, "Erreur lors de la connexion à la base de données.", ex);
        }
    }

    /**
     * Méthode pour obtenir la connexion à la base de données.
     *
     * @return Connection à la base de données.
     */
    public static Connection getConnection() {
        return connection;
    }
}
