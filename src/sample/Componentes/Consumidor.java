package sample.Componentes;

public class Consumidor extends Thread{

    RecursoCompartido obRC;

    public Consumidor(RecursoCompartido ob) {
        this.obRC=ob;
    }

    @Override
    public void run(){
        super.run();
        for (int i=0;i<15;i++){
            obRC.VaciarRecurso();
        }
    }
}
