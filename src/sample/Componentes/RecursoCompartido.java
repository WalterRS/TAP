package sample.Componentes;

public class RecursoCompartido {
    private int  pos =0;
    int [] recurso = new int[5];
    boolean stopThread=false;

    public synchronized void LlenarRecurso(int valor){

        try {
            if (stopThread ==true){
                System.out.println("Productor se Detiene");
                wait();
                System.out.println("Productor se Reinicia");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor["+pos+"]="+valor);
        recurso[pos]=valor;
        pos ++;
        if (pos ==recurso.length);{
            pos--;
            notify();//Notifica y Libera el recurso
            stopThread=true;
        }
    }
    public synchronized void VaciarRecurso(){

        try {
            if (stopThread==false) {
                System.out.println("Consumidor se Detiene");
                wait();
                System.out.println("Consumidor se Reinicia");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int valor =recurso[pos];
        System.out.println(valor+"=Valor["+pos+"]");
        pos --;
        if (pos <0);{
            pos++;
            notify();//Notifica y Libera el recurso
            stopThread=false;
        }
    }
}
