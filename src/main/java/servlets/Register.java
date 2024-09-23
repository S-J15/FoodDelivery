package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo1";
    private static final String DB_USER = "root"; // replace with your MySQL username
    private static final String DB_PASSWORD = "sql@1521"; // replace with your MySQL password

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String contactNumber = request.getParameter("contact_number");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement("INSERT INTO user_cred (email, password, name, contact_number) VALUES (?, ?, ?, ?)")) {
             
            ps.setString(1, email);
            ps.setString(2, password); // Consider hashing the password before saving
            ps.setString(3, name);
            ps.setString(4, contactNumber);
            ps.executeUpdate();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>User registered successfully!</h2>");
            out.println("<a href='index.jsp'>Go back</a>");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}
