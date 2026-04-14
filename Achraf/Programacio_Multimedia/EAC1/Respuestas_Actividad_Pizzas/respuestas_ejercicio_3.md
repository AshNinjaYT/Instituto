# 🍕 Respuestas: Ejercicio 3 (Navegación y ViewModel Compartido)

---

## 🧭 a) Navegación

### ¿Qué hace el NavHost?
El `NavHost` es el contenedor gráfico principal donde ocurre el intercambio de pantallas. Actúa como si fuese un marco vacío en medio de la aplicación que se encarga de "escuchar" en qué ruta nos encontramos (por ejemplo, `"inici"` o `"comanda"`) y, en base a esa ruta de texto, decide qué `@Composable` de todo nuestro código debe dibujar dentro de ese marco.

### ¿Para qué sirve el NavController?
El `NavController` es el "motor" o controlador lógico que permite interactuar con el `NavHost`. Es el objeto encargado de llevar la cuenta del historial de pantallas visitadas (la pila de navegación o *BackStack*). Gracias a él podemos ordenar acciones como:
- Avanzar a la siguiente pantalla (`navController.navigate("ruta")`).
- Volver hacia atrás sin romper la app.
- Vaciar el historial de golpe al terminar el pedido (usando `popUpTo`).

---

## 📱 b) TopAppBar

### ¿Dónde la has colocado?
He colocado la `TopAppBar` dentro del parámetro `topBar` del componente base **`Scaffold`**, el cual engloba a toda la aplicación entera justo por debajo del tema principal (`ComandasDePizzasTheme`). De esta manera, su título (que va cambiando dinámicamente según la ruta actual del `NavController`) siempre se mantiene anclado arriba y visible, independientemente de qué contenido cargue el `NavHost`.

### ¿Por qué es útil tenerla dentro de un Scaffold?
El `Scaffold` es una pieza fundamental de Material Design que implementa la estructura gráfica básica requerida de una pantalla. Si pusiéramos la `TopAppBar` "flotando" manualmente dentro de un diseño `Column`, tendríamos que estar calculando siempre a mano los márgenes y alturas exactas. Al meterla en su ranura oficial (`topBar = { ... }`) del `Scaffold`, este calcula internamente el espacio que ocupa (el `innerPadding`) e impide automáticamente que el título se solape con nuestros campos de texto o los botones de las pantallas de la Pizza.

---

## 🧠 c) ViewModel Compartido

### ¿Qué datos guarda el ViewModel (`PizzaOrderViewModel`)?
Guarda centralizadamente las tres variables maestras del estado del pedido:
1. `customerName` (String): El nombre del cliente.
2. `quantity` (Int): Entérico con la cantidad de pizzas.
3. `pizzaType` (String): El tipo de sabor elegido (Ej: "Margarita").
*También guarda la lógica y las funciones protegidas para modificarlas o resetearlas al completo.*

### ¿Por qué es útil que sea compartido entre pantallas?
Es tremendamente útil porque **desacopla (separa) los datos de la interfaz gráfica**.
El `NavHost` funciona destruyendo gráficamente la pantalla vieja cuando navegas a la nueva para ahorrar RAM del móvil. Al tener un único `ViewModel` instanciado a nivel general (en la `MainActivity`) y pasarlo como parámetro tanto a `PantallaComanda` como a `PantallaResum`, conseguimos tener un "almacén central en la nube" del que todas las pantallas separadas pueden leer y escribir a la vez. Cuando `PantallaResum` le pregunta al `ViewModel` qué nombre ha escrito el cliente, lo obtiene instantáneamente porque esos datos sobrevivieron flotando de forma segura detrás de los focos.

### ¿Qué pasaría si cada pantalla tuviera sus propias variables locales?
Si declarásemos las variables en la `PantallaComanda` de manera tradicional (por ejemplo con `var nombre by remember { ... }`), esos datos nacerían y morirían exclusivamente dentro de esa función.
Al llamar a `navController.navigate("resum")`, Compose retiraría la pantalla 2 de la memoria y dibujaría la 3 desde cero, **destruyendo y perdiendo irreparablemente las variables locales en el proceso**. 
La `PantallaResum` nacería completamente ciega, y necesitaríamos ingeniar un laberinto de argumentos de texto y *callbacks* gigantes en las rutas de navegación pasándose un montón de variables a la vez para simular que recordamos lo que el usuario había marcado, provocando un código desastroso, rígido y lleno de posibilidades de fuga de memoria ante rotaciones de pantalla.
