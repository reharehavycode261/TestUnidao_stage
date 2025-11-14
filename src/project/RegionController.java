package project;

import etu2024.framework.annotation.JsonObject;
import etu2024.framework.annotation.Url;
import etu2024.framework.annotation.RestAPI;
import mg.uniDao.core.Database;
import mg.uniDao.exception.DaoException;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegionController {
    private final Database database;
    private ResourceBundle messages;

    public RegionController() throws DaoException {
        this.database = new Database("database_config_path");
        setLocale(Locale.getDefault());
    }

    public void setLocale(Locale locale) {
        this.messages = ResourceBundle.getBundle("MessagesBundle", locale);
    }

    public String getLocalizedText(String key) {
        return messages.getString(key);
    }

    // Exemple d'utilisation dans une méthode
    public void someMethod() {
        System.out.println(getLocalizedText("welcome.message"));
    }
}