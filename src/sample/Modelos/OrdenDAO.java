package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrdenDAO {
    private int idFolio ;
    private int idMesa ;
    private int idPlatillo;
    private String fecha;
    private double precio;
    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdFolio() {
        return idFolio;
    }

    public void setIdFolio(int idFolio) {
        this.idFolio = idFolio;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ObservableList<OrdenDAO> SELECCIONAR(){
        ObservableList<OrdenDAO> lista= FXCollections.observableArrayList();
        OrdenDAO objePDAO= null;
        String consulta="SELECT * FROM tblorden ORDER BY idFolio DESC";
        try{
            Statement stmt= MySQL_Restaurnt.con.createStatement();
            ResultSet resu=stmt.executeQuery(consulta);
            while (resu.next()){
                objePDAO= new OrdenDAO();
                objePDAO.idFolio=resu.getInt("idFolio");
                objePDAO.idMesa=resu.getInt("idMesa");
                objePDAO.idPlatillo=resu.getInt("idPlatillo");
                objePDAO.fecha= resu.getString("fecha");
                objePDAO.precio= resu.getDouble("precio");
                if (resu.getInt("estado")==1){
                    objePDAO.estado="ATENDIENDO";
                }else
                    if(resu.getInt("estado")==2){
                    objePDAO.estado="TERMINADA";
                }
                lista.add(objePDAO);
            }
        }catch (Exception e){}
        return lista;

    }
    public void INSERTAR(int idm,int idP,int est,double pre){
        String consulta = "INSERT INTO tblorden(idmesa,idplatillo,estado,fecha,precio) " +
                " VALUES("+idm+","+idP+","+est+","+"curdate()"+","+pre+"0)";
        try {
            Statement stmt= MySQL_Restaurnt.con.createStatement();
            stmt.executeUpdate(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void ACTUALIZAR(int idMesa ){
        String consulta = "UPDATE tblorden SET estado = 2 where idMesa="+idMesa;
        try {
            Statement stmt= MySQL_Restaurnt.con.createStatement();
            stmt.executeUpdate(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
