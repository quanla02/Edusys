/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Ciuz
 */
public class JDBCUtils {

    private static Connection conn;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;databaseName=EduSys";
                conn = DriverManager.getConnection(url, "sa", "123");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
