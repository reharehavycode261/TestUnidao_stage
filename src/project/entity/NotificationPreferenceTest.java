package project.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class NotificationPreferenceTest {

    @Test
    public void testConstructorAndGetters() {
        Long userId = 1L;
        boolean email = true;
        boolean inApp = false;
        boolean push = true;

        NotificationPreference preference = new NotificationPreference(userId, email, inApp, push);

        Assertions.assertNull(preference.getId(), "Initial ID should be null");
        Assertions.assertEquals(userId, preference.getUserId(), "User ID should match the constructor argument");
        Assertions.assertEquals(email, preference.isEmail(), "Email preference should match the constructor argument");
        Assertions.assertEquals(inApp, preference.isInApp(), "In-app preference should match the constructor argument");
        Assertions.assertEquals(push, preference.isPush(), "Push preference should match the constructor argument");
    }

    @Test
    public void testSetters() {
        NotificationPreference preference = new NotificationPreference(1L, true, false, true);

        Long newId = 2L;
        Long newUserId = 3L;
        boolean newEmail = false;
        boolean newInApp = true;
        boolean newPush = false;

        preference.setId(newId);
        preference.setUserId(newUserId);
        preference.setEmail(newEmail);
        preference.setInApp(newInApp);
        preference.setPush(newPush);

        Assertions.assertEquals(newId, preference.getId(), "ID should be updated to the new value");
        Assertions.assertEquals(newUserId, preference.getUserId(), "User ID should be updated to the new value");
        Assertions.assertEquals(newEmail, preference.isEmail(), "Email preference should be updated to the new value");
        Assertions.assertEquals(newInApp, preference.isInApp(), "In-app preference should be updated to the new value");
        Assertions.assertEquals(newPush, preference.isPush(), "Push preference should be updated to the new value");
    }

    @Test
    public void testEdgeCases() {
        NotificationPreference preference = new NotificationPreference(null, false, false, false);

        Assertions.assertNull(preference.getUserId(), "User ID should be null if initialized with null");
        Assertions.assertFalse(preference.isEmail(), "Email preference should be false if initialized with false");
        Assertions.assertFalse(preference.isInApp(), "In-app preference should be false if initialized with false");
        Assertions.assertFalse(preference.isPush(), "Push preference should be false if initialized with false");

        preference.setUserId(0L);
        Assertions.assertEquals(0L, preference.getUserId(), "User ID should be set to 0");
    }
}