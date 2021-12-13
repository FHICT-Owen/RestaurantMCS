package com.example.restaurantmcs.Ingredients;

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

@WebMvcTest(IngredientController.class)
class IngredientControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IngredientService ingredientService;

    @Test
    void shouldReturnIngredients() throws Exception {
        Ingredient ingredient1 = new Ingredient(1, 1,"Paprika", true, true);
        Ingredient ingredient2 = new Ingredient(2, 1,"Oregano", false, false);

        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient2);

        when(ingredientService.getIngredients()).thenReturn(ingredientList);

        mockMvc.perform(get("/api/v1/ingredient"))
                .andDo(print()).andExpect(status().isOk()).andExpect(content().json(convertObjectToJsonString(ingredientList)));
    }

    @Test
    void shouldAddNewIngredient() throws Exception {
        Ingredient ingredient = new Ingredient(1, 1,"Paprika", true, true);

        mockMvc.perform(post("/api/v1/ingredient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonString(ingredient)))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldDeleteIngredient() throws Exception {
        mockMvc.perform(delete("/api/v1/ingredient/{id}", 1))
                .andDo(print()).andExpect(status().isOk());
    }

    private String convertObjectToJsonString(List<Ingredient> list) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private String convertObjectToJsonString(Ingredient item) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(item);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}