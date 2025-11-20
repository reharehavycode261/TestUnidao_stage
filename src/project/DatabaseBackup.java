package project;

import mg.uniDao.core.Database;
import mg.uniDao.core.Service;
import mg.uniDao.exception.DaoException;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseBackup {

    private final Database database;

    public DatabaseBackup(Database database) {
        this.database = database;
    }

    public void generateBackupScript(String fileName) throws DaoException, IOException {
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();
             FileWriter fileWriter = new FileWriter(fileName)) {

            // Get tables from the database
            ResultSet tables = statement.executeQuery("SHOW TABLES");
            while (tables.next()) {
                String tableName = tables.getString(1);

                // Get table creation script
                ResultSet createTable = statement.executeQuery("SHOW CREATE TABLE " + tableName);
                if (createTable.next()) {
                    String createTableSql = createTable.getString(2) + ";";
                    fileWriter.write(createTableSql + "\n\n");
                }

                // Get data from the table
                ResultSet data = statement.executeQuery("SELECT * FROM " + tableName);
                int columns = data.getMetaData().getColumnCount();

                while (data.next()) {
                    StringBuilder insert = new StringBuilder("INSERT INTO " + tableName + " VALUES(");
                    for (int i = 1; i <= columns; i++) {
                        insert.append("'").append(data.getString(i).replace("'", "''")).append("'");
                        if (i < columns) insert.append(", ");
                    }
                    insert.append(");");
                    fileWriter.write(insert.toString() + "\n");
                }
                fileWriter.write("\n");
            }
        } catch (Exception e) {
            throw new DaoException("Error generating backup script: " + e.getMessage(), e);
        }
    }
}