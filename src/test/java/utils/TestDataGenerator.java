package utils;

import org.automation.testing.my_crud_dto.Project;
import org.automation.testing.my_crud_dto.Task;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataGenerator {
    private static final Random random = new Random();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Project generateRandomProject() {
        String name = generateRandomString(10);
        String description = generateRandomString(20);
        String beginning = generateRandomDate();
        String ending = generateRandomDateAfter(beginning);

        return new Project(name, description, beginning, ending, null, null);
    }
    public String generateUser(int length) {
        StringBuilder sb = new StringBuilder(length);
        SecureRandom random = new SecureRandom();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }

        return sb.toString();
    }

    public Task generateNewTask(UUID projectId) {
        Task newTask = new Task();
        newTask.setId(UUID.randomUUID());
        newTask.setName("Название задачи");
        newTask.setDescription("Описание задачи");
        newTask.setDeadline(LocalDate.now().plusDays(7).toString());
        newTask.setStatus("in progress");

        Project project = new Project();
        project.setId(projectId);
        newTask.setProject(project);

        return newTask;
    }

    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    private static String generateRandomDate() {
        long minDay = 0;
        long maxDay = 365L * 2;
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        Date randomDate = new Date(System.currentTimeMillis() + randomDay * 24 * 60 * 60 * 1000);
        return dateFormat.format(randomDate);
    }

    private static String generateRandomDateAfter(String startDate) {
        try {
            Date start = dateFormat.parse(startDate);
            long minDay = 1;
            long maxDay = 365L;
            long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

            Date randomDate = new Date(start.getTime() + randomDay * 24 * 60 * 60 * 1000);
            return dateFormat.format(randomDate);
        } catch (Exception e) {
            e.printStackTrace();
            return generateRandomDate();
        }
    }
}
