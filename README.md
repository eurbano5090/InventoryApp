# 📦 InventoryApp

Aplicación móvil Android desarrollada en **Kotlin + Jetpack Compose**, que permite gestionar inventarios, consultar productos desde una API REST, almacenarlos localmente con Room y visualizar los datos incluso sin conexión.  
Incluye arquitectura limpia, DI con Hilt y pruebas unitarias/instrumentadas.

<img width="163" height="540" alt="image" src="https://github.com/user-attachments/assets/c92174e7-47a6-47a5-8cc1-ceba8f7e35e8" />
<img width="153" height="552" alt="image" src="https://github.com/user-attachments/assets/95211e7a-a310-4574-acb0-19161d3db8b7" />





---

## 🚀 Características principales

### 🔹 Gestión de Inventario
- Lista de productos con precio, imagen y stock.  
- Sincronización desde una API externa.  
- Persistencia local con Room.

### 🔹 Consumo de API (Retrofit)
- Obtención de productos desde un servicio REST.  
- Mapeo de modelos: **API → Dominio → Entidad Room**.

### 🔹 Almacenamiento Local (Room)
- Guardado automático de los productos descargados.  
- Carga offline desde base de datos local.  
- DAOs optimizados para inserción y actualización.

### 🔹 UI con Jetpack Compose
- Pantallas dinámicas y reactivas basadas en **StateFlow**.  
- Listado de productos totalmente Compose.  
- Diseño moderno usando **Material 3**.

### 🔹 Inyección de Dependencias con Hilt
- Repositorios, DAOs y ViewModels provistos mediante Hilt.

---

## 📁 Estructura Real del Proyecto

app/
└ ── com/example/inventory

├── core/

│ └── navigation/

├── data/

│ ├── dao/

│ ├── entity/

│ ├── model/

│ ├── remote/

│ └── repositories/

├── di/

├── ui/

│ ├── component/

│ └── theme/

└── view/

├── auth/

└── auth/home/

└── component/


---

## 🧪 Pruebas

### ✔ Pruebas Unitarias
- Verificación de mapeos entre modelos API y entidades Room.  
- Test de funciones puras del repositorio y del ViewModel.  

### ✔ Prueba Instrumentada (UI Test)
- Verifica que la UI actualiza la lista después de consumir la API.  
- Simulación de interacción real con la interfaz.

---

## 🔐 Release Listo para Producción
- Configuración de `signingConfigs` para compilar en modo **release**.  
- Generación de **APK**  desde Android Studio.  

---

## 🛠️ Tecnologías Utilizadas

- Kotlin  
- Jetpack Compose  
- Room  
- Retrofit + Gson  
- Hilt  
- Coroutines  
- ViewModel + StateFlow  
- Material 3  
- JUnit  
- Android Studio  

---

## ✨ Autora
**Elisa Urbano**  
Desarrolladora Full Stack  
Java · Kotlin · Android · Spring Boot · Node.js · PostgreSQL · Python · Dyango
