package Achraf.Ej2.Questionario;
// Afegeix un getter i un setter per a l'atribut model a la classe Coche.
public class Coche { 
    private String model;

    public String GetModel(){
        return model;
    }
    public void SetModel(String model){
        this.model = model;
    }
    public void mostrarModel(){
        System.out.println("El model del coche és: " + model);
    }
}