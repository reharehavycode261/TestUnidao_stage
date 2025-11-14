package project;

import etu2024.framework.annotation.JsonObject;
import etu2024.framework.annotation.RestAPI;
import etu2024.framework.annotation.Url;
import etu2024.framework.utility.Mapping;
import mg.uniDao.core.Database;
import mg.uniDao.core.Service;
import mg.uniDao.exception.DaoException;
import mg.uniDao.provider.GenericSqlProvider;
import com.google.gson.Gson;

import java.util.List;
import java.util.ArrayList;

public class RegionController {
    private final Database database;
    private List<String> savedQueries;

    public RegionController() throws DaoException {
        this.database = GenericSqlProvider.get("database.json");
        this.savedQueries = new ArrayList<>();
    }

    @Url("/searchRegions")
    @RestAPI
    public List<Region> searchRegions(@JsonObject String query, @JsonObject String filter) throws DaoException {
        System.out.println("Recherche des régions avec le terme: " + query + " et filter: " + filter);

        List<Region> regions = new ArrayList<>();
        // Ajouter ici la logique de recherche des régions
        return regions;
    }

    @Url("/autocomplete")
    @RestAPI
    public List<String> autocomplete(@JsonObject String term) {
        List<String> suggestions = new ArrayList<>();
        // Simuler l'auto-complétion
        suggestions.add(term + "1");
        suggestions.add(term + "2");
        return suggestions;
    }

    @Url("/saveQuery")
    @RestAPI
    public String saveQuery(@JsonObject String query, @JsonObject String filter) {
        String savedQuery = "Requête: " + query + ", Filtre: " + filter;
        savedQueries.add(savedQuery);
        return new Gson().toJson(savedQueries);
    }
}