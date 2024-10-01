package com.superdupermarkt.model;

import com.superdupermarkt.strategy.ProductStrategy;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * Represents general products.
 */
@Getter
@Setter
public abstract class Product {
    private static final double PRICE_PER_QUALITY = 0.10;

    protected String description;
    private final long baseQuality;
    protected long quality;
    private final LocalDate shelvedOn;
    protected LocalDate dueDate;
    protected final double basePrice;
    protected double dailyPrice;

    private final ProductStrategy strategy;

    /**
     * Constructor
     * @param description String
     * @param quality long
     * @param dueDate LocalDate
     * @param basePrice double
     * @param strategy ProductStrategy
     */
    public Product(String description, long quality, LocalDate dueDate, double basePrice, ProductStrategy strategy) {
        this.basePrice = basePrice;
        this.description = description;
        this.baseQuality = quality;
        this.quality = quality;
        this.shelvedOn = LocalDate.now();
        this.dueDate = dueDate;
        this.strategy = strategy;
        this.dailyPrice = calculateDailyPrice();
    }

    /**
     * Updates the product according to the given LocalDate.
     * @param today LocalDate
     */
    public void updateProduct(LocalDate today) {
        strategy.updateQuality(this, today);
        strategy.updatePrice(this, today);
    }

    /**
     * Checks if the product needs to be removed from stock.
     * @param today LocalDate
     * @return {@code true} if the product should be removed, {@code false} otherwise
     */
    public boolean toBeRemoved(LocalDate today) {
        return strategy.toBeRemoved(this, today);
    }


    /**
     * Checks if the product can be stocked.
     * @return {@code true} if the product can be stocked, {@code false} otherwise
     */
    public boolean canBeStocked() {
        return strategy.canBeStocked(this);
    }

    /**
     * Calculates the daily prize on the base formula that is the same for all Products.
     * @return double, daily price.
     */
    public double calculateDailyPrice() {
        return basePrice + PRICE_PER_QUALITY * quality;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        return "Product{" +
                "description='" + description + '\'' +
                ", quality=" + quality +
                ", dueDate=" + dueDate +
                ", dailyPrice=" + df.format(dailyPrice) +
                '}';
    }
}
