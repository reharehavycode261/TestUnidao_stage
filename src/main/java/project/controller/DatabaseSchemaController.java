package project.controller;

import etu2024.framework.annotation.RestAPI;
import etu2024.framework.annotation.Url;
import etu2024.framework.utility.Mapping;
import mg.uniDao.core.Database;
import mg.uniDao.core.Service;
import mg.uniDao.exception.DaoException;
import mg.uniDao.provider.GenericSqlProvider;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestAPI
public class DatabaseSchemaController {

    private final Database database;

    public DatabaseSchemaController() throws DaoException {
        this.database = GenericSqlProvider.get("database.json");
    }

    @Url("/importSchema")
    @PostMapping("/importSchema")
    public String importSchema(@RequestParam("file") MultipartFile file) {
        // TODO: Implémentation de l'importation du schéma
        if (file.isEmpty()) {
            return "File is empty!";
        }
        try {
            // Logique pour lire le fichier et importer le schéma dans la base de données
            // parse file and import schema
            return "Schema imported successfully!";
        } catch (IOException e) {
            return "Error during schema import: " + e.getMessage();
        }
    }

    @Url("/exportSchema")
    @GetMapping("/exportSchema")
    public String exportSchema() {
        // TODO: Implémentation de l'exportation du schéma
        try {
            // Logique pour exporter le schéma de la base de données et écrire dans un fichier
            return "Schema exported successfully!";
        } catch (Exception e) {
            return "Error during schema export: " + e.getMessage();
        }
    }
}