package Ej2.Questionario;

public class Main {
    public static void main(String[] args) {

        Llibre llibre = new Llibre();
        Professor professor = new Professor();
        Animal animal = new Animal();
        Pellicula pellicula = new Pellicula();
        llibre.titol = "berserk";
        llibre.autor = "Kentaro Miura";
        professor.nom = "Josep";
        professor.assignatura = "Programació";
        animal.nom = "gat";
        animal.especie = "felí";
        pellicula.titol = "Your Name";
        pellicula.director = "Makoto Shinkai";

        llibre.llegir();
        professor.ensenyar();
        animal.ferSoroll();
        pellicula.reproduir();
    }
}
