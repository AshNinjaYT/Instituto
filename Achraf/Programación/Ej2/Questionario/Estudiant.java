package Achraf.Ej2.Questionario;

public class Estudiant {
    String nom;
    int edat;
    private String dni;

    public String GetDni() {
        return dni;
    }

    public void SetDni(String dni) {
        this.dni = dni;
    }

    public void mostraInformacion(String dni) {
        System.out.println("Nom: " + nom + " Edat: " + edat + " DNI: " + dni);
    }
}
