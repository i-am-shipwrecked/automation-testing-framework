package utils;

import org.automation.testing.my_crud_dto.User;

import java.util.UUID;

public class TestContext {
    private static TestContext instance;
    private UUID userId;
    private User userData;

    private TestContext() {
    }

    public static synchronized TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    public User getUserData() {
        return userData;
    }
    public void setUserData(User userData) {
        this.userData = userData;
    }
}
