package com.priyanka.batchprocessing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String user="root";
        String password="Solanki@18#";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url,user,password);
            System.out.println("Connection established successfully");

            con.setAutoCommit(false);

            Statement statement = con.createStatement();
            statement.addBatch("INSERT INTO employees(name, job_title, salary) VALUES('Vashu','HR Manager',54000);");
            statement.addBatch("INSERT INTO employees(name, job_title, salary) VALUES('Karan','Devops Engineer',64000);");
            statement.addBatch("INSERT INTO employees(name, job_title, salary) VALUES('Rashi','Backend Engineer',80000);");
            int[] batchResult = statement.executeBatch();
            con.commit();
            System.out.println("Batch executed successfully");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
