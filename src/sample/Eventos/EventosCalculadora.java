package sample.Eventos;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class EventosCalculadora implements EventHandler {

    private String valor;
    public static String result;
    public static TextField txtOperation;
    public static String suma1, suma2,simbolo;
    public static boolean bandPunto=false,bandValor=false,banderaIgual=false, banderaError=false;
    public EventosCalculadora (String valor, TextField txtOp){
        this.valor=valor;
        this.txtOperation=txtOp;

    }

    @Override
    public void handle(Event event) {

        switch (valor){
            case "1":
                if (bandValor==false)
                    txtOperation.setText(txtOperation.getText()+valor);
                break;
            case "2":
                if (bandValor==false)
                    txtOperation.setText(txtOperation.getText()+valor);
                break;
            case "3":
                if (bandValor==false)
                    txtOperation.setText(txtOperation.getText()+valor);
                break;
            case "4":
                if (bandValor==false)
                    txtOperation.setText(txtOperation.getText()+valor);
                break;
            case "5":
                if (bandValor==false)
                    txtOperation.setText(txtOperation.getText()+valor);
                break;
            case "6":
                if (bandValor==false)
                   txtOperation.setText(txtOperation.getText()+valor);
                break;
            case "7":
                if (bandValor==false)
                    txtOperation.setText(txtOperation.getText()+valor);
                break;
            case "8":
                if (bandValor==false)
                    txtOperation.setText(txtOperation.getText()+valor);
                break;
            case "9":
                if (bandValor==false)
                  txtOperation.setText(txtOperation.getText()+valor);
                break;
            case "0":
                if (txtOperation.getText().equals("") && bandValor==false)
                    txtOperation.setText(valor);
                 else
                    txtOperation.setText(txtOperation.getText()+valor);

                break;
            case "+":
                if (!txtOperation.getText().equals(""))
                {
                    bandPunto=false;
                    bandValor=false;
                    banderaIgual=false;
                    suma1 = txtOperation.getText();
                    simbolo = "+" ;
                    txtOperation.setText("");
                }
                break;
            case "-":
                if (!txtOperation.getText().equals(""))
                {
                    bandPunto=false;
                    bandValor=false;
                    banderaIgual=false;
                    suma1 = txtOperation.getText();
                    simbolo= "-" ;
                    txtOperation.setText("");
                }
                break;
            case "*":
                if (!txtOperation.getText().equals(""))
                {
                    bandPunto=false;
                    bandValor=false;
                    banderaIgual=false;
                    suma1 = txtOperation.getText();
                    simbolo = "*" ;
                    txtOperation.setText("");
                }
                break;
            case "/":
                if (!txtOperation.getText().equals(""))
                {
                    bandPunto=false;
                    bandValor=false;
                    banderaIgual=false;
                    suma1 = txtOperation.getText();
                    simbolo = "/" ;
                    txtOperation.setText("");
                }
                break;

            case ".":
                if (bandPunto==false){
                    bandPunto=true;
                    bandValor=false;
                    txtOperation.setText(txtOperation.getText()+valor);
                }
                break;
            case "=":
                if ( !suma1.equals(null) && !simbolo.equals(null) && !suma1.equals(".")  && banderaIgual==false
                        &&  banderaError==false && !suma1.equals("") && !txtOperation.getText().equals("") & !txtOperation.getText().equals("."))
                {
                    banderaIgual=true;
                    suma2 = txtOperation.getText();
                    if (!suma2.equals(".") && !suma2.equals(null)){
                        result = calculadora(suma1, suma2, simbolo);
                        if (!result.equals("Infinity") && !result.equals("NaN"))
                            txtOperation.setText(result);
                        else{
                            txtOperation.setText("E0");
                            banderaError=true;
                        }
                    }else{
                        txtOperation.setText("E0");
                        banderaError=true;
                    }
                }
                else{
                    banderaError=true;
                    txtOperation.setText("E0");
                }
                break;
        }
    }
    public String calculadora(String dato, String dato2, String signo){
        Double resultado=null,sum1,sum2;
        sum1=Double.parseDouble(dato);
        sum2=Double.parseDouble(dato2);
        String respuesta;

        if (signo.equals("-")) {
            resultado=sum1-sum2;
        }
        if (signo.equals("+")) {
            resultado=sum1+sum2;
        }
        if (signo.equals("*")) {
            resultado=sum1*sum2;
        }
        if (signo.equals("/")) {
                resultado=sum1/sum2;
        }
        bandPunto=true;
        bandValor=true;
        suma1="";
        suma2="";

        respuesta = resultado.toString();
        return respuesta;
    }
}

