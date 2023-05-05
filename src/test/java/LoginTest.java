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

        Assert.assertEquals(loggedInUserName, testUser.getUserName());
    }
}
