package com.driver.Service;

import com.driver.Repository.RepositoryClass;
import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Services {
    @Autowired
    private RepositoryClass repositoryClassObj;

    public String addHotel(Hotel hotel){
        return repositoryClassObj.addHotel(hotel);
    }
    public Integer addUser(User user){
        return repositoryClassObj.addUser(user);
    }

    public String getHotelWithMostFacilities(){
        List<Hotel>hotelList=repositoryClassObj.getListOfHotel();
        Collections.sort(hotelList,(a,b)->{
            int cmp=b.getFacilities().size()-
            a.getFacilities().size();
            if(cmp!=0) return cmp;
            else return a.getHotelName().compareTo(b.getHotelName());
        });
        if(hotelList.size()>0) return hotelList.get(0).getHotelName();
        return "";
    }

    public int bookingRoom(Booking booking){
        for(Hotel hotel : repositoryClassObj.getListOfHotel()){
            if(hotel.getHotelName().equals(booking.getHotelName())){
                int availRoom=hotel.getAvailableRooms();
                int bookRoom=booking.getNoOfRooms();

                if(bookRoom>availRoom) return -1;
                else{
                    hotel.setAvailableRooms(availRoom-bookRoom);
                    return repositoryClassObj.newBooking(booking,hotel);
                }
            }
        }
        return -1;
    }
}
