package conexiones;

//import com.sun.jdi.connect.spi.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;



/**
 *
 * @author Oscar
 */
public class conexion {
    
  private static final String url="jdbc:mysql://localhost:3306/hotel_alura";
  private static final String usuario="root";
  private static final String contraseña="@leger1194";
  private static final String clase="com.mysql.cj.jdbc.Driver";
    
  public static Connection conectar() {
      
      Connection conexion=null;
      try{
         Class.forName(clase);
         conexion= DriverManager.getConnection(url,usuario,contraseña);
        //  JOptionPane.showMessageDialog(null, "conexion establecida");
          
         // conexion.close();
          
          
         
      }catch(ClassNotFoundException | SQLException e){
         
          JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos " +e.toString());
          
      }/*finally {
              try {
                  if (conexion != null) {
                      conexion.close();
                  }
              }catch(SQLException ex){
                  
              }
          }*/
      
      return conexion;
  }
  

    
}
