# 🍕 Respuestas: Ejercicio de Comandas de Pizzas (Jetpack Compose)

---

## 📊 1. Diferencias entre tipos de variables (Contadores)

A continuación se detalla el comportamiento de las tres variables de estado al realizar diferentes acciones funcionales sobre el dispositivo o emulador:

### 🔘 Prueba 1 (Pulsar el botón "+1 contadores")
* **`contadorNormal`**  
  > La interfaz siempre muestra `0`. Aunque el valor interno de la variable aumente al pulsar el botón, Jetpack Compose **no es notificado** del cambio, por lo que no redibuja ese texto. Además, como el botón también altera los estados de memory/saveable, Compose vuelve a pintar la interfaz y recrea la variable con su valor inicial (`var contadorNormal = 0`).
  
* **`contadorRemember`**  
  > Se incrementa y se actualiza correctamente en la pantalla. Al usar `remember` junto con un `MutableState`, le avisa a Compose que hay un cambio y le pide que redibuje (recomposición), **recordando** su valor actual mientras el componente siga vivo y visible.

* **`contadorSaveable`**  
  > Visualmente funciona exactamente igual que el `remember`: se incrementa en pantalla, avisa a Compose del cambio y conserva el último valor sumado.

---

### 🔄 Prueba 2 (Rotación de pantalla)
* **`contadorNormal`**  
  > Continúa mostrando su valor por defecto de `0`.
  
* **`contadorRemember`**  
  > **Se reinicia a `0`**. Al girar la pantalla en Android, la aplicación destruye la `Activity` por completo (`onDestroy`) y la vuelve a crear desde cero con el nuevo tamaño o disposición (`onCreate`). En esta destrucción total, el `remember` simple **olvida** el estado que guardaba.
  
* **`contadorSaveable`**  
  > **¡Mantiene su valor perfectamente!** Esta es su ventaja principal. `rememberSaveable` se ancla al mecanismo nativo de Android (`savedInstanceState`) capaz de serializar y guardar datos de estado en un `Bundle` antes de destruir la Activity por rotarla, recuperándolos en cuanto el sistema vuelve a crear la ventana.  
  *(Nota: Los campos de texto del formulario de la app, como "Nom" o "Quantitat", también usan esta técnica para que no se borren en medio de una comanda al girar el móvil).*

---

### 📱 Prueba 3 (App en segundo plano y vuelta)
Si envías la aplicación al menú de Inicio de Android (segundo plano) brevemente y la vuelves a abrir, **los tres mantienen los valores que tenían**. Esto ocurre porque la `Activity` no se ha llegado a cerrar definitivamente, sino que ha quedado pausada (`onPause` y `onStop`) en la memoria RAM del teléfono.

**⚠️ Excepción Crítica:**  
Si el sistema operativo necesita liberar memoria RAM y "mata" tu aplicación mientras está de fondo, cuando la vuelvas a abrir se recreará desde cero. En este escenario, **solo `contadorSaveable` recuperará su número**, mientras que `contadorRemember` se habrá reiniciado tal como en la rotación.

---
---

## ⏳ 2. Ciclo de vida de la Activity

### 📝 Etapas observadas (Logcat y UI)
A lo largo de la ejecución, se han registrado las siguientes etapas del ciclo nativo de Android:  
`onCreate`, `onStart`, `onResume`, `onPause`, `onStop`, `onDestroy` y `onRestart`.

### ⏱️ Orden cronológico según las acciones
1. **Al abrir la app por primera vez:**  
   `onCreate` ➔ `onStart` ➔ `onResume`
2. **Al enviar la app a segundo plano (Inicio):**  
   `onPause` ➔ `onStop`
3. **Al volver a la app desde el segundo plano:**  
   `onRestart` ➔ `onStart` ➔ `onResume`
4. **Al girar la pantalla:**  
   `onPause` ➔ `onStop` ➔ `onDestroy` *(Activity vieja destruida)* ➔ `onCreate` ➔ `onStart` ➔ `onResume` *(Activity nueva creada horizontal/vertical)*
5. **Al cerrar la app definitivamente (Botón Atrás o limpiar la multitarea):**  
   `onPause` ➔ `onStop` ➔ `onDestroy`

### 💡 Cuándo se llama a cada etapa
* **`onCreate`**: Solo ocurre una vez por ciclo de vida completo. Construye la aplicación por primera vez y enlaza el contenido UI inicial.
* **`onStart`**: La interfaz visual se "prepara" y está a punto de ser visible para el usuario, aunque aún no intercepta clics ni interacciones.
* **`onResume`**: La app ya está totalmente en *Foreground* (primer plano visible). Permite al usuario interactuar, escribir o tocar botones libremente.
* **`onPause`**: La actividad pasa a segundo plano temporal. Puede suceder si se abre una ventana flotante encima, se quitan los permisos, o si el usuario sale de la app pero ésta aún se ve en el menú de aplicaciones recientes. Se congela o "pausa" toda animación.
* **`onStop`**: La ventana desaparece totalmente de la vista del usuario (minimización).
* **`onRestart`**: Función puente que se dispara si estábamos en estado `onStop` y el usuario ha decidido volver a abrirnos, procediendo a llamar a `onStart` de nuevo.
* **`onDestroy`**: Fin del ciclo. Limpieza exhaustiva de la ventana y variables dinámicas de memoria RAM.

---
---

## 🧠 3. Uso del ViewModel vs Interfaz de Estado

### ⚖️ Diferencia principal frente a los Contadores
Las variables de Compose (`remember` o `rememberSaveable`) operan vinculadas estrictamente al árbol visual estético; por ende, su ciclo de vida y su procesamiento están emparejados a la pantalla actual. 

En cambio, un **`ViewModel`** es un componente arquitectónico especial (proporcionado por Google) que existe en un estrato de la aplicación **completamente aislado** del diseño visual y de la pantalla. El `ViewModel` sobrevive automáticamente a la destrucción temporal por giros o recargas de `Activity` de manera pasiva y natural, sin necesitar etiquetas extras de serialización como `rememberSaveable`.

### 🚀 Grandes Ventajas de utilizar ViewModel
1. **Separación de responsabilidades (Clean Code):**  
   Dejamos de ensuciar el archivo de "dibujar pantalla" (*UI*) con funciones lógicas complicadas. La vista se dedica a mostrar píxeles y colores, y manda las tareas de procesamiento y guardado al `ViewModel`.
   
2. **Robustez estructural:**  
   Salvar datos sencillos (como un `int` para los contadores) con un `rememberSaveable` está bien, pero si usamos esta táctica para guardar una lista de datos enorme que se va llenando sin parar (como pasa aquí con el *Historial del Ciclo de Vida*), cada vez que giremos la pantalla la aplicación se trabaría serializando decenas de variables visuales a un `Bundle`. Como el `ViewModel` persiste sin requerir serialización en la rotación de pantalla, otorga un rendimiento y escalabilidad netamente superior para los historiales.
