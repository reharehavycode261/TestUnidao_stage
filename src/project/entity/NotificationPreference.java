package project.entity;

public class NotificationPreference {
    private Long id;
    private Long userId;
    private boolean email;
    private boolean inApp;
    private boolean push;

    // Constructeurs, getters et setters

    public NotificationPreference(Long userId, boolean email, boolean inApp, boolean push) {
        this.userId = userId;
        this.email = email;
        this.inApp = inApp;
        this.push = push;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isEmail() {
        return email;
    }

    public void setEmail(boolean email) {
        this.email = email;
    }

    public boolean isInApp() {
        return inApp;
    }

    public void setInApp(boolean inApp) {
        this.inApp = inApp;
    }

    public boolean isPush() {
        return push;
    }

    public void setPush(boolean push) {
        this.push = push;
    }
}