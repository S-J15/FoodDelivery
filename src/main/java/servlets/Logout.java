package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.Map;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sql@1521";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session1 = request.getSession(false);  // Fetch session without creating a new one
        
        if (session1 != null) {
            Integer userId = (Integer) session1.getAttribute("userId");
            Map<Integer, Integer> cart = (Map<Integer, Integer>) session1.getAttribute("cart");

            // Save the cart to the database if it's not empty
            if (userId != null && cart != null && !cart.isEmpty()) {
                saveCartToDatabase(userId, cart);
            }

            session1.invalidate();  // Invalidate session on logout
        }
        response.sendRedirect("login.jsp");
    }

    // Method to save the user's cart to the database
    private void saveCartToDatabase(int userId, Map<Integer, Integer> cart) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // First, delete any existing cart entries for the user
            String deleteQuery = "DELETE FROM user_carts WHERE user_id = ?";
            ps = conn.prepareStatement(deleteQuery);
            ps.setInt(1, userId);
            ps.executeUpdate();
            ps.close();

            // Now, insert the updated cart items
            String insertQuery = "INSERT INTO user_carts (user_id, product_id, quantity) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(insertQuery);

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
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
