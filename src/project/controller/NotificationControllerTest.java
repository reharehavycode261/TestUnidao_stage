package project.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.entity.NotificationPreference;
import project.service.NotificationService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotificationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(notificationController).build();
    }

    @Test
    public void testSendNotification_AllPreferences() throws Exception {
        // Arrange
        NotificationPreference preference = new NotificationPreference();
        preference.setEmail(true);
        preference.setPush(true);

        // Act & Assert
        mockMvc.perform(post("/api/notifications/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":true,\"push\":true}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Notifications sent successfully!"));

        verify(notificationService, times(1)).sendEmailNotification(anyString(), anyString(), anyString());
        verify(notificationService, times(1)).sendPushNotification(anyString(), anyString(), anyString());
        verify(notificationService, times(1)).sendInAppNotification(eq(preference), anyString());
    }

    @Test
    public void testSendNotification_EmailOnly() throws Exception {
        // Arrange
        NotificationPreference preference = new NotificationPreference();
        preference.setEmail(true);
        preference.setPush(false);

        // Act & Assert
        mockMvc.perform(post("/api/notifications/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":true,\"push\":false}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Notifications sent successfully!"));

        verify(notificationService, times(1)).sendEmailNotification(anyString(), anyString(), anyString());
        verify(notificationService, times(0)).sendPushNotification(anyString(), anyString(), anyString());
        verify(notificationService, times(1)).sendInAppNotification(eq(preference), anyString());
    }

    @Test
    public void testSendNotification_PushOnly() throws Exception {
        // Arrange
        NotificationPreference preference = new NotificationPreference();
        preference.setEmail(false);
        preference.setPush(true);

        // Act & Assert
        mockMvc.perform(post("/api/notifications/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":false,\"push\":true}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Notifications sent successfully!"));

        verify(notificationService, times(0)).sendEmailNotification(anyString(), anyString(), anyString());
        verify(notificationService, times(1)).sendPushNotification(anyString(), anyString(), anyString());
        verify(notificationService, times(1)).sendInAppNotification(eq(preference), anyString());
    }

    @Test
    public void testSendNotification_NoPreferences() throws Exception {
        // Arrange
        NotificationPreference preference = new NotificationPreference();
        preference.setEmail(false);
        preference.setPush(false);

        // Act & Assert
        mockMvc.perform(post("/api/notifications/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":false,\"push\":false}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Notifications sent successfully!"));

        verify(notificationService, times(0)).sendEmailNotification(anyString(), anyString(), anyString());
        verify(notificationService, times(0)).sendPushNotification(anyString(), anyString(), anyString());
        verify(notificationService, times(1)).sendInAppNotification(eq(preference), anyString());
    }

    @Test
    public void testSendNotification_InvalidRequestBody() {
        // Act & Assert
        assertThrows(Exception.class, () -> {
            mockMvc.perform(post("/api/notifications/send")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{invalid json"))
                    .andExpect(status().isBadRequest());
        });
    }
}