## FP_DA2_0A01 – Programació Avançada (Angular)

##### Unitat 4 – Navegació i optimització (RA4)

# EAC

#### (Curs 2025 – 26 / 2n semestre)

## Presentació i resultats d'aprenentatge

Aquest exercici d’avaluació continuada (EAC) es correspon amb els continguts treballats a la
unitat 4 **–** Navegació i optimització (RA4).

Els resultats d'aprenentatge que es plantegen són:

RA4 - Incorpora navegació entre vistes, control d'accés bàsic i prepara l'aplicació per a la seva
publicació

## Criteris d’avaluació

La puntuació màxima assignada a cada activitat s’indica a l’enunciat.

Els criteris que es tindran en compte per avaluar el treball de l’alumnat són els següents:

Els criteris d’avaluació poden ser els que figuren al currículum o es poden concretar. Els criteris
d’avaluació indiquen les accions i els continguts de l’activitat o condicions que permeten valorar
si s’ha aconseguit el resultat d’aprenentatge establert.

Cada RA es concreta amb diversos criteris d’avaluació (CA), que es mostren a continuació:

```
● RA4 : Incorpora navegació entre vistes, control d'accés bàsic i prepara l'aplicació per a la
seva publicació.
○ Configuració del sistema de rutes per crear aplicacions de múltiples pàgines
○ Gestió de la navegació entre vistes amb URLs significatives i paràmetres de ruta
○ Implementació d'un flux d'inici de sessió i control d'accés bàsic amb guards
○ Aplicació de mecanismes per preservar l'estat d'usuari i protegir seccions
○ Optimització dels recursos mitjançant lazy loading, OnPush i virtualització
○ Generació d'un paquet llest per a publicació i evidència del procés
```

### Forma i data de lliurament

Forma de lliurament

S’haurà de lliurar aquest document de text (PDF o ODT) degudament omplert amb les dades
del projecte i l’enllaç al repositori GitHub.

Important:

```
● El document serveix únicament com a fitxa identificativa del projecte.
● La correcció de l’EAC es farà exclusivament sobre el repositori GitHub , revisant el
codi, l’estructura i l’historial de commits. S’ha de tenir en compte el que es demana en
l’enunciat com les captures i altres.
● El contingut del document no serà avaluat tècnicament.
```

Data límit: 12/05/

Nota: Assegureu-vos que el repositori és Públic perquè el professor pugui accedir-hi per
corregir-lo.

Nom del fitxer a entregar: La sintaxi per al nom del fitxer segueix el format:
DA2_OA01_EACX_Cognom_Inicial
Per exemple, l'estudiant Joan García Santos posaria: DA2_OA01_EACX_Garcia_S

L’arxiu amb la solució s’ha d’enviar a la bústia de **“XXXX Lliurament EAC4”** de l'aula, dins del
termini establert. Tingueu en compte que el sistema no permetrà fer lliuraments després de la
data i hora indicades.

El termini de lliurament de l’EAC4 finalitzarà a les 23:59 h del dia 12/05/2026. La proposta de
solució es publicarà el dia 13/05/2026. Les qualificacions es mostraran el dia 20/05/2026.

### Enunciat

##### Atenció!

```
Els Exercicis d'Avaluació Continuada (EAC) estan dissenyats per ajudar-vos a
preparar les Proves d'Avaluació Finals (PAF). Per aquest motiu, us
recomanem que realitzeu aquest EAC després d'haver treballat els materials
del curs i completat les activitats d’autoavaluació corresponents.
```

```
És important que feu l'EAC de manera autònoma, sense utilitzar ajudes externes,
incloent-hi eines d'intel·ligència artificial, ja que això us permetrà avaluar
correctament el vostre aprenentatge i preparar-vos millor per la PAF.
Us recordem que les PAF són exàmens en paper, en els quals no podreu
consultar recursos addicionals. Per tant, és fonamental que practiqueu en
condicions similars a les de l'examen final.
```

En aquest mòdul, cada alumne desenvoluparà la seva pròpia aplicació Angular.

```
● Temàtica Lliure: Podeu triar la temàtica que vulgueu per a la vostra aplicació (ex: un
gestor de tasques, un catàleg de pel·lícules, una agenda de contactes, una app de
receptes, etc.). L'única condició és que sigui un projecte viable per anar ampliant durant el
curs.
● Autoria i Originalitat: És imprescindible que l'aplicació sigui única. No es permet clonar
repositoris d'altres companys ni fer servir el mateix nom o contingut que un altre alumne.
● Requisits Tècnics Comuns: Tot i que la temàtica és lliure, tots els projectes han de
complir exactament els mateixos requisits tècnics (versions, estructura de carpetes,
configuració) descrits en aquest enunciat.
```

