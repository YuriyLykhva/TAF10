package util;

public class User {
    private String userEmail;
    private String password;
    private String userName;

    public User(String userEmail, String password, String userName) {
        this.userEmail = userEmail;
        this.password = password;
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public static User createUser() {
        return new User(TestDataReader.getTestData("testdata.user.email"),
                TestDataReader.getTestData("testdata.user.password"),
                TestDataReader.getTestData("testdata.user.name"));
    }
}
