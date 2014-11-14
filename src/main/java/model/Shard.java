package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Shard extends Base {

    /*-----------------------------------------------------------------------*/
    public Shard(String database) {

        this.database = database;
    }

    /*-----------------------------------------------------------------------*/
    public void patition_tables(List<String> tables) throws ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            String SQL = "CREATE DATABASE  IF NOT EXISTS " + database;
            System.out.println("SQL = " + SQL);
            PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(SQL);
            stmt.executeUpdate();
            stmt.close();

            for (String table : tables) {
                String dbTable = database + "." + table;
                System.out.println();

                SQL = "DROP TABLE IF EXISTS " + dbTable + ";";
                stmt = (PreparedStatement) conn.prepareStatement(SQL);
                stmt.executeUpdate();

                SQL = "CREATE TABLE IF NOT EXISTS " + dbTable
                        + "\n( "
                        + "\n  sha256url char(64) CHARACTER SET ascii NOT NULL, "
                        + "\n  md5root char(32) CHARACTER SET ascii NOT NULL, "
                        + "\n  url varchar(255) CHARACTER SET utf8 NOT NULL, "
                        + "\n  root varchar(64) CHARACTER SET utf8 NOT NULL, "
                        + "\n  tags varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL, "
                        + "\n  title varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL, "
                        + "\n  body varchar(2048) COLLATE utf8_unicode_ci DEFAULT NULL, "
                        + "\n  kincaid smallint(6) NOT NULL DEFAULT '0', "
                        + "\n  flesch smallint(6) NOT NULL DEFAULT '0', "
                        + "\n  fog smallint(6) NOT NULL DEFAULT '0', "
                        + "\n  spamdex smallint(6) unsigned NOT NULL DEFAULT '0', "
                        + "\n  sig smallint(6) unsigned NOT NULL DEFAULT '0', "
                        + "\n  ratio smallint(6) unsigned NOT NULL DEFAULT '0', "
                        + "\n  period char(2) CHARACTER SET ascii NOT NULL, "
                        + "\n  rank smallint(6) NOT NULL DEFAULT '0', "
                        + "\n  nlp smallint(6) NOT NULL DEFAULT '0', "
                        + "\n  hits smallint(6) NOT NULL DEFAULT '0' "
                        + "\n) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci; ";

                System.out.println(SQL);
                stmt = (PreparedStatement) conn.prepareStatement(SQL);
                stmt.executeUpdate();

            }

            conn.close();

        } catch (SQLException ex) {
            System.out.println("Line61 = " + ex);
        }
    }
    /*-----------------------------------------------------------------------*/

}
