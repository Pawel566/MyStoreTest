Feature: Add to cart product and buy it

  Scenario: User want buy product
    Given I'm login to buy a product with email "janxyz1@example.com" and password "user123"
    When I search product "Hummingbird Printed Sweater" and select it
    When I choose size and i change quanity
    And I add to cart selected product and proceed to checkout
    When I choose the shipping address
    And I select delivery and payment method
    Then I agree to the terms of service and place order
    And I do screenshot my order
