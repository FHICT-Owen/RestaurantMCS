package com.example.restaurantmcs.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) { this.restaurantRepository = restaurantRepository; }

    public void createRestaurant(Restaurant restaurant) {
        Optional<Restaurant> restaurantByName = restaurantRepository.findRestaurantByName(restaurant.getName());
        if (restaurantByName.isPresent())
            throw new EntityExistsException("Name already taken!");
        restaurantRepository.save(restaurant);
    }

    public boolean updateRestaurant(Integer id, Restaurant restaurant) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if(optionalRestaurant.isPresent()) {
            Restaurant actualRestaurant = optionalRestaurant.get();
            actualRestaurant.setName(restaurant.getName());
            restaurantRepository.save(actualRestaurant);
            return true;
        }
        return false;
    }

    public List<Restaurant> getRestaurants() { return restaurantRepository.findAll(); }

    public Optional<Restaurant> getRestaurantByName(String name) { return restaurantRepository.findRestaurantByName(name); }
}
