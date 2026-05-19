# TALLER-Corutinas-Kotlin

# TaskSimulator

Aplicación Android desarrollada en Kotlin con Jetpack Compose para demostrar la diferencia entre programación síncrona y asíncrona utilizando Kotlin Coroutines.

## Objetivo

El propósito de esta aplicación es mostrar de forma práctica cómo las tareas pesadas pueden bloquear el hilo principal de Android y cómo las Coroutines permiten ejecutar operaciones largas sin congelar la interfaz de usuario.

---

## Funcionalidades

### Tarea Bloqueante
Simula una operación pesada usando `Thread.sleep(4000)`, lo que bloquea completamente el hilo principal y congela la interfaz.

### Tarea Asíncrona
Usa `viewModelScope.launch`, `Dispatchers.IO` y `delay(4000)` para realizar la misma tarea sin bloquear la UI.

### Tareas Paralelas
Ejecuta tres tareas al mismo tiempo utilizando varias coroutines, reduciendo el tiempo total de ejecución.

### Tareas Secuenciales
Ejecuta tres tareas una tras otra sin bloquear la interfaz.

### Contador en Tiempo Real
Actualiza un contador del 1 al 10 cada segundo.

### Manejo de Errores
Simula un fallo de red y captura la excepción usando `try-catch`.

###  Cancelación de Tareas
Permite iniciar una tarea larga y cancelarla antes de que termine.

### Log de Eventos
Muestra en pantalla cada evento importante ocurrido durante la ejecución.

---

## Tecnologías Utilizadas

- Kotlin
- Android Studio
- Jetpack Compose
- Kotlin Coroutines
- ViewModel


---

## Estructura del Proyecto

TaskSimulator/
├── app/src/main/java/com/example/tasksimulator/
│ ├── MainActivity.kt
│ └── TaskViewModel.kt

---

## ▶️ Cómo Ejecutar

1. Abrir el proyecto en Android Studio.
2. Sincronizar Gradle.
3. Ejecutar la aplicación en un emulador o dispositivo Android.
4. Probar cada botón y observar el comportamiento de la interfaz.

---

## Conceptos Aprendidos

- Hilo principal (Main Thread)
- Programación síncrona
- Programación asíncrona
- Coroutines
- `suspend fun`
- `delay()`
- `Thread.sleep()`
- `Dispatchers.IO`
- `viewModelScope`


---

## 👨‍🎓 Autor

Andres Ballesteros  
Universidad de Caldas  
Programación para Dispositivos Móviles
