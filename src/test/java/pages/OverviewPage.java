package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OverviewPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By accountsOverviewHeading = By.xpath("//h1[contains(text(),'Accounts Overview')]");

    public OverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isDisplayed() {
        try {
            WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(accountsOverviewHeading));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
