package project.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.internet.MimeMessage;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {

    private JavaMailSender emailSender;
    private FirebaseMessaging firebaseMessaging;
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        emailSender = mock(JavaMailSender.class);
        firebaseMessaging = mock(FirebaseMessaging.class);
        notificationService = new NotificationService(emailSender, firebaseMessaging);
    }

    @Test
    void testSendEmailNotificationSuccess() throws Exception {
        String to = "test@example.com";
        String subject = "Test Subject";
        String text = "Test email content";

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(emailSender.createMimeMessage()).thenReturn(mimeMessage);

        notificationService.sendEmailNotification(to, subject, text);

        ArgumentCaptor<MimeMessage> mimeMessageCaptor = ArgumentCaptor.forClass(MimeMessage.class);
        verify(emailSender, times(1)).send(mimeMessageCaptor.capture());

        MimeMessage sentMessage = mimeMessageCaptor.getValue();
        MimeMessageHelper helper = new MimeMessageHelper(sentMessage, true);
        assertEquals(to, helper.getTo()[0]);
        assertEquals(subject, helper.getSubject());
        assertEquals(text, helper.getText());
    }

    @Test
    void testSendEmailNotificationFailure() throws Exception {
        String to = "test@example.com";
        String subject = "Test Subject";
        String text = "Test email content";

        when(emailSender.createMimeMessage()).thenThrow(new RuntimeException("Email sending failed"));

        assertDoesNotThrow(() -> notificationService.sendEmailNotification(to, subject, text));
    }

    @Test
    void testSendPushNotificationSuccess() throws Exception {
        String token = "testToken";
        String title = "Test Title";
        String body = "Test Body";

        notificationService.sendPushNotification(token, title, body);

        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
        verify(firebaseMessaging, times(1)).send(messageCaptor.capture());

        Message sentMessage = messageCaptor.getValue();
        assertEquals(token, sentMessage.getToken());
        assertEquals(title, sentMessage.getData().get("title"));
        assertEquals(body, sentMessage.getData().get("body"));
    }

    @Test
    void testSendPushNotificationFailure() throws Exception {
        String token = "testToken";
        String title = "Test Title";
        String body = "Test Body";

        doThrow(new RuntimeException("Push notification failed")).when(firebaseMessaging).send(any(Message.class));

        assertDoesNotThrow(() -> notificationService.sendPushNotification(token, title, body));
    }

    @Test
    void testSendInAppNotification() {
        NotificationPreference preference = mock(NotificationPreference.class);
        when(preference.isInApp()).thenReturn(true);

        String message = "Test in-app message";
        assertDoesNotThrow(() -> notificationService.sendInAppNotification(preference, message));
    }

    @Test
    void testDoNotSendInAppNotification() {
        NotificationPreference preference = mock(NotificationPreference.class);
        when(preference.isInApp()).thenReturn(false);

        String message = "Test in-app message";
        assertDoesNotThrow(() -> notificationService.sendInAppNotification(preference, message));
    }
}