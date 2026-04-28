Generalitat de Catalunya
Departament d’Educació
Institut Obert de Catalunya
CFGS Desenvolupament d'Aplicacions Multiplataforma (DAM)
Mòdul 08 – Programació multimèdia i dispositius mòbils
LibGDX – passos inicials (actualització)
(imatge adaptada del document de l'aula fp_dam_m08_u4_pdfindex.pdf).
I^73
v
01
1
Índex de continguts
Introducció............................................................................................................................
Creació del projecte.............................................................................................................
Codi generat – realització dels projectes base.....................................................................
Realització dels projectes base............................................................................................
Problema detectat (i solució)..............................................................................................
Introducció............................................................................................................................
La finalitat d'aquest breu^1 document és actualitzar la informació que hi ha al document de l'aula,
fp_dam_m08_u4_pdfindex.pdf. Els principals canvis que s'aborden són la creació amb l'eina de configuració
de projectes que usin libGDX i el treball amb Kotlin.

Creació del projecte.............................................................................................................
Al document de l'aula explica com utilitzar l'eina gdx-setup. Aquesta eina continua existint i pràcticament no
ha variat. Tanmateix, han sorgit noves necessitats i, per cobrir-les, s'ha creat una nova eina: gdx-liftoff. Pot
descarregar-se des de la pàgina Creating a Project – libGDX, https://libgdx.com/wiki/start/project-generation.

Un cop descarregada, veureu que conté tres versions binàries: un executable per a Linux, un altre per a
Windows i un fitxer .jar, per executar-lo directament en qualsevol JVM. En aquest cas, si ens situem a la
mateixa carpeta on és el fitxer gdx-litoff, s'executa amb l'ordre:

java -jar ./gdx-liftoff-XXXXXXX.jar
Cal aclarir que gdx-liftoff-XXXXXXX.jar és el nom del fitxer .jar i XXXXXXX es correspon amb la versió
del fitxer i pot variar depenent de la versió que us descarregueu. Al moment de fer aquest document,
l'última versió era 1.13.0.1 i, per tant, l'ordre seria: java -jar ./gdx-liftoff-1.13.0.1.jar.

La primera pantalla que surt és

Cal omplir el nom del projecte, el paquet on anirà l'aplicació (al cas de l'exemple és cat.xtec.ioc) i la
classe principal, que és SpaceRacer.

A continuació, cal fer clic a «PROJECT OPTIONS». Ens sortirà aquesta pantalla:

Clicant a cada requadre
podem triar les plataformes
per les quals volem generar el
projecte, el(s) llenguatges en
els quals escriurem el pro-
grama i les extensions que
volem utilitzar

1 10 pàgines semblen moltes, però gran part d'elles estan ocupades per captures de pantalla.

Plataformes

Al nostre cas, volem fer
una aplicació només per a
Android.

Així, només selecciona-
rem l'opció «ANDROID».
L'opció «CORE» ve selec-
cionada per defecte i no
pot desmarcar-se (és ne-
cessària).

A continuació, ja podeu fer
clic al botó «OK».

Llenguatges

Aquí podem triar el(s) llenguatge(s) amb els quals
treballarem. Tots ells funcionen amb una JVM. Si voleu
fer l'aplicació amb Java, en aquesta opció no cal ni tan
sols entrar-hi. Si voleu fer l'aplicació amb Kotlin,
seleccioneu només aquest llenguatge.

En acabar, feu clic a «OK».

Extensions

Aquí podem triar complements que voldrem utilitzar. Així, inclourà al projecte totes les dependències que
necessàries per a poder usar els complements que necessitem. Per fer aquesta aplicació no cal triar-ne cap
(per tant, no cal ni entrar-hi).

Per últim, hem de triar també la plantilla que volem per al nostre projecte. És la que utilitzarà per generar el codi d'inici.
S'hi accedeix fent clic al quadre de llista de selecció o al botó «CHOOSE»:

.
Pantalla Extensions

La pantalla «TEMPLATES» (plantilles) té dues parts: la primera amb les plantilles bàsiques i una segona
part amb plantilles de tercers. Les plantilles bàsiques són les següents:

Per l'aplicació de Java s'ha utilitzat la plantilla ApplicationAdapter i per a l'aplicació de Kotlin s'ha utilitzat la
plantilla Kotlin. A tots dos casos, genera una classe «llençadora» que crida a la classe que conté el joc,
GameMain i la pròpia classe GameMain, a la carpeta core. La crea en una altra carpeta perquè es pugui
utilitzar des de launchers d'altres plataformes (és possible demanar que en creï més d'un, cadascun per a
una plataforma). GameMain , senzillament, és una subclasse d' ApplicationAdapter que està buida. En el
següent apartat analitzarem el codi i veurem com s'han generat els projectes base.

