import org.testng.Assert;
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
}
