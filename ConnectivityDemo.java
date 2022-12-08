/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.*;

/**
 *
 * @author shravya
 */
public class ConnectivityDemo {

    public static void main(String[] args) {

        try {
//            step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

//            step2 create the connection object
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "shravya123");

//            step3 create the statement object
            Statement stmt = con.createStatement();
            String query = "create table studentAssistPlatform(name varchar(20),roll int)";
            int n = stmt.executeUpdate(query);
            if(n==0)
                System.out.println("Table created");
            else
                System.out.println("Table not created");
//            step5 close the connection object
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
