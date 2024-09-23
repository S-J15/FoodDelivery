package servlets;

import java.util.*;

public class OrderSummary {
    private Map<Integer, Integer> products; // Product ID and quantity
    private double totalAmount;
    private Date orderDate;

    // Constructor
    public OrderSummary() {
        this.products = new HashMap<>(); // Initialize the map
    }

    // Getters and Setters
    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Integer> products) {
        this.products = products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
