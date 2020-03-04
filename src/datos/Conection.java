/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;
import java.sql.*;
import java.util.*;

/**
 *
 * @author estel
 */
public class Conection {
    private static String JDBC_DRIVER;
    private static String JDBC_URL;
    private static String JDBC_USER;
    private static String JDBC_PASS;
    private static Driver driver=null;
    private static String JDBC_FILE_NAME="jdbc";
    
    
    public static Properties loadProperties(String file){
        Properties prop=new Properties();
        ResourceBundle bundle=ResourceBundle.getBundle(file);
        Enumeration e=bundle.getKeys();
        String key=null;
        while(e.hasMoreElements()){
            key=(String) e.nextElement();//devuelve object y lo tranfiere a un STRING:
            prop.put(key, bundle.getObject(key));
        }
        JDBC_DRIVER=prop.getProperty("driver");
        JDBC_URL=prop.getProperty("url");
        JDBC_USER=prop.getProperty("user");
        JDBC_PASS=prop.getProperty("pass");
        return prop;
    }
   public static synchronized Connection getConnection() throws SQLException{
       if(driver==null){
           try{
               //Cargamos propiedades de conexión a la DB:
               loadProperties(JDBC_FILE_NAME);
               //se registra el driver
               Class jdbcDriverClass=Class.forName(JDBC_DRIVER);
               driver=(Driver)jdbcDriverClass.newInstance();
               DriverManager.registerDriver(driver);
           }catch(Exception e){
               System.out.println("Fallo en cargaer el driver JDBC");
               e.printStackTrace();
           }
       }
       return  DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS);
   }
   public static void close(ResultSet rs){
       try{
           if(rs!=null){
               rs.close();
           }
       }catch(SQLException sqleEx){
           sqleEx.printStackTrace();
       }
   }
   
   //cierre de prepareStatement
   public static void close(PreparedStatement stmt){
       try{
           if(stmt!=null){
               stmt.close();
           }
       }catch(SQLException sqlEx){
           sqlEx.printStackTrace();
       }
   }
   //cierre de conectión:
   public static void close(Connection conn){
       try{
           if(conn!=null){
               conn.close();
           }
       }catch(SQLException sqlEx){
           sqlEx.printStackTrace();
       }
   }
}
