package utils;

public class TestDataGenerator {

    private static final String PASSWORD = "TestPass123";

    public static String generateUniqueUsername() {
        return "user" + System.currentTimeMillis();
    }

    public static String getFirstName() {
        return "Test";
    }

    public static String getLastName() {
        return "User";
    }

    public static String getAddress() {
        return "123 Test Street";
    }

    public static String getCity() {
        return "Testville";
    }

    public static String getState() {
        return "TS";
    }

    public static String getZipCode() {
        return "12345";
    }

    public static String getPhone() {
        return "5550100";
    }

    public static String getSsn() {
        return "123-45-6789";
    }

    public static String getPassword() {
        return PASSWORD;
    }
}
