package com.superdupermarkt.strategy;

import org.junit.jupiter.api.Test;

import com.superdupermarkt.model.Wine;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WineStrategyTest {

    @Test
    void testWineUpdateQuality() {
        Wine wine = new Wine("Rosé", 35, LocalDate.now(), 30.0);
        assertEquals(35, wine.getQuality());
        wine.updateProduct(LocalDate.now().plusDays(1));
        assertEquals(35, wine.getQuality());
        wine.updateProduct(LocalDate.now().plusDays(10));
        assertEquals(36, wine.getQuality());
        wine.updateProduct(LocalDate.now().plusDays(30));
        assertEquals(38, wine.getQuality());
    }


    @Test
    void testWineUpdateQualityMaximumQuality() {
        Wine wine = new Wine("Rosé", 50, LocalDate.now(), 30.0);
        assertEquals(50, wine.getQuality());
        wine.updateProduct(LocalDate.now().plusDays(10));
        assertEquals(50, wine.getQuality());
    }

    @Test
    void testWineUpdateQualityDueDateFarInFuture() {
        Wine wine = new Wine("Rosé", 40, LocalDate.now().plusDays(30), 30.0);
        assertEquals(40, wine.getQuality());
        wine.updateProduct(LocalDate.now().plusDays(10));
        assertEquals(40, wine.getQuality());
    }

    @Test
    void testWinePriceChange() {
        Wine wine = new Wine("Rosé", 50, LocalDate.now(), 30.0);
        assertEquals(30.0, wine.getDailyPrice());
        wine.updateProduct(LocalDate.now().plusDays(1));
        assertEquals(30.0, wine.getDailyPrice());
    }

    @Test
    void testWineToBeRemoved() {
        Wine wine = new Wine("Rosé", 50, LocalDate.now(), 30.0);
        assertFalse(wine.toBeRemoved(LocalDate.now()));
    }

    @Test
    void testWineCanBeStocked() {
        Wine wine = new Wine("Rosé", 50, LocalDate.now(), 30.0);
        assertTrue(wine.canBeStocked());
    }

    @Test
    void testWineCantBeStocked() {
        Wine wine = new Wine("Rosé", -50, LocalDate.now(), 30.0);
        assertFalse(wine.canBeStocked());
    }
}
