package terratremol;

public class RiscTerratremol {
    
    public static String nivellRisc(double magnitud) throws MagnitudIncorrectaException {
        if (magnitud > 0 && magnitud <= 10) {
            if (magnitud < 2.0) {
                return "No percebut";
            } else if (magnitud < 4.0) {
                return "Lleu";
            } else if (magnitud < 6.0) {
                return "Moderat";
            } else if (magnitud < 8.0) {
                return "Fort";
            } else {
                return "Catastrfic";
            }
        } else {
            throw new MagnitudIncorrectaException("Valor de magnitud incorrecte: " + magnitud);
        }
    }
}