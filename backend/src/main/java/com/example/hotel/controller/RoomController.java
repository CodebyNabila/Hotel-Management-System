
package com.example.hotel.controller;

import com.example.hotel.dto.BookingRequest;
import com.example.hotel.dto.CheckoutResponse;
import com.example.hotel.dto.FoodRequest;
import com.example.hotel.model.Room;
import com.example.hotel.model.RoomType;
import com.example.hotel.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {

    private final RoomService roomService;
    public RoomController(RoomService roomService) { this.roomService = roomService; }

    @GetMapping("/available")
    public List<Room> available() {
        return roomService.getAvailableRooms();
    }

    @GetMapping("/available/{type}")
    public List<Room> availableByType(@PathVariable String type) {
        RoomType rt = RoomType.valueOf(type.toUpperCase());
        return roomService.getAvailableByType(rt);
    }

    @PostMapping("/book/{roomNumber}")
    public Room book(@PathVariable Integer roomNumber, @RequestBody BookingRequest req) {
        return roomService.bookRoom(roomNumber, req);
    }

    @PostMapping("/order/{roomNumber}")
    public Room order(@PathVariable Integer roomNumber, @RequestBody FoodRequest req) {
        return roomService.addFoodOrder(roomNumber, req);
    }

    @PostMapping("/checkout/{roomNumber}")
    public CheckoutResponse checkout(@PathVariable Integer roomNumber) {
        return roomService.checkout(roomNumber);
    }

    @GetMapping("/{roomNumber}")
    public Room get(@PathVariable Integer roomNumber) {
        return roomService.getRoom(roomNumber);
    }
}
