public class E2 {

    public static void main(String[] args) {
        // Dimensiones iniciales
        int llarg1 = 300;
        int ample1 = 150;
        int prof1 = 20;
        int llarg2 = 300;
        int ample2 = 80;
        int prof2 = 35;

        int area1 = ample1 * llarg1;
        int area2 = ample2 * llarg2;
        System.out.println("Area piscina 1: " + area1);
        System.out.println("Area piscina 2: " + area2);

        int volm1 = area1 * prof1;
        int volm2 = area2 * prof2;
        System.out.println("Volum piscina 1: " + volm1);
        System.out.println("Volum piscina 2: " + volm2);

        int llargNou = llarg1;
        int ampleNou = ample1 + ample2;
        System.out.println("Llarg total de les piscines: " + llargNou);
        System.out.println("Ample total de les piscines: " + ampleNou);

        int areaNou = ampleNou * llargNou;
        int volumNou = volm1 + volm2;
        System.out.println("Area combinada de les piscines: " + areaNou);
        System.out.println("Volum total de les piscines: " + volumNou);

        //
        int tmpPr1 = prof1;
        prof1 = prof2;
        prof2 = tmpPr1;
        System.out.println("Profuntitat de la piscina 1 intercambiada amb la 2ª: " + prof1);
        System.out.println("Profuntitat de la piscina 2 intercambiada amb la 1ª: " + prof2);

        // Recalcular el volum de la piscina 1 y piscina 2 després de l'intercanvi
        volm1 = area1 * prof1;
        volm2 = area2 * prof2;
        System.out.println("Volum de la piscina 1 recalculada: " + volm1);
        System.out.println("Volum de la piscina 2 recalculada: " + volm2);

    }
}