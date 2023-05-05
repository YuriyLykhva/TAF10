package page;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    public static final int WAIT_TIMEOUT_SECONDS = 10;

    public WebDriver driver;

    public abstract BasePage openPage();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().window().maximize();
    }
}
