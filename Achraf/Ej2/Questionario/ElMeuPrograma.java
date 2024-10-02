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

        Calculadora calculadora = new Calculadora(); // Se crea una instancia de la clase Calculadora
        calculadora.numeroSuma1 = 5; // Se asigna el primer número para la suma
        calculadora.numeroSuma2 = 5; // Se asigna el segundo número para la suma
        calculadora.sumar(); // Se llama al método para realizar la suma
        calculadora.numeroMult1 = 12; // Se asigna el primer número para la multiplicación
        calculadora.numeroMult2 = 2; // Se asigna el segundo número para la multiplicación
        calculadora.multiplicar(); // Se llama al método para realizar la multiplicación

        Salutacio salutacio = new Salutacio(); // Se crea una instancia de la clase Salutacio
        salutacio.nom = "Pere"; // Se asigna el nombre para la salutación
        salutacio.saludar(); // Se llama al método

        Conversor conversor = new Conversor(); // Se crea una instancia de la clase Conversor
        conversor.euros = 50; // Se asigna la cantidad en euros
        conversor.convertirADolars(); // Se llama al método

        Estudiant estudiant = new Estudiant();
        estudiant.nom = "Anna";
        estudiant.edat = 20;
        estudiant.SetDni("12345678A");
        String dni = estudiant.GetDni();
        estudiant.mostraInformacion(dni);

        Coche coche = new Coche();
        coche.SetModel("A3");
        coche.mostrarModel();
    }
}