Amb aquestes plantilles no cal utilitzar per res ni Jetpack Compose ni els layouts XML anteriors a
Jetpack Compose (hi ha un exemple de layout al document de l'aula).

Un cop triada la plantilla, es pot fer clic al botó «NEXT», que és a continuació del botó per triar la plantilla.

Surt una nova pantalla, on podem seleccionar complements de tercers.

No en triarem cap complement i farem clic al botó «NEXT». Surt l'última pantalla abans de generar el
projecte:

En aquesta pantalla podem seleccionar les versions de la biblioteca, la màquina virtual de Java i de la nostra
aplicació. També podem demanar que incloguin alguns assets (és l'opció que surt per defecte) i que s'hi
afegeixi el fitxer «llegeix-me». És molt important seleccionar el camí del projecte (la carpeta on volem que
es generi el projecte) i on són els SDK d'Android.

Pot saber-se on són els SDK d'Andorid des del mateix Android Studio. Cal seguir els següents passos:

Obrir un projecte anterior.
Seleccionar l'opció Tools → SDK Manager
A la pantalla que surt, el camí dels SDK d'Android és al quadre «Android SDK Location:

Un cop introduïdes les dues dades, si fem clic al botó «GENERATE», es generarà el projecte.

Codi generat – realització dels projectes base.....................................................................
Codi generat

Com hem dit, el codi que es genera és una classe que llença la classe que conté el joc. Mirem el codi:

En Java:
public class AndroidLauncher extends AndroidApplication {
@Override
protected void onCreate ( Bundle savedInstanceState ) {
super. onCreate ( savedInstanceState );
AndroidApplicationConfiguration configuration =
new AndroidApplicationConfiguration ();
configuration. useImmersiveMode = true; // Recommended, but not required.
initialize (new GameMain (), configuration );
}
}

En Kotlin:
class AndroidLauncher : AndroidApplication () {
override fun onCreate ( savedInstanceState : Bundle ?) {
super. onCreate ( savedInstanceState )
initialize ( GameMain (), AndroidApplicationConfiguration (). apply {
// Configure your application here.
useImmersiveMode = true // Recommended, but not required.
})
}
}

Com veiem, són molt semblants. Són a la carpeta android/src, al paquet corresponent.

GameMain és la classe on realment comença el joc. En crea una per començar a programar:

En Java:
/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
platforms. */
public class GameMain extends ApplicationAdapter {
}

En Kotlin:

/ [com.badlogic.gdx.ApplicationListener] implementation shared by all
platforms.** */
class GameMain : ApplicationAdapter ()

Són a la carpeta common/src perquè el codi pot usar-se des de qualsevol plataforma.

Realització dels projectes base............................................................................................
Els projectes que trobareu a l'aula són una actualització del que s'explica al document de l'aula
fp_dam_m08_u4_pdfindex.pdf. S'han seguit els següents passos per elaborar-los:

Generar els projectes amb gdx-liftoff. S'ha explicat a l'apartat anterior.
Modificar les classes AndroidLauncher per afegir-hi la configuració desitjada i fer que invoqui a la classe
SpaceRace en lloc de invocar la classe GameMain. Aquesta configuració s'ha agafat de la versió anterior de
l'aplicació que hi havia a l'aula i que està explicada al document fp_dam_m08_u4_pdfindex.pdf.
Les classes han quedat així:

En Java:

public class AndroidLauncher extends AndroidApplication {
@Override
protected void onCreate ( Bundle savedInstanceState ) {
super. onCreate ( savedInstanceState );
AndroidApplicationConfiguration config =
new AndroidApplicationConfiguration ();
// Deshabilitem els sensors que no anem a fer servir
config. useAccelerometer = false;
config. useCompass = false;
// Impedim que s'apagui la pantalla
config. useWakelock = true;
// Posem el mode immersive per ocultar botons software
config. useImmersiveMode = true;
// Apliquem la configuració
initialize (new SpaceRace (), config );
}
}

En Kotlin:

class AndroidLauncher : AndroidApplication () {
override fun onCreate ( savedInstanceState : Bundle ?) {
super. onCreate ( savedInstanceState )
initialize ( SpaceRace (), AndroidApplicationConfiguration (). apply {
//Application configuration
useAccelerometer = false;
useCompass = false;
// Impedim que s'apagui la pantalla
useWakelock = true;
// Posem el mode immersive per ocultar botons software
useImmersiveMode = true
})
}
}

Substiuir la classe GameMain per SpaceRacer i copiar la resta de l'aplicació, que ja estava feta (i
s'explica al document fp_dam_m08_u4_pdfindex.pdf).
Al cas de Kotlin, convertir automàticament, amb el generador d'Android Studio, el codi Java a Kotlin. Un
cop acabada la conversió ha calgut corregir manualment uns pocs errors menors que apareixien al codi.
El document fp_dam_m08_u4_pdfindex.pdf explica només la versió en Java, però, com les classes són les
mateixes, amb la versió Kotlin al costat de la mateixa aplicació podeu veure com s'implementa en Kotlin el
que està explicat en aquest document.

Per últim, important: els assets cal copiar-los a la carpeta que porta el mateix nom perquè en executar el
codi siguin trobats.

Problema detectat (i solució)..............................................................................................
El procés s'ha fet amb la versió 2023.3.1 d'Android Studio. Un problema detectat al moment de fer el «build»
del projecte ha estat que aquesta versió de l'entorn no suportava la versió del plugin Gradle del projecte
generat. Això es pot resoldre canviant la versió d'aquest plugin a File → Project Structure. Cal fer clic al
requadre que hi ha al costat del quadre combinat «Android Gladle Plugin Version i modificant-lo a la finestra
que surt: