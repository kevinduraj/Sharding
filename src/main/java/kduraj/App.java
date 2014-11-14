package kduraj;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    static int db = 10;
    static String database = "engine";
    static String url = "jdbc:mysql://127.0.0.1:3306";
    static final String user = "root";
    static final String password = "password";

    /*-----------------------------------------------------------------------*/
    public void patition_tables(List<String> tables) throws ClassNotFoundException {

        PreparedStatement stmt = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection(App.url, App.user, App.password)) {

            String SQL = "CREATE DATABASE  IF NOT EXISTS " + database;
            System.out.println("SQL = " + SQL);
            stmt = (PreparedStatement) conn.prepareStatement(SQL);
            stmt.executeUpdate();

            for (String table : tables) {
                System.out.println("Creating Table: " + database + "." + table);

                SQL = "DROP TABLE IF EXISTS " + database + "." + table + ";";
                stmt = (PreparedStatement) conn.prepareStatement(SQL);
                stmt.executeUpdate();

                SQL = "CREATE TABLE " + database + "." + table + " (\n"
                        + "  sha256url char(64) CHARACTER SET ascii NOT NULL,\n"
                        + "  md5root char(32) CHARACTER SET ascii NOT NULL,\n"
                        + "  url varchar(255) CHARACTER SET utf8 NOT NULL,\n"
                        + "  root varchar(64) CHARACTER SET utf8 NOT NULL,\n"
                        + "  tags varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,\n"
                        + "  title varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,\n"
                        + "  body varchar(2048) COLLATE utf8_unicode_ci DEFAULT NULL,\n"
                        + "  kincaid smallint(6) NOT NULL DEFAULT '0',\n"
                        + "  flesch smallint(6) NOT NULL DEFAULT '0',\n"
                        + "  fog smallint(6) NOT NULL DEFAULT '0',\n"
                        + "  spamdex smallint(6) unsigned NOT NULL DEFAULT '0',\n"
                        + "  sig smallint(6) unsigned NOT NULL DEFAULT '0',\n"
                        + "  ratio smallint(6) unsigned NOT NULL DEFAULT '0',\n"
                        + "  period char(2) CHARACTER SET ascii NOT NULL,\n"
                        + "  rank smallint(6) NOT NULL DEFAULT '0',\n"
                        + "  nlp smallint(6) NOT NULL DEFAULT '0',\n"
                        + "  hits smallint(6) NOT NULL DEFAULT '0'\n"
                        + ") ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";

                System.out.println("\nSQL = " + SQL + "\n");
                stmt = (PreparedStatement) conn.prepareStatement(SQL);
                stmt.executeUpdate();

            }
        } catch (SQLException ex) {
            System.out.println("Line61 = " + ex);
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    /*-----------------------------------------------------------------------*/

    private static List<String> hexPermutation() {

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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        char ch = 0;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("\nDatabase Partitioning\n");
            System.out.println("1. Engine Number ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter Engine Number");
                    db = (scan.nextInt());
                    database += db;
                    break;
                default:
                    System.out.println("Wrong Entry (Type 1) \n ");
                    break;
            }

            System.out.println("\nContinue (Type y or n) \n");
            ch = scan.next().charAt(0);

        } while (ch == 'N' || ch == 'n');
        /*-----------------------------------------------------------------------*/
        App app = new App();
        List<String> list = hexPermutation();
        app.patition_tables(list);
        /*-----------------------------------------------------------------------*/
    }
}
