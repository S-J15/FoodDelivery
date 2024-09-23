<%@ page import="java.util.*" %>
<%@ page import="servlets.OrderSummary" %>
<%@ page import="servlets.Product" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order History</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #1e1e2f; /* Dark background */
            color: #f8f9fa; /* Light text color */
        }
        .continue-shopping {
            margin-bottom: 20px; /* Space below continue shopping button */
            background-color: #28a745; /* Green background */
            border-color: #28a745; /* Green border */
            transition: background-color 0.3s, transform 0.3s; /* Transition effects */
        }
        .continue-shopping:hover {
            background-color: #218838; /* Darker green on hover */
            transform: translateY(-2px); /* Slight lift effect */
        }
        .order-block {
            background-color: #2a2a45; /* Darker card background */
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 4px 8px rgba(255, 255, 255, 0.1);
        }
        .order-title {
            font-weight: bold;
            font-size: 1.5rem;
            margin-bottom: 10px;
            color: #f8f9fa; /* Light color for headings */
            display: inline-block; /* Make title inline */
        }
        .total-amount {
            font-weight: bold;
            font-size: 1.8rem; /* Increased font size for total amount */
            color: #28a745; /* Green for total amount */
            float: right; /* Align to the right */
        }
        .product-item {
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 15px;
            margin: 5px;
            background-color: #3b3b58; /* Lighter card background */
            transition: transform 0.3s, box-shadow 0.3s;
        }
        .product-item:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <a href="home.jsp" class="btn continue-shopping">
            <i class="fas fa-shopping-cart"></i> Continue Shopping
        </a>
        <h1 class="text-center">Order History</h1>
        <%
            List<OrderSummary> orderHistory = (List<OrderSummary>) request.getSession().getAttribute("orderHistory");
            Map<Integer, Product> orderedProductsMap = (Map<Integer, Product>) request.getAttribute("orderedProductsMap");
            DecimalFormat df = new DecimalFormat("#.00");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

            if (orderHistory != null && !orderHistory.isEmpty()) {
                for (OrderSummary order : orderHistory) {
                    Map<Integer, Integer> products = order.getProducts();
                    double totalAmount = order.getTotalAmount();
                    Date orderDate = order.getOrderDate();
        %>
                    <div class="order-history">
                        <div class="order-block">
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="order-title">Order Summary</div>
                                <div class="total-amount">$<%= df.format(totalAmount) %></div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="row">
                                        <%
                                        for (Map.Entry<Integer, Integer> entry : products.entrySet()) {
                                            int productId = entry.getKey();
                                            int quantity = entry.getValue();
                                            
                                            // Safely retrieve the product from the orderedProductsMap
                                            Product product = (orderedProductsMap != null) ? orderedProductsMap.get(productId) : null;
                                            
                                            if (product != null) {
                                                double itemTotal = product.getPrice() * quantity;
                                        %>
                                            <div class="col-md-4 product-item">
                                                <strong>Product:</strong> <%= product.getName() %> <br>
                                                <strong>Quantity:</strong> <%= quantity %> <br>
                                                <strong>Price:</strong> $<%= df.format(product.getPrice()) %> <br>
                                                <strong>Total:</strong> $<%= df.format(itemTotal) %> <br>
                                            </div>
                                        <%
                                            }
                                        }
                                        %>
                                    </div>
                                    <div>Date: <%= sdf.format(orderDate) %></div>
                                </div>
                            </div>
                        </div>
                    </div>
        <%
                }
            } else {
        %>
            <div class="alert alert-warning text-center">You have no order history.</div>
        <%
            }
        %>
    </div>
</body>
</html>
