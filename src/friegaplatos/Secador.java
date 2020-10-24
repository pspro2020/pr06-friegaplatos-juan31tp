package friegaplatos;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Secador implements Runnable {

    private final Bandeja bandejaMojada;
    private final Bandeja bandejaSeca;
    Random rnd=new Random();
    String role;

    public Secador(Bandeja bandejaSeca, Bandeja bandejaMojada) {
        this.bandejaSeca=bandejaSeca;
        this.bandejaMojada=bandejaMojada;
        role="Secador";
    }

    @Override
    public void run() {
        Plato plato;
        while (!Thread.currentThread().isInterrupted()){
            try {
                plato=bandejaMojada.sacar(role);
                TimeUnit.SECONDS.sleep(rnd.nextInt(8)+4);
                bandejaSeca.colocar(plato,role);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
