package utils.models;

public class BookingsResponse
{
    private int bookingid;

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    @Override
    public String toString()
    {
        return "BookingId{" +
                "bookingid=" + bookingid +
                '}';
    }
}
