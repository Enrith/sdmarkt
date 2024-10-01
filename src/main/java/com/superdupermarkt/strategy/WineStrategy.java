package com.superdupermarkt.strategy;

import com.superdupermarkt.model.Product;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * A strategy for managing wine products. Wine quality increases over time,
 * and its price does not change. Wine is never removed from stock.
 */
public class WineStrategy implements ProductStrategy {
    private static final long MAX_QUALITY = 50;

    /**
     * Updates the quality of a wine product. Quality increases over time, but is capped at a maximum value.
     *
     * @param product the wine product to update
     * @param today   the current date
     */
    @Override
    public void updateQuality(Product product, LocalDate today) {
        long quality = product.getQuality();

        if (quality >= MAX_QUALITY || product.getDueDate().isAfter(today)) {
            return;
        }

        long daysOnShelf = ChronoUnit.DAYS.between(product.getDueDate(), today);
        product.setQuality(product.getBaseQuality() + (daysOnShelf / 10));
    }

    /**
     * Price of wine products does not change over time.
     *
     * @param product the wine product to update
     * @param today   the current date
     */
    @Override
    public void updatePrice(Product product, LocalDate today) {
        // Wine prices remain constant.
        product.setDailyPrice(product.getBasePrice());
    }

    /**
     * Wine is never removed from stock.
     *
     * @param product the wine product to check
     * @param today   the current date
     * @return {@code false}, indicating wine is never removed
     */
    @Override
    public boolean toBeRemoved(Product product, LocalDate today) {
        return false; // Wine is never removed.
    }

    /**
     * Determines if a wine product can be stocked. Wine can be stocked as long as its quality is non-negative.
     *
     * @param product the wine product to check
     * @return {@code true} if the product's quality is non-negative, {@code false} otherwise
     */
    @Override
    public boolean canBeStocked(Product product) {
        return product.getQuality() >= 0;
    }
}
