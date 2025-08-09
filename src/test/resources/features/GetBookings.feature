Feature:Get bookings

  # Normally, it is better practice to check if the endpoint is returning correct number of bookings from the database.
  # This can be done by fetching the existing bookings with a linq or testing api and comparing the number returned by
  # the get endpoint but since we don't have database access we are just verifying that the response isn't null.

  @Booking
  Scenario: A user can successfully get all existing booking
    When the user requests all existing booking
    Then all booking are fetched successfully