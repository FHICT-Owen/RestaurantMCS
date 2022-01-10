package com.digitalmenu.restaurantservice.Ingredients;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityExistsException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IngredientServiceUnitTest {

    @Mock
    private IngredientRepository ingredientRepository ;
    @InjectMocks
    private IngredientService underTest;

    @BeforeEach
    void setUp() {underTest = new IngredientService(ingredientRepository);}

    @Test
    void canGetIngredients() {
        underTest.getIngredients();
        verify(ingredientRepository).findAll();
    }

    @Test
    void createIngredient() {
        Ingredient ingredient = new Ingredient(1, 1,"Paprika", "Paprika",true, true);

        underTest.createIngredient(ingredient);

        ArgumentCaptor<Ingredient> dishArgumentCaptor =
                ArgumentCaptor.forClass(Ingredient.class);
        verify(ingredientRepository)
                .save(dishArgumentCaptor.capture());

        Ingredient actual = dishArgumentCaptor.getValue();

        // then
        assertThat(actual).isEqualTo(ingredient);
    }

    @Test
    void willThrowWhenIngredientNameIsTakenOnCreate() {
        // given
        Ingredient ingredient = new Ingredient(1, 1,"Paprika", "Paprika",true, true);

        given(ingredientRepository.findIngredientByName(anyString()))
                .willReturn(java.util.Optional.of(ingredient));
        // when
        // then
        assertThatThrownBy(() -> underTest.createIngredient(ingredient))
                .isInstanceOf(EntityExistsException.class)
                .hasMessageContaining("Ingredient already exist!");
        verify(ingredientRepository, never()).save(any());
    }

    @Test
    void canRemoveIngredient() {
        // given
        int id = 42;

        given(ingredientRepository.existsById(id)).willReturn(true);
        // when
        // then
        underTest.removeIngredient(id);
        verify(ingredientRepository).deleteById(id);
    }

    @Test
    void willThrownWhenIdDoesNotExistOnRemove()
    {
        // given
        int id = 42;

        given(ingredientRepository.existsById(id)).willReturn(false);

        assertThatThrownBy(() -> underTest.removeIngredient(id))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Ingredient with id " + id + " does not exists!");
    }
}