package utils;

import lombok.Getter;
import lombok.Setter;
import org.automation.testing.my_crud_dto.User;

import java.util.UUID;

@Setter
@Getter
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

}