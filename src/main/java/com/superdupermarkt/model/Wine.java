package com.superdupermarkt.model;

import com.superdupermarkt.strategy.WineStrategy;
import java.time.LocalDate;

/**
 * Represents all wine products.
 */
public class Wine extends Product {

    /**
     * Constructor
     * @param description String
     * @param quality long, needs to be equal or greater than zero.
     * @param dueDate LocalDate, used in quality calculation.
     * @param basePrice double
     */
    public Wine(String description, long quality, LocalDate dueDate, double basePrice) {
        super(description, quality, dueDate, basePrice, new WineStrategy());
        // Wine does not have a daily price, and is always the base price.
        dailyPrice = basePrice;
    }
}
