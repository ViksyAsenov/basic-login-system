package com.servlet;

import java.sql.*;

public class ConnectToDB {
    private static final String dburl = "jdbc:mysql://localhost:3306/account-system";
    private static final String dbusername = "root";
    private static final String dbpassword = "123456";
    private static final String dbdriver = "com.mysql.jdbc.Driver";

    public static void LoadDriver() {
        try {
            Class.forName(dbdriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(dburl, dbusername, dbpassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public static String insert(User user) {
        LoadDriver();
        Connection con = getConnection();

        String result = "Account Registered!";

        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            result = "Something went wrong...";
        }

        return result;
    }

    public static String get(User user) {
        LoadDriver();
        Connection con = getConnection();

        String result = "Wrong Credentials!";

        String sql = "SELECT * FROM users WHERE username = ? and password = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return "Welcome!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result = "Something went wrong...";
        }

        return result;
    }
}
