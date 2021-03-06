package com.digitalmenu.restaurantservice.Restaurant;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('access:restaurant')")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody @Valid Restaurant restaurant) {
        return new ResponseEntity<>(restaurantService.createRestaurant(restaurant), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('access:restaurant')")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody @Valid Restaurant restaurant) {
        restaurantService.updateRestaurant(restaurant);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('access:restaurant')")
    public void deleteRestaurant(@PathVariable Integer id) {
        restaurantService.deleteRestaurant(id);
    }
}
