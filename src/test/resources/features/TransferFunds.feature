Feature: Transfer Funds

  @regression
  Scenario: Transfer funds between accounts
    Given I am logged in to the Para Bank application
    And I have a checking account and a savings account
    When I transfer 100 from checking to savings
    Then the transfer should be successful
    And the balances should reflect the transfer
