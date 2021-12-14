package com.example.restaurantmcs.Restaurant;
import com.example.restaurantmcs.Ingredients.Ingredient;
import com.example.restaurantmcs.Ingredients.IngredientController;
import com.example.restaurantmcs.Ingredients.IngredientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantController.class)
public class RestraurantControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    RestaurantService restaurantService;

    @Test
    void shouldDeleteRestaurant() throws Exception {
        mockMvc.perform(delete("/api/v1/restaurant/{id}", 1))
                .andDo(print()).andExpect(status().isOk());
    }

    private String convertObjectToJsonString(List<Restaurant> list) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private String convertObjectToJsonString(Restaurant item) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(item);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
