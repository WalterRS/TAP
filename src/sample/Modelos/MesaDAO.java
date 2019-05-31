package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MesaDAO {
    int idMesa;
    int lugares;
    int idstatus;

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getLugares() {
        return lugares;
    }

    public void setLugares(int lugares) {
        this.lugares = lugares;
    }

    public int getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(int idstatus) {
        this.idstatus = idstatus;
    }

    public ObservableList<MesaDAO> SELECCION_MESA(int id){

        ObservableList<MesaDAO> lista = FXCollections.observableArrayList();
        MesaDAO obj= null;
        String consulta="SELECT * FROM tblmesa WHERE idstatus="+id;
        try{
            Statement stmt= MySQL_Restaurnt.con.createStatement();
            ResultSet resu=stmt.executeQuery(consulta);
            while (resu.next()){
                obj= new MesaDAO();
                obj.idMesa= resu.getInt("idMesa");
                obj.lugares= resu.getInt ("lugares");
                obj.idstatus=resu.getInt("idstatus");
                lista.add(obj);
            }
        }catch (Exception e){}
        return lista;
    }
    public void ACTUALIZAR(int idMe,int idst){
        String consulta = "UPDATE tblmesa SET idstatus="+idst+" where idMesa="+idMe;
        try {
            Statement stmt= MySQL_Restaurnt.con.createStatement();
            stmt.executeUpdate(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  ObservableList<MesaDAO>  BUSCAR(int idMe){
        ObservableList<MesaDAO> lista = FXCollections.observableArrayList();
        MesaDAO obj= null;

        String consulta = "SELECT lugares from tblmesa where idMesa="+idMe;
        try {
            Statement stmt= MySQL_Restaurnt.con.createStatement();
            ResultSet resu=stmt.executeQuery(consulta);
            while (resu.next()){
                obj= new MesaDAO();
                obj.lugares= resu.getInt ("lugares");
                lista.add(obj);
            }
        }catch (Exception e){}
        return lista;
    }
}
