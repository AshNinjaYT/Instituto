import java.util.ArrayList;

public class Atleta {
    String nom;
    Pais pais;
    String disciplina;
    private int numMedalles;
    private ArrayList<Medalla> numMedallesList;

    public int getNumMedalles() {
        return numMedalles;
    }

    Atleta(String nom, Pais pais, String disciplina) {
        this.nom = nom;
        this.pais = pais;
        this.disciplina = disciplina;
        this.numMedallesList = new ArrayList<>();
    }

    void guanyarMedalla(Medalla m) {
        if (numMedalles <= 10) {
            numMedalles++;
            numMedallesList.add(m);
        }
    }

    void mostrarMedalles()  {
        System.out.println("Medallas de l'atleta " + nom + ":");
        for (Medalla m : numMedallesList) {
            System.out.println(m.descripcio());            
        }
    }
}