Feature:Patch booking

  @Booking
  Scenario: A user can successfully update name and surname in an existing booking
    Given an existing booking
    And a new session token
    When the user requests to update the name and surname in the booking
    Then the booking is patched successfully