package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OpenAccountPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By openNewAccountLink = By.xpath("//a[contains(text(),'Open New Account')]");
    private By accountTypeDropdown = By.id("type");
    private By openAccountButton = By.xpath("//input[@value='Open New Account']");
    private By successMessage = By.id("rightPanel");
    private By accountId = By.xpath("//a[contains(@id,'accountId')] | //td[contains(text(),'Account')]");

    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickOpenNewAccount() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(openNewAccountLink));
        element.click();
    }

    public void selectAccountType(String accountType) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(accountTypeDropdown));
        Select select = new Select(dropdown);
        select.selectByVisibleText(accountType);
    }

    public void clickOpenAccount() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(openAccountButton));
        element.click();
    }

    public void openNewAccount(String accountType) {
        clickOpenNewAccount();
        selectAccountType(accountType);
        clickOpenAccount();
    }

    public boolean isAccountCreated() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            String text = element.getText();
            return text.contains("Account") || text.contains("opened") || text.contains("created") || text.contains("Congratulations");
        } catch (Exception e) {
            return false;
        }
    }

    public String getAccountId() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(accountId));
            return element.getText();
        } catch (Exception e) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'activity.htm')]")));
                String href = element.getAttribute("href");
                if (href != null && href.contains("id=")) {
                    String id = href.substring(href.indexOf("id=") + 3);
                    if (id.contains("&")) {
                        id = id.substring(0, id.indexOf("&"));
                    }
                    return id;
                }
            } catch (Exception ex) {
                return null;
            }
            return null;
        }
    }
}
