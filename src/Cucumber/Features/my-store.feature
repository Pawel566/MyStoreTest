Feature: Create new Address as login user

  Scenario Outline: User can add new address
    Given I'm login on my store page with email "janxyz1@example.com" and password "user123"
    When I click addresses button
    And I click on "Create new address"
    And I enter alias "<alias>", address "<address>", city "<city>", zip "<zip>", country "<country>", phone "<phone>"
    Then I send form and i can see message "Address successfully added!"
    And Close browser

    Examples:
      | alias | address   | city       | zip   | country        | phone     |
      | Home  | Street 1  | London     | 12345 | United Kingdom | 123456789 |
      | Home2 | Street 10 | Manchester | 54321 | United Kingdom | 987654321 |
      | Home3 | Street 1  | Glasgow    | 99999 | United Kingdom | 111222333 |