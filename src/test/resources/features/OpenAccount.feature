Feature: Open New Account

  @regression
  Scenario: Open a new checking account
    Given I am logged in to the Para Bank application
    When I open a new checking account
    Then the account should be created successfully

  @regression
  Scenario: Open a new savings account
    Given I am logged in to the Para Bank application
    When I open a new savings account
    Then the account should be created successfully
