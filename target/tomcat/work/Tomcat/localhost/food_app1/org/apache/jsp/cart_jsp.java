/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2024-09-23 06:24:45 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.DecimalFormat;
import servlets.Product;
import servlets.CartDao;

public final class cart_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <title>Your Cart</title>\r\n");
      out.write("    <link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css\">\r\n");
      out.write("    <style>\r\n");
      out.write("        body {\r\n");
      out.write("            font-family: 'Roboto', sans-serif;\r\n");
      out.write("            background-color: #1e1e2f; /* Dark background */\r\n");
      out.write("            color: #f8f9fa; /* Light text color */\r\n");
      out.write("        }\r\n");
      out.write("        h1 {\r\n");
      out.write("            color: #f8f9fa; /* Light color for headings */\r\n");
      out.write("        }\r\n");
      out.write("        .cart-summary {\r\n");
      out.write("            background-color: #2a2a45; /* Card background */\r\n");
      out.write("            border-radius: 10px;\r\n");
      out.write("            padding: 20px;\r\n");
      out.write("            margin-top: 20px;\r\n");
      out.write("            transition: box-shadow 0.3s;\r\n");
      out.write("        }\r\n");
      out.write("        .cart-summary:hover {\r\n");
      out.write("            box-shadow: 0 4px 20px rgba(255, 255, 255, 0.2); /* Light shadow on hover */\r\n");
      out.write("        }\r\n");
      out.write("        .cart-item {\r\n");
      out.write("            display: flex;\r\n");
      out.write("            align-items: center;\r\n");
      out.write("            justify-content: space-between;\r\n");
      out.write("            padding: 15px;\r\n");
      out.write("            border-bottom: 1px solid #444;\r\n");
      out.write("        }\r\n");
      out.write("        .cart-item:last-child {\r\n");
      out.write("            border-bottom: none;\r\n");
      out.write("        }\r\n");
      out.write("        .cart-image {\r\n");
      out.write("            width: 50px; /* Set a small size for the image */\r\n");
      out.write("            height: auto; /* Maintain aspect ratio */\r\n");
      out.write("            margin-right: 15px; /* Space between image and text */\r\n");
      out.write("        }\r\n");
      out.write("        .total {\r\n");
      out.write("            font-size: 1.5em;\r\n");
      out.write("            margin-top: 20px;\r\n");
      out.write("        }\r\n");
      out.write("        .checkout-button {\r\n");
      out.write("            display: flex;\r\n");
      out.write("            align-items: center;\r\n");
      out.write("            justify-content: center;\r\n");
      out.write("            border-radius: 25px; /* Rounded buttons */\r\n");
      out.write("            padding: 10px 20px; /* Increase padding */\r\n");
      out.write("            font-size: 18px; /* Increase font size */\r\n");
      out.write("            transition: background-color 0.3s, transform 0.3s; /* Transition effects */\r\n");
      out.write("            background-color: #28a745; /* Default green color */\r\n");
      out.write("            color: white; /* Text color */\r\n");
      out.write("            border: none; /* Remove border */\r\n");
      out.write("            cursor: pointer; /* Pointer cursor */\r\n");
      out.write("        }\r\n");
      out.write("        .checkout-button:hover {\r\n");
      out.write("            background-color: #218838; /* Darker green on hover */\r\n");
      out.write("            transform: translateY(-2px); /* Slight lift on hover */\r\n");
      out.write("        }\r\n");
      out.write("        .checkout-button i {\r\n");
      out.write("            margin-right: 8px; /* Space between icon and text */\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <div class=\"container mt-5\">\r\n");
      out.write("        <h1 class=\"text-center\">Your Cart</h1>\r\n");
      out.write("        <a href=\"home.jsp\" class=\"btn btn-primary mb-3\">Continue Shopping</a>\r\n");
      out.write("        ");

            HttpSession session1 = request.getSession();
            Integer userId = (Integer) session1.getAttribute("userId");
            Map<Integer, Integer> cart = (Map<Integer, Integer>) session1.getAttribute("cart");
            double totalAmount = 0.0;
            DecimalFormat df = new DecimalFormat("#.00");

            if (cart == null || cart.isEmpty()) {
                if (userId != null) {
                    CartDao cartDAO = new CartDao();
                    cart = cartDAO.loadCartFromDatabase(userId);
                    session1.setAttribute("cart", cart);
                }
            }

            if (cart == null || cart.isEmpty()) {
        
      out.write("\r\n");
      out.write("            <div class=\"alert alert-warning text-center\">Your cart is empty.</div>\r\n");
      out.write("        ");

            } else {
                List<Product> productList = new ArrayList<Product>();
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;

                try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo1", "root", "sql@1521");
                    ps = conn.prepareStatement("SELECT * FROM products WHERE id = ?");

                    for (Integer productId : cart.keySet()) {
                        ps.setInt(1, productId); // Convert String to int
                        rs = ps.executeQuery(); // Execute query for each productId
                        while (rs.next()) {
                            Product product = new Product();
                            product.setId(rs.getInt("id"));
                            product.setName(rs.getString("name"));
                            product.setPrice(rs.getDouble("price"));
                            product.setImageFilename(rs.getString("image_filename"));
                            product.setCategory(rs.getString("category"));
                            productList.add(product);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    out.println("<p class='text-danger'>Error retrieving product details.</p>");
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (ps != null) ps.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if (!productList.isEmpty()) {
        
      out.write("\r\n");
      out.write("                    <div class=\"cart-summary\">\r\n");
      out.write("                        <h5 class=\"card-title\">Cart Summary</h5>\r\n");
      out.write("                        <div class=\"card-text\">\r\n");
      out.write("                            ");

                            for (Product product : productList) {
                                Integer count = cart.get(product.getId()); // Retrieve as Integer
                                if (count != null) { // Check if count is not null
                                    double itemTotal = product.getPrice() * count;
                                    totalAmount += itemTotal;
                            
      out.write("\r\n");
      out.write("                                <div class=\"cart-item\">\r\n");
      out.write("                                    <img src=\"");
      out.print( product.getImageFilename() );
      out.write("\" alt=\"");
      out.print( product.getName() );
      out.write("\" class=\"cart-image\">\r\n");
      out.write("                                    <div>\r\n");
      out.write("                                        <strong>Product:</strong> ");
      out.print( product.getName() );
      out.write(' ');
      out.write('(');
      out.write('x');
      out.print( count );
      out.write(") <br>\r\n");
      out.write("                                        <strong>Price:</strong> $");
      out.print( df.format(product.getPrice()) );
      out.write(' ');
      out.write('x');
      out.write(' ');
      out.print( count );
      out.write(" = $");
      out.print( df.format(itemTotal) );
      out.write("\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    <form action=\"removeFromCart\" method=\"POST\">\r\n");
      out.write("                                        <input type=\"hidden\" name=\"productId\" value=\"");
      out.print( product.getId() );
      out.write("\">\r\n");
      out.write("                                        <button type=\"submit\" class=\"btn btn-danger btn-sm\">Remove</button>\r\n");
      out.write("                                    </form>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            ");

                                }
                            }
                            
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <h2 class=\"total text-right\">Total Amount Payable: $");
      out.print( df.format(totalAmount) );
      out.write("</h2>\r\n");
      out.write("                    <div class=\"text-center mt-4\">\r\n");
      out.write("                        <form action=\"checkout\" method=\"POST\">\r\n");
      out.write("                            <button type=\"submit\" class=\"checkout-button\">\r\n");
      out.write("                                <i class=\"fas fa-shopping-cart\"></i> Proceed to Checkout\r\n");
      out.write("                            </button>\r\n");
      out.write("                        </form>\r\n");
      out.write("                    </div>\r\n");
      out.write("        ");

                } else {
        
      out.write("\r\n");
      out.write("                    <div class=\"alert alert-danger text-center\">No products found in your cart.</div>\r\n");
      out.write("        ");

                }
            }
        
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\"></script>\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js\"></script>\r\n");
      out.write("    <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}