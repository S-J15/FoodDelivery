package servlets;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/cart")
public class Cart1 extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sql@1521";
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        HttpSession session1 = request.getSession();

        // Initialize cart as a Map if it doesn't exist
        if (session1.getAttribute("cart") == null) {
            session1.setAttribute("cart", new HashMap<Integer, Integer>());
        }

        // Retrieve the cart from the session
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session1.getAttribute("cart");

        // Increment the count for the product
        cart.put(productId, cart.getOrDefault(productId, 0) + 1);
        
        // Save the cart to the database if user is logged in
        Integer userId = (Integer) session1.getAttribute("userId");
        if (userId != null) {
            CartDao cartDao = new CartDao();
            cartDao.saveCartToDatabase(userId, cart);
        }

        // Update the cart in the session
        session1.setAttribute("cart", cart);

        // Redirect to home.jsp after adding the item to the cart
        response.sendRedirect("home.jsp");
    }
}
