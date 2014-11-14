package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/*-----------------------------------------------------------------------*/
public class Base {

    String database;
    String url = "jdbc:mysql://127.0.0.1:3306";
    String driver = "com.mysql.jdbc.Driver";
    String user = "root";
    String password = "password";

    /*-----------------------------------------------------------------------*/
    public void execute(String SQL) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            com.mysql.jdbc.PreparedStatement stmt;

            System.out.println("SQL = " + SQL);
            stmt = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(SQL);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        }
    }
    /*-----------------------------------------------------------------------*/

    public List<String> hexPermutation() {

        String hexadecimal = "0123456789abcdef";
        List<String> list = new ArrayList<>();

        char[] c = hexadecimal.toCharArray();

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c.length; j++) {
                for (int k = 0; k < c.length; k++) {
                    String str = String.format("part_%c%c%c", c[i], c[j], c[k]);
                    list.add(str);
                }
            }
        }
        return list;
    }
    /*-----------------------------------------------------------------------*/    

}
