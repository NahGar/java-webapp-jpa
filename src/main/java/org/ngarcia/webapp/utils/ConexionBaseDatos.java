package org.ngarcia.webapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {

   private static String url =
           "jdbc:mysql://localhost:3307/java_curso?serverTimezone=America/Montevideo";
   private static String username = "root";
   private static String password = "root";

   public static Connection getConnection() throws SQLException {
      return DriverManager.getConnection(url,username,password);
   }
}
