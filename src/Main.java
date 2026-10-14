import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "Solanki@18#";

        //String query = "select * from employees;";
        String query = "insert into employees(id,name,job_title,salary) values(3,'Radha','AI Engineer',100000);";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("Connection established successfully");
            Statement stmt = con.createStatement();
            //RETRIEVING DATA FROM DB:
//            ResultSet rs = stmt.executeQuery(query);
//            while(rs.next()){
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String job_title = rs.getString("job_title");
//                double salary = rs.getDouble("salary");
//                System.out.println("======================================================================");
//                System.out.println("ID: "+id+", Name: "+name+", Job Title: "+job_title+", Salary: "+salary);
//                System.out.println("======================================================================");
//            }
//            rs.close();

            //INSERTING DATA INTO DB:
            int rowsaffected = stmt.executeUpdate(query);
            if(rowsaffected > 0){
                System.out.println("Insert Successfull "+rowsaffected+" row(s) affected");
            }else{
                System.out.println("Insertion Failed");
            }

            stmt.close();
            con.close();
            System.out.println("Connection closed successfully");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}