<%@ page import="java.util.List" %>
<%@ page import="servlets.Product" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.util.Map" %>
<%@ page import="servlets.CartDao" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Food App</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #1e1e2f; /* Dark background */
            color: #f8f9fa; /* Light text color */
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .logout-btn {
            display: flex;
            align-items: center;
        }
        .product-card {
            border: 1px solid #ccc;
            border-radius: 10px;
            margin: 15px;
            padding: 15px;
            transition: box-shadow 0.3s;
            background-color: #2a2a45; /* Card background */
        }
        .product-card:hover {
            box-shadow: 0 4px 20px rgba(255, 255, 255, 0.2); /* Light shadow on hover */
        }
        .product-card img {
            width: 100%;
            height: auto;
            max-height: 150px; /* Maintain uniform height for images */
        }
        .category-card {
            border-radius: 10px;
            margin: 15px;
            padding: 20px;
            text-align: center;
            color: #f8f9fa; /* Text color */
            transition: background-color 0.3s, box-shadow 0.3s;
            text-decoration: none; /* Remove underline */
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100%; /* Make the card equal height */
        }
        .category-card:hover {
            box-shadow: 0 4px 20px rgba(255, 255, 255, 0.2);
            filter: brightness(1.1); /* Slightly brighten on hover */
        }
        .category-fruit { background-color: #ff6f61; }
        .category-vegetable { background-color: #6cc24a; }
        .category-cereal { background-color: #f9d74c; }
        .category-bakery { background-color: #ffb74d; }
        .category-dairy { background-color: #64b5f6; }
        .category-all { background-color: #ab47bc; }

        h1, h2 {
            text-align: center;
            color: #f8f9fa; /* Light color for headings */
        }
        .btn {
            border-radius: 25px; /* Rounded buttons */
            padding: 10px 20px; /* Increase padding */
            font-size: 18px; /* Increase font size */
        }
        .btn i {
            margin-right: 5px; /* Space between icon and text */
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="header">
            <h1>Welcome to the Home Page</h1>
            <div class="logout-btn">
                <%
                    HttpSession session1 = request.getSession(false);
                    String userName = (String) session1.getAttribute("user");
                    Integer userId = (Integer) session1.getAttribute("userId"); // Assuming userId is stored in session
                    if (userName != null) {
                %>
                    <h2 class="mr-3">Hello <%= userName %></h2>
                    <a class="btn btn-danger btn-sm" href="logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
                <%
                    } else {
                        response.sendRedirect("login.jsp");
                        return; // Stop further processing
                    }
                %>
            </div>
        </div>

        <div class="text-center mb-4">
            <a href="cart.jsp" class="btn btn-success"><i class="fas fa-shopping-cart"></i> Go to Cart</a>
            <a href="orderHistory.jsp" class="btn btn-info ml-2"><i class="fas fa-history"></i> View Order History</a>
        </div>

        <h2 class="my-4">Categories</h2>
        <div class="row text-center">
            <div class="col-md-4 col-sm-6 mb-4">
                <a href="products?category=Fruit" class="category-card card category-fruit">
                    <h3>Fruit</h3>
                </a>
            </div>
            <div class="col-md-4 col-sm-6 mb-4">
                <a href="products?category=Vegetable" class="category-card card category-vegetable">
                    <h3>Vegetable</h3>
                </a>
            </div>
            <div class="col-md-4 col-sm-6 mb-4">
                <a href="products?category=Cereal" class="category-card card category-cereal">
                    <h3>Cereal</h3>
                </a>
            </div>
            <div class="col-md-4 col-sm-6 mb-4">
                <a href="products?category=Bakery" class="category-card card category-bakery">
                    <h3>Bakery</h3>
                </a>
            </div>
            <div class="col-md-4 col-sm-6 mb-4">
                <a href="products?category=Dairy" class="category-card card category-dairy">
                    <h3>Dairy</h3>
                </a>
            </div>
            <div class="col-md-4 col-sm-6 mb-4">
                <a href="products?category=All" class="category-card card category-all">
                    <h3>All</h3>
                </a>
            </div>
        </div>

        <h2 class="my-4">Available Products</h2>
        <div class="row">
            <%
                List<Product> productList = (List<Product>) request.getAttribute("productList");
                CartDao cartDao = new CartDao();
                Map<Integer, Integer> userCart = cartDao.loadCartFromDatabase(userId); // Get user's cart from DB
                if (productList != null && !productList.isEmpty()) {
                    for (Product product : productList) {
                        int quantity = userCart.getOrDefault(product.getId(), 0); // Get quantity from cart
            %>
                <div class="col-md-4 col-sm-6">
                    <div class="product-card card">
                        <img src="<%= product.getImageFilename() %>" alt="<%= product.getName() %>">
                        <div class="card-body text-center">
                            <h3 class="card-title"><%= product.getName() %></h3>
                            <p class="card-text">Price: $<%= product.getPrice() %></p>
                            <p class="card-text">In Cart: <%= quantity %></p>
                            <form action="cart" method="POST">
                                <input type="hidden" name="productId" value="<%= product.getId() %>">
                                <button type="submit" class="btn btn-primary">Add to Cart</button>
                            </form>
                        </div>
                    </div>
                </div>
            <%
                    }
                } else {
            %>
                <div class="col-12">
                    <p class="text-danger text-center">Select a category to view available products.</p>
                </div>
            <%
                }
            %>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
