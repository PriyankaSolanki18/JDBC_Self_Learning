package com.priyanka.transactionalhandling;

import java.awt.dnd.DragGestureEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String user="root";
        String password="Solanki@18#";

        String withdrawQuery="update accounts set balance = balance - ? where account_number = ?;";
        String depositQuery="update accounts set balance = balance + ? where account_number = ?;";

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

            try{
                PreparedStatement withdrawStatement = con.prepareStatement(withdrawQuery);
                PreparedStatement depositStatement = con.prepareStatement(depositQuery);

                withdrawStatement.setDouble(1,500.00);
                withdrawStatement.setString(2,"account456");

                depositStatement.setDouble(1,500.00);
                depositStatement.setString(2,"account423"); //wrong account so transaction will rollback here

                int rowsAffectedWithdrawl = withdrawStatement.executeUpdate();
                int rowsAffectedDeposit = depositStatement.executeUpdate();

                if(rowsAffectedWithdrawl > 0 && rowsAffectedDeposit > 0){
                    con.commit();
                    System.out.println("Transaction successful");
                }else{
                    con.rollback();
                    System.out.println("Transaction failed");
                }

            }catch (SQLException e){
                System.out.println(e.getMessage());
            }


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
