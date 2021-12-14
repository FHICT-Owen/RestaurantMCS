package com.example.restaurantmcs.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) { this.restaurantService = restaurantService; }

    @GetMapping("/{restaurantName}")
    public Optional<Restaurant> getRestaurantByName(@PathVariable("restaurantName") String restaurantName) { return restaurantService.getRestaurantByName(restaurantName); }

    @GetMapping
    public List<Restaurant> getRestaurants() { return restaurantService.getRestaurants(); }

    @PostMapping
    public void createRestaurant(@RequestBody Restaurant restaurant) { restaurantService.createRestaurant(restaurant); }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable("restaurantId") Integer id) {
        boolean success = restaurantService.updateRestaurant(id, restaurant);
        if (success)
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Integer id) {
        restaurantService.removeRestaurant(id);
    }
}
