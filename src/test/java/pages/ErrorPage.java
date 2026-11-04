package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ErrorPage {

    private final WebDriver driver;

    public ErrorPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean hasErrorMessage(String expected) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            WebElement error = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error")));
            return error.getText().contains(expected);
        } catch (TimeoutException e) {
            System.out.println("=== TEMPORARY DEBUG LOGGING ===");
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Source: " + driver.getPageSource());
            System.out.println("=== END TEMPORARY DEBUG LOGGING ===");
            throw e;
        }
    }
}