package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TransferFundsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By transferFundsLink = By.xpath("//a[contains(text(),'Transfer Funds')]");
    private By fromAccountDropdown = By.id("fromAccountId");
    private By toAccountDropdown = By.id("toAccountId");
    private By amountField = By.id("amount");
    private By transferButton = By.xpath("//input[@value='Transfer']");
    private By successMessage = By.id("rightPanel");
    private By fromAccountBalance = By.xpath("//table//tr[1]/td[2]");
    private By toAccountBalance = By.xpath("//table//tr[2]/td[2]");

    public TransferFundsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickTransferFunds() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(transferFundsLink));
        element.click();
    }

    public void selectFromAccount(String accountId) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(fromAccountDropdown));
        Select select = new Select(dropdown);
        select.selectByValue(accountId);
    }

    public void selectToAccount(String accountId) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(toAccountDropdown));
        Select select = new Select(dropdown);
        select.selectByValue(accountId);
    }

    public void enterAmount(String amount) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(amountField));
        element.clear();
        element.sendKeys(amount);
    }

    public void clickTransfer() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(transferButton));
        element.click();
    }

    public void transferFunds(String amount) {
        clickTransferFunds();
        selectFirstFromAccount();
        selectFirstToAccount();
        enterAmount(amount);
        clickTransfer();
    }

    private void selectFirstFromAccount() {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(fromAccountDropdown));
        Select select = new Select(dropdown);
        java.util.List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            String value = option.getAttribute("value");
            if (value != null && !value.isEmpty()) {
                select.selectByValue(value);
                break;
            }
        }
    }

    private void selectFirstToAccount() {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(toAccountDropdown));
        Select select = new Select(dropdown);
        java.util.List<WebElement> options = select.getOptions();
        boolean firstSelected = false;
        for (WebElement option : options) {
            String value = option.getAttribute("value");
            if (value != null && !value.isEmpty()) {
                if (!firstSelected) {
                    firstSelected = true;
                } else {
                    select.selectByValue(value);
                    break;
                }
            }
        }
    }

    public boolean isTransferSuccessful() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            String text = element.getText();
            return text.contains("Transfer") || text.contains("Complete") || text.contains("successfully") || text.contains("has been");
        } catch (Exception e) {
            return false;
        }
    }

    public String getFromAccountBalance() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(fromAccountBalance));
            return element.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public String getToAccountBalance() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(toAccountBalance));
            return element.getText();
        } catch (Exception e) {
            return null;
        }
    }
}
