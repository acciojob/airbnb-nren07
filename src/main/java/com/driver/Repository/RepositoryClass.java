package com.driver.Repository;

import com.driver.model.Booking;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositoryClass {
    Map<String,Hotel>hotelDb=new HashMap<>();
    Map<Integer, User>userDb=new HashMap<>();
    Map<String, Booking>bookingDb=new HashMap<>();
    Map<Integer,Integer>countBookingDb=new HashMap<>();

    public Map<Integer, User> getUserDb() {
        return userDb;
    }

    public Map<String, Booking> getBookingDb() {
        return bookingDb;
    }

    public Map<Integer, Integer> getCountBookingDb() {
        return countBookingDb;
    }

    public void setHotelDb(Map<String, Hotel> hotelDb) {
        this.hotelDb = hotelDb;
    }

    public void setUserDb(Map<Integer, User> userDb) {
        this.userDb = userDb;
    }

    public void setBookingInDb(String id, Booking booking) {
        this.bookingDb.put(id,booking);
    }

    public void setCountBookingInDb(int aadharID) {
        this.countBookingDb.put(aadharID,this.countBookingDb.getOrDefault(aadharID,0)+1);
    }

    public String addHotel(Hotel hotel){
        String hotelName=hotel.getHotelName();
        if(hotelDb.containsKey(hotelName) || hotelName==null || hotel==null) return "FAILURE";
        hotelDb.put(hotelName,hotel);
        return "SUCCESS";
    }

    public Integer addUser(User user){
        int id=user.getaadharCardNo();
        userDb.put(id,user);
        return id;
    }

    public Map<String, Hotel> getHotelDb() {
        return hotelDb;
    }
}
