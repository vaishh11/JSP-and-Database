package com.example.login;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String jdbcURL = "jdbc:mysql://localhost:3306/loginexample";
private static final String jdbcUsername = "your_username";
private static final String jdbcPassword = "your_password";
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
ServletException, IOException {
String userId = request.getParameter("userId");
String password = request.getParameter("password");
try {
if (validate(userId, password)) {
HttpSession session = request.getSession();
session.setAttribute("userId", userId);
response.sendRedirect("welcome.jsp");
} else {
response.sendRedirect("login.jsp");
}
} catch (ClassNotFoundException | SQLException e) {
e.printStackTrace();
}
}
private boolean validate(String userId, String password) throws ClassNotFoundException,
SQLException {
boolean status = false;

Class.forName("com.mysql.cj.jdbc.Driver");
try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername,
jdbcPassword)) {
String sql = "SELECT * FROM users WHERE user_id = ? AND password = ?";
PreparedStatement statement = connection.prepareStatement(sql);
statement.setString(1, userId);
statement.setString(2, password);
ResultSet result = statement.executeQuery();
status = result.next();
}
return status;
}
}
