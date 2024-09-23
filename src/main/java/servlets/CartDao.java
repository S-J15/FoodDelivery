package servlets;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CartDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sql@1521";

    // Load cart from the database based on the user ID
    public Map<Integer, Integer> loadCartFromDatabase(Integer userId) {
        Map<Integer, Integer> cart = new HashMap<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Establish the connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Query to get the product_id and quantity for a given user
            ps = conn.prepareStatement("SELECT product_id, quantity FROM user_carts WHERE user_id = ?");
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            // Iterate over the result set and populate the cart map
            while (rs.next()) {
                Integer productId = rs.getInt("product_id");  // Get product_id
                Integer quantity = rs.getInt("quantity");     // Get quantity
                cart.put(productId, quantity);               // Add to cart map
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources in the correct order
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cart;  // Return the cart data as a Map<Integer, Integer>
    }

    // Save cart to the database based on the user ID
    public void saveCartToDatabase(Integer userId, Map<Integer, Integer> cart) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Establish connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Clear the existing cart for the user
            ps = conn.prepareStatement("DELETE FROM user_carts WHERE user_id = ?");
            ps.setInt(1, userId);
            ps.executeUpdate();
            ps.close(); // Close the delete statement

            // Insert updated cart data
            ps = conn.prepareStatement("INSERT INTO user_carts (user_id, product_id, quantity) VALUES (?, ?, ?)");

            for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                Integer productId = entry.getKey();
                Integer quantity = entry.getValue();
                ps.setInt(1, userId);
                ps.setInt(2, productId);
                ps.setInt(3, quantity);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
