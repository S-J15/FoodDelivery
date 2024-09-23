package servlets;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/products")
public class Home1 extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sql@1521";

    // Method to get all products without filtering
    private List<Product> getAllProducts() throws SQLException {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
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
        return productList;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String query = "SELECT * FROM products";
        boolean hasCategoryFilter = category != null && !category.equals("All") && category.length() >= 3;

        if (hasCategoryFilter) {
            query += " WHERE category = ?";
        }

        List<Product> productList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            // Set the category filter parameter only if needed
            if (hasCategoryFilter) {
                ps.setString(1, category);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setImageFilename(rs.getString("image_filename"));
                product.setCategory(rs.getString("category"));
                productList.add(product);
            }

            // Set the filtered product list
            request.setAttribute("productList", productList);

            // If no products found for the selected category, fetch all products
            if (productList.isEmpty()) {
                List<Product> allProducts = getAllProducts();
                request.setAttribute("productList", allProducts);  // Override with all products
            }

            // Forward the request to home.jsp
            request.getRequestDispatcher("home.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred while fetching products.");
        }
    }
}
