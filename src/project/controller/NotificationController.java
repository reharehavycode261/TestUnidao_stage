package project.controller;

import org.springframework.web.bind.annotation.*;
import project.entity.NotificationPreference;
import project.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public String sendNotification(@RequestBody NotificationPreference preference) {
        if (preference.isEmail()) {
            notificationService.sendEmailNotification("user@example.com", "Test Subject", "Email notification content.");
        }
        if (preference.isPush()) {
            notificationService.sendPushNotification("user-firebase-token", "Push Notification Title", "Push notification content.");
        }
        notificationService.sendInAppNotification(preference, "In-App notification content.");
        return "Notifications sent successfully!";
    }
}