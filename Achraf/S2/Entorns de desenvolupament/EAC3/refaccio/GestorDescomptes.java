package refaccio;

public class GestorDescomptes {

    //Definicion de constantes para los descuentos.
    private static final double DESCOMPTE_BASIC = 0.05;
    private static final double DESCOMPTE_PREMIUM1 = 0.10;
    private static final double DESCOMPTE_PREMIUM2 = 0.15;
    private static final double DESCOMPTE_VIP = 0.20;

    //Definicion de constantes para los umbrales.
    private static final double UMBRAL_BAJO = 100;
    private static final double UMBRAL_MEDIO = 200;
    private static final double UMBRAL_ALTO = 500;

    //Definicion de constantes para los tipos de clientes.
    private static final String CLIENTE_BASIC = "basic";
    private static final String CLIENTE_PREMIUM = "premium";
    private static final String CLIENTE_VIP = "vip";

    //Definicion de constante para mensaje de cliente no reconocido.
    private static final String CLIENTE_NO_RECONOCIDO = "Tipus de client no reconegut.";

    //Metodo principal para calcular el precio final despues de aplicar el descuento.
    public static double calculaPreuFinal(double totalCompra, String tipusClient) {

        //Definir las variables y hacer el calculo del descuento y precio final.
        double descompte = calcularDescuento(totalCompra, tipusClient);;
        double preuFinal = totalCompra - descompte;

        //Llamada al metodo para mostrar el mensaje final y retornar el precio final.
        mensaje(tipusClient, descompte, preuFinal);
        return preuFinal;
    }


    //Metodo para calcular el descuento en formato switch para no repetir codigo y asi mejorar la legibilidad seleccionando solo el caso de cada cliente.
    private static double calcularDescuento(double total, String tipus) {
        double descompte = 0;
        switch (tipus) {
            case CLIENTE_BASIC:
                if(total > UMBRAL_BAJO) descompte = total * DESCOMPTE_BASIC;
            case CLIENTE_PREMIUM:
                if (total > UMBRAL_BAJO) descompte = total * DESCOMPTE_PREMIUM1;
                if (total > UMBRAL_ALTO) descompte = total * DESCOMPTE_PREMIUM2;
            case CLIENTE_VIP:
                if(total > UMBRAL_MEDIO) descompte = total * DESCOMPTE_VIP;
            default:
                System.out.println(CLIENTE_NO_RECONOCIDO);
                return descompte;
        }
    }

    //Metodo para mostrar el mensaje final al usuario.
    public static void mensaje(string text, double descompte, double preuFinal) {
        if (text == CLIENTE_BASIC || text == CLIENTE_PREMIUM || text == CLIENTE_VIP) {
            System.out.println("Client " + text + ". Descompte aplicat: " + descompte + " €. Preu final: " + preuFinal + " €.");
        }
        else {
            System.out.println(CLIENTE_NO_RECONOCIDO);
        }
    }
}