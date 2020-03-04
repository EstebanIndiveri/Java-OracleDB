/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs;
import java.sql.*;
import datos.Conection;
import oracle.jdbc.*;
/**
 *
 * @author estel
 */
public class TestCursores {
    public static void main(String [] args){
        //utilizamos una clase de oracle para poder procesar el cursor
        //que regresa la funcion de oracle
        OracleCallableStatement oraCallStmt=null;
        OracleResultSet deptResultSet=null;
        try{
            Connection con=Conection.getConnection();
            //Tiene dos parametros que posteriormente se definirán.
            //llamos al metodo: 1- tipo de retorno( CURSOR) 2-valor del id del departamento
            oraCallStmt=(OracleCallableStatement) con.prepareCall("{?=call ref_cursor_package.get_dept_ref_cursor(?)}");
            //indicamosel tipo de regreo, el cual es un cursor:
            
            oraCallStmt.registerOutParameter(1,OracleTypes.CURSOR);//Parametro 1
            oraCallStmt.setInt(2, 200);//establecemos departamento_id, parametro 2;
            oraCallStmt.execute();
            
            //Recuperamos el resultSet y lo convertimos a un tipo ORACLE:
            deptResultSet=(OracleResultSet) oraCallStmt.getCursor(1);
            while(deptResultSet.next()){
                //Lo ordenamos desde PL nos trae la columna 1
                System.out.print("id_departamento: "+deptResultSet.getInt(1));
                System.out.print(", Nombre_Departamento"+deptResultSet.getString(2));
                System.out.print(", Ubicación_id: "+deptResultSet.getString(3));
                System.out.println();
            }
         oraCallStmt.close();   
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
