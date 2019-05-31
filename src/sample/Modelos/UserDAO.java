package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO {
    int iduser;
    String name;
    String password;

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ObservableList<UserDAO> SELECCION_USER(int id){
        ObservableList<UserDAO> lista = FXCollections.observableArrayList();
        UserDAO obj= null;
        String consulta="SELECT * FROM tbluser WHERE iduser="+id;
        try{
            Statement stmt= MySQL_Restaurnt.con.createStatement();
            ResultSet resu=stmt.executeQuery(consulta);
            while (resu.next()){
                obj= new UserDAO();
                obj.iduser= resu.getInt("iduser");
                obj.name= resu.getString("name");
                obj.password=resu.getString("password");
                lista.add(obj);
            }
        }catch (Exception e){}
        return lista;
    }
}
