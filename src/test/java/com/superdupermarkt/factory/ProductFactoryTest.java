package com.superdupermarkt.factory;

import com.superdupermarkt.model.Cheese;
import com.superdupermarkt.model.Product;
import com.superdupermarkt.model.ProductType;
import com.superdupermarkt.model.Wine;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ProductFactory class.
 */
class ProductFactoryTest {

    @Test
    void testCreateCheeseProduct() {
        Product product = ProductFactory.createProduct(ProductType.CHEESE, "Gouda", 80, LocalDate.now().plusDays(30), 5.00);

        assertNotNull(product, "Product should not be null");
        assertInstanceOf(Cheese.class, product, "Product should be of type Cheese");
        assertEquals("Gouda", product.getDescription(), "Description should match");
        assertEquals(80, product.getQuality(), "Quality should match");
        assertEquals(5.00, product.getBasePrice(), "Base price should match");
        assertEquals(LocalDate.now().plusDays(30), product.getDueDate(), "Due date should match");
    }

    @Test
    void testCreateWineProduct() {
        Product product = ProductFactory.createProduct(ProductType.WINE, "Cabernet Sauvignon", 90, LocalDate.now().plusDays(365), 15.00);

        assertNotNull(product, "Product should not be null");
        assertInstanceOf(Wine.class, product, "Product should be of type Wine");
        assertEquals("Cabernet Sauvignon", product.getDescription(), "Description should match");
        assertEquals(90, product.getQuality(), "Quality should match");
        assertEquals(15.00, product.getBasePrice(), "Base price should match");
        assertEquals(LocalDate.now().plusDays(365), product.getDueDate(), "Due date should match");
    }

    @Test
    void testCreateInvalidProductType() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ProductFactory.createProduct(null, "Unknown", 50, LocalDate.now().plusDays(10), 10.00);
        });

        assertEquals("ProductType cannot be null", exception.getMessage(), "Exception message should match");
    }


    @Test
    void testCreateDummyProductList() {
        List<Product> products = ProductFactory.createDummyProductList();

        assertNotNull(products, "Product list should not be null");
        assertEquals(7, products.size(), "Dummy product list should contain 7 products");

        Product firstProduct = products.get(0);
        assertInstanceOf(Cheese.class, firstProduct, "First product should be a Cheese");
        assertEquals("Gouda", firstProduct.getDescription(), "First product description should match");
        assertEquals(80, firstProduct.getQuality(), "First product quality should match");

        Product lastProduct = products.get(6);
        assertInstanceOf(Wine.class, lastProduct, "Last product should be a Wine");
        assertEquals("Too low Quality Redwine", lastProduct.getDescription(), "Last product description should match");
        assertEquals(-1, lastProduct.getQuality(), "Last product quality should match");
    }
}
