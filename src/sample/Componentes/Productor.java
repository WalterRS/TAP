package sample.Componentes;

public class Productor extends Thread{

    RecursoCompartido obRC;

    public Productor(RecursoCompartido ob) {
        this.obRC=ob;
    }

    @Override
    public void run(){
        super.run();
        for (int i=0;i<15;i++){
            obRC.LlenarRecurso((int)(Math.random()*1000));
        }
    }

}
