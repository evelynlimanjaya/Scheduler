package DAO;

import java.sql.ResultSet;
import java.sql.Statement;

import static DAO.DBConnection.connection;

/**
 * This class is used to make SQL statement and get the result of the SQL statement.
 */
public class Query {
    private static String query;
    private static Statement stmt;
    private static ResultSet result;

    /**
     * This method determine the execution of the SQL statement.
     * @param q String of the SQL statement.
     */
    public static void makeQuery(String q){
        query =q;
        try{
            stmt=connection.createStatement();
            // determine query execution
            if(query.toLowerCase().startsWith("select"))
                result=stmt.executeQuery(q);
            if(query.toLowerCase().startsWith("delete")||query.toLowerCase().startsWith("insert")||query.toLowerCase().startsWith("update"))
                stmt.executeUpdate(q);

        }
        catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }

    /**
     * This method gets the result of the SQL statement.
     * @return Result of SQL statement.
     */
    public static ResultSet getResult(){
        return result;
    }
}
