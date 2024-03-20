package DBActions;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DatabaseActions {


    private static Connection conn=null;
    private static Statement smt;
    private static ResultSet rSet=null;
    PreparedStatement preparedStatement;
    int id;
    String name;
    ArrayList<String> studentName  = new ArrayList<>();
    ArrayList<Integer> studentID= new ArrayList<>();


    public static void connectToDatabase(String db_url, String user, String password) throws SQLException, ClassNotFoundException {

        try {
            conn = DriverManager.getConnection(db_url,user,password);
            if (conn != null) {
                System.out.println("Connected to the database");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
    }

    public static void createTable() throws SQLException {
        smt= conn.createStatement();
        String sql = "CREATE TABLE Student " +
                "(stu_id INTEGER not NULL, " +
                " stu_name VARCHAR(255), " +
                " last_modified DATETIME DEFAULT GETDATE(), " +
                " PRIMARY KEY ( id ))";

        smt.executeUpdate(sql);
        System.out.println("Created table Student in given database...");
        String insertToDb = "INSERT INTO Student (stu_id, stu_name,last_modified) VALUES (1, 'kanika')";

        // Execute the update query and store the number of affected rows
        int rowsAffected = smt.executeUpdate(insertToDb);

        // Check if the insert operation was successful
        if(rowsAffected > 0) {
            System.out.println("Inserted");
        } else {
            System.out.println("Failed");
        }
    }

    /**
     * Pass valid SQL query and get the result
     *
     * @param sql
     * @return
     */
    public ResultSet query(String sql) {

        try {
            smt = conn.createStatement();
            rSet = smt.executeQuery(sql);

        } catch (Exception e) {

            System.out.println(e.toString());
        }
        return (rSet);
    }

    //move to test class
    public void getDBData(String sql,String columnFirst,String colSecond){
        // Execute the query and store the results in a ResultSet object
        try (ResultSet resultSet = smt.executeQuery(sql)) {

            // Iterate through the result set (if any rows are returned)
            while (resultSet.next()) {
                // Extract values from the row
                 studentID.add(resultSet.getInt(columnFirst)) ;
                 studentName.add(resultSet.getString(colSecond));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("Id is :" + id + " and name is: " + name);
    }

    public ResultSet getCount(String tableName) throws SQLException {
        smt= conn.createStatement();
        rSet= smt.executeQuery( "SELECT COUNT(*) FROM "+tableName+"+");
        return rSet;
    }

    public String updateQuery(String tableToUpdate, String fieldFirst, String fieldWhere) throws SQLException {
        String sql= "UPDATE "+tableToUpdate+" SET "+tableToUpdate+"."+fieldFirst+"=? WHERE "+tableToUpdate+"."+fieldWhere+"=?";
        System.out.println("Query Value is :"+sql);
        return sql;
    }

    public void updateValue(String tableToUpdate, String fieldFirst,String fieldWhere, String updatedValue) {
        try {
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(updateQuery(tableToUpdate,fieldFirst,fieldWhere));
            preparedStatement.setString(1, updatedValue);
            preparedStatement.addBatch();
            /*for (int i=0; i<listStatus.size(); i++) {
                preparedStatement.setInt(1, listStatus.get(i));
                preparedStatement.setInt(2, i+1);
                preparedStatement.addBatch();
            }*/
            int[] res= preparedStatement.executeBatch();
            System.out.println(Arrays.toString(res));
            conn.commit();
            conn.setAutoCommit(true);
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();

            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
