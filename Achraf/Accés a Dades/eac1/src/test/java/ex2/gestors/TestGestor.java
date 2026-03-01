/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ex2.gestors;

import ex2.model.Estacio;
import ex2.model.Pista;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author joan
 */
public class TestGestor {

    private static List<Pista> pistes;

    public TestGestor() {
    }

    @BeforeAll
    public static void setUpClass() {
        pistes = List.of(
                new Pista("A101", "La Canal", "Negra", "Pols", 60, 1500, 450, true),
                new Pista("A102", "Pista del Bosc", "Blava", "Pols-Dura", 45, 2100, 200, true),
                new Pista("A103", "Debutants I", "Verda", "Dura", 30, 500, 50, true),
                new Pista("A104", "Muro de les Àligues", "Negra", "Sense neu", 0, 1800, 600, false),
                new Pista("A105", "Camí Ral", "Verda", "Primavera", 40, 3200, 150, true),
                new Pista("A106", "La Directa", "Vermella", "Pols", 55, 1200, 380, true),
                new Pista("A107", "Costa dels Avets", "Vermella", "Dura", 10, 2400, 310, false),
                new Pista("A108", "Pala Central", "Vermella", "Pols-Dura", 50, 900, 250, true),
                new Pista("A109", "Passeig de la Neu", "Blava", "Primavera", 35, 4100, 120, true),
                new Pista("A110", "Tub de la Coma", "Negra", "Pols", 70, 1350, 410, true)
        );

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of llegirFitxerXML method, of class GestorEstacio.
     */
    @Test
    public void testLlegirFitxerXML() throws GestorEstacioException {
        System.out.println("llegirFitxerXML");
        String nomFitxer = "estacio.xml";
        GestorEstacio instance = new GestorEstacio();

        Estacio expResult = new Estacio(
                "ST-001",
                "Neu Gran",
                "Cerdanya",
                "https://www.neugran.cat",
                2535,
                "Molt bona",
                80
        );
        for (Pista pista : pistes) {
            expResult.getPistes().add(pista);
        }

        Estacio result = instance.llegirFitxerXML(nomFitxer);
        assertEquals(expResult, result);
        assertThrows(GestorEstacioException.class, () -> instance.llegirFitxerXML("no_existeix.xml"));
    }

    /**
     * Test of gravarFitxerXML method, of class GestorEstacio.
     */
    @Test
    public void testGravarFitxerXML() throws GestorEstacioException {
        System.out.println("gravarFitxerXML");
        String nomFitxer = "estacio2.xml";
        GestorEstacio instance = new GestorEstacio();

        Estacio estaciogravada = new Estacio(
                "ST-001",
                "Neu Gran",
                "Cerdanya",
                "https://www.neugran.cat",
                2535,
                "Molt bona",
                80
        );
        for (Pista pista : pistes) {
            estaciogravada.getPistes().add(pista);
        }

        instance.gravarFitxerXML(nomFitxer, estaciogravada);

        Estacio estaciollegida = instance.llegirFitxerXML(nomFitxer);

        assertEquals(estaciollegida, estaciogravada);
        assertThrows(GestorEstacioException.class, () -> instance.gravarFitxerXML(null, estaciogravada));
    }

    /**
     * Test of llegirFitxerJSON method, of class GestorEstacio.
     */
    @Test
    public void testLlegirFitxerJSON() throws GestorEstacioException {
        System.out.println("llegirFitxerJSON");
        String nomFitxer = "estacio.json";
        GestorEstacio instance = new GestorEstacio();

        Estacio expResult = new Estacio(
                "ST-001",
                "Neu Gran",
                "Cerdanya",
                "https://www.neugran.cat",
                2535,
                "Molt bona",
                80
        );
        for (Pista pista : pistes) {
            expResult.getPistes().add(pista);
        }

        Estacio result = instance.llegirFitxerJSON(nomFitxer);
        assertEquals(expResult, result);
        assertThrows(GestorEstacioException.class, () -> instance.llegirFitxerJSON("no_existeix.json"));
    }

    /**
     * Test of gravarFitxerJSON method, of class GestorEstacio.
     */
    @Test
    public void testGravarFitxerJSON() throws GestorEstacioException {
        System.out.println("gravarFitxerJSON");
        String nomFitxer = "estacio2.json";
        GestorEstacio instance = new GestorEstacio();

        Estacio estaciogravada = new Estacio(
                "ST-001",
                "Neu Gran",
                "Cerdanya",
                "https://www.neugran.cat",
                2535,
                "Molt bona",
                80
        );
        for (Pista pista : pistes) {
            estaciogravada.getPistes().add(pista);
        }

        instance.gravarFitxerJSON(nomFitxer, estaciogravada);

        Estacio estaciollegida = instance.llegirFitxerJSON(nomFitxer);

        assertEquals(estaciollegida, estaciogravada);
        assertThrows(GestorEstacioException.class, () -> instance.gravarFitxerJSON(null, estaciogravada));
    }

    /**
     * Test of actuaitzaEstatObertura method, of class Estacio.
     */
    @ParameterizedTest
    @MethodSource("dadesObertura")
    void testActualitzaEstatObertura(List<Boolean> obertesEsperades, int percentatgeEsperat) {
        // Crear l'estació
        Estacio estacio = new Estacio(
                "ST-001",
                "Neu Gran",
                "Cerdanya",
                "https://www.neugran.cat",
                2535,
                "Molt bona",
                80
        );

        // Afegir pistes segons la llista de booleans
        int idx = 1;
        for (Boolean oberta : obertesEsperades) {
            Pista pista = new Pista(
                    "P" + idx,
                    "Pista " + idx,
                    "blava",
                    "neu pols",
                    50,
                    1000,
                    200,
                    oberta
            );
            estacio.getPistes().add(pista);
            idx++;
        }

        // Cridar al mètode que calcula percentatge
        estacio.actualitzaEstatObertura();

        assertEquals(percentatgeEsperat, estacio.getEstatObertura(),
                "El percentatge de pistes obertes arrodonit no és correcte");
    }

    // Llistes de pistesobertes i percentatge calculat
    static Stream<Arguments> dadesObertura() {
        return Stream.of(
                Arguments.of(
                        List.of(true, true, false, false),
                        50
                ),
                Arguments.of(
                        List.of(false, false, false, true),
                        25
                ),
                Arguments.of(
                        List.of(true, true, true, false, false, false),
                        50
                ),
                Arguments.of(
                        List.of(true, false, true, false, true, false, true),
                        57 // 4 de 7 obertes → 57,14 → 57
                ),
                Arguments.of(
                        List.of(true, false, false, false, false, false, false, false, false),
                        11 // 1 de 9 obertes → 11,11 → 11
                ),
                Arguments.of(
                        List.of(), // cas sense pistes
                        0
                )
        );
    }

}
