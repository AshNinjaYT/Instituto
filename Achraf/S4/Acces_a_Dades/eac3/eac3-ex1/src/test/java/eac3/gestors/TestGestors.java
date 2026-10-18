/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package eac3.gestors;

import eac3.model.Pista;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import net.xqj.basex.BaseXXQDataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author professor
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestGestors {

    private static XQDataSource xQDataSource;
    private static List<Pista> pistes;

    private static void creaLlista() {
        pistes = new ArrayList<>();
        int i = 0;

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

    public TestGestors() {
    }

    @BeforeAll
    public static void setUpClass() throws XQException, ManagerException {
        xQDataSource = new BaseXXQDataSource();
        xQDataSource.setProperty("serverName", "localhost");
        xQDataSource.setProperty("port", "1984");

        XQConnection conn = xQDataSource.getConnection("admin", "admin");
        PistaManager pistaManager = new PistaManager(conn);
        pistaManager.deleteAll();
        conn.close();

        creaLlista();
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

    @Test
    @Order(1)
    public void testInsert() throws Exception {
        System.out.println("inserir");

        XQConnection conn = xQDataSource.getConnection("admin", "admin");
        PistaManager instance = new PistaManager(conn);

        for (Pista pista : pistes) {
            instance.insert(pista);
        }

        for (Pista pista : pistes) {
            assertThrows(ManagerException.class, () -> {
                instance.insert(pista);
            });
        }

        conn.close();

    }

    @Test
    @Order(2)
    public void testGetPista() throws Exception {
        System.out.println("getPista");
        XQConnection conn = xQDataSource.getConnection("admin", "admin");
        PistaManager instance = new PistaManager(conn);

        for (Pista pista : pistes) {
            Pista pista2 = instance.getPista(pista.getId());
            assertEquals(pista, pista2);
        }

        conn.close();
    }


    @ParameterizedTest
    @ValueSource(strings = {"Telecabina Nord", "Telecadira Central", "Telesquí Sud"})
    @Order(4)
    public void testGetPistesAmbRemuntadors(String remuntador) throws Exception {
        System.out.println("testGetPistesAmbRemuntadors");

        XQConnection conn = xQDataSource.getConnection("admin", "admin");
        PistaManager instance = new PistaManager(conn);

        List<Pista> expResult = pistes.stream()
                .filter((pista) -> {
                    return pista.getRemuntadors().stream().filter((s) -> s.equalsIgnoreCase(remuntador)).count() > 0;
                })
                .collect(Collectors.toList());

        List<Pista> result = instance.getPistesAmbRemuntador(remuntador);

        assertEquals(expResult, result);

        conn.close();

    }

    
    @Test
    @Order(5)
    public void testDelete() throws Exception {
        System.out.println("eliminar");

        XQConnection conn = xQDataSource.getConnection("admin", "admin");
        PistaManager instance = new PistaManager(conn);

        for (Pista pista : pistes) {
            instance.delete(pista.getId());
            assertThrows(ManagerException.class,
                    () -> {
                        instance.delete(pista.getId());
                    });
        }

        conn.close();
    }

   
}
