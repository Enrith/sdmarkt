package com.superdupermarkt.strategy;

import com.superdupermarkt.model.Product;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

/**
 * A strategy for managing cheese products, including updating their quality,
 * price, and determining stock status.
 */
public class CheeseStrategy implements ProductStrategy {
    private static final int MIN_QUALITY = 30;
    private static final int MIN_DUE_DATE = 50;
    private static final int MAX_DUE_DATE = 100;
    private static final int QUALITY_DECAY_PER_DAY = 1;

    /**
     * Updates the quality of a cheese product. The quality decays by a fixed amount each day.
     *
     * @param product the cheese product to update
     * @param today   the current date
     */
    @Override
    public void updateQuality(Product product, LocalDate today) {
        long daysOnShelf = ChronoUnit.DAYS.between(product.getShelvedOn(), today);
        product.setQuality(product.getBaseQuality() - daysOnShelf * QUALITY_DECAY_PER_DAY);
    }

    /**
     * Updates the daily price of a cheese product.
     *
     * @param product the cheese product to update
     * @param today   the current date
     */
    @Override
    public void updatePrice(Product product, LocalDate today) {
        product.setDailyPrice(product.calculateDailyPrice());
    }

    /**
     * Checks if the cheese product should be removed from stock. A product is removed
     * if it has expired or its quality has fallen below a minimum threshold.
     *
     * @param product the cheese product to check
     * @param today   the current date
     * @return {@code true} if the product should be removed, {@code false} otherwise
     */
    @Override
    public boolean toBeRemoved(Product product, LocalDate today) {
        Predicate<Product> isExpired = p -> !today.isBefore(p.getDueDate());
        Predicate<Product> hasPoorQuality = p -> p.getQuality() < MIN_QUALITY;
        return isExpired.or(hasPoorQuality).test(product);
    }

    /**
     * Determines if a cheese product can be stocked. The product can be stocked
     * if it falls within a valid quality range and has a due date within the specified window.
     *
     * @param product the cheese product to check
     * @return {@code true} if the product can be stocked, {@code false} otherwise
     */
    @Override
    public boolean canBeStocked(Product product) {
        LocalDate minDueDate = LocalDate.now().plusDays(MIN_DUE_DATE);
        LocalDate maxDueDate = LocalDate.now().plusDays(MAX_DUE_DATE);
        Predicate<Product> respectsMinDate = p -> !p.getDueDate().isBefore(minDueDate);
        Predicate<Product> respectsMaxDate = p -> !p.getDueDate().isAfter(maxDueDate);
        Predicate<Product> validQuality = p -> p.getQuality() >= MIN_QUALITY;
        return respectsMinDate.and(respectsMaxDate).and(validQuality).test(product);
    }
}
