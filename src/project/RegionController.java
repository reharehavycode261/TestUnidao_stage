package project;

import mg.uniDao.core.Service;
import mg.uniDao.exception.DaoException;
import mg.uniDao.provider.GenericSqlProvider;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {

    private final Service service;

    public RegionController() throws DaoException {
        this.service = GenericSqlProvider.get("database.json").connect("TEST", true);
    }

    @GetMapping
    public List<Region> getRegions() throws DaoException {
        // Code existant pour obtenir la liste des régions
        return service.findAll(Region.class);
    }

    @PostMapping("/reorder")
    public void reorderRegions(@RequestBody List<Integer> newOrder) throws DaoException {
        // Code pour mettre à jour l'ordre des régions selon newOrder
        // Assuming the list is IDs of the regions in the new order
        for (int index = 0; index < newOrder.size(); index++) {
            int regionId = newOrder.get(index);
            Region region = service.findById(Region.class, regionId);
            if (region != null) {
                region.setOrder(index);
                service.update(region);
            }
        }
    }
}