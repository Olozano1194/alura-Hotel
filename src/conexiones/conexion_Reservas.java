package conexiones;

import java.util.Date;
import javax.swing.JTextField;

/**
 *
 * @author Oscar
 */
public class conexion_Reservas {
    
    private String formaPago;
    private Date fechaCheckIn,fechaCheckOut;
    private int  valorTarifa,id_Reserva;

    public conexion_Reservas() {
    }

    public conexion_Reservas(int id_reserva) {
        this.id_Reserva = id_reserva;
    }
    
    

    
    public int getId_reserva() {
        return id_Reserva;
    }

    public void setId(int id_reserva) {
        this.id_Reserva = id_reserva;
    }

    public Date getFechaCheckIn() {
        return fechaCheckIn;
    }

    public void setFechaCheckIn(Date fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
    }

    public Date getFechaCheckOut() {
        return fechaCheckOut;
    }

    public void setFechaCheckOut(Date fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
    }
    
   public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public int getValorTarifa() {
        return valorTarifa;
    }

    public void setValorTarifa(int valorTarifa) {
        this.valorTarifa = valorTarifa;
    }

    public void setValorTarifa(JTextField txt_valor_reserva) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    
    
    
    
}
