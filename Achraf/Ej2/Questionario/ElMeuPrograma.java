package Achraf.Ej2.Questionario;

public class ElMeuPrograma {
    public static void main(String[] args) {
        Llibro llibro = new Llibro(); // Se crea una instancia de la clase llibro
        llibro.titol = "Mentida"; // Se asigna el título del libro
        llibro.autor = "Care Santos"; // Se asigna el autor del libro
        llibro.llegir(); // Se llama al método

        Professor professor = new Professor(); // Se crea una instancia de la clase Professor
        professor.nom = "Josep"; // Se asigna el nombre del profesor
        professor.assignatura = "Programacion"; // Se asigna la asignatura que enseña el profesor
        professor.ensenyar(); // Se llama al método

        Animal animal = new Animal(); // Se crea una instancia de la clase Animal
        animal.nom = "gato"; // Se asigna el nombre del animal
        animal.especie = "felino"; // Se asigna la especie del animal
        animal.ferSoroll(); // Se llama al método

        Pelicula pelicula = new Pelicula(); // Se crea una instancia de la clase pelicula
        pelicula.titol = "Your Name"; // Se asigna el título de la película
        pelicula.director = "Makoto Shinkai"; // Se asigna el director de la película
        pelicula.reproduir(); // Se llama al método

        Calculadora calculadora = new Calculadora();
        calculadora.n1 = 2; // 
        calculadora.n2 = 5;
        calculadora.sumar();
    }
}
