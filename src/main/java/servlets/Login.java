package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sql@1521";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM user_cred WHERE email = ? AND password = ?")) 
        {
            ps.setString(1, email);
            ps.setString(2, password);  // In real-world scenarios, hash the password and verify

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Successful login
                HttpSession session1 = request.getSession();
                session1.setAttribute("user", rs.getString("name"));  // Store user name in session
                session1.setAttribute("userId", rs.getInt("id"));     // Store user ID in session

                // Load user cart from database and set it in session
                Map<Integer, Integer> cart = loadUserCart(rs.getInt("id"));
                session1.setAttribute("cart", cart);

                System.out.println("Login successful!");
                response.sendRedirect("home.jsp?category=all");
            } else {
                // Login failed
                response.sendRedirect("login.jsp?error=Invalid+email+or+password");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }

    // Method to load the user's cart from the database
    private Map<Integer, Integer> loadUserCart(int userId) {
        Map<Integer, Integer> cart = new HashMap<>();
        String query = "SELECT product_id, quantity FROM user_carts WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            // Retrieve the cart items and populate the map
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                cart.put(productId, quantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }
}
