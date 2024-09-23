package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/checkout")
public class Checkout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session1 = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session1.getAttribute("cart");

        if (cart != null && !cart.isEmpty()) {
            OrderSummary orderSummary = new OrderSummary();
            orderSummary.setProducts(cart);
            double totalAmount = 0.0;

            for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                int productId = entry.getKey();
                int quantity = entry.getValue();
                double productPrice = getProductPrice(productId);
                totalAmount += productPrice * quantity;
            }

            orderSummary.setTotalAmount(totalAmount);
            orderSummary.setOrderDate(new java.util.Date());

            // Save order summary to the database
            saveOrderSummaryToDatabase(orderSummary, (Integer) session1.getAttribute("userId"));

            // Store the order summary in the session
            List<OrderSummary> orderHistory = (List<OrderSummary>) session1.getAttribute("orderHistory");
            if (orderHistory == null) {
                orderHistory = new ArrayList<>();
            }
            orderHistory.add(orderSummary);
            session1.setAttribute("orderHistory", orderHistory);

            // Clear the cart
            session1.removeAttribute("cart");
            response.sendRedirect("orderhistory");
        } else {
            response.sendRedirect("cart.jsp");
        }
    }

    private double getProductPrice(int productId) {
        double price = 0.0;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo1", "root", "sql@1521");
             PreparedStatement ps = conn.prepareStatement("SELECT price FROM products WHERE id = ?")) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                price = rs.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }

    private void saveOrderSummaryToDatabase(OrderSummary orderSummary, int userId) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo1", "root", "sql@1521");
             PreparedStatement ps = conn.prepareStatement("INSERT INTO order_summaries (user_id, products, total_amount, order_date) VALUES (?, ?, ?, ?)")) {
            ps.setInt(1, userId);
            ps.setString(2, orderSummary.getProducts().toString());
            ps.setDouble(3, orderSummary.getTotalAmount());
            ps.setTimestamp(4, new Timestamp(orderSummary.getOrderDate().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String cartToJson(Map<Integer, Integer> cart) {
        StringBuilder json = new StringBuilder("{");
        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            json.append("\"").append(entry.getKey()).append("\":").append(entry.getValue()).append(",");
        }
        if (json.length() > 1) {
            json.setLength(json.length() - 1);
        }
        json.append("}");
        return json.toString();
    }
}
