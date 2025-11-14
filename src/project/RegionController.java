package project;

import etu2024.framework.annotation.RestAPI;
import etu2024.framework.annotation.Url;
import mg.uniDao.core.Database;
import mg.uniDao.exception.DaoException;
import mg.uniDao.provider.GenericSqlProvider;
import project.notification.NotificationService;

public class RegionController {
    private final Database database;
    private final NotificationService notificationService;

    public RegionController() throws DaoException {
        this.database = GenericSqlProvider.get("database.json");
        this.notificationService = new NotificationService();
    }

    @RestAPI
    @Url("/region/setNotificationPreferences")
    public void setNotificationPreferences(int regionId, List<String> channels) throws DaoException {
        // Logic to update notification preferences for a region
        Region region = database.findById(Region.class, regionId);
        region.setNotificationChannels(channels);
        database.update(region);
        
        notificationService.updateChannels(region);
    }
}