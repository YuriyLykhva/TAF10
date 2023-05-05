import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.SignInPage;
import util.User;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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
                {"AndrewTate123@gmail.com", "1607200525ZZmm", "Andrew Tate"},
                {"luka.dropik@gmail.com", "qwertyuiop[]\\asdfghjkl;'zxcvbnm,./1234567890", "Luka Dropik"},
        };
    }

    @Test(dataProvider = "credentials")
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

    @DataProvider(name = "csvCredentials")
    public Object[][] csvCredentials() throws IOException, CsvException {
        CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/File.csv"));
        List<String[]> users = csvReader.readAll();

        String[][] csvDataObject = new String[users.size()][3];
        for (int i = 0; i < users.size(); i++) {
            csvDataObject[i] = users.get(i);
        }
        return csvDataObject;
    }

    @Test(enabled = true, description = "Test login with email and password from CSV file", dataProvider = "csvCredentials")
    public void loginViaCsvFile(String email, String password, String userName) {
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
