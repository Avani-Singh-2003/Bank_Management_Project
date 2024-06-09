package bank_management_project;

import java.sql.*;

public class conn {

    Connection c;
    Statement s;

    public conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/bankmanagementsystem", "root", "Q6)N$QU#s-");
            s = c.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

}
