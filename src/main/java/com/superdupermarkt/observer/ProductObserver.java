package com.superdupermarkt.observer;

import com.superdupermarkt.model.Product;

import java.util.List;

/**
 * Interface for observing product stock changes.
 */
public interface ProductObserver {

    /**
     * Called when product stock is updated.
     *
     * @param products List<Product> a list of updated products
     */
    void notifyStock(List<Product> products);

    /**
     * Called when products become dead stock.
     *
     * @param products List<Product> a list of dead stock products
     */
    void notifyDeadStock(List<Product> products);
}

