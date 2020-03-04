/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs;
import datos.Conection;
import java.sql.*;
/**
 *
 * @author estel
 */
public class TestProcedimientos {
    public static void main(String[]args){
        int empleadoID=100;
        //la formula que aplica el Store Procedure es
        //Salario=salario*incremento:
        
        double incrementoSalario=1.1;//incremento 10%
        Connection conn;
        
        try{
            conn=Conection.getConnection();
            Statement stmt=null;
            ResultSet rset=null;
            CallableStatement cstmt=null;
            
            stmt=conn.createStatement();
            
            //Llamamos al SP para incrementar salario
            
            System.out.println("Aumento del 10% al empleado: "+empleadoID);
            cstmt=conn.prepareCall("{call set_employee_salary(?,?}");
            cstmt.setInt(1, empleadoID);
            
            cstmt.setDouble(2,incrementoSalario);
            cstmt.execute();
            cstmt.close();
            
            //Obtenemos el nuevo valor del salario para empledo seleccionado
            String query="SELECT first_name, salary FROM employees"+" WHERE employee_id= "+empleadoID;
            rset=stmt.executeQuery(query);
            rset.next();
            System.out.println("Nombre: "+rset.getString(1));
            System.out.println("Salario nuevo: "+rset.getFloat(2));
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
