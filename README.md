# Práctica 9 - HydroWatch 💧
**Aplicación de Hidratación para Wear OS y Android**

## 👨‍💻 Información del Estudiante
- **Nombre**: Cortés Buendía Martín Francisco
- **Boleta**: 2022630507
- **Materia**: Desarrollo de Aplicaciones Móviles Nativas
- **Práctica**: 9 - Aplicaciones para Vestibles
- **Fecha de entrega**: 8 de diciembre de 2025

---

## 📖 Descripción del Proyecto

HydroWatch es una aplicación de seguimiento de hidratación diseñada para dispositivos Wear OS y Android. La aplicación permite a los usuarios monitorear su consumo diario de agua, establecer metas personalizadas y recibir recordatorios para mantener una hidratación adecuada.

### Características Principales

**Versión Wear OS:**
- Interfaz optimizada para pantallas circulares y rectangulares
- Indicador visual de progreso circular
- Contador de vasos de agua consumidos
- Almacenamiento local persistente
- Sincronización con Firebase Realtime Database

**Versión Mobile:**
- Interfaz Material Design 3
- Gestión de metas personalizadas (1-20 vasos)
- Funcionalidad completa de incremento/decremento
- Sincronización en tiempo real con la nube
- Notificaciones push mediante Firebase Cloud Messaging

---

## ✅ Requisitos Cumplidos

### Parte 1: Desarrollo Individual de una Aplicación para un Vestible

#### Ejercicio 1: Configuración del Entorno de Desarrollo
- [x] Instalación de la última versión de Android Studio
- [x] Habilitación de herramientas de desarrollo de Wear OS desde SDK Manager
- [x] Configuración de emulador Wear OS (API 30)
- [x] Verificación de conexión mediante `adb devices`

#### Ejercicio 2: Desarrollo de la Aplicación Base

**Diseño de la Interfaz:**
- [x] Implementación de componentes Material Design para Wear OS
- [x] Compatibilidad con pantallas circulares y rectangulares
- [x] Uso de ScalingLazyColumn para navegación optimizada
- [x] Paleta de colores de alto contraste para visibilidad

**Implementación Técnica:**
- [x] Navegación mediante gestos estándar de Wear OS (scroll vertical)
- [x] Almacenamiento local con DataStore Preferences
- [x] Manejo eficiente de recursos y batería
- [x] Arquitectura MVVM con ViewModel y Repository

**Aplicación Complementaria para Android:**
- [x] Versión funcional para dispositivos Android convencionales
- [x] Replicación de funcionalidades principales
- [x] Interfaz adaptada a pantallas grandes
- [x] Funcionalidades adicionales (ajuste de meta, decremento)

**Categoría Seleccionada:**
- [x] Salud y Bienestar - Recordatorios de hidratación

### Parte 2: Desarrollo Colaborativo de una Integración Avanzada

#### Ejercicio 3: Integración con Servicios en la Nube

**Sistema de Notificaciones:**
- [x] Configuración de Firebase Cloud Messaging (FCM)
- [x] Servicio de mensajería implementado en ambas versiones
- [x] Canal de notificaciones de alta prioridad
- [x] Intents para abrir la aplicación desde notificaciones

**Sincronización de Datos:**
- [x] Implementación de Firebase Realtime Database
- [x] Sincronización automática al registrar vasos de agua
- [x] Caché local mediante DataStore para operación offline
- [x] Identificación de dispositivo (wear/mobile) en sincronización

---

## 🛠 Tecnologías Utilizadas

### Lenguaje y Framework
- **Kotlin 2.0.21** - Lenguaje de programación principal
- **Jetpack Compose** - Framework moderno para UI declarativa
- **Wear OS Compose 1.4.0** - Componentes específicos para smartwatches

### Arquitectura y Patrones
- **MVVM (Model-View-ViewModel)** - Patrón de arquitectura
- **Repository Pattern** - Abstracción de fuentes de datos
- **StateFlow** - Manejo reactivo de estado
- **Kotlin Coroutines** - Programación asíncrona

### Almacenamiento y Sincronización
- **DataStore Preferences** - Almacenamiento local tipo key-value
- **Firebase Realtime Database** - Base de datos NoSQL en tiempo real
- **Firebase Cloud Messaging** - Servicio de notificaciones push

