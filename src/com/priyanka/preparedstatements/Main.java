package com.priyanka.preparedstatements;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="Solanki@18#";

        //String query="select * from employees where name=? and job_title=?;";
        String query="insert into employees(id,name,job_title,salary) values(?,?,?,?);";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("Connection Established successfully");
            Scanner sc = new Scanner(System.in);
            int id=sc.nextInt();
            sc.nextLine();
            String name=sc.nextLine();
            String job_title=sc.nextLine();
            double salary=sc.nextDouble();

            PreparedStatement ps = con.prepareStatement(query);
//            ps.setString(1,"Radha");
//            ps.setString(2,"AI Engineer");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String job_title = rs.getString("job_title");
//                double salary = rs.getDouble("salary");
//
//                System.out.println("ID: " + id);
//                System.out.println("Name: "+name);
//                System.out.println("Job Title: "+job_title);
//                System.out.println("Salary: "+salary);
//            }

            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setString(3,job_title);
            ps.setDouble(4,salary);

            int rowsaffected = ps.executeUpdate();
            if(rowsaffected>0){
                System.out.println("Data inserted successfully");
            }else{
                System.out.println("Data insertion failed");
            }

            //rs.close();
            ps.close();
            con.close();
            System.out.println();
            System.out.println("Connection closed successfully");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
