Feature:Put booking

  @Booking
  Scenario: A user can successfully update an existing booking
    Given an existing booking
    And a new session token
    When the user requests to update the checkout date and the total price
    Then the booking is updated successfully