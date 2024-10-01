package com.superdupermarkt.observer;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.superdupermarkt.model.Product;

/**
 * Manages products and notifies observers of stock updates and dead stock.
 */
@NoArgsConstructor
public class ProductManager {
    private final List<ProductObserver> observers = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();

    /**
     * Adds a product observer.
     *
     * @param observer the observer to be added
     */
    public void addObserver(ProductObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes a product observer.
     *
     * @param observer the observer to be removed
     */
    public void removeObserver(ProductObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies observers of updated product stock.
     *
     * @param products the updated list of products
     */
    public void notifyObserversStock(List<Product> products) {
        for (ProductObserver observer : observers) {
            observer.notifyStock(products);
        }
    }

    /**
     * Notifies observers of dead stock products.
     *
     * @param products the list of dead stock products
     */
    public void notifyObserversDeadStock(List<Product> products) {
        for (ProductObserver observer : observers) {
            observer.notifyDeadStock(products);
        }
    }

    /**
     * Adds multiple products.
     *
     * @param products the list of products to be added
     */
    public void addProducts(List<Product> products) {
        for (Product product : products) {
            addProduct(product);
        }
    }

    /**
     * Adds a single product if it can be stocked.
     *
     * @param product the product to be added
     */
    public void addProduct(Product product) {
        if (product.canBeStocked()) {
            products.add(product);
        } else {
            System.out.println("Product could not be stocked: " + product);
        }
    }

    /**
     * Updates product statuses and notifies observers of stock and dead stock changes.
     *
     * @param today the current date for product updates
     */
    public void updateProducts(LocalDate today) {
        Iterator<Product> iterator = products.iterator();
        List<Product> toBeRemoved = new ArrayList<>();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            product.updateProduct(today);
            if (product.toBeRemoved(today)) {
                toBeRemoved.add(product);
                iterator.remove();
            }
        }

        notifyObserversStock(products);
        notifyObserversDeadStock(toBeRemoved);
    }
}

