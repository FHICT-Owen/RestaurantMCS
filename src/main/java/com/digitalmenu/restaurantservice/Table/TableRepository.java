package com.digitalmenu.restaurantservice.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<RestaurantTable, Integer> {
    List<RestaurantTable> findTablesByRestaurantId(Integer restaurantId);
    boolean existsRestaurantTableByTableNumberAndRestaurantId(Integer tableNumber, Integer restaurantId);
}
