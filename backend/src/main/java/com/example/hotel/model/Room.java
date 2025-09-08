
package com.example.hotel.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {
    @Id
    private Integer roomNumber;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private boolean booked = false;

    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    private String secondaryName;
    private String secondaryContact;
    private String secondaryGender;

    private double roomChargePerDay;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodItem> foodOrders = new ArrayList<>();

    public Room() {}

    public Integer getRoomNumber() { return roomNumber; }
    public void setRoomNumber(Integer roomNumber) { this.roomNumber = roomNumber; }

    public RoomType getRoomType() { return roomType; }
    public void setRoomType(RoomType roomType) { this.roomType = roomType; }

    public boolean isBooked() { return booked; }
    public void setBooked(boolean booked) { this.booked = booked; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public String getSecondaryName() { return secondaryName; }
    public void setSecondaryName(String secondaryName) { this.secondaryName = secondaryName; }

    public String getSecondaryContact() { return secondaryContact; }
    public void setSecondaryContact(String secondaryContact) { this.secondaryContact = secondaryContact; }

    public String getSecondaryGender() { return secondaryGender; }
    public void setSecondaryGender(String secondaryGender) { this.secondaryGender = secondaryGender; }

    public double getRoomChargePerDay() { return roomChargePerDay; }
    public void setRoomChargePerDay(double roomChargePerDay) { this.roomChargePerDay = roomChargePerDay; }

    public List<FoodItem> getFoodOrders() { return foodOrders; }
    public void setFoodOrders(List<FoodItem> foodOrders) { this.foodOrders = foodOrders; }
    public void addFoodOrder(FoodItem f) { this.foodOrders.add(f); }
}
