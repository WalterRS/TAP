package sample.Componentes;


import javafx.scene.control.ProgressBar;

public class Vengadores extends Thread{
    private ProgressBar pgbCarril;
    public Vengadores(String nombre,ProgressBar pgbCarril){
        super(nombre);
        this.pgbCarril=pgbCarril;
    }

    @Override
    public void run() {
        super.run();

            try {
                double avance=0;
                while (avance<1){
                    avance+=Math.random()/10;
                    pgbCarril.setProgress(avance);
                sleep((long)(Math.random()*1000));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}
