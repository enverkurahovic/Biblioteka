package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Konekcija {

    public static Connection connect() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/biblioteka",
                    "root",
                    ""
            );

            return con;

        } catch (Exception e) {

            System.out.println(e);

            return null;
        }

    }

}