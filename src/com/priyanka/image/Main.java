package com.priyanka.image;

import java.io.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="Solanki@18#";

        //insertion:
        //String image_path="C:\\Users\\HP\\Downloads\\Priyanka Profile Picture.jpg";
        //String query="insert into image_table(image_data) values(?)";

        //retrieval:
        String folder_path="E:\\Test JDBC\\src\\com\\priyanka\\image\\";
        String query="select image_data from image_table where image_id=(?);";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("Connection established successfully");

              //insertion:
//            FileInputStream fileInputStream = new FileInputStream(image_path);
//            byte[] image_data = new byte[fileInputStream.available()];
//            fileInputStream.read(image_data);
//            PreparedStatement preparedStatement = con.prepareStatement(query);
//            preparedStatement.setBytes(1,image_data);
//            int affectedRows=preparedStatement.executeUpdate();
//            if(affectedRows>0){
//                System.out.println("Image insertion successful");
//            }else{
//                System.out.println("Image insertion failed");
//            }

            //retrieval:
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                byte[] image_data = resultSet.getBytes("image_data");
                String image_path = folder_path+"profilepic.jpg";
                OutputStream outputStream = new FileOutputStream(image_path);
                outputStream.write(image_data);
            }else{
                System.out.println("Image not found");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
