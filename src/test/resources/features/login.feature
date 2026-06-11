Feature: Login

This feature verifies that a valid user can successfully
  log in to the ODPP application and navigate to the
  Registration page.

    @Smoke @Signin
    Scenario: Signin
    This scenario validates the end-to-end login functionality
    using valid credentials. The user launches the application,
    enters login details, selects the "Remember Me" option,
    clicks Sign In, and verifies that the Registration page
    is displayed successfully.

    
      Given I Launch the application
      When I should be on the Appian ODPP page
      When I enter the credentials
      When I click on Remember me checkbox
      And I cick on Signin 
      Then I should see the registration page



      
 
