
package com.example.hotel.repository;

import com.example.hotel.model.Room;
import com.example.hotel.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByRoomTypeAndBookedFalse(RoomType roomType);
    List<Room> findByBookedFalse();
}
