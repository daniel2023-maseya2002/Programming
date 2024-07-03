package registration.auca.student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayInfo")
public class FirstClass extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of PostgreSQL server
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5433/auca_db";
    private static final String JDBC_USER = "dani";
    private static final String JDBC_PASSWORD = "Smooth1.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<title>User Information</title>");
        response.getWriter().println("<style>");
        response.getWriter().println("body { font-family: 'Montserrat', sans-serif; background: linear-gradient(to right, #e2e2e2, #c9d6ff); display: flex; flex-direction: column; align-items: center; justify-content: space-between; min-height: 100vh; margin: 0; }");
        response.getWriter().println(".container { background-color: #fff; border-radius: 30px; box-shadow: 0 5px 15px rgba(0, 0, 0, 0.35); width: 768px; max-width: 100%; padding: 40px; text-align: center; margin-bottom: 20px; }");
        response.getWriter().println("h1 { margin-bottom: 20px; }");
        response.getWriter().println("label { display: block; text-align: left; margin-top: 15px; }");
        response.getWriter().println("input { width: calc(100% - 30px); padding: 10px; margin-top: 5px; border: 1px solid #ccc; border-radius: 8px; }");
        response.getWriter().println("button { background-color: #512da8; color: #fff; font-size: 12px; padding: 10px 45px; border: 1px solid transparent; border-radius: 8px; font-weight: 600; letter-spacing: 0.5px; text-transform: uppercase; margin-top: 10px; cursor: pointer; }");
        response.getWriter().println("button:hover { background-color: #311b92; }");
        response.getWriter().println("footer { text-align: center; font-size: 0.9em; color: #555; margin-top: auto; padding: 10px; background-color: #f0f0f0; width: 100%; }");
        response.getWriter().println("</style>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<div class='container'>");
        response.getWriter().println("<h1>User Information</h1>");
        response.getWriter().println("<form method='post' action='displayInfo'>");
        response.getWriter().println("<label for='id'>ID:</label>");
        response.getWriter().println("<input type='text' id='id' name='id' required>");
        response.getWriter().println("<label for='firstName'>First Name:</label>");
        response.getWriter().println("<input type='text' id='firstName' name='firstName' required>");
        response.getWriter().println("<label for='lastName'>Last Name:</label>");
        response.getWriter().println("<input type='text' id='lastName' name='lastName' required>");
        response.getWriter().println("<button type='submit'>Submit</button>");
        response.getWriter().println("</form>");
        response.getWriter().println("</div>");
        response.getWriter().println("<footer>");
        response.getWriter().println("2024 &copy; Daniel Maseya. All Rights Reserved.");
        response.getWriter().println("</footer>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve user input
        String id = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        // Insert into database
        try {
            // Load PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish connection to PostgreSQL database
            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                // SQL query to insert user information
                String sql = "INSERT INTO users (user_id, first_name, last_name) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, id);
                    stmt.setString(2, firstName);
                    stmt.setString(3, lastName);
                    // Execute the query
                    stmt.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(); // Handle this more gracefully in production
            throw new ServletException("Database connection failed", ex);
        }

        // Display confirmation message
        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<title>User Information</title>");
        response.getWriter().println("<style>");
        response.getWriter().println("body { font-family: 'Montserrat', sans-serif; background: linear-gradient(to right, #e2e2e2, #c9d6ff); display: flex; flex-direction: column; align-items: center; justify-content: space-between; min-height: 100vh; margin: 0; }");
        response.getWriter().println(".container { background-color: #fff; border-radius: 30px; box-shadow: 0 5px 15px rgba(0, 0, 0, 0.35); width: 768px; max-width: 100%; padding: 40px; text-align: center; margin-bottom: 20px; }");
        response.getWriter().println("h1 { margin-bottom: 20px; }");
        response.getWriter().println("p { font-size: 18px; color: #4caf50; margin-top: 20px; }");
        response.getWriter().println("table { width: 100%; margin-top: 20px; border-collapse: collapse; }");
        response.getWriter().println("td { padding: 10px; border: 1px solid #ccc; }");
        response.getWriter().println("button { background-color: #512da8; color: #fff; font-size: 12px; padding: 10px 45px; border: 1px solid transparent; border-radius: 8px; font-weight: 600; letter-spacing: 0.5px; text-transform: uppercase; margin-top: 20px; cursor: pointer; }");
        response.getWriter().println("button:hover { background-color: #311b92; }");
        response.getWriter().println("footer { text-align: center; font-size: 0.9em; color: #555; margin-top: auto; padding: 10px; background-color: #f0f0f0; width: 100%; }");
        response.getWriter().println("</style>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<div class='container'>");
        response.getWriter().println("<h1>User Information</h1>");
        response.getWriter().println("<p>Information saved successfully!</p>");
        response.getWriter().println("<table>");
        response.getWriter().println("<tr><td>ID:</td><td>" + id + "</td></tr>");
        response.getWriter().println("<tr><td>First Name:</td><td>" + firstName + "</td></tr>");
        response.getWriter().println("<tr><td>Last Name:</td><td>" + lastName + "</td></tr>");
        response.getWriter().println("</table>");
        response.getWriter().println("<button onclick='goBack()'>Back</button>");
        response.getWriter().println("</div>");
        response.getWriter().println("<footer>");
        response.getWriter().println("2024 &copy; Daniel Maseya. All Rights Reserved.");
        response.getWriter().println("</footer>");
        response.getWriter().println("<script>");
        response.getWriter().println("function goBack() {");
        response.getWriter().println("  window.history.back();");
        response.getWriter().println("}");
        response.getWriter().println("</script>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
