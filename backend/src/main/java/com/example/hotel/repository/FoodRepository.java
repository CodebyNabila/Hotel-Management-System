
package com.example.hotel.repository;

import com.example.hotel.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<FoodItem, Long> {}
