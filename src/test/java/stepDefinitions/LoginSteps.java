package stepDefinitions;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ErrorPage;
import pages.LoginPage;
import pages.OverviewPage;
import pages.RegisterPage;
import utils.ConfigReader;
import utils.TestDataGenerator;

public class LoginSteps {

    private TestContext testContext;

    public LoginSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("I am in the log in page of the Para Bank application")
    public void i_am_in_the_log_in_page_of_the_para_bank_application() {
        testContext.getDriver().get(ConfigReader.getBaseUrl());
    }

    @When("I register a new user")
    public void i_register_a_new_user() {
        LoginPage loginPage = new LoginPage(testContext.getDriver());
        loginPage.clickRegister();

        RegisterPage registerPage = new RegisterPage(testContext.getDriver());
        String username = TestDataGenerator.generateUniqueUsername();
        String password = TestDataGenerator.getPassword();

        registerPage.register(
                TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getAddress(),
                TestDataGenerator.getCity(),
                TestDataGenerator.getState(),
                TestDataGenerator.getZipCode(),
                TestDataGenerator.getPhone(),
                TestDataGenerator.getSsn(),
                username,
                password
        );

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed");

        testContext.setUsername(username);
        testContext.setPassword(password);
    }

    @When("I log out")
    public void i_log_out() {
        LoginPage loginPage = new LoginPage(testContext.getDriver());
        loginPage.logout();
    }

    @When("I enter username and password from registration")
    public void i_enter_username_and_password_from_registration() {
        LoginPage loginPage = new LoginPage(testContext.getDriver());
        loginPage.login(testContext.getUsername(), testContext.getPassword());
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        LoginPage loginPage = new LoginPage(testContext.getDriver());
        loginPage.login(username, password);
    }

    @Then("I should be on the {string} page")
    public void i_should_be_on_the_page(String expectedPage) {
        if (expectedPage.equals("Overview")) {
            OverviewPage overviewPage = new OverviewPage(testContext.getDriver());
            Assert.assertTrue(overviewPage.isDisplayed(), "Overview page was not displayed");
        } else if (expectedPage.equals("Error")) {
            ErrorPage errorPage = new ErrorPage(testContext.getDriver());
            String expectedError = "The username and password could not be verified.";
            Assert.assertTrue(errorPage.hasErrorMessage(expectedError),
                    "Expected error message not found");
        }
    }

    @Then("I should see error message {string}")
    public void i_should_see_error_message(String errorMessage) {
        ErrorPage errorPage = new ErrorPage(testContext.getDriver());
        Assert.assertTrue(errorPage.hasErrorMessage(errorMessage),
                "Expected error message not found: " + errorMessage);
    }

    @Then("I should be taken to the Overview page")
    public void i_should_be_taken_to_the_overview_page() {
        OverviewPage overviewPage = new OverviewPage(testContext.getDriver());
        Assert.assertTrue(overviewPage.isDisplayed(), "Overview page was not displayed");
    }
}