package com.digitalmenu.restaurantservice.Restaurant;

import com.digitalmenu.restaurantservice.exception.common.ElementAlreadyExistsException;
import com.digitalmenu.restaurantservice.exception.common.NoSuchElementFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant createRestaurant(Restaurant restaurant) {

        if (restaurantRepository.existsRestaurantByName(restaurant.getName()))
            throw new ElementAlreadyExistsException("Restaurant with name: " + restaurant.getName() + " already exists");

        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Restaurant restaurant) {
        var foundRestaurant = restaurantRepository.findById(restaurant.getId())
                .orElseThrow(() -> new NoSuchElementFoundException("Restaurant not found"));
        foundRestaurant.setName(restaurant.getName());
        return restaurantRepository.save(foundRestaurant);
    }

    public void deleteRestaurant(Integer id) {
        if (!restaurantRepository.existsById(id))
            throw new NoSuchElementFoundException("Restaurant not found");

        restaurantRepository.deleteById(id);
    }
}
