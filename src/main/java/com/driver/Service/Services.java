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
        int maxFacility=0;
        String hotelName="";
        Map<String,Hotel>hotelDb=repositoryClassObj.getHotelDb();
        for(String id : hotelDb.keySet()){
            int facility=hotelDb.get(id).getFacilities().size();
            if(facility>maxFacility) hotelName=hotelDb.get(id).getHotelName();
            else if(facility==maxFacility){
                if(hotelName.compareTo(hotelDb.get(id).getHotelName())<0){
                    hotelName=hotelName;
                }else{
                    hotelName=hotelDb.get(id).getHotelName();
                }
            }
        }
        return hotelName;

    }

    public int bookingRoom(Booking booking){
        Map<String,Hotel> hotelMap=repositoryClassObj.getHotelDb();
        for(Hotel hotel : hotelMap.values()){
            if(hotel.getHotelName().equals(booking.getHotelName())){
                int availRoom=hotel.getAvailableRooms();
                int bookRooms=booking.getNoOfRooms();
                if(bookRooms>availRoom) return -1;
                else{
                    String bookingId=UUID.randomUUID().toString();
                    int aadharId=booking.getBookingAadharCard();
                    booking.setBookingId(bookingId);
                    repositoryClassObj.setBookingInDb(bookingId,booking);
                    repositoryClassObj.setCountBookingInDb(aadharId);
                    hotel.setAvailableRooms(availRoom-bookRooms);
                    int amountToBePaid=bookRooms*hotel.getPricePerNight();
                    return  hotel.getPricePerNight()*bookRooms;
                }
            }
        }
        return -1;
    }
    public int getBooking(int adharId){
        Map<Integer,Integer>countDbBooking=repositoryClassObj.getCountBookingDb();
        return countDbBooking.get(adharId);
    }

    public Hotel updateFacility(List<Facility> newFacilities,String hotelName){
        Map<String,Hotel>hotelMap=repositoryClassObj.getHotelDb();
        List<Facility>oldFacilities=hotelMap.get(hotelName).getFacilities();
        for(Facility facility : newFacilities){
            if(oldFacilities.contains(facility)) continue;
            else oldFacilities.add(facility);
        }
        Hotel hotel=hotelMap.get(hotelName);
        hotel.setFacilities(oldFacilities);
        hotelMap.put(hotelName,hotel);
        return hotel;
    }
}
