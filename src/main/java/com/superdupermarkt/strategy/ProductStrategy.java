package com.superdupermarkt.strategy;

import com.superdupermarkt.model.Product;
import java.time.LocalDate;

/**
 * Strategy interface for updating product quality, price, and stock status.
 */
public interface ProductStrategy {

    /**
     * Updates the quality of a product based on the given date.
     *
     * @param product the product to update
     * @param today   the given date
     */
    void updateQuality(Product product, LocalDate today);

    /**
     * Updates the price of a product based on the given date.
     *
     * @param product the product to update
     * @param today   the given date
     */
    void updatePrice(Product product, LocalDate today);

    /**
     * Determines if a product should be removed from stock.
     *
     * @param product the product to check
     * @param today   the given date
     * @return {@code true} if the product should be removed, {@code false} otherwise
     */
    boolean toBeRemoved(Product product, LocalDate today);

    /**
     * Determines if a product can be stocked.
     *
     * @param product the product to check
     * @return {@code true} if the product can be stocked, {@code false} otherwise
     */
    boolean canBeStocked(Product product);
}
