package com.digitalmenu.restaurantservice.Restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
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
        underTest.deleteRestaurant(id);
        verify(restaurantRepository).deleteById(id);
    }

    @Test
    void willThrownWhenIdDoesNotExistOnRemove()
    {
        // given
        int id = 42;

        given(restaurantRepository.existsById(id)).willReturn(false);

        assertThatThrownBy(() -> underTest.deleteRestaurant(id))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Restaurant with id " + id + " does not exist!");
    }
}