#### Material de Suport

Els materials del curs es poden consultar com a ampliació o reforç, però no són necessaris
per completar aquesta activitat, ja que tots els requisits es descriuen en aquest enunciat.

No obstant això, podeu consultar els materials del curs com a referència i suport addicional:

```
● https://itrascastro.github.io/ioc/da2_oa01/
● En concret per aquesta EAC, al bloc 4 de la unitat 4:
https://itrascastro.github.io/ioc/da2_oa01/unitat-4/bloc-4/
```

### Espai de Resposta de l’alumne

Instruccions: Abans de lliurar aquest document a la tasca, ompliu obligatòriament els següents
camps amb la informació del vostre projecte.

```
Nom complet de l'alumne:
```

```
Nom de l'aplicació (carpeta del projecte):
```

```
Breu descripció funcional (Què farà la vostra app?):
```

```
URL del repositori GitHub (pot ser públic):
```

```
URL de la branca de treball corresponent de GitHub (ra4-navegacio):
```

## A continuació es detallen els exercicis que es demanen en

## aquest EAC.

La puntuació màxima és de 10 punts. Es valorarà la precisió tècnica, l'organització del codi i la
correcta gestió del control de versions segons els estàndards descrits a continuació.

## Exercici 1: Routing — Rutes, paràmetres i navegació

## (2,5 punts)

#### Enunciat: Implementeu el sistema de rutes complet de l'aplicació. L'aplicació ha de disposar

d'un mínim de 4 vistes diferenciades amb navegació entre elles sense recàrrega de pàgina.

Rutes mínimes obligatòries:

```
Ruta Component Descripció
```

```
/ Redirecció^ Redirigeix a la ruta principal^
```

```
/cataleg (o equivalent) CatalegPage Pàgina principal amb llistat d'elements
```

```
/cerca Cerca Vista de cerca^
```

```
/detall/:id Detall Vista de detall d'un element concret
```

```
/preferits Preferits Secció de preferits (protegida, vegeu Ex. 2)^
```

```
/login Login Formulari d'autenticació simulada
```

```
** (wildcard) — Redirigeix a la ruta principal per a URLs no reconegudes
```

Fitxers i configuració obligatòria:

- Definiu les rutes a src/app/app.routes.ts exportant una constant routes de tipus Routes.
- Configureu provideRouter(routes) a src/app/app.config.ts.
- Afegiu <router-outlet></router-outlet> al template de AppComponent.
- Importeu RouterModule (o RouterOutlet, RouterLink, RouterLinkActive individualment) a

l'AppComponent.

- Creeu un component de navegació amb:
- Enllaços amb routerLink a les vistes principals.
- Indicador visual de ruta activa amb routerLinkActive.

Vista de detall amb paràmetre:

- La ruta /detall/:id ha de llegir el paràmetre id de la URL usant ActivatedRoute:

```
import { ActivatedRoute } from '@angular/router';
```

###### // ...

```
const id = this.route.snapshot.paramMap.get('id');
```

- Mostreu les dades de l'element corresponent (obtingudes del servei existent o de dades de prova

basades en l'id).

Redirecció i ruta wildcard:

- Configureu { path: '', redirectTo: 'cataleg', pathMatch: 'full' } per a la ruta buida.
- Configureu { path: '\*\*', redirectTo: 'cataleg' } com a última ruta per gestionar les URLs no

reconegudes.

Documentació obligatòria a docs/navegacio.md:

- Taula amb el mapa complet de rutes (path, component, accés públic o privat).
- Breu explicació de com s'ha configurat provideRouter, RouterOutlet i RouterLink.

Captures requerides:

- Captura 1: Navegació principal visible al navegador amb la ruta activa destacada (per exemple,

a /cerca).

- Captura 2: Vista de detall carregada a la URL /detall/[id], amb el valor del paràmetre visible a la

barra d'adreces.

