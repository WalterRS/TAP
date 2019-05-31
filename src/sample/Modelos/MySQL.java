/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.Modelos;


import java.sql.Connection;
import java.sql.DriverManager;

public class MySQL {

    private static String hostname   = "127.0.0.1";
    private static String dbname = "memeflix";
    private static String dbuser = "root";
    private static String dbpass = "1234";
    public static Connection conn=null;


    public static void getConexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+hostname+":3306/"+dbname,dbuser,dbpass);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}