import java.util.Scanner;

public class ejercicio {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Numero 1: ");
        int n1 = input.nextInt();
        System.out.print("Operador: ");
        String operador = input.next();
        System.out.print("Numero 2: ");
        int n2 = input.nextInt();
        if (operador == "+") {
            System.out.println(n1 + " + " + n2 + " = " + (n1 + n2));
        }
        else if (operador == "-") {
            System.out.println(n1 + " - " + n2 + " = " + (n1 - n2));
        } 
        else if (operador == "x") {
            System.out.println(n1 + " x " + n2 + " = " + (n1 * n2));
        } 
        else if (operador == "/") {
            System.out.println(n1 + " / " + n2 + " = " + (n1 / n2));
        }
    }
}