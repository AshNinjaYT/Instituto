# Respostes teoricopràctiques EAC1

## Exercici 1

### Per què és útil separar la lògica de correcció en una classe independent?

Resposta:
És útil perquè s'aplica el principi de responsabilitat única (SRP) i es permet la reutilització de codi. D'aquesta manera, la lògica per verificar si un lliurament és vàlid (`CorrectorLliurament`) està centralitzada en un sol lloc i pot ser utilitzada tant pel procés extern (`ValidadorLliurament`) com per la tasca multihil (`TascaCorreccio`). Així evitem duplicar codi i facilitem tant el manteniment com les proves del sistema.

### Per què `ValidadorLliurament` necessita un mètode `main(String[] args)`?

Resposta:
Perquè `ValidadorLliurament` s'ha d'executar com un programa autònom i independent, arrencat com a procés fill del sistema operatiu mitjançant `ProcessBuilder`. A Java, qualsevol classe que vulgui ser el punt d'inici d'un nou procés o aplicació necessita obligatòriament tenir el mètode d'entrada estàndard `public static void main(String[] args)` per rebre els arguments des de la línia de comandes.

### Per què és útil retornar codis de sortida diferents?

Resposta:
Perquè els codis de sortida (codis de retorn o *exit codes*) permeten comunicar al procés pare (`CorrectorProcessos`) el resultat de l'execució d'una manera directa, ràpida i sense haver de parsejar text. Per conveni als sistemes operatius, el codi `0` indica que tot ha anat correctament, el codi `2` indica un error de validació i el codi `10` que els arguments eren invàlids. Això fa que la comunicació entre processos sigui neta i molt fiable.

## Exercici 2

### Quin paper fa `CorrectorProcessos` com a procés pare?

Resposta:
`CorrectorProcessos` actua com a coordinador principal: s'encarrega de descobrir la llista de lliuraments a corregir, prepara les ordres d'execució de Java, llança els processos fills mitjançant `ProcessBuilder`, redirigeix la seva sortida estàndard i d'error cap a fitxers a la carpeta `resultats/`, controla que cap procés quedi penjat mitjançant un temps límit (timeout), i finalment recull els codis de sortida de tots els fills per generar el resum global.

### Quin paper fa `ValidadorLliurament` com a procés fill?

Resposta:
Fa el paper de treballador independent: s'executa en la seva pròpia instància de la Màquina Virtual de Java (JVM) i en el seu propi espai de memòria aïllat. Rep com a arguments l'identificador de l'alumne i la ruta, executa la lògica de correcció a través de `CorrectorLliurament`, escriu el resultat per la sortida estàndard i acaba notificant el resultat amb el codi de sortida corresponent (`0` o `2`).

### Què podria passar si el procés pare no esperés la finalització del procés fill?

Resposta:
Si el procés pare no espera (`waitFor`) la finalització dels processos fills, intentaria llegir els fitxers de resultats abans que els fills haguessin acabat d'escriure la seva feina, obtenint dades incompletes o fitxers buits. A més, a nivell de sistema operatiu es podrien generar "processos zombi" (processos que han acabat però el seu estat no ha estat alliberat pel pare) o processos orfes si el pare finalitza abans que els fills acabin.

### Quina diferència hi ha entre llançar tots els processos abans de recollir resultats i executar-los un per un de manera seqüencial?

Resposta:
Si s'executen de forma seqüencial (llançar el primer, esperar que acabi, llançar el segon, etc.), el temps total d'execució és la suma de tots els temps individuals. En canvi, si primer es llancen tots els processos fills i després s'espera la seva finalització, els processos s'executen en paral·lel aprofitant els múltiples nuclis del processador. Això redueix dràsticament el temps total que triga l'aplicació a corregir totes les entregues.

## Exercici 3

### Què aporta `ExecutorService` respecte de crear fils manualment?

Resposta:
Aporta una gestió molt més eficient i controlada dels recursos del sistema gràcies al patró *pool* de fils. En lloc de crear i destruir costosos objectes `Thread` contínuament, manté un conjunt de fils reutilitzables. A més, permet limitar la quantitat de fils concurrents per no saturar la CPU o la memòria, gestiona automàticament una cua de tasques pendents i ofereix eines de control com `Future`, cancel·lacions i temps d'espera màxims (timeouts).

### Per què fem servir `Callable<ResultatCorreccio>` i no només `Runnable`?

Resposta:
Perquè la interfície `Callable` conté el mètode `call()`, el qual permet retornar un valor (en aquest cas, un objecte `ResultatCorreccio`) i permet llançar excepcions verificades. En canvi, el mètode `run()` de `Runnable` és `void` (no pot retornar res de manera directa) i no permet llançar excepcions comprovades cap a qui ha llançat la tasca.

