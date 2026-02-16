Feature: Login to VocaGame
      
  @Login @Regression
  Scenario Outline: Login with multiple credential combinations
    Given User open application
    When User navigate to login page
    And User input email "<email>"
    And User click continue button
    And User input password "<password>"
    And User click login button
    Then Login result should be "<result>"

    Examples:
      | email              			| password          | result        |
      | henricoverdian@gmail.com    | Dewarico123@  	| success       |
      | valid@gmail.com    			| WrongPassword     | wrong_pass    |
      |                    			|                   | empty_field   |