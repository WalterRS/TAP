package sample.Modelos;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQL_Restaurnt {
    private static String hostname   = "127.0.0.1";
    private static String dbname = "restaurant";
    private static String dbuser = "root";
    private static String dbpass = "1234";
    public static Connection con=null;

    public static void getConexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://"+hostname+":3306/"+dbname,dbuser,dbpass);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
