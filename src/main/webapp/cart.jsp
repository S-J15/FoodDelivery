<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="servlets.Product" %>
<%@ page import="servlets.CartDao" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Cart</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #1e1e2f; /* Dark background */
            color: #f8f9fa; /* Light text color */
        }
        h1 {
            color: #f8f9fa; /* Light color for headings */
        }
        .cart-summary {
            background-color: #2a2a45; /* Card background */
            border-radius: 10px;
            padding: 20px;
            margin-top: 20px;
            transition: box-shadow 0.3s;
        }
        .cart-summary:hover {
            box-shadow: 0 4px 20px rgba(255, 255, 255, 0.2); /* Light shadow on hover */
        }
        .cart-item {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 15px;
            border-bottom: 1px solid #444;
        }
        .cart-item:last-child {
            border-bottom: none;
        }
        .cart-image {
            width: 50px; /* Set a small size for the image */
            height: auto; /* Maintain aspect ratio */
            margin-right: 15px; /* Space between image and text */
        }
        .total {
            font-size: 1.5em;
            margin-top: 20px;
        }
        .checkout-button {
            display: flex;
            align-items: center;
            justify-content: center;
            border-radius: 25px; /* Rounded buttons */
            padding: 10px 20px; /* Increase padding */
            font-size: 18px; /* Increase font size */
            transition: background-color 0.3s, transform 0.3s; /* Transition effects */
            background-color: #28a745; /* Default green color */
            color: white; /* Text color */
            border: none; /* Remove border */
            cursor: pointer; /* Pointer cursor */
        }
        .checkout-button:hover {
            background-color: #218838; /* Darker green on hover */
            transform: translateY(-2px); /* Slight lift on hover */
        }
        .checkout-button i {
            margin-right: 8px; /* Space between icon and text */
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Your Cart</h1>
        <a href="home.jsp" class="btn btn-primary mb-3">Continue Shopping</a>
        <%
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
        %>
            <div class="alert alert-warning text-center">Your cart is empty.</div>
        <%
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
        %>
                    <div class="cart-summary">
                        <h5 class="card-title">Cart Summary</h5>
                        <div class="card-text">
                            <%
                            for (Product product : productList) {
                                Integer count = cart.get(product.getId()); // Retrieve as Integer
                                if (count != null) { // Check if count is not null
                                    double itemTotal = product.getPrice() * count;
                                    totalAmount += itemTotal;
                            %>
                                <div class="cart-item">
                                    <img src="<%= product.getImageFilename() %>" alt="<%= product.getName() %>" class="cart-image">
                                    <div>
                                        <strong>Product:</strong> <%= product.getName() %> (x<%= count %>) <br>
                                        <strong>Price:</strong> $<%= df.format(product.getPrice()) %> x <%= count %> = $<%= df.format(itemTotal) %>
                                    </div>
                                    <form action="removeFromCart" method="POST">
                                        <input type="hidden" name="productId" value="<%= product.getId() %>">
                                        <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                                    </form>
                                </div>
                            <%
                                }
                            }
                            %>
                        </div>
                    </div>
                    <h2 class="total text-right">Total Amount Payable: $<%= df.format(totalAmount) %></h2>
                    <div class="text-center mt-4">
                        <form action="checkout" method="POST">
                            <button type="submit" class="checkout-button">
                                <i class="fas fa-shopping-cart"></i> Proceed to Checkout
                            </button>
                        </form>
                    </div>
        <%
                } else {
        %>
                    <div class="alert alert-danger text-center">No products found in your cart.</div>
        <%
                }
            }
        %>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