- Captura 3: Comportament correcte en accedir a una URL inexistent (redirecció a la ruta

principal visible a la barra d'adreces).

```
Rúbrica A (2.50p) B (2.00p) C (1.25p) D (0.5p) E (0.00p)
```

```
Routing:
Rutes,
params i
navegació
```

```
Sistema de
rutes
complet: ≥
vistes, ruta
/detall/:id
amb
ActivatedRo
ute
funcional,
redirectTo i
ruta
wildcard
configurade
s,
routerLinkA
ctive visible.
docs/naveg
acio.md
complert.
Les 3
captures
adjuntades.
```

```
Rutes
principals
funcionals i
detall amb
paràmetre,
però falta la
ruta
wildcard, el
redirectTo o
la
documentac
ió és
incompleta.
2 captures
adjuntades.
```

```
Rutes
bàsiques
configurade
s però el
component
de detall no
llegeix el
paràmetre :i
d o la
navegació
no usa
routerLinkA
ctive.
Documenta
ció molt
escassa.
```

```
Sistema de
rutes
parcialment
implementat
```

. Menys de
4 vistes o
router-outlet
absent.

```
No realitzat.
```

## Exercici 2: Auth/Guards — Flux d'accés i protecció de

## rutes (2 punts)

#### Enunciat: Implementeu un sistema d'autenticació simulada i protegiu la secció de preferits

#### amb una guàrdia de ruta.

Servei d'autenticació (src/app/serveis/auth.service.ts):

El servei ha de gestionar l'estat de l'usuari amb un BehaviorSubject:

import { Injectable } from '@angular/core';

import { BehaviorSubject, Observable } from 'rxjs';

export interface Usuari {

id: number;

nom: string;

email: string;

###### }

@Injectable({ providedIn: 'root' })

export class AuthService {

private usuariActual$ = new BehaviorSubject<Usuari | null>(null);

estaAutenticat(): boolean {

return this.usuariActual$.value !== null;

###### }

obtenirUsuari(): Observable<Usuari | null> {

return this.usuariActual$.asObservable();

###### }

login(email: string, contrasenya: string): boolean {

// Credencials fixes de prova

if (email === 'admin@test.com' && contrasenya === '1234') {

this.usuariActual$.next({ id: 1, nom: 'Admin', email });

return true;

###### }

return false;

###### }

logout(): void {

this.usuariActual$.next(null);

###### }

###### }

Podeu adaptar les credencials, el nom del camp i l'estructura de Usuari a la temàtica del vostre projecte,

però heu de mantenir l'estructura reactiva amb BehaviorSubject.

Component de Login (src/app/pages/login/ o equivalent):

- Formulari amb els camps necessaris per recollir email i contrasenya.
- En enviar el formulari, crideu authService.login().
- Si retorna true, navegueu programàticament a /preferits usant Router.navigate(['/preferits']).
- Si retorna false, mostreu un missatge d'error visible a la plantilla.

Guàrdia de ruta (src/app/guards/auth.guard.ts):

Creeu una guàrdia funcional de tipus CanActivateFn que comprovi estaAutenticat():

import { CanActivateFn, Router } from '@angular/router';

import { inject } from '@angular/core';

import { AuthService } from '../serveis/auth.service';

export const authGuard: CanActivateFn = (route, state) => {

const authService = inject(AuthService);

const router = inject(Router);

if (authService.estaAutenticat()) {

return true;

###### }

return router.createUrlTree(['/login'], {

queryParams: { returnUrl: state.url }

###### });

###### };

Apliceu la guàrdia a la ruta /preferits a app.routes.ts:

{ path: 'preferits', component: PreferitsComponent, canActivate: [authGuard] }

Indicador d'autenticació a la navegació:

Al component de navegació, subscriviu-vos a authService.obtenirUsuari() (o useu el async pipe) per

mostrar condicionalment:

- Quan no autenticat: un enllaç «Iniciar sessió» cap a /login.
- Quan autenticat: el nom de l'usuari i un botó «Tancar sessió» que cridi authService.logout().

Captures requerides:

- Captura 1: Intent d'accés directe a /preferits sense autenticació — redirecció automàtica a /login

visible a la barra d'adreces.

- Captura 2: Formulari de login amb missatge d'error visible (credencials incorrectes introduïdes).
- Captura 3: Accés a /preferits correcte després d'autenticar-se — nom d'usuari visible a la

navegació.

```
Rúbrica A (2.00p) B (1.50p) C (1.00p) D (0.50p) E (0.00p)
```

```
Auth/Guar
ds: Flux
d'accés i
protecció
```

```
AuthService
amb
BehaviorSu
bject,
estaAutenti
```

```
AuthService
funcional
però amb
algun camp
o tipatge
```

```
AuthService
parcialment
implementat
o amb
errors de
```

```
AuthService
absent o
sense
BehaviorSu
bject.
```

```
No realitzat.
```

```
cat(), login()
i logout()
funcionals.
Guàrdia
CanActivate
Fn amb
inject() i
createUrlTr
ee que
redirigeix a
/login. Login
navega a
/preferits en
èxit i mostra
error en
fallada.
Indicador
d'autenticac
ió a la
navegació.
Les 3
captures
adjuntades.
```

```
imprecís.
Guàrdia
operativa
però no
redirigeix
correctame
nt, o
l'indicador
d'autenticac
ió a la
navegació
no es
mostra. 2
captures
adjuntades.
```

```
tipatge. La
guàrdia no
s'aplica a la
ruta, o el
login no
navega
programàtic
ament amb
Router.navi
gate. 1
captura
adjuntada.
```

```
Guàrdia
absent o
login sense
gestió
d'errors.
Cap captura
adjuntada.
```

## Exercici 3: Lazy Loading — Modularització funcional

## (1,5 punts)

Enunciat: Implementeu el lazy loading per carregar la secció de preferits de forma diferida,
reduint el bundle inicial de l'aplicació.

Configuració del lazy loading a app.routes.ts:

Substituïu la importació directa del component de preferits per una importació dinàmica. Trieu l'opció

que s'adapti millor a la vostra estructura:

// Opció A _—_ loadChildren (si teniu sub-rutes pròpies a preferits):

###### {

path: 'preferits',

loadChildren: () =>

import('./pages/preferits/preferits.routes').then(m => m.PREFERITS_ROUTES),

canActivate: [authGuard]

###### }

// Opció B _—_ loadComponent (component standalone sense sub-rutes):

###### {

path: 'preferits',

loadComponent: () =>

import('./pages/preferits/preferits.component').then(m => m.PreferitsComponent),

canActivate: [authGuard]

###### }

Important: Elimineu la importació estàtica del component (o mòdul) de preferits de la capçalera del

fitxer de rutes. Si la deixeu, el mòdul es carregarà de forma immediata i el lazy loading no tindrà efecte.

La guàrdia authGuard s'ha de mantenir activa a la ruta amb lazy loading (tal com es veu als exemples

anteriors).

Verificació del lazy loading:

_Obriu les DevTools (F12) → pestanya_ Network _→_ filtreu per JS. En accedir a /preferits per primera

vegada (amb sessió iniciada), heu de veure que es descarrega un fitxer JavaScript addicional (chunk) que

no estava present en la càrrega inicial.

Captures requerides:

- Captura 1: Pestanya Network de les DevTools mostrant la descàrrega diferida d'un fitxer .js

addicional (chunk) en accedir a /preferits per primera vegada.

```
Rúbrica A (1.5p) B (1.125p) C (0.75p) D (0.375p) E (0.00p)
```

```
Lazy
Loading:
Modularitz
ació
funcional
```

```
Lazy
loading
implementat
amb
loadChildre
n o
loadCompo
nent.
```

```
Lazy
loading
configurat
però la
guàrdia s'ha
perdut en la
refactoritzac
ió, o la
```

```
Lazy
loading
configurat
però la
guàrdia s'ha
perdut en la
refactoritzac
ió, i la
```

```
Sintaxi de
lazy loading
present al
codi però
no funciona
en execució
(chunk no
es carrega
```

```
Lazy
loading no
implementat
.
```

```
Importació
estàtica
eliminada.
La guàrdia
es manté
activa.
Captura de
Network
amb el
chunk
visible
adjuntada.
```

```
captura de
Network no
mostra
clarament la
descàrrega
del chunk.
```

```
captura de
Network no
mostra
clarament la
descàrrega
del chunk.
```

```
o error de
ruta).
```

## Exercici 4: Optimització — OnPush i virtualització

## amb Angular CDK (2 punts)

Enunciat: Optimitzeu el rendiment de l'aplicació aplicant l'estratègia OnPush als components
presentacionals i la virtualització de llistes grans amb el CDK d'Angular.

ChangeDetectionStrategy.OnPush:

Afegiu changeDetection: ChangeDetectionStrategy.OnPush a un mínim de 2 components

presentacionals (per exemple, la targeta d'element i el component de detall):

import { Component, ChangeDetectionStrategy, Input } from '@angular/core';

@Component({

selector: 'app-element-card',

changeDetection: ChangeDetectionStrategy.OnPush,

template: `...`

###### })

export class ElementCardComponent {

@Input() element!: ElementCataleg;

###### }

Recordeu que amb OnPush, Angular només comprova el component quan les seves entrades (@Input())

canvien de referència. Per tant, no muteu els objectes directament; creeu-ne de nous per activar la

detecció de canvis.

Virtualització de llista amb Angular CDK (ScrollingModule):

Instal·leu el paquet si no el teniu:

npm install @angular/cdk

Importeu ScrollingModule al vostre component (o mòdul):

import { ScrollingModule } from '@angular/cdk/scrolling';

A la vista del llistat principal (amb un mínim de 50 elements), substituïu el \*ngFor habitual per

CdkVirtualScrollViewport amb \*cdkVirtualFor:

<cdk-virtual-scroll-viewport [itemSize]="altadaElement" style="height: 500px;">

<div *cdkVirtualFor="let element of elements">

<app-element-card [element]="element"></app-element-card>

</div>

</cdk-virtual-scroll-viewport>

- [itemSize] ha de ser l'alçada aproximada en píxels de cada element de la llista.
- La llista ha de contenir un mínim de 50 elements.

Documentació obligatòria a docs/optimitzacio.md:

- Quins components tenen OnPush i per quin motiu s'han triat.
- Configuració de la virtualització: valor d'itemSize usat i nombre d'elements de la llista.

Captures requerides:

- Captura 1: Llistat virtualitzat visible al navegador amb scroll actiu i un nombre elevat

d'elements.

```
Rúbrica A (2.00p) B (1.50p) C (1.00p) D (0.50p) E (0.00p)
```

```
Optimitzaci
ó: OnPush
i
virtualitzac
ió
```

```
OnPush
aplicat a ≥
components
presentacio
nals.
CdkVirtualS
crollViewpor
t amb
```

```
OnPush
correcte
però la
virtualitzaci
ó té errors
(itemSize
incorrecte,
menys de
```

```
OnPush
present
però
CdkVirtualS
crollViewpor
t absent, o
viceversa.
Documenta
```

```
Intent
d'implementa
ció amb
errors que
impedeixen
el
funcionamen
t
```

```
No realitzat.
```

```
*cdkVirtualF
or i ≥
elements.
docs/optimit
zacio.md
complert.
Captura
adjuntada
.
```

###### 50

```
elements) o
documentac
ió
incompleta
```

```
ció molt
escassa.
```

## Exercici 5: Build de producció i evidència d'anàlisi (

## punts)

Enunciat: Genereu el build de producció de l'aplicació i aporteu l'evidència de la mida del
bundle resultant.

Build de producció:

ng build --configuration production

El build ha de completar-se sense errors. Els fitxers generats es trobaran a la carpeta dist/.

Evidència del bundle (obligatòria **—** trieu una opció o les dues):

- Opció A **—** Captura de la terminal: Captura de la sortida del terminal on es vegi la taula de

mides dels fitxers generats (Initial Chunk Files, Lazy Chunk Files, mida total, etc.).

- Opció B **—** Webpack Bundle Analyzer: Anàlisi detallada:

```
# Afegiu a package.json (scripts):
```

```
# "build:stats": "ng build --stats-json",
```

```
# "analyze": "webpack-bundle-analyzer dist/nom-app/browser/stats.json"
```

```
npm run build:stats
```

```
npx webpack-bundle-analyzer dist/nom-app/browser/stats.json
```

```
Adjunteu una captura del gràfic generat.
```

README obligatori (README.md a l'arrel del repositori):

El README.md ha d'incloure les seccions següents:

1. Descripció del projecte — Nom i breu descripció de l'aplicació.
2. Mapa de rutes — Taula amb path, component i si l'accés és públic o privat.
3. Instruccions d'execució en local:

```
git clone [url-repositori]
```

```
cd [nom-projecte]
```

```
npm install
```

```
ng serve
```

```
# Obrir http://localhost:
```

4. Build de producció — Instruccions i mida aproximada del bundle obtinguda.
5. Credencials de prova — Email i contrasenya per accedir a la secció protegida.

Captures requerides:

- Captura 1: Sortida del terminal amb el build de producció completat — taula de mides del

bundle visible.

Rúbrica A (2.00p) B (1.50p) C (1.00p) D (0.50p) E (0.00p)

Build:
Build de
producció
i evidència

```
Build sense
errors.
README.m
d complet
amb mapa
de rutes,
instruccions
d'execució i
credencials
de prova.
Captura del
bundle amb
mides
visibles
adjuntada.
```

```
Build
completat
però
README.m
d incomplet
(falten
seccions) o
la captura
no mostra
les mides.
```

```
Build
realitzat
però sense
README.m
d o sense
captura
d'evidència.
```

```
Build amb
errors o
evidència
absent.
README.m
d buit o
inexistent.
```

```
No realitzat.
```
