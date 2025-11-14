package project;

import etu2024.framework.annotation.JsonObject;
import etu2024.framework.annotation.RestAPI;
import etu2024.framework.annotation.Url;
import etu2024.framework.utility.Mapping;
import mg.uniDao.core.Database;
import mg.uniDao.core.Service;
import mg.uniDao.exception.DaoException;
import mg.uniDao.provider.GenericSqlProvider;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class RegionController {
    private final Database database;

    public RegionController() throws DaoException {
        database = GenericSqlProvider.get("database.json");
    }

    @RestAPI
    @Url("/regions")
    public List<Region> getRegions(String sortBy, boolean descending, int page, int size) throws DaoException {
        List<Region> regions = fetchAllRegions();

        if (sortBy != null && !sortBy.isEmpty()) {
            regions = regions.stream()
                .sorted((r1, r2) -> {
                    int comparison;
                    switch (sortBy) {
                        case "name":
                            comparison = r1.getName().compareToIgnoreCase(r2.getName());
                            break;
                        // Ajoutez d'autres cas pour trier par différentes colonnes
                        default:
                            comparison = 0;
                    }
                    return descending ? -comparison : comparison;
                })
                .collect(Collectors.toList());
        }

        // Pagination
        int fromIndex = Math.max(0, page * size);
        int toIndex = Math.min(fromIndex + size, regions.size());
        return regions.subList(fromIndex, toIndex);
    }

    private List<Region> fetchAllRegions() throws DaoException {
        Service service = database.connect("TEST", true);
        List<Region> regions = service.findAll(new Region());
        service.closeConnection();
        return regions;
    }
}