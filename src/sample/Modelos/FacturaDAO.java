package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FacturaDAO {
    int folio;
    String fecha;
    String hora;
    double total;

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    public ObservableList<FacturaDAO> SELECCION_FAC(){
        ObservableList<FacturaDAO> lista = FXCollections.observableArrayList();
        FacturaDAO obj= null;
        String consulta="SELECT * FROM tblfactura";
        try{
            Statement stmt= MySQL_Restaurnt.con.createStatement();
            ResultSet resu=stmt.executeQuery(consulta);
            while (resu.next()){
                obj= new FacturaDAO();
                obj.folio= resu.getInt("folio");
                obj.fecha= resu.getString("fecha");
                obj.hora=resu.getString("hora");
                obj.total=resu.getDouble("total");
                lista.add(obj);
            }
        }catch (Exception e){}
        return lista;
    }
    public void INSERTAR(double tot){
        String consulta = "INSERT INTO tblfactura(fecha,hora,total) VALUES(curdate(),curtime(),"+tot+")";
        try {
            Statement stmt= MySQL_Restaurnt.con.createStatement();
            stmt.executeUpdate(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
