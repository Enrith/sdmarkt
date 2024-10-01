package com.superdupermarkt.model;

import com.superdupermarkt.strategy.CheeseStrategy;

import java.time.LocalDate;

/**
 * Represents all cheese products.
 */
public class Cheese extends Product {

    /**
     * Constructor
     * @param description String
     * @param quality long, needs to be at least 30 for the cheese to be able to be put in stock.
     * @param dueDate LocalDate, needs to be in range of 50 to 100 days in the future.
     * @param basePrice double, base price used for the calculation of the daily price.
     */
    public Cheese(String description, long quality, LocalDate dueDate, double basePrice) {
        super(description, quality, dueDate, basePrice, new CheeseStrategy());
    }
}
