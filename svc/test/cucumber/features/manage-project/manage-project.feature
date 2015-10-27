Feature: Manage project
  As a user
  I want navigate to the manage projects page
  So I see my current project list

  Scenario: I click manage products and it takes us to our projects
    Given I am on the dashboard
    And No projects are currently defined
    When I click on sample
    Then I click on manage project
    Then It takes me to an empty project list

   Scenario: I click manage products and it takes us to our projects
     Given I am on the dashboard
     And there has been three projects already added
     When I click on sample
     Then I click on manage project
     Then It takes me to a project list with three projects already populated
