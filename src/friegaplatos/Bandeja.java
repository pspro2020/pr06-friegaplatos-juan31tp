package friegaplatos;

import java.time.LocalTime;
import java.util.ArrayList;


public class Bandeja {

    ArrayList<Plato> platosEnBandeja = new ArrayList();

    public Bandeja(int nPlatos) {
        for (int i=0;i<nPlatos;i++){
            platosEnBandeja.add(new Plato(i));
        }
    }

    public void meterPlato(Plato plato, String role) throws InterruptedException {
        synchronized (this){
            while (platosEnBandeja.size()>10){
                System.out.println(LocalTime.now() + " -- " + " Please, " + role + " there's no capacity for more dishes");
                wait();
            }
            platosEnBandeja.add(plato);
            System.out.println(LocalTime.now() + " -- " + role + " put the dish numbered with the serial: " + plato.getSerial());
            notifyAll();
        }
    }

    public Plato sacarPlato(String role) throws InterruptedException {
        Plato plato;
        synchronized (this){
            while (platosEnBandeja.isEmpty()){
                System.out.println(LocalTime.now() + " -- " + " Please, " + role + " wait, there are no dishes");
                wait();
            }
            plato=platosEnBandeja.remove(0);
            System.out.println(LocalTime.now() + " -- " +role + " took the dish numbered with the serial: " + plato.getSerial());
            notifyAll();
            return plato;
        }
    }

}
