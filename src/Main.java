import mg.uniDao.core.Database;
import mg.uniDao.core.Service;
import mg.uniDao.exception.DaoException;
import mg.uniDao.provider.GenericSqlProvider;
import project.Region;

/**
 * Classe principale pour initier la connexion à la base de données et effectuer des opérations CRUD de base.
 * 
 * Exemple d'utilisation:
 * <pre>
 * {@code
 * public class Main {
 *     public static void main(String[] args) throws DaoException {
 *         // Créer une connexion à la base de données
 *         Database postgresSql = GenericSqlProvider.get("database.json");
 *
 *         // Connecter au service de la base de données
 *         Service service = postgresSql.connect("TEST", true);
 *
 *         // Créer une instance de Region
 *         Region region = new Region("R002", "South Region");
 *
 *         // Mettre à jour une région dans la base de données par ID
 *         postgresSql.updateById(service, region, 48);
 *         
 *         // Déconnecter le service
 *         service.close();
 *     }
 * }
 * }
 * </pre>
 */
public class Main {
    public static void main(String[] args) throws DaoException {
        Database postgresSql = GenericSqlProvider.get("database.json");

        Service service = postgresSql.connect("TEST", true);

        Region region = new Region();
        postgresSql.updateById(service, region, 48);

        service.close();
    }
}