package com.superdupermarkt.main;

import com.superdupermarkt.factory.ProductFactory;
import com.superdupermarkt.observer.ProductLogger;
import com.superdupermarkt.observer.ProductManager;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();
        manager.addProducts(ProductFactory.createDummyProductList());
        ProductLogger observer = new ProductLogger();
        manager.addObserver(observer);

        for (int i = 0; i <= 50; i++) {
            LocalDate today = LocalDate.now().plusDays(i);
            System.out.println("\nDay "+ i + ": " + today);
            manager.updateProducts(today);
        }

        manager.removeObserver(observer);
    }
}