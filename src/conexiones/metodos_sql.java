package conexiones;

import com.mysql.cj.xdevapi.Statement;
import com.toedter.calendar.JDateChooser;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.JOptionPane;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;



/**
 *
 * @author Oscar
 */
public class metodos_sql extends conexion {
    
   // private static ResultSet Resultado;

    public boolean Guardar(conexion_Huespedes huespedes) {

        PreparedStatement Sentencia_preparada = null;
        Connection conexion = conectar();
        conexion_Reservas reservas= new conexion_Reservas();
        
        SimpleDateFormat calendario = new SimpleDateFormat("yyyy-MM-dd");
        String fechaNacimiento = calendario.format(huespedes.getFecha_nacimiento());

        String Sentencia_Guardar = ("INSERT INTO huespedes(Nombre,Apellido,Fecha_Nacimiento,Nacionalidad,Telefono,Id_Reserva) VALUES (?,?,?,?,?,(select IdReserva from reservas order by IdReserva desc limit 1))");
       //   String Sentencias_Guardar = ("SELECT h.Nombre, h.Apellido, h.Fecha_Nacimiento,h.Nacionalidad,h.Telefono FROM huespedes h inner join reservas r on h.Id_Reserva=r.Id");
       //   String Sentencia_Guardar2 = ("INSERT INTO huespedes h(h.Apellido, h.Fecha_Nacimiento,h.Nacionalidad,h.Telefono, inner join reservas r on h.Id_Reserva=r.Id) VALUES(?,?,?,?,?,?)");
       //  String Sentencia_Guardar2 = ("INSERT INTO reservas(Id) VALUES(SELECT Id FROM huespedes order by Id desc limit 1)");
       
          try {
            Sentencia_preparada = conexion.prepareStatement(Sentencia_Guardar);
            Sentencia_preparada.setString(1, huespedes.getNombre());
            Sentencia_preparada.setString(2, huespedes.getApellido());
            Sentencia_preparada.setString(3, fechaNacimiento);
            Sentencia_preparada.setString(4, huespedes.getNacionalidad());
            Sentencia_preparada.setString(5, huespedes.getTelefono());
          //  Sentencia_preparada.setInt(6, huespedes.getId_Reserva());
           // Sentencia_preparada.setInt(7, huespedes.getId());
            Sentencia_preparada.executeUpdate();
            return true;

        } catch (SQLException e) {

            System.out.println("No se pudo guardar el huésped "+e.getMessage());

            return false;

        }/*finally {
              try {
                  if (conexion != null) {
                      conexion.close();
                  }
              }catch(SQLException ex){
                  return false;
              }
          } */
    }
    
    public boolean Modificar_Datos_Huespedes(String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, Integer idReserva, Integer idHuesped) {

        PreparedStatement Sentencia_preparada = null;
        Connection conexion = conectar();
        
      //  SimpleDateFormat calendario = new SimpleDateFormat("yyyy-MM-dd");
      //  String fechaNacimiento = calendario.format(huespedes.getFecha_nacimiento());

        String Consulta = "UPDATE huespedes SET Nombre=?, Apellido=?, Fecha_Nacimiento=?, Nacionalidad=?, Telefono=?, Id_Reserva=? WHERE IdHuesped=? ";
        
       


        try {

            Sentencia_preparada = conexion.prepareStatement(Consulta);
            Sentencia_preparada.setString(1, nombre);
            Sentencia_preparada.setString(2, apellido);
            Sentencia_preparada.setString(  3, fechaNacimiento);
            Sentencia_preparada.setString(4, nacionalidad);
            Sentencia_preparada.setString(5, telefono);
            Sentencia_preparada.setInt(6, idReserva);
            Sentencia_preparada.setInt(7, idHuesped);
           

            int i = Sentencia_preparada.executeUpdate();

            if (i > 0) {

                JOptionPane.showMessageDialog(null, "Datos Modificados");

            } else {

                JOptionPane.showMessageDialog(null, "No se Modificaron los Datos");
            }
            return true;

        } catch (SQLException  e) {

            System.out.println("Error: "+e.getMessage());

            return false;
        }

    }
    
    public boolean EliminarHuesped(Integer idHuesped){
        PreparedStatement Sentencia_preparada= null;
        Connection conexion= conectar();
        
        String ConsultaHuespedes = "DELETE FROM huespedes WHERE IdHuesped=? ";
        
               
        try {
            Sentencia_preparada=conexion.prepareStatement(ConsultaHuespedes);
            Sentencia_preparada.setInt(1, idHuesped);
            int i=Sentencia_preparada.executeUpdate();
            
            if (i > 0) {

                JOptionPane.showMessageDialog(null, "Datos Eliminar");

            } else {

                JOptionPane.showMessageDialog(null, "No se Eliminaron los Datos");
            }
            
            return true;
            
        } catch (SQLException e) {
            
            System.out.println("No se pudo Eliminar el huesped" +e.getMessage());
        }
        
        return false;
        
        
    } 
    
  

   

}
