Feature:Get booking

  @Booking
  Scenario: A user can successfully get an existing booking
    Given an existing booking
    When the user requests an existing booking
    Then the booking is fetched successfully