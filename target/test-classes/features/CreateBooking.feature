Feature:Create booking
# Normally, we should test this with some negative scenarios but since
# this is a simple playground API we cannot have negative scenarios.
# For example, when you set checkout date to a date earlier than the checkin date
# the endpoint should return error but it is still creating the booking.

  @Booking
  Scenario: A user can successfully create a booking
    When a user requests a new booking
    Then the booking request is saved successfully
