package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/removeFromCart")
public class RemoveProd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));

        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        if (cart != null && cart.containsKey(productId)) {
            int quantity = cart.get(productId);
            
            if (quantity > 1) {
                // Decrease the quantity by 1
                cart.put(productId, quantity - 1);
            } else {
                // Remove the product if quantity is 1
                cart.remove(productId);
            }
            session.setAttribute("cart", cart); // Update session cart
        }

        response.sendRedirect("cart.jsp"); // Redirect back to the cart
    }
}
