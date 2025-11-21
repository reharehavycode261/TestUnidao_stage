package project;

import etu2024.framework.annotation.JsonObject;
import etu2024.framework.annotation.RestAPI;
import etu2024.framework.annotation.Url;
import mg.uniDao.core.Database;
import mg.uniDao.exception.DaoException;
import mg.uniDao.provider.GenericSqlProvider;

import java.util.List;

public class RegionController {
    private final Database database;

    public RegionController() throws DaoException {
        // TODO: Implémentez une méthode de connexion sécurisée à la base de données
        database = new Database();
    }

    @Url("/regions")
    @RestAPI
    @JsonObject
    public List<Region> getAllRegions() throws DaoException {
        List<Region> regions = GenericSqlProvider.findAll(Region.class, database);
        // TODO: Filtrer les régions supprimées logiquement
        return regions;
    }

    // TODO: Ajouter des tests unitaires pour chaque méthode du contrôleur
}