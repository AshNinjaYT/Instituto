public static void calcularDescompteTotal(boolean clientVIP, boolean cupoDescompte) {

    double total = 0;
    double descompte = 0;

    int numProductes = scanner.nextInt();

    if (numProductes <= 0) {
        System.out.println("Error: nombre de productes invàlid");
    } else {
        int i = 1;

        while (i <= numProductes) {
            double preuProducte = scanner.nextDouble();

            if (preuProducte < 0) {
                System.out.println("Error: preu invàlid");
            } else {
                total = total + preuProducte;
            }

            i = i + 1;
        }
        if (clientVIP) {
            descompte = descompte + 10;
        }

        if (cupoDescompte) {
            descompte = descompte + 5;
        }

        if (total > 100) {
            descompte = descompte + 5;
        }
        double preuFinal = total - (total * descompte / 100);
        System.out.println("Preu final: " + preuFinal);
    }
}