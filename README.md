# 📱 Semana 09 - Servicio Web en Android con Retrofit

**Autora:** Yamile Ochoa  
**Tecnología:** Android Studio (Kotlin + Jetpack Compose + Retrofit)  
**Tema:** Consumo de API y visualización de datos en una interfaz moderna.

---

## 🎯 **Objetivo**
Desarrollar una aplicación Android que consuma datos desde un servicio web utilizando **Retrofit** y los muestre en una interfaz construida con **Jetpack Compose**.  
El proyecto permite listar información de usuarios obtenida desde una API y realizar búsquedas dinámicas dentro de la lista.

---

## 🧩 **Tecnologías y Librerías Utilizadas**
- **Kotlin**
- **Jetpack Compose**
- **Retrofit**
- **Gson Converter**
- **Coroutines**
- **ViewModel & StateFlow**
- **Material Design 3**

---

## 🧠 **Estructura del Proyecto**

```
com.ochoa.semana09_servicios
│
├── data/
│   ├── model/
│   │   └── User.kt
│   └── network/
│       └── ApiService.kt
│
├── repository/
│   └── UserRepository.kt
│
├── viewmodel/
│   └── UserViewModel.kt
│
├── screen/
│   └── UserListScreen.kt
│
└── ui/theme/
    ├── Color.kt
    ├── Theme.kt
    └── Type.kt
```

---

## ⚙️ **Pasos Realizados**

1. **Configuración de dependencias**
   - Retrofit, Gson Converter, Coroutines, Compose y ViewModel agregados en `build.gradle`.

2. **Creación del modelo de datos (`User.kt`)**
   - Contiene los campos `id`, `name`, `username`, `email`, `phone` y `website`.

3. **Definición del servicio API (`ApiService.kt`)**
   - Método `getUsers()` con anotación `@GET("users")` para obtener la lista de usuarios.

4. **Implementación del repositorio (`UserRepository.kt`)**
   - Configura Retrofit con la base URL y el conversor de JSON a objeto Kotlin.

5. **Lógica en el ViewModel (`UserViewModel.kt`)**
   - Manejo de estados: `Loading`, `Success` y `Error`.
   - Uso de **StateFlow** para controlar datos y texto de búsqueda.

6. **Interfaz con Jetpack Compose (`UserListScreen.kt`)**
   - Incluye barra de búsqueda, lista de usuarios, tarjetas de información y manejo visual de errores o resultados vacíos.

7. **Personalización de tema (`Color.kt` y `Theme.kt`)**
   - Se eliminó el uso de colores rosados.
   - Se aplicó una paleta moderna con tonos azules y neutros.

---

## 📷 **Vista Previa**
La aplicación muestra una lista limpia y moderna de usuarios, permitiendo buscar por nombre o username.  
En caso de error o resultados vacíos, se muestra un mensaje elegante con opción de reintento.

---

## 🧩 **Conclusiones Personales**

1. Aprendí a consumir datos de una API usando Retrofit y manejar los estados en Compose.  
2. Entendí la importancia de usar ViewModel y coroutines para mantener una app fluida.  
3. Logré aplicar un diseño más profesional modificando la paleta de colores y los componentes visuales.

---

## 💬 **Créditos**
Proyecto desarrollado por **Yamile Ochoa**  
📅 *Laboratorio Semana 09 – Servicios Web en Android (Tecsup)*
