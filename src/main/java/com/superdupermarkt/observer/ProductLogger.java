package com.superdupermarkt.observer;

import com.superdupermarkt.model.Product;

import java.util.List;

/**
 * ProductLogger implementing the ProductObserver interface.
 * Prints notifications to stdout.
 */
public class ProductLogger implements ProductObserver{

    /**
     * Prints stock notification to stdout.
     * @param products List<Product>
     */
    @Override
    public void notifyStock(List<Product> products) {
        System.out.println("Current stock:");
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    /**
     * Prints dead stock notification to stdout.
     * @param products List<Product>
     */
    @Override
    public void notifyDeadStock(List<Product> products) {
        System.out.println("Dead stock:");
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }
}
