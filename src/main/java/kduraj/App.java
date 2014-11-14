package kduraj;

import model.Shard;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {

    /*-----------------------------------------------------------------------*/
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        int db = 10;
        String database = "engine";

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
        Shard shard = new Shard(database);
        List<String> list = shard.hexPermutation();
        shard.patition_tables(list);

        /*-----------------------------------------------------------------------*/
    }
}
