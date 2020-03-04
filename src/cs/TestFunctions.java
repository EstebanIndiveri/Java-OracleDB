/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs;
import datos.Conection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author estel
 */
public class TestFunctions {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    int empleadoId=1;//Identificador a recuperar salario
    
    try{
        Connection con=Conection.getConnection();
        CallableStatement cstmt=null;
        double salarioMensual;
        
        
            cstmt=con.prepareCall("{ ? = call get_employee_salary(?) }");
            //una funcion regresa un valor
            // por lo que lo registramos en el pirimer parametro:
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            
            cstmt.setInt(2,empleadoId);
            cstmt.execute();
            salarioMensual=cstmt.getDouble(1);
            cstmt.close();
            System.out.println("Empleado con id: "+empleadoId);
            System.out.println("Salario $"+salarioMensual);
    }catch(SQLException ex){
        
        ex.printStackTrace();
    }
    
    
    }
    
}
