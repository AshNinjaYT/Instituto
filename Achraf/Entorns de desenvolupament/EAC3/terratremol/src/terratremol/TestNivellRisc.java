package terratremol;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestNivellRisc {

    // CASO 1: Valor -1.0 (Debe lanzar excepción)
    @Test
    void testNivellRiscNegativo() {
        assertThrows(MagnitudIncorrectaException.class, () -> {
            RiscTerratremol.nivellRisc(-1.0);
        });
    }

    // CASO 2: Valor 0.0 (Debe lanzar excepción porque el código dice magnitud > 0)
    @Test
    void testNivellRiscCero() {
        assertThrows(MagnitudIncorrectaException.class, () -> {
            RiscTerratremol.nivellRisc(0.0);
        });
    }

    // CASO 3: Valor 1.5 -> "No percebut"
    @Test
    void testNivellRisc1_5() throws MagnitudIncorrectaException {
        assertEquals("No percebut", RiscTerratremol.nivellRisc(1.5));
    }

    // CASO 4: Valor 3.5 -> "Lleu"
    @Test
    void testNivellRisc3_5() throws MagnitudIncorrectaException {
        assertEquals("Lleu", RiscTerratremol.nivellRisc(3.5));
    }

    // CASO 5: Valor 5.0 -> "Moderat"
    @Test
    void testNivellRisc5_0() throws MagnitudIncorrectaException {
        assertEquals("Moderat", RiscTerratremol.nivellRisc(5.0));
    }

    // CASO 6: Valor 7.0 -> "Fort"
    @Test
    void testNivellRisc7_0() throws MagnitudIncorrectaException {
        assertEquals("Fort", RiscTerratremol.nivellRisc(7.0));
    }

    // CASO 7: Valor 9.0 -> "Catastrfic" (Ojo, comprueba si tu código lleva acento o no)
    @Test
    void testNivellRisc9_0() throws MagnitudIncorrectaException {
        // Ajusta el texto esperado según lo que tengas en RiscTerratremol.java
        assertEquals("Catastrfic", RiscTerratremol.nivellRisc(9.0));
    }

    // CASO 8: Valor 12.0 (Debe lanzar excepción)
    @Test
    void testNivellRisc12_0() {
        assertThrows(MagnitudIncorrectaException.class, () -> {
            RiscTerratremol.nivellRisc(12.0);
        });
    }
}
