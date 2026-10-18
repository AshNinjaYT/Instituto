/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package eac3;

import eac3.gestors.GestorEstacio;
import eac3.gestors.GestorExcepcio;
import eac3.gestors.GestorItinerariRaquetes;
import eac3.gestors.GestorPista;
import eac3.gestors.GestorPistaAlpina;
import eac3.gestors.GestorPistaNordica;
import java.util.ArrayList;
import java.util.List;
import eac3.model.Estacio;
import eac3.model.ItinerariRaquetes;
import eac3.model.Pista;
import eac3.model.PistaAlpina;
import eac3.model.PistaNordica;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = {eac3.MainClass.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestGestor {

    @Autowired
    private GestorEstacio gestorEstacio;

    @Autowired
    private GestorPista gestorPista;

    @Autowired
    private GestorPistaAlpina gestorPistaAlpina;

    @Autowired
    private GestorPistaNordica gestorPistaNordica;

    @Autowired
    private GestorItinerariRaquetes gestorItinerariRaquetes;

    private static List<Estacio> estacions;
    private static List<Pista> pistes;

    private void inicialitzaDades() throws GestorExcepcio {
        netejaBD();

        createPistes();
        createEstacions();

        for (Estacio e : estacions) {
            gestorEstacio.afegeixEstacio(e);
        }

    }

    private void netejaBD() throws GestorExcepcio {
        gestorPista.esborraTotesPistes();
        gestorEstacio.esborraTotesEstacions();
    }

    private static void createPistes() {
        pistes = new ArrayList<>();

        // Pistes alpines (10)
        pistes.add(new PistaAlpina("PA-01", "Blava Central", 1450, true, 320, "Blava", 45, "Pols", 35, true));
        pistes.add(new PistaAlpina("PA-02", "Verda Escola", 620, true, 80, "Verda", 60, "Transformada", 12, true));
        pistes.add(new PistaAlpina("PA-03", "Vermella Nord", 2100, false, 520, "Vermella", 25, "Dura", 45, false));
        pistes.add(new PistaAlpina("PA-04", "Negra Canal", 980, false, 420, "Negra", 18, "Gelada", 65, false));
        pistes.add(new PistaAlpina("PA-05", "Blava Bosc", 1750, true, 360, "Blava", 55, "Pols", 38, true));
        pistes.add(new PistaAlpina("PA-06", "Vermella Cresta", 1950, true, 610, "Vermella", 35, "Dura", 50, false));
        pistes.add(new PistaAlpina("PA-07", "Verda Prats", 700, true, 90, "Verda", 50, "Humida", 10, true));
        pistes.add(new PistaAlpina("PA-08", "Blava Panoramica", 2300, true, 480, "Blava", 40, "Pols", 32, true));
        pistes.add(new PistaAlpina("PA-09", "Negra Paret", 1200, false, 550, "Negra", 15, "Gelada", 70, false));
        pistes.add(new PistaAlpina("PA-10", "Vermella Llarga", 2600, true, 640, "Vermella", 28, "Dura", 48, false));

        // Pistes nordiques (10)
        pistes.add(new PistaNordica("PN-01", "Nordic Loop", 5200, true, 130, 30, "Humida", false, "Classic", true));
        pistes.add(new PistaNordica("PN-02", "Planell", 3800, true, 90, 35, "Transformada", true, "Classic", true));
        pistes.add(new PistaNordica("PN-03", "Llacs", 6100, false, 160, 20, "Dura", false, "Skating", false));
        pistes.add(new PistaNordica("PN-04", "Bosc Nord", 4500, true, 110, 40, "Pols", true, "Skating", true));
        pistes.add(new PistaNordica("PN-05", "Riu", 3000, true, 70, 45, "Humida", false, "Classic", true));
        pistes.add(new PistaNordica("PN-06", "Altiplans", 7200, false, 200, 15, "Gelada", false, "Skating", false));
        pistes.add(new PistaNordica("PN-07", "Vall", 5600, true, 140, 25, "Pols", true, "Classic", true));
        pistes.add(new PistaNordica("PN-08", "Fageda", 4100, true, 100, 32, "Transformada", true, "Skating", true));
        pistes.add(new PistaNordica("PN-09", "Pistes Reials", 6800, false, 180, 18, "Dura", false, "Classic", false));
        pistes.add(new PistaNordica("PN-10", "Serra", 5000, true, 150, 28, "Pols", true, "Skating", true));
//
//        // Itineraris de raquetes (10)
        pistes.add(new ItinerariRaquetes("IR-01", "Ruta Bosc", 3200, false, 180, 30, "Dura", false, "Mitjana", true, true, 90));
        pistes.add(new ItinerariRaquetes("IR-02", "Mirador", 2400, true, 150, 35, "Humida", false, "Facil", false, true, 75));
        pistes.add(new ItinerariRaquetes("IR-03", "Cims", 5200, false, 420, 20, "Gelada", false, "Dificil", false, false, 180));
        pistes.add(new ItinerariRaquetes("IR-04", "Llac Gelat", 2800, true, 120, 45, "Transformada", true, "Facil", true, true, 80));
        pistes.add(new ItinerariRaquetes("IR-05", "Borda", 3600, true, 200, 25, "Pols", true, "Mitjana", false, true, 110));
        pistes.add(new ItinerariRaquetes("IR-06", "Bosquets", 4100, false, 260, 18, "Dura", false, "Mitjana", true, false, 130));
        pistes.add(new ItinerariRaquetes("IR-07", "Coll", 3000, true, 170, 30, "Humida", false, "Facil", true, true, 95));
        pistes.add(new ItinerariRaquetes("IR-08", "Riera", 2700, true, 140, 38, "Transformada", true, "Facil", false, true, 85));
        pistes.add(new ItinerariRaquetes("IR-09", "Serra Alta", 6000, false, 480, 12, "Gelada", false, "Dificil", false, false, 210));
        pistes.add(new ItinerariRaquetes("IR-10", "Pineda", 3500, true, 190, 28, "Pols", true, "Mitjana", true, true, 105));
    }

    private static void createEstacions() {

        estacions = new ArrayList<>();

        estacions.add(new Estacio("E-01", "Estacio Nord", "Cerdanya", 2535, "https://exemple.cat/e01", "A", 0.0));
        estacions.add(new Estacio("E-02", "Estacio Central", "Ripolles", 2450, "https://exemple.cat/e02", "A", 0.0));
        estacions.add(new Estacio("E-03", "Estacio Ponent", "Vall d'Aran", 2620, "https://exemple.cat/e03", "B", 0.0));
        estacions.add(new Estacio("E-04", "Estacio Alta", "Pallars Sobira", 2750, "https://exemple.cat/e04", "A+", 0.0));
        estacions.add(new Estacio("E-05", "Estacio Sud", "Bergueda", 2400, "https://exemple.cat/e05", "B+", 0.0));
        estacions.add(new Estacio("E-06", "Estacio Pirineu", "Alt Urgell", 2505, "https://exemple.cat/e06", "A", 0.0));

        assignaPistes(estacions.get(0), 0, 5);
        assignaPistes(estacions.get(1), 5, 10);
        assignaPistes(estacions.get(2), 10, 15);
        assignaPistes(estacions.get(3), 15, 20);
        assignaPistes(estacions.get(4), 20, 25);
        assignaPistes(estacions.get(5), 25, 30);

        for (Estacio e : estacions) {
            e.calcularPercentatgeObertura();
        }
    }

    private static void assignaPistes(Estacio estacio, int inici, int fiExclusiu) {
        for (Pista pista : pistes.subList(inici, fiExclusiu)) {
            pista.setEstacio(estacio);
            estacio.getPistes().add(pista);
        }
    }

    @BeforeEach
    public void setUp() throws GestorExcepcio {
        inicialitzaDades();
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAfegeixEstacio() {
        for (Estacio estacio : estacions) {
            assertThrows(GestorExcepcio.class, () -> gestorEstacio.afegeixEstacio(estacio));
        }
    }

    @Test
    public void testObteTotesEstacions() throws GestorExcepcio {
        List<Estacio> totes = gestorEstacio.obteTotesEstacions();

        assertEquals(estacions.size(), totes.size());
        assertTrue(totes.containsAll(estacions), "Les estacions obtingudes no coincideixen amb les esperades");
    }

    @ParameterizedTest

    @ValueSource(strings = {"E-04", "E-05", "E-06"})
    public void testEsborraEstacio(String idEstacio) throws GestorExcepcio {
        Estacio estacio = estacions.stream().filter((e) -> idEstacio.equals(e.getId())).findFirst().orElse(null);

        gestorEstacio.esborraEstacio(idEstacio);
        estacions.remove(estacio);

        List<Estacio> result = gestorEstacio.obteTotesEstacions();
        List<Estacio> expResult = estacions;

        assertEquals(expResult.size(), result.size());
        assertTrue(expResult.containsAll(result));
        assertThrows(GestorExcepcio.class, () -> {
            gestorEstacio.esborraEstacio(estacio.getId());
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5})
    public void testAfegeixPista(int indexEstacio) throws GestorExcepcio {
        Estacio estacio = estacions.get(indexEstacio);
        PistaAlpina nova = new PistaAlpina("PA-11", "Torrent Blau", 1300, true, 260, "Blava", 42, "Pols", 33, true);
        nova.setEstacio(estacio);
        estacio.getPistes().add(nova);
        estacio.calcularPercentatgeObertura();

        gestorPista.afegeixPista(estacio.getId(), nova);

        List<Estacio> estacionsEsperades = estacions;
        List<Estacio> estacionsObtingudes = gestorEstacio.obteTotesEstacions();

        assertEquals(estacionsEsperades.size(), estacionsObtingudes.size());
        assertTrue(estacionsObtingudes.containsAll(estacionsEsperades), "Les pistes no coincideixen amb les esperades despres de l'alta");

        assertThrows(GestorExcepcio.class, () -> gestorPista.afegeixPista("E-XX", nova));
        assertThrows(GestorExcepcio.class, () -> gestorPista.afegeixPista(estacio.getId(), nova));
        assertThrows(GestorExcepcio.class, () -> gestorPista.afegeixPista(estacio.getId(), nova));
    }

    @ParameterizedTest
    @ValueSource(ints = {20, 18, 10, 9, 5})

    public void testEsborraPista(int index) throws GestorExcepcio {

        Pista pista = pistes.get(index);
        pistes.remove(pista);
        pista.getEstacio().getPistes().remove(pista);

        gestorPista.esborraPista(pista.getId());

        List<Estacio> result = gestorEstacio.obteTotesEstacions();
        List<Estacio> expResult = estacions;

        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.containsAll(result), true);
        assertThrows(GestorExcepcio.class, () -> {
            gestorPista.esborraPista(pista.getId());
        });

    }

    @ParameterizedTest
    @ValueSource(ints = {14, 7, 3})

    public void testActualitzaGruixNeuPista(int index) throws GestorExcepcio {
        final int INCREMENT = 48888;

        Pista p = pistes.get(index);
        p.setGruixNeu(p.getGruixNeu() + INCREMENT);

        gestorPista.actualitzaGruixNeuPista(p.getId(), INCREMENT);

        List<Estacio> result = gestorEstacio.obteTotesEstacions();
        List<Estacio> expResult = estacions;

        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.containsAll(result), true);
        assertThrows(GestorExcepcio.class, () -> {
            gestorPista.actualitzaGruixNeuPista("CODI_INEXISTENT", INCREMENT);
        });

    }

    @ParameterizedTest
    @CsvSource({
        "PA-03,true,80.0",
        "PA-01,false,40.0",
        "PA-09,true,100.0",
        "PN-06,true,80.0"
    })

    public void testActualitzaObertaPista(String idPista, boolean oberta, double percentatgeEsperat) throws GestorExcepcio {
        gestorPista.actualitzaObertaPista(idPista, oberta);

        Pista pistaActualitzada = gestorPista.obtePista(idPista);
        Estacio estacio = pistaActualitzada.getEstacio();

        assertEquals(oberta, pistaActualitzada.isOberta());
        assertEquals(percentatgeEsperat, estacio.getPercentatgePistesObertes());
        assertThrows(GestorExcepcio.class, () -> gestorPista.actualitzaObertaPista("PA-XX", true));
    }

    @ParameterizedTest
    @CsvSource({
        "E-01,Blava,PA-01|PA-05",
        "E-02,Vermella,PA-06|PA-10",
        "E-02,Negra,PA-09",
        "E-01,Lila,''"
    })
    public void testObtePistesAlpinesEstacioPerColor(String idEstacio, String color, String idsEsperatsCsv)
            throws GestorExcepcio {

        List<PistaAlpina> obtingudes = gestorPistaAlpina.obtePistesAlpinesEstacioPerColor(idEstacio, color);

        List<PistaAlpina> esperades = new ArrayList<>();
        if (idsEsperatsCsv != null && !idsEsperatsCsv.isBlank()) {
            for (String id : idsEsperatsCsv.split("\\|")) {
                Pista pista = pistes.stream()
                        .filter(p -> id.equals(p.getId()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("ID de pista no trobat a les dades de test: " + id));
                esperades.add((PistaAlpina) pista);
            }
        }

        assertEquals(esperades.size(), obtingudes.size());
        assertTrue(obtingudes.containsAll(esperades), "Les pistes alpines obtingudes no coincideixen amb les esperades");
        assertThrows(GestorExcepcio.class,
                () -> gestorPistaAlpina.obtePistesAlpinesEstacioPerColor("E-XX", "Blava"));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testObtePistesNordiquesPerTrepitjada(boolean trepitjada) throws GestorExcepcio {

        List<PistaNordica> pistesNordiques = gestorPistaNordica.obtePistesNordiquesPerTrepitjada(trepitjada);
        long expected = pistes.stream()
                .filter(PistaNordica.class::isInstance)
                .map(PistaNordica.class::cast)
                .filter(p -> p.isTrepitjada() == trepitjada)
                .count();

        assertEquals(expected, pistesNordiques.size());
        assertTrue(pistesNordiques.stream().allMatch(p -> p.isTrepitjada() == trepitjada));
    }

    @Test
    public void testObteItinerarisRaquetesFinsTemps() throws GestorExcepcio {
        List<ItinerariRaquetes> fins80 = gestorItinerariRaquetes.obteItinerarisRaquetesFinsTemps(80);
        assertEquals(2, fins80.size());
        assertTrue(fins80.stream().allMatch(i -> i.getTempsEstimat() <= 80));
    }

}
