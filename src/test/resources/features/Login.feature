Feature: Login Functionality

  @smoke
  Scenario: Login with a newly registered user
    Given I am in the log in page of the Para Bank application
    When I register a new user
    And I log out
    And I enter username and password from registration
    Then I should be taken to the Overview page

  @negative @regression
  Scenario Outline: Login with invalid credentials
    Given I am in the log in page of the Para Bank application
    When I enter username "<username>" and password "<password>"
    Then I should see error message "<errorMessage>"

    Examples:
      | username  | password        | errorMessage                                             |
      | invalid   | invalid         | The username and password could not be verified.         |
      | keith123  | wrongpassword   | The username and password could not be verified.         |
      | wronguser | secretpassword  | The username and password could not be verified.         |
      |           | TestPass123     | Please enter a username and password.                    |
      | testuser  |                 | Please enter a username and password.                    |
      |           |                 | Please enter a username and password.                    |