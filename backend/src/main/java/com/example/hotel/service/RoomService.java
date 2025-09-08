
package com.example.hotel.service;

import com.example.hotel.dto.BookingRequest;
import com.example.hotel.dto.CheckoutResponse;
import com.example.hotel.dto.FoodRequest;
import com.example.hotel.exception.ResourceNotFoundException;
import com.example.hotel.model.*;
import com.example.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RoomService {

    private final RoomRepository roomRepo;

    public RoomService(RoomRepository roomRepo) {
        this.roomRepo = roomRepo;
        initRoomsIfEmpty();
    }

    private void initRoomsIfEmpty() {
        if(roomRepo.count() == 0) {
            for(int i=1;i<=10;i++){
                Room r = new Room();
                r.setRoomNumber(i);
                r.setRoomType(RoomType.LUXURY_DOUBLE);
                r.setRoomChargePerDay(4000);
                roomRepo.save(r);
            }
            for(int i=11;i<=30;i++){
                Room r = new Room();
                r.setRoomNumber(i);
                r.setRoomType(RoomType.DELUXE_DOUBLE);
                r.setRoomChargePerDay(3000);
                roomRepo.save(r);
            }
            for(int i=31;i<=40;i++){
                Room r = new Room();
                r.setRoomNumber(i);
                r.setRoomType(RoomType.LUXURY_SINGLE);
                r.setRoomChargePerDay(2200);
                roomRepo.save(r);
            }
            for(int i=41;i<=60;i++){
                Room r = new Room();
                r.setRoomNumber(i);
                r.setRoomType(RoomType.DELUXE_SINGLE);
                r.setRoomChargePerDay(1200);
                roomRepo.save(r);
            }
        }
    }

    public List<Room> getAvailableRooms() {
        return roomRepo.findByBookedFalse();
    }

    public List<Room> getAvailableByType(RoomType type) {
        return roomRepo.findByRoomTypeAndBookedFalse(type);
    }

    @Transactional
    public Room bookRoom(Integer roomNumber, BookingRequest req) {
        Room room = roomRepo.findById(roomNumber).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        if(room.isBooked()) throw new IllegalStateException("Room already booked");
        Customer c = new Customer(req.name, req.contact, req.gender);
        room.setCustomer(c);
        if(req.name2 != null && !req.name2.isBlank()){
            room.setSecondaryName(req.name2);
            room.setSecondaryContact(req.contact2);
            room.setSecondaryGender(req.gender2);
        }
        room.setBooked(true);
        return roomRepo.save(room);
    }

    public Room getRoom(Integer roomNumber) {
        return roomRepo.findById(roomNumber).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
    }

    @Transactional
    public Room addFoodOrder(Integer roomNumber, FoodRequest req) {
        Room room = getRoom(roomNumber);
        if(!room.isBooked()) throw new IllegalStateException("Room not booked");
        String[] menu = {"Sandwich","Pasta","Noodles","Coke"};
        double[] prices = {50,60,70,30};
        int idx = req.itemNo - 1;
        if(idx < 0 || idx >= menu.length) throw new IllegalArgumentException("Invalid menu item");
        FoodItem fi = new FoodItem(menu[idx], req.quantity, prices[idx] * req.quantity);
        room.addFoodOrder(fi);
        return roomRepo.save(room);
    }

    @Transactional
    public CheckoutResponse checkout(Integer roomNumber) {
        Room room = getRoom(roomNumber);
        if(!room.isBooked()) throw new IllegalStateException("Room not booked");
        double amount = 0;
        double roomCharge = room.getRoomChargePerDay();
        amount += roomCharge;
        List<String> foodSummary = new ArrayList<>();
        for(FoodItem fi : room.getFoodOrders()){
            amount += fi.getPrice();
            foodSummary.add(fi.getName() + " x " + fi.getQuantity() + " = " + fi.getPrice());
        }
        room.setBooked(false);
        room.setCustomer(null);
        room.setSecondaryName(null);
        room.setSecondaryContact(null);
        room.setSecondaryGender(null);
        room.getFoodOrders().clear();
        roomRepo.save(room);

        CheckoutResponse resp = new CheckoutResponse();
        resp.roomCharge = roomCharge;
        resp.foodSummary = foodSummary;
        resp.total = amount;
        return resp;
    }
}
