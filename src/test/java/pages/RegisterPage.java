package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By firstNameField = By.id("customer.firstName");
    private By lastNameField = By.id("customer.lastName");
    private By addressField = By.id("customer.address.street");
    private By cityField = By.id("customer.address.city");
    private By stateField = By.id("customer.address.state");
    private By zipCodeField = By.id("customer.address.zipCode");
    private By phoneField = By.id("customer.phoneNumber");
    private By ssnField = By.id("customer.ssn");
    private By usernameField = By.id("customer.username");
    private By passwordField = By.id("customer.password");
    private By confirmPasswordField = By.id("repeatedPassword");
    private By registerButton = By.xpath("//input[@value='Register']");
    private By successMessage = By.id("rightPanel");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterFirstName(String firstName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        element.clear();
        element.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
        element.clear();
        element.sendKeys(lastName);
    }

    public void enterAddress(String address) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(addressField));
        element.clear();
        element.sendKeys(address);
    }

    public void enterCity(String city) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(cityField));
        element.clear();
        element.sendKeys(city);
    }

    public void enterState(String state) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(stateField));
        element.clear();
        element.sendKeys(state);
    }

    public void enterZipCode(String zipCode) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(zipCodeField));
        element.clear();
        element.sendKeys(zipCode);
    }

    public void enterPhone(String phone) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField));
        element.clear();
        element.sendKeys(phone);
    }

    public void enterSsn(String ssn) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(ssnField));
        element.clear();
        element.sendKeys(ssn);
    }

    public void enterUsername(String username) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        element.clear();
        element.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        element.clear();
        element.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField));
        element.clear();
        element.sendKeys(confirmPassword);
    }

    public void clickRegister() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        element.click();
    }

    public void register(String firstName, String lastName, String address, String city,
                         String state, String zipCode, String phone, String ssn,
                         String username, String password) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterAddress(address);
        enterCity(city);
        enterState(state);
        enterZipCode(zipCode);
        enterPhone(phone);
        enterSsn(ssn);
        enterUsername(username);
        enterPassword(password);
        enterConfirmPassword(password);
        clickRegister();
    }

    public boolean isRegistrationSuccessful() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            String text = element.getText();
            return text.contains("account created") || text.contains("Welcome");
        } catch (Exception e) {
            return false;
        }
    }
}