### Per què és important tancar correctament un `ExecutorService`?

Resposta:
Perquè els fils interns del *pool* d'un `ExecutorService` són fils no dimoni (non-daemon threads) que es queden vius esperant noves feines de manera indefinida. Si no es crida a `shutdown()` o `shutdownNow()`, aquests fils mantindran la Màquina Virtual de Java (JVM) en funcionament permanent, provocant que el programa mai acabi d'executar-se i deixant recursos bloquejats a la memòria.

### Quina diferència hi ha entre el temps acumulat de processament i el temps real d'execució?

Resposta:
El temps acumulat de processament és la suma matemàtica de tot el temps que ha trigat cada tasca per separat (com si s'haguessin executat una darrere de l'altra en un sol fil). El temps real d'execució és el temps de rellotge total transcorregut des de l'inici fins al final del programa. Com que diverses tasques s'executen simultàniament en diferents fils del pool, el temps real és molt menor que el temps acumulat.

## Exercici 4

### Què és una condició de cursa?

Resposta:
Una condició de cursa (*race condition*) és un error de concurrència que passa quan dos o més fils accedeixen i modifiquen simultàniament la mateixa variable o recurs compartit sense una sincronització adequada. El resultat final depèn de l'ordre d'execució imprevisible dels fils i dels canvis de context de la CPU, fent que es puguin produir inconsistències o pèrdues de dades.

### Per què pot aparèixer una condició de cursa en aquest exercici?

Resposta:
Pot aparèixer a `MarcadorCorreccions` perquè totes les tasques que corren en fils diferents comparteixen la mateixa instància del marcador i criden alhora al mètode `registrar()`. Aquest mètode incrementa comptadors com `totalProcessats++` o `totalCorrectes++`. Com que l'operació d'increment no és atòmica, si dos fils la fan alhora es produeixen solapaments i es perden actualitzacions (tal com es veu a la prova d'estrès sense sincronització).

### Per què `volatile` no seria suficient per protegir increments de comptadors?

Resposta:
Perquè `volatile` només garanteix la **visibilitat** de la variable entre fils (assegura que es llegeix sempre de memòria principal i no de la memòria cau del processador), però **no garanteix l'atomicitat**. L'operació d'incrementar (`compte++`) es compon en realitat de tres passos a nivell de màquina: 1) llegir el valor actual, 2) sumar 1, i 3) escriure el nou valor. Entre aquests passos, un altre fil pot intercalar-se i llegir un valor desfasat. Per això cal fer servir `synchronized` o tipus atòmics com `AtomicInteger`.

### Com podem saber que l'informe final és coherent?

Resposta:
Sabem que és coherent comprovant la consistència matemàtica de les dades finals: que la suma de les correccions correctes més els errors coincideixi exactament amb el nombre total de lliuraments processats (`totalProcessats == totalCorrectes + totalErrors`), i que el nombre total de processats coincideixi amb el total d'elements enviats inicialment, sense que s'hagi perdut cap actualització pel camí.

## Exercici 5 - Sistema concurrent propi (Justificació i reflexió)

### Quin element del vostre sistema representa el treball que cal processar?
Representa el treball la classe `Paquet` (`dam.psp.eac1.exercici5.Paquet`), que conté l'identificador únic del paquet, el seu pes en kilograms i la ciutat de destinació.

### Quina part del vostre sistema s'executa concurrentment?
L'execució de la classe `TascaProcessarPaquet` (que implementa `Callable<ResultatProcessament>`), on múltiples fils d'un `ExecutorService` de 4 fils processen de forma concurrent la validació i control de pes dels paquets.

### Quin estat compartit heu hagut de protegir?
S'ha protegit l'objecte `EstatCentreDistribucio`, el qual manté els comptadors globals de paquets processats, paquets acceptats, paquets rebutjats amb sobrepès, el pes acumulat i el registre de fils únics que hi han participat. Tots els seus mètodes d'accés i modificació estan declarats com a `synchronized` per evitar col·lisions.

### Com podeu demostrar que la sincronització funciona correctament?
Es demostra mitjançant el mètode `esCoherent(int totalEsperat)`, el qual verifica que el total d'elements generats (250 paquets) coincideix exactament amb el total de paquets processats, i que la suma d'acceptats (220) i rebutjats (30) és exactament 250, mostrant a la consola `COHERENT=true`. Si no hi hagués sincronització, en executar les tasques en paral·lel es perdrien registres i la comprovació donaria `false`.

