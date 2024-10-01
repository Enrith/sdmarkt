package com.superdupermarkt.observer;

import com.superdupermarkt.model.Cheese;
import com.superdupermarkt.model.Product;
import lombok.Getter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductManagerTest {
    private ProductManager productManager;
    private DummyObserver dummyObserver;

    @BeforeEach
    public void setUp() {
        productManager = new ProductManager();
        dummyObserver = new DummyObserver();
        productManager.addObserver(dummyObserver);
    }

    @AfterEach
    public void tearDown() {
        productManager.removeObserver(dummyObserver);
    }

    @Test
    public void testAddProduct_CanBeStocked() {
        Cheese product = new Cheese("Gouda", 80, LocalDate.now().plusDays(60), 5.00);
        productManager.addProduct(product);
        productManager.updateProducts(LocalDate.now());
        assertTrue(dummyObserver.getStockNotifications().contains(product));
    }

    @Test
    public void testAddProduct_CannotBeStocked() {
        Cheese product = new Cheese("Low Quality Gouda", 29, LocalDate.now().plusDays(50), 5.00);
        productManager.addProduct(product);
        productManager.updateProducts(LocalDate.now());
        assertTrue(dummyObserver.getStockNotifications().isEmpty());
    }

    @Test
    public void testUpdateProducts_RemovesExpiredProducts() {
        LocalDate today = LocalDate.now();
        Cheese product1 = new Cheese("Gouda", 100, today.plusDays(70), 5.00);
        Cheese product2 = new Cheese("Past due Date Gouda", 100, today.plusDays(60), 5.00);

        productManager.addProduct(product1);
        productManager.addProduct(product2);
        productManager.updateProducts(today.plusDays(61));

        assertTrue(dummyObserver.getStockNotifications().contains(product1));
        assertFalse(dummyObserver.getStockNotifications().contains(product2));
        assertTrue(dummyObserver.getDeadStockNotifications().contains(product2));
    }

    @Getter
    private class DummyObserver implements ProductObserver {
        private final List<Product> stockNotifications = new ArrayList<>();
        private final List<Product> deadStockNotifications = new ArrayList<>();

        @Override
        public void notifyStock(List<Product> products) {
            stockNotifications.clear();
            stockNotifications.addAll(products);
        }

        @Override
        public void notifyDeadStock(List<Product> products) {
            deadStockNotifications.clear();
            deadStockNotifications.addAll(products);
        }
    }
}
