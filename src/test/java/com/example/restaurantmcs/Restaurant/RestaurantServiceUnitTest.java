package com.example.restaurantmcs.Restaurant;
import com.example.restaurantmcs.Ingredients.IngredientRepository;
import com.example.restaurantmcs.Ingredients.IngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityExistsException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceUnitTest {
    @Mock
    private RestaurantRepository restaurantRepository ;
    @InjectMocks
    private RestaurantService underTest;

    @BeforeEach
    void setUp() {underTest = new RestaurantService(restaurantRepository);}

    @Test
    void canRemoveRestaurant() {
        // given
        int id = 3;

        given(restaurantRepository.existsById(id)).willReturn(true);
        // when
        // then
        underTest.removeRestaurant(id);
        verify(restaurantRepository).deleteRestaurantById(id);
    }

    @Test
    void willThrownWhenIdDoesNotExistOnRemove()
    {
        // given
        int id = 42;

        given(restaurantRepository.existsById(id)).willReturn(false);

        assertThatThrownBy(() -> underTest.removeRestaurant(id))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Restaurant with id " + id + " does not exist!");
    }
}
