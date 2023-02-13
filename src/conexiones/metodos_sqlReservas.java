/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexiones;

import static conexiones.conexion.conectar;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Oscar
 */
public class metodos_sqlReservas extends conexion{
    
    
     public boolean GuardarReserva(conexion_Reservas reservas) {
         
        PreparedStatement Sentencia_preparada = null;
        Connection conexion = conectar();
        
         
         SimpleDateFormat calendario = new SimpleDateFormat("yyyy-MM-dd");
         String checkIn = calendario.format(reservas.getFechaCheckIn());
         String checkOut = calendario.format(reservas.getFechaCheckOut());
        
       /* DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        LocalDate fechaIn = LocalDate.parse((CharSequence) calendar_checkIn.getDate(), formato);
        LocalDate fechaOut = LocalDate.parse((CharSequence) calendar_checkOut.getDate(), formato); */

        String Sentencia_Guardar = ("INSERT INTO reservas(Fecha_Entrada,Fecha_Salida,Valor,Forma_Pago) VALUES(?,?,?,?)");
        
         try {
            
            Sentencia_preparada = conexion.prepareStatement(Sentencia_Guardar);
            Sentencia_preparada.setString(1, checkIn);
            Sentencia_preparada.setString(2, checkOut);
            Sentencia_preparada.setInt(3, reservas.getValorTarifa());
            Sentencia_preparada.setString(4, reservas.getFormaPago());
           // Sentencia_preparada.setInt(5, reservas.getId());
            Sentencia_preparada.executeUpdate();
            return true;

        } catch (SQLException e) {

            System.out.println("No se pudo guardar la reserva "+e.getMessage());

            return false;

        }finally {
              try {
                  if (conexion != null) {
                      conexion.close();
                  }
              }catch(SQLException ex){
                  return false;
              }
          }
    }
     
    public boolean ModificarDatosReserva(String checkIn, String checkOut, Integer valorTarifa, String formaPago, Integer Id_Reserva) {

        PreparedStatement Sentencia_preparada = null;
        Connection conexion = conectar();
        
     //   SimpleDateFormat calendario = new SimpleDateFormat("yyyy-MM-dd");
     //   String checkIn = calendario.format(reservas.getFechaCheckIn());
    //    String checkOut = calendario.format(reservas.getFechaCheckOut());

        String Consulta = "UPDATE reservas SET Fecha_Entrada=?, Fecha_Salida=?, Valor=?, Forma_Pago=? WHERE IdReserva=?";
                                             //   Fecha_Entrada,Fecha_Salida,Valor,Forma_Pago,Id
                                             
       
        try {

            Sentencia_preparada = conexion.prepareStatement(Consulta);
            Sentencia_preparada.setString(1, checkIn);
            Sentencia_preparada.setString(2, checkOut);
            Sentencia_preparada.setInt(3, valorTarifa);
            Sentencia_preparada.setString(4, formaPago);
            Sentencia_preparada.setInt(5, Id_Reserva);
            //Sentencia_preparada.execute();

            int i = Sentencia_preparada.executeUpdate();

            if (i > 0) {

                JOptionPane.showMessageDialog(null, "Datos de la reserva Modificados");

            } else {

                JOptionPane.showMessageDialog(null, "No se Modificaron los Datos de la reserva");
            }
            return true;

        } catch (SQLException | HeadlessException e) {

            System.out.println(e.toString());

            return false;
        } 

    }
    
    public boolean EliminarReserva(Integer idReserva){
        
        PreparedStatement Sentencia_preparada= null;
        Connection conexion= conectar();
        
        String ConsultaReservas = "DELETE FROM reservas WHERE IdReserva=?";
        
        try {
            Sentencia_preparada= conexion.prepareStatement(ConsultaReservas);
            Sentencia_preparada.setInt(1, idReserva);
            int i=Sentencia_preparada.executeUpdate();
            
            if (i > 0) {

                JOptionPane.showMessageDialog(null, "Datos Eliminados");

            } else {

                JOptionPane.showMessageDialog(null, "No se Eliminaron los Datos");
            }
            
            return true;
            
        } catch (SQLException e) {
            
            System.out.println("Error: "+e.getMessage());
        }
        
        return false;
        
    }
    
}
