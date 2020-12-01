/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.northeastern.cs5500.delivery.controller;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import edu.northeastern.cs5500.delivery.model.FoodItem;
import edu.northeastern.cs5500.delivery.repository.InMemoryRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FoodItemControllerTest {
    FoodItem testFoodItem1;
    FoodItem testFoodItem2;
    FoodItem testFoodItem3;

    @BeforeEach
    public void init() {
        testFoodItem1 = new FoodItem();
        testFoodItem1.setId(new ObjectId());
        testFoodItem1.setFoodItem("Philly Cheesesteak sandwich");
        testFoodItem1.setFoodPrice(1199);

        testFoodItem2 = new FoodItem();
        testFoodItem2.setId(new ObjectId());
        testFoodItem2.setFoodItem("Italian Classic sandwich");
        testFoodItem2.setFoodPrice(1299);

        testFoodItem3 = new FoodItem();
        testFoodItem3.setId(new ObjectId());
        testFoodItem3.setFoodItem("Spiced Tofu sandwich");
        testFoodItem3.setFoodPrice(1199);
    }

    @Test
    void testRegisterCreatesFoodItems() {
        FoodItemController foodItemController =
                new FoodItemController(new InMemoryRepository<FoodItem>());
        assertThat(foodItemController.getFoodItems()).isNotEmpty();
    }

    @Test
    void testRegisterCreatesValidFoodItems() {
        FoodItemController foodItemController =
                new FoodItemController(new InMemoryRepository<FoodItem>());

        for (FoodItem foodItem : foodItemController.getFoodItems()) {
            assertWithMessage(foodItem.getFoodItem()).that(foodItem.isValid()).isTrue();
        }
    }

    @Test
    void testCanAddFoodItem() throws ExceptionClass {
        FoodItemController foodItemController =
                new FoodItemController(new InMemoryRepository<FoodItem>());
        ObjectId addedFoodItemId = testFoodItem1.getId();
        FoodItem addedFoodItem1 = foodItemController.addFoodItem(testFoodItem1);

        FoodItem addedFoodItemInCollection = foodItemController.getFoodItem(addedFoodItemId);
        assertEquals(addedFoodItem1, addedFoodItemInCollection);
        assertEquals(testFoodItem1.getFoodItem(), addedFoodItemInCollection.getFoodItem());
    }

    @Test
    void testCanUpdateFoodItem() throws ExceptionClass {
        FoodItemController foodItemController =
                new FoodItemController(new InMemoryRepository<FoodItem>());
        FoodItem addedFoodItem = foodItemController.addFoodItem(testFoodItem2);
        ObjectId addedFoodItemId2 = addedFoodItem.getId();
        Integer newPrice = 1350;
        addedFoodItem.setFoodPrice(1350);
        foodItemController.updateFoodItem(addedFoodItem);
        Integer addedPrice = foodItemController.getFoodItem(addedFoodItemId2).getFoodPrice();
        assertEquals(newPrice, addedPrice);
    }

    @Test
    void testCanDeleteFoodItem() throws ExceptionClass {
        FoodItemController foodItemController =
                new FoodItemController(new InMemoryRepository<FoodItem>());
        FoodItem addedFoodItem = foodItemController.addFoodItem(testFoodItem3);
        ObjectId addedFoodItemId3 = addedFoodItem.getId();
        foodItemController.deleteFoodItem(addedFoodItemId3);
        assertNotEquals(addedFoodItemId3, foodItemController.getFoodItem(addedFoodItemId3));
    }
}