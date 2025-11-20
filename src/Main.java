import mg.uniDao.core.Database;
import mg.uniDao.core.Service;
import mg.uniDao.exception.DaoException;
import mg.uniDao.provider.GenericSqlProvider;
import project.Region;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws DaoException {
        Database postgresSql = GenericSqlProvider.get("database.json");

        Service service = postgresSql.connect("TEST", true);

        Region region = new Region();
        postgresSql.updateById(service, region, 48);

        // Appel à la nouvelle fonctionnalité de sauvegarde
        try {
            generateBackupScript(service.getConnection(), "backup.sql");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        service.close();
    }

    /**
     * Génère un script SQL de sauvegarde de la base de données
     * @param connection la connexion à la base de données
     * @param filePath le chemin du fichier où sauvegarder le script SQL
     * @throws SQLException si une erreur SQL survient
     * @throws IOException si une erreur d'écriture du fichier survient
     */
    public static void generateBackupScript(Connection connection, String filePath) throws SQLException, IOException {
        StringBuilder sqlScript = new StringBuilder();

        try (Statement stmt = connection.createStatement()) {
            // Obtention des tables de la base de données
            ResultSet tables = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='public'");

            while (tables.next()) {
                String tableName = tables.getString("table_name");
                ResultSet tableData = stmt.executeQuery("SELECT * FROM " + tableName);
                int columnCount = tableData.getMetaData().getColumnCount();

                sqlScript.append("-- Table: ").append(tableName).append("\n");
                sqlScript.append("CREATE TABLE ").append(tableName).append(" (\n");
                for (int i = 1; i <= columnCount; i++) {
                    sqlScript.append("  ").append(tableData.getMetaData().getColumnName(i))
                             .append(" ").append(tableData.getMetaData().getColumnTypeName(i));
                    if (i < columnCount) sqlScript.append(",\n");
                }
                sqlScript.append("\n);\n");

                // Ajout des données de la table
                while (tableData.next()) {
                    sqlScript.append("INSERT INTO ").append(tableName).append(" VALUES (");
                    for (int i = 1; i <= columnCount; i++) {
                        sqlScript.append("'").append(tableData.getString(i)).append("'");
                        if (i < columnCount) sqlScript.append(", ");
                    }
                    sqlScript.append(");\n");
                }
                sqlScript.append("\n");
            }
        }

        // Écriture du script dans le fichier
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(sqlScript.toString());
        }
    }
}