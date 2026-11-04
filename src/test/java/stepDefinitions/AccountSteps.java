package stepDefinitions;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;
import pages.OpenAccountPage;
import pages.RegisterPage;
import utils.ConfigReader;
import utils.TestDataGenerator;

public class AccountSteps {

    private TestContext testContext;

    public AccountSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("I am logged in to the Para Bank application")
    public void i_am_logged_in_to_the_para_bank_application() {
        testContext.getDriver().get(ConfigReader.getBaseUrl());

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

        loginPage.logout();
        loginPage.login(username, password);
    }

    @When("I open a new checking account")
    public void i_open_a_new_checking_account() {
        OpenAccountPage openAccountPage = new OpenAccountPage(testContext.getDriver());
        openAccountPage.openNewAccount("CHECKING");
        String accountId = openAccountPage.getAccountId();
        if (accountId != null) {
            testContext.setFromAccountId(accountId);
        }
    }

    @When("I open a new savings account")
    public void i_open_a_new_savings_account() {
        OpenAccountPage openAccountPage = new OpenAccountPage(testContext.getDriver());
        openAccountPage.openNewAccount("SAVINGS");
        String accountId = openAccountPage.getAccountId();
        if (accountId != null) {
            testContext.setToAccountId(accountId);
        }
    }

    @Then("the account should be created successfully")
    public void the_account_should_be_created_successfully() {
        OpenAccountPage openAccountPage = new OpenAccountPage(testContext.getDriver());
        Assert.assertTrue(openAccountPage.isAccountCreated(), "Account was not created successfully");
    }
}
