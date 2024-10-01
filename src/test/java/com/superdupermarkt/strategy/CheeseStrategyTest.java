package com.superdupermarkt.strategy;

import org.junit.jupiter.api.Test;

import com.superdupermarkt.model.Cheese;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CheeseStrategyTest {

    @Test
    void testCheeseQualityReduction() {
        Cheese cheese = new Cheese("Guoda", 35, LocalDate.now().plusDays(50), 2.5);
        assertEquals(35, cheese.getQuality());
        cheese.updateProduct(LocalDate.now().plusDays(1));
        assertEquals(34, cheese.getQuality());
    }

    @Test
    void testCheeseDailyPriceChange() {
        Cheese cheese = new Cheese("Guoda", 35, LocalDate.now().plusDays(50), 2.5);
        assertEquals((2.5 + 35 * 0.1), cheese.getDailyPrice());
        cheese.updateProduct(LocalDate.now().plusDays(1));
        assertEquals((2.5 + 34 * 0.1), cheese.getDailyPrice());
    }

    @Test
    void testCheeseToBeRemovedThroughQuality() {
        Cheese cheese = new Cheese("Guoda", 29, LocalDate.now().plusDays(50), 2.5);
        assertEquals(29, cheese.getQuality());
        assertTrue(cheese.toBeRemoved(LocalDate.now()));
    }

    @Test
    void testCheeseToNotBeRemovedThroughQuality() {
        Cheese cheese = new Cheese("Guoda", 31, LocalDate.now().plusDays(50), 2.5);
        assertEquals(31, cheese.getQuality());
        assertFalse(cheese.toBeRemoved(LocalDate.now()));
    }

    @Test
    void testCheeseToBeRemovedThroughDueDate() {
        Cheese cheese = new Cheese("Guoda", 31, LocalDate.now(), 2.5);
        assertEquals(31, cheese.getQuality());
        assertTrue(cheese.toBeRemoved(LocalDate.now().plusDays(1)));
    }

    @Test
    void testCheeseToNotBeRemovedThroughDueDate() {
        Cheese cheese = new Cheese("Guoda", 31, LocalDate.now().plusDays(1), 2.5);
        assertEquals(31, cheese.getQuality());
        assertFalse(cheese.toBeRemoved(LocalDate.now()));
    }

    @Test
    void testCheeseCanBeStocked() {
        Cheese cheese = new Cheese("Guoda", 30, LocalDate.now().plusDays(50), 2.5);
        assertTrue(cheese.canBeStocked());
    }

    @Test
    void testCheeseCantBeStockedDueDateTooEarly() {
        Cheese cheese = new Cheese("Guoda", 30, LocalDate.now().plusDays(49), 2.5);
        assertFalse(cheese.canBeStocked());
    }

    @Test
    void testCheeseCantBeStockedDueDateTooLate() {
        Cheese cheese = new Cheese("Guoda", 30, LocalDate.now().plusDays(101), 2.5);
        assertFalse(cheese.canBeStocked());
    }

    @Test
    void testCheeseCantBeStockedQualityTooLow() {
        Cheese cheese = new Cheese("Guoda", 29, LocalDate.now().plusDays(50), 2.5);
        assertFalse(cheese.canBeStocked());
    }
}
