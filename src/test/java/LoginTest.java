import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.SignInPage;
import util.User;

public class LoginTest extends BaseTest{
    @Test
    public void loginWithUser () {
        User testUser = User.createUser();
        String loggedInUserName = new SignInPage(driver)
                .openPage()
                .loginViaModel(testUser)
                .getLoggedInUserName();

        String editedLoggedInUserName = loggedInUserName
                .substring(loggedInUserName.indexOf(" ") + 1, loggedInUserName.length() - 1);

        Assert.assertEquals(editedLoggedInUserName, testUser.getUserName());
    }

    @DataProvider(name = "credentials")
    public Object[][] credentials() {
        return new String[][]{
                {"tqwretqwe@email.com", "4Zmw!MjXZrFWPN3", "Bill Gates"},
                {"tqwretqwe@email.com", "4Zmw!MjXZrFWPN3", "Bill Gate"},
        };
    }

    @Test(enabled = true, dataProvider = "credentials")
    public void loginViaDataProvider(String email, String password, String userName) {

        String loggedInUserName =
                new SignInPage(driver)
                        .openPage()
                        .loginViaCredentials(email, password)
                        .getLoggedInUserName();

        String editedLoggedInUserName = loggedInUserName
                .substring(loggedInUserName.indexOf(" ") + 1, loggedInUserName.indexOf("!"));

        Assert.assertEquals(editedLoggedInUserName, userName);
    }
}
