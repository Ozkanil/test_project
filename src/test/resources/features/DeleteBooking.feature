Feature:Delete booking

  @Booking
  Scenario: A user can successfully delete an existing booking
    Given an existing booking
    And a new session token
    When the user requests to delete the booking
    Then the booking is deleted successfully