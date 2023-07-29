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


    public String addHotel(Hotel hotel){
        try{
            String hotelName=hotel.getHotelName();
            hotelDb.put(hotelName,hotel);
            return "SUCCESS";
        }catch(Exception e){
            return "FAILURE";
        }

    }

    public Integer addUser(User user){
        int id=user.getaadharCardNo();
        userDb.put(id,user);
        return id;
    }

    public List<Hotel> getListOfHotel(){
        List<Hotel>hotelList=new ArrayList<>();
        for(String key : hotelDb.keySet()){
            hotelList.add(hotelDb.get(key));
        }
        return hotelList;
    }

    public int newBooking(Booking booking,Hotel hotel){
        String bookingId=booking.getBookingId();
        int amountToBePaid=booking.getNoOfRooms()*hotel.getPricePerNight();
        booking.setAmountToBePaid(amountToBePaid);
        bookingDb.put(bookingId,booking);
        return booking.getAmountToBePaid();
    }

}
