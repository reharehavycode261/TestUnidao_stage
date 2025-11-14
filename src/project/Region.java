package project;

import java.util.ArrayList;
import java.util.List;

public class Region {

    private int id;
    private String name;
    private List<String> notificationChannels;

    public Region() {
        this.notificationChannels = new ArrayList<>();
        initializeDefaultChannels();
    }

    private void initializeDefaultChannels() {
        this.notificationChannels.add("in-app");
        this.notificationChannels.add("email");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNotificationChannels() {
        return notificationChannels;
    }

    public void setNotificationChannels(List<String> notificationChannels) {
        this.notificationChannels = notificationChannels;
    }
}