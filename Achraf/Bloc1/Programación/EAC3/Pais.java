import java.util.ArrayList;

public class Pais {
    String nom;
    private int numMedalles;
    private ArrayList<Medalla> numMedallesList;

    public int getNumMedalles() {
        return numMedalles;
    }

    Pais (String nom) {
        this.nom = nom;
        numMedalles = 0;
    }

    public void novaMedalla(Medalla m) {
        if (numMedalles <= 100) {
            numMedalles++;
        } else  {
            System.out.println("No puede tener mas de 100 medallas");
        }
    }
    void mostrarMedalles()  {
        System.out.println("Medallas de l'atleta " + nom + ":");
        for (Medalla m : numMedallesList) {
            System.out.println(m.descripcio());            
        }
    }
}
