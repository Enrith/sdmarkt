package com.superdupermarkt.factory;

import com.superdupermarkt.model.Cheese;
import com.superdupermarkt.model.Product;
import com.superdupermarkt.model.ProductType;
import com.superdupermarkt.model.Wine;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductFactory in charge of Product creation.
 */
public class ProductFactory {

    /**
     * static method, creating a single Product, based on the given parameters.
     * @param type ProductType
     * @param description String
     * @param quality long
     * @param dueDate LocalDate
     * @param basePrice double
     * @return Product of the given parameters.
     * @throws IllegalArgumentException if unknown ProductType is supplied.
     */
    public static Product createProduct(ProductType type, String description, long quality, LocalDate dueDate, double basePrice) {
        if (type == null) {
            throw new IllegalArgumentException("ProductType cannot be null");
        }

        switch (type) {
            case CHEESE:
                return new Cheese(description, quality, dueDate, basePrice);
            case WINE:
                return new Wine(description, quality, dueDate, basePrice);
            default:
                throw new IllegalArgumentException("Unknown ProductType: " + type);
        }
    }

    /**
     * Creates and returns a list of dummy products, able to showcase the different use cases of product stocking / deterioration.
     * @return a dummy list of products
     */
    public static List<Product> createDummyProductList() {
        List<Product> products = new ArrayList<>();

        products.add(ProductFactory.createProduct(ProductType.CHEESE, "Gouda", 80, LocalDate.now().plusDays(50), 5.00));
        products.add(ProductFactory.createProduct(ProductType.CHEESE, "Gouda", 35, LocalDate.now().plusDays(50), 5.00));
        products.add(ProductFactory.createProduct(ProductType.CHEESE, "Low Quality Gouda", 29, LocalDate.now().plusDays(50), 5.00));
        products.add(ProductFactory.createProduct(ProductType.CHEESE, "Too short due date Gouda", 35, LocalDate.now().plusDays(49), 5.00));
        products.add(ProductFactory.createProduct(ProductType.CHEESE, "Too long due date Gouda", 35, LocalDate.now().plusDays(101), 5.00));

        products.add(ProductFactory.createProduct(ProductType.WINE, "Redwine", 20, LocalDate.now(), 10.00));
        products.add(ProductFactory.createProduct(ProductType.WINE, "Too low Quality Redwine", -1, LocalDate.now(), 10.00));

        return products;
    }
}
