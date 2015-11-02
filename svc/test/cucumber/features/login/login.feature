Feature: Loginpage
  As a user
  I want to visit the login page
  So I can log into the application

  Scenario: Entering valid credentials into login page takes us to dashboard
    Given I am on the login view
    And I supply valid credentials
    When I login
    Then I see the dashboard
