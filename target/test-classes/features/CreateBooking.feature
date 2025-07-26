Feature:Create booking

  @Booking
  Scenario: A user can successfully create a booking
    When a user requests a new booking
    Then the booking request is saved successfully