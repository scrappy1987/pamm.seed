Feature: Manage project
  As a user
  I want to view projects
  So that I know what projects are in progress

  Scenario: I can manage projects when there are no existing projects
    Given I am on the dashboard
    And There are no projects
    And I choose sample
    When I choose manage project
    Then There are no projects for me to manage

   Scenario: I can manage projects when there are existing projects
     Given I am on the dashboard
     And There are three projects
     And I choose sample
     When I choose manage project
     Then There are three projects for me to manage