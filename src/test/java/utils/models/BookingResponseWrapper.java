package utils.models;

public class BookingResponseWrapper {

        private int bookingid;
        private BookingRequest booking;

        public int getBookingid() {
            return bookingid;
        }

        public void setBookingid(int bookingid) {
            this.bookingid = bookingid;
        }

        public BookingRequest getBooking() {
            return booking;
        }

        public void setBooking(BookingRequest booking) {
            this.booking = booking;
        }
    }
