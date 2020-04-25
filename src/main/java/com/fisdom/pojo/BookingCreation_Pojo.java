package com.fisdom.pojo;

/**
 * Created by nandita.dubey on 25/04/20.
 */
public class BookingCreation_Pojo {

    String firstname;
    String lastname;
    String totalprice;
    String depositpaid;
    BookingDates_Pojo bookingdates;
    String additionalneeds;


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(String depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDates_Pojo getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates_Pojo bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }
}
