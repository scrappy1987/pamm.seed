Feature: Loginpage
  As a user
  I want my credentials to be recognised
  So I am authorized to manage projecrs

  Scenario: Entering valid credentials into login page takes us to dashboard
    Given I am on the login view
    And I supply valid credentials
    When I login
    Then I see the dashboard