### Bibliotecas Principales
```gradle
// Wear OS
androidx.wear:wear:1.3.0
androidx.wear.compose:compose-material:1.4.0
androidx.wear.compose:compose-foundation:1.4.0

// Firebase
com.google.firebase:firebase-bom:32.7.0
com.google.firebase:firebase-messaging-ktx
com.google.firebase:firebase-database-ktx

// DataStore
androidx.datastore:datastore-preferences:1.0.0

// Lifecycle
androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7
```

---


## 🏗 Arquitectura del Proyecto

### Estructura de Directorios
```
practica9/
├── app/                          # Módulo Wear OS
│   └── src/main/
│       ├── java/com/escom/practica9martin/
│       │   ├── data/
│       │   │   ├── PreferencesManager.kt
│       │   │   └── WaterRepository.kt
│       │   ├── presentation/
│       │   │   ├── MainActivity.kt
│       │   │   ├── WaterViewModel.kt
│       │   │   ├── WaterScreen.kt
│       │   │   └── theme/
│       │   └── service/
│       │       └── MyFirebaseMessagingService.kt
│       └── res/
│
├── mobile/                       # Módulo Android
│   └── src/main/
│       ├── java/com/escom/practica9martin/
│       │   ├── data/
│       │   │   ├── PreferencesManager.kt
│       │   │   └── WaterRepository.kt
│       │   ├── MainActivity.kt
│       │   ├── WaterViewModel.kt
│       │   └── service/
│       │       └── MyFirebaseMessagingService.kt
│       └── res/
│
└── build.gradle.kts
```

### Flujo de Datos

1. **Usuario interactúa** con la UI (WaterScreen)
2. **WaterScreen** invoca métodos del **WaterViewModel**
3. **WaterViewModel** llama al **WaterRepository**
4. **WaterRepository**:
   - Actualiza **PreferencesManager** (almacenamiento local)
   - Sincroniza con **Firebase Realtime Database**
5. **StateFlow** notifica cambios a la UI
6. **WaterScreen** se recompone con nuevos datos

---

## 🚀 Instalación y Configuración

### Prerrequisitos

- Android Studio Hedgehog (2023.1.1) o superior
- JDK 11 o superior
- SDK de Android:
  - API 30 o superior (Wear OS)
  - API 24 o superior (Mobile)
- Cuenta de Firebase con proyecto configurado

### Pasos de Instalación

1. **Clonar el repositorio:**
```bash
git clone https://github.com/MartinCortes20/practica9WearOs
cd practica9-wear-os
```

