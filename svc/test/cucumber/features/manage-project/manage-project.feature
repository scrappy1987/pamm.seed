Feature: Manage project
  As a user
  I want navigate to the manage projects page
  So I see my current project list

  Scenario: I click manage products when there are no projects
    Given I am on the dashboard
    And There are no projects
    And I choose sample
    When I choose manage project
    Then There are no projects for me to manage

   Scenario: I click manage products when there are existing projects
     Given I am on the dashboard
     And There are three projects
     And I choose sample
     When I choose manage project
     Then There are three projects for me to manage
