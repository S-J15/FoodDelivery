package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/orderhistory")
public class OrderHistory extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session1 = request.getSession(false);
        List<OrderSummary> orderHistory = (List<OrderSummary>) session1.getAttribute("orderHistory");

        if (orderHistory != null && !orderHistory.isEmpty()) {
            List<Product> orderedProducts = new ArrayList<>();
            Map<Integer, Product> orderedProductsMap = new HashMap<>();

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo1", "root", "sql@1521");
                 PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE id = ?")) {

                for (OrderSummary order : orderHistory) {
                    Map<Integer, Integer> products = order.getProducts();
                    double totalAmount = 0;

                    for (Integer productId : products.keySet()) {
                        ps.setInt(1, productId);
                        try (ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) {
                                Product product = new Product();
                                product.setId(rs.getInt("id"));
                                product.setName(rs.getString("name"));
                                product.setPrice(rs.getDouble("price"));
                                product.setImageFilename(rs.getString("image_filename"));
                                orderedProducts.add(product);
                                orderedProductsMap.put(productId, product);
                                
                                totalAmount += product.getPrice() * products.get(productId);
                            }
                        }
                    }

                    order.setTotalAmount(totalAmount);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("orderedProducts", orderedProducts);
            request.setAttribute("orderHistory", orderHistory);
            request.setAttribute("orderedProductsMap", orderedProductsMap);
            RequestDispatcher dispatcher = request.getRequestDispatcher("orderHistory.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("cart.jsp");
        }
    }
}
