package utils;

public class RetryManager {
    private static final int MAX_RETRY_COUNT = 3;
    private static int retryCount = 0;

    public static boolean shouldRetry() {
        return retryCount < MAX_RETRY_COUNT;
    }

    public static void incrementRetryCount() {
        retryCount++;
    }

    public static void resetRetryCount() {
        retryCount = 0;
    }
}
