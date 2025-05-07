# 📱 Android Learning Projects – Pedro Baonza

Este repositorio recopila una serie de **proyectos prácticos Android** desarrollados con el objetivo de aprender, experimentar y aplicar conocimientos sólidos sobre desarrollo móvil, arquitectura limpia, Kotlin, testing profesional y programación moderna con corrutinas.

Cada proyecto incluido aquí aborda una temática concreta del ecosistema Android, desde arquitectura y diseño de aplicaciones escalables hasta pruebas unitarias avanzadas y asincronía con coroutines y Flow. Esta colección no solo representa un portafolio técnico, sino también un camino de aprendizaje y evolución profesional.

---

## 🧭 Índice General

1. [ToDoList App](#-1-todolist-app)
2. [TestingAppDemo](#-2-testingappdemo)
3. [Spaceflight](#-3-spaceflight)
4. [FundamentosJUnit](#-4-fundamentosjunit)
5. [CorrutinasFlow](#-5-corrutinasflow)
6. [Contacto](#-contacto)
7. [Licencia](#-licencia)

---

## ✅ 1. ToDoList App

Una aplicación de gestión de tareas construida con arquitectura **MVVM + Clean Architecture**, diseñada para poner en práctica el desarrollo completo de una app funcional con base de datos local, testing automatizado y separación de responsabilidades.

### 📌 Características
- Añadir, editar, eliminar y listar tareas
- Persistencia con **Room Database**
- Arquitectura escalable: **MVVM + UseCases + Repository**
- Pruebas unitarias con **JUnit** y **Mockito**
- Pruebas de UI con **Espresso**
- Uso de **corrutinas** para operaciones en background

### 🧪 Testing Aplicado
- Test Driven Development (TDD)
- Cobertura de lógica de negocio
- Pruebas de integración y funcionalidad
- Mocking con Mockito

---

## 🧪 2. TestingAppDemo

Aplicación demostrativa enfocada en **consumo de APIs REST** con Retrofit y en la integración de pruebas a todos los niveles: unitarias, integración y validaciones. Representa una base robusta para apps que requieren comunicación con servicios externos.

### 🎯 Objetivos
- Consumo de datos desde una API pública
- Aplicación de **Clean Architecture** y principios SOLID
- Separación de lógica con UseCases
- Implementación completa del patrón **MVVM**
- Validadores lógicos con Hamcrest

### 📦 Tecnologías
- Kotlin + Coroutines
- Retrofit + ViewModel
- Recyclerview + ViewBinding
- JUnit, Espresso, Hamcrest, Mockito

---

## 🚀 3. Spaceflight

Proyecto realista que consume una API de noticias espaciales y presenta artículos en tiempo real. Representa una app moderna, sencilla y funcional que aplica MVVM, Retrofit y estado reactivo con LiveData.

### 📌 Funcionalidades
- Llamadas HTTP para cargar artículos
- Mostrar resultados en RecyclerView
- Capa de repositorio desacoplada
- Arquitectura MVVM con ViewModelFactory

### 🔮 Mejoras futuras
- Filtrado, búsqueda, favoritos
- Soporte offline
- Testing automatizado completo
- Mejora de UI y soporte para temas

---

## 🧠 4. FundamentosJUnit

Proyecto didáctico completamente centrado en **JUnit 4** y en el aprendizaje profundo de pruebas unitarias en Android. Ideal para entender cómo funcionan las aserciones, tests parametrizados y reglas personalizadas.

### 📋 Incluye:
- Aserciones básicas (`assertEquals`, `assertTrue`, etc.)
- Validación de clases modelo (`User.kt`)
- Tests parametrizados
- Reglas personalizadas (`LocationESRule`)
- Ejercicios con arrays, strings, booleanos y condiciones

### 🧪 Enfoque
- Aprender a probar lógica sin UI
- Diseñar tests claros y reutilizables
- Refactorizar código guiado por TDD

---

## 🔄 5. CorrutinasFlow

Proyecto base para explorar **Kotlin Coroutines** y el uso de **Flow** en Android. Ideal para entender asincronía, flujos de datos reactivos, cancelaciones, y cómo mantener la UI responsiva.

### 🎯 Propósito Educativo
- Entender `suspend functions`, `launch`, `async`, `withContext`
- Aplicar `Flow` para emitir estados
- Introducir `StateFlow` y `SharedFlow`
- Base para integrar lógica reactiva y arquitecturas limpias

### 📦 Preparado para:
- Simular llamadas de red
- Manejar estados de carga
- Visualizar eventos en tiempo real
- Integrar con Room o ViewModel

---

## 📚 Aprendizajes Globales

Estos proyectos en conjunto me han permitido:

- Dominar el patrón **MVVM** y conceptos de **Clean Architecture**
- Aprender a escribir código **testeable y mantenible**
- Utilizar herramientas como **Retrofit, Room, ViewModel, LiveData y Coroutines**
- Profundizar en testing con **JUnit, Mockito, Espresso y Hamcrest**
- Conocer el ciclo completo del desarrollo Android, desde diseño hasta pruebas

---

## 📬 Contacto

Pedro Baonza  
- GitHub: [PedroBaonza](https://github.com/PedroBaonza)  
- Email: [pedrobaonza0115@gmail.com](mailto:pedrobaonza0115@gmail.com)

---

## 📄 Licencia

Este repositorio está distribuido bajo la licencia MIT. Puedes usar el código con fines educativos o como base para tus propios proyectos.

---

✨ *Gracias por revisar esta colección de proyectos. Cada uno representa un paso más en mi formación como desarrollador Android profesional. Si tienes sugerencias o quieres colaborar, ¡contáctame!*
