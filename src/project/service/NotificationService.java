package project.service;

import project.entity.NotificationPreference;
// Import des dépendances pour l'email et Firebase
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.internet.MimeMessage;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

public class NotificationService {
    private final JavaMailSender emailSender;
    private final FirebaseMessaging firebaseMessaging;

    public NotificationService(JavaMailSender emailSender, FirebaseMessaging firebaseMessaging) {
        this.emailSender = emailSender;
        this.firebaseMessaging = firebaseMessaging;
    }

    public void sendEmailNotification(String to, String subject, String text) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPushNotification(String token, String title, String body) {
        Message message = Message.builder()
                .putData("title", title)
                .putData("body", body)
                .setToken(token)
                .build();
        
        try {
            firebaseMessaging.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendInAppNotification(NotificationPreference preference, String message) {
        if (preference.isInApp()) {
            // Logique pour envoyer une notification in-app
            System.out.println("In-app notification: " + message);
        }
    }
}