/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package gestors;

import model.Pista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author joan
 */
@TestMethodOrder(OrderAnnotation.class)
public class TestGestor {

    static private GestorPista gestor;
    static private Connection conn;
    static private List<Pista> pistes;

    private static void crearPistes() {
        // Note: This method would require a SkiSlope model class and appropriate manager
        // Following the pattern of createSongs() but adapted for ski slopes
        pistes = new ArrayList<>();

        pistes.add(new Pista("PS-001", "Green Meadow", "Verda", true, 1500, 200, 10, 30, "Polsosa", Arrays.asList("Telecadira Central", "Telesquí Sud")));
        pistes.add(new Pista("PS-002", "Blue Lake", "Blava", false, 2500, 400, 20, 50, "Humida", Arrays.asList("Telecadira Central")));
        pistes.add(new Pista("PS-003", "Red Mountain", "Vermella", true, 3500, 600, 30, 70, "Dura", Arrays.asList("Telecadira Central", "Telecabina Nord")));
        pistes.add(new Pista("PS-004", "Black Ice", "Negra", false, 4500, 800, 40, 100, "Glacial", Arrays.asList("Telecabina Nord")));
        pistes.add(new Pista("PS-005", "Sunrise Valley", "Verda", true, 1800, 250, 12, 35, "Polsosa", Arrays.asList("Telecabina Nord", "Telesquí Sud")));
        pistes.add(new Pista("PS-006", "Crystal Path", "Blava", true, 2200, 350, 18, 45, "Humida", Arrays.asList("Telecabina Nord")));
        pistes.add(new Pista("PS-007", "Eagle's Nest", "Vermella", false, 3800, 650, 35, 75, "Dura", Arrays.asList("Telesquí Sud")));
        pistes.add(new Pista("PS-008", "Devil's Drop", "Negra", true, 5000, 900, 45, 110, "Glacial", Arrays.asList("Telesquí Sud", "Telefèric Cim")));
        pistes.add(new Pista("PS-009", "Whispering Pines", "Verda", false, 1600, 220, 11, 32, "Polsosa", Arrays.asList("Telesquí Sud")));
        pistes.add(new Pista("PS-010", "Silver Stream", "Blava", true, 2700, 420, 22, 55, "Humida", Arrays.asList("Telefèric Cim")));
        pistes.add(new Pista("PS-011", "Thunder Ridge", "Vermella", true, 4000, 700, 38, 80, "Dura", Arrays.asList("Telefèric Cim", "Telecadira Central")));
        pistes.add(new Pista("PS-012", "Avalanche Alley", "Negra", false, 4800, 850, 42, 105, "Glacial", Arrays.asList("Telefèric Cim")));
        pistes.add(new Pista("PS-013", "Moonlight Trail", "Verda", true, 1700, 240, 13, 33, "Polsosa", Arrays.asList("Telecadira Central")));
        pistes.add(new Pista("PS-014", "Frozen Falls", "Blava", false, 2600, 380, 21, 52, "Humida", Arrays.asList("Telecabina Nord", "Telefèric Cim")));
        pistes.add(new Pista("PS-015", "Wild Wolf", "Vermella", true, 3700, 620, 32, 72, "Dura", Arrays.asList("Telesquí Sud")));
        pistes.add(new Pista("PS-016", "Death Wish", "Negra", true, 5200, 950, 48, 115, "Glacial", Arrays.asList("Telefèric Cim")));
        pistes.add(new Pista("PS-017", "Butterfly Slope", "Verda", false, 1550, 210, 10, 31, "Polsosa", Arrays.asList("Telecadira Central")));
        pistes.add(new Pista("PS-018", "Azure Dream", "Blava", true, 2400, 390, 19, 48, "Humida", Arrays.asList("Telecabina Nord")));
        pistes.add(new Pista("PS-019", "Crimson Canyon", "Vermella", false, 3600, 630, 33, 73, "Dura", Arrays.asList("Telesquí Sud", "Telecadira Central")));
        pistes.add(new Pista("PS-020", "Extreme Edge", "Negra", true, 4700, 820, 41, 102, "Glacial", Arrays.asList("Telefèric Cim")));

    }


    public TestGestor() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException, GestorExcepcio {
        String url = "jdbc:postgresql://localhost:5432/eac2ex1_2526s2";

        String usuari = "ioc";
        String contrasenya = "ioc";
        conn = DriverManager.getConnection(url, usuari, contrasenya);

        gestor = new GestorPista(conn);
        crearPistes();
    }

    @AfterAll
    public static void tearDownClass() throws SQLException {
        conn.close();
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    @Order(1)
    public void testDeleteAll() throws GestorExcepcio {
        gestor.esborraTot();
    }

    @Test
    @Order(2)
    public void testAfegeixPista() throws GestorExcepcio {
        for (Pista pista : pistes) {
            gestor.afegeixPista(pista);
            assertThrows(GestorExcepcio.class, () -> gestor.afegeixPista(pista));
        }
    }

    @Test
    @Order(3)
    public void testObtePista() throws GestorExcepcio {
        for (Pista pista : pistes) {
            assertEquals(pista, gestor.obtePista(pista.getId()));
        }

        assertThrows(GestorExcepcio.class, () -> gestor.obtePista("---"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "Telecadira Central",
        "Telefèric Cim",
        "Telesquí Sud",
        "Telecabina Nord",
        "Remuntador Inexistent"
    })
    @Order(4)
    public void testObtePistesPerRemuntador(String remuntador) throws GestorExcepcio {
        List<Pista> pistesRemuntador = gestor.obtePistesPerRemuntador(remuntador);

        // Comptem quantes pistes haurien de tenir aquest remuntador
        long pistesEsperades = pistes.stream()
            .filter(p -> p.getRemuntadorsAcces().contains(remuntador))
            .count();

        assertEquals(pistesEsperades, pistesRemuntador.size(),
            String.format("El remuntador '%s' hauria de tenir %d pistes", remuntador, pistesEsperades));

        // Verifiquem que totes les pistes retornades continguin el remuntador
        for (Pista pista : pistesRemuntador) {
            assertTrue(pista.getRemuntadorsAcces().contains(remuntador),
                String.format("La pista %s hauria de contenir el remuntador '%s'",
                    pista.getId(), remuntador));
        }

        // Verifiquem que totes les pistes que haurien de tenir aquest remuntador estiguin presents
        for (Pista pista : pistes) {
            if (pista.getRemuntadorsAcces().contains(remuntador)) {
                assertTrue(pistesRemuntador.contains(pista),
                    String.format("La pista %s amb remuntador '%s' hauria d'estar a la llista retornada",
                        pista.getId(), remuntador));
            }
        }
    }

    @Test
    @Order(5)
    public void testEsborraPista() throws GestorExcepcio {
        for (Pista pista : pistes) {
            gestor.esborraPista(pista.getId());
            assertThrows(GestorExcepcio.class, () -> gestor.esborraPista(pista.getId()));
        }
    }
}
