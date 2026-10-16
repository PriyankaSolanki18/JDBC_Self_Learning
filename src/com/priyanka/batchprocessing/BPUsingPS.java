package com.priyanka.batchprocessing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

//batch processing using prepared statement
public class BPUsingPS {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String user="root";
        String password="Solanki@18#";

        String query = "INSERT INTO employees(name, job_title, salary) VALUES(?,?,?);";

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

            PreparedStatement ps = con.prepareStatement(query);

            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.print("Enter name: ");
                String name=sc.nextLine();

                System.out.print("Job title: ");
                String job_title=sc.nextLine();

                System.out.print("Salary: ");
                double salary=sc.nextDouble();

                ps.setString(1,name);
                ps.setString(2,job_title);
                ps.setDouble(3,salary);

                sc.nextLine();
                ps.addBatch();

                System.out.println("Add more values Y/N?");
                String decision=sc.nextLine();

                if(decision.toUpperCase().equals("N")){
                    break;
                }
            }

            int[] batchResult = ps.executeBatch(); // this array will store how many rows were affected - eg. : insertion

            con.commit();
            System.out.println("Batch executed successfully");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