2. **Configurar Firebase:**
   - Crear proyecto en [Firebase Console](https://console.firebase.google.com/)
   - Agregar dos aplicaciones Android con package: `com.escom.practica9martin`
   - Descargar `google-services.json` para cada app
   - Colocar archivos en:
     - `app/google-services.json`
     - `mobile/google-services.json`

3. **Habilitar servicios de Firebase:**
   - En Firebase Console → Realtime Database → Crear base de datos
   - Configurar reglas en modo prueba:
```json
   {
     "rules": {
       ".read": true,
       ".write": true
     }
   }
```

4. **Sincronizar proyecto:**
```bash
./gradlew clean
./gradlew build
```

5. **Ejecutar aplicaciones:**

   **Wear OS:**
```bash
   ./gradlew :app:installDebug
```

   **Mobile:**
```bash
   ./gradlew :mobile:installDebug
```

---

## 📸 Capturas de Pantalla

### Versión Wear OS

#### Pantalla Principal - Estado Inicial
![Wear OS - Inicio](<img width="232" height="226" alt="Captura de pantalla 2025-12-14 a la(s) 4 53 19 p m" src="https://github.com/user-attachments/assets/f45d2a13-591b-4a79-a67a-d55d6a8f1f31" />,<img width="232" height="226" alt="Captura de pantalla 2025-12-14 a la(s) 4 53 53 p m" src="https://github.com/user-attachments/assets/fff47620-43a8-4b7e-a506-df5cd0b741e2" />)


**Descripción:**
Esta captura muestra la interfaz inicial de la aplicación en un dispositivo Wear OS. Se observa:
- Título "Hidratación" con ícono de gota de agua
- Indicador circular de progreso (0/8)
- Botón principal "+ Beber Agua"
- Botón secundario "Reiniciar Día"

**Justificación:**
La interfaz está optimizada para smartwatches mediante el uso de ScalingLazyColumn, que permite navegación fluida mediante scroll vertical. Los elementos están espaciados adecuadamente para evitar toques accidentales en pantallas pequeñas.

---

#### Pantalla Principal - Progreso Parcial
![Wear OS - Progreso](<img width="232" height="226" alt="Captura de pantalla 2025-12-14 a la(s) 4 54 30 p m" src="https://github.com/user-attachments/assets/087a22ac-e98a-4c37-938a-eb49cd51309d" />)

**Descripción:**
Muestra la aplicación después de registrar 4 vasos de agua. El indicador circular refleja el 50% de progreso hacia la meta diaria de 8 vasos.

**Justificación:**
El indicador circular es ideal para pantallas circulares de smartwatches. El uso de colores de alto contraste (azul #03A9F4 sobre fondo oscuro) garantiza visibilidad en exteriores.

---

#### Pantalla Principal - Meta Alcanzada
![Wear OS - Meta Alcanzada](<img width="232" height="226" alt="Captura de pantalla 2025-12-14 a la(s) 4 54 59 p m" src="https://github.com/user-attachments/assets/e19a7fef-4fea-4ce4-9918-af4dc1a32178" />)


**Descripción:**
Pantalla que muestra la meta diaria cumplida (8/8 vasos) con mensaje de felicitación "¡Meta alcanzada!".

**Justificación:**
El feedback visual inmediato motiva al usuario. El mensaje de éxito utiliza color verde (#4CAF50) para reforzar el logro positivo.

---

### Versión Mobile

#### Pantalla Principal - Interfaz Completa
![Mobile - Principal]

<img width="348" height="689" alt="Captura de pantalla 2025-12-14 a la(s) 4 59 39 p m" src="https://github.com/user-attachments/assets/65bf51f7-e74c-43de-93a5-a451d9d74db9" />


**Descripción:**
Interfaz de la versión mobile mostrando:
- Encabezado "HydroWatch"
- Indicador circular grande de progreso
- Contador central
- Tres botones de acción: "+ Beber Agua", "- Quitar", "Reiniciar"
- Opción "Cambiar meta diaria"

**Justificación:**
La versión mobile aprovecha el espacio adicional para ofrecer funcionalidades extendidas como el decremento y ajuste de meta, manteniendo la coherencia visual con la versión Wear OS.

---

#### Diálogo de Cambio de Meta
![Mobile - Cambiar Meta]
<img width="348" height="689" alt="Captura de pantalla 2025-12-14 a la(s) 4 59 54 p m" src="https://github.com/user-attachments/assets/14322c99-13ef-4f0a-8d48-6ba5508efb46" />




**Descripción:**
Diálogo modal que permite al usuario ajustar su meta diaria de hidratación entre 1 y 20 vasos.

**Justificación:**
Esta funcionalidad adicional en la versión mobile permite personalización avanzada. El campo de texto valida la entrada para aceptar solo números, previniendo errores del usuario.

---

### Firebase Console

#### Realtime Database - Sincronización
![Firebase - Database]
<img width="1369" height="652" alt="Captura de pantalla 2025-12-14 a la(s) 4 57 32 p m" src="https://github.com/user-attachments/assets/b82777b0-e28f-4a37-8517-bc34eaa5af64" />


**Descripción:**
Captura de Firebase Realtime Database mostrando la estructura de datos:
```json
water_intake/
  ├── user_wear/
  │   └── timestamp_1/
  │       ├── timestamp: 1701234567890
  │       ├── glasses: 1
  │       └── device: "wear"
  └── user_mobile/
      └── timestamp_2/
          ├── timestamp: 1701234578901
          ├── glasses: 1
          └── device: "mobile"
```

**Justificación:**
La estructura de datos permite identificar el origen de cada registro (wear/mobile) y mantener un historial completo con timestamps. Esto facilita futuras implementaciones de estadísticas y análisis de patrones de hidratación.

---
