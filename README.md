# App de Competiciones

**Autor:** Erick De Jesus Mendoza Arrieta  
**Materia:** Programación Móvil  
**Correo:** mendoza.erick06@outlook.com  

---

## Descripción del Proyecto

Esta aplicación fue desarrollada en **Android Studio** como parte del curso de **Programación Móvil**.  
El proyecto tiene como propósito **mostrar información relacionada con competiciones deportivas**, incluyendo jugadores, equipos y las competiciones en las que participan.  
Los datos se visualizan a través de distintas pantallas con adaptadores personalizados.

---

## Funcionalidades Principales

- Visualización de **competiciones deportivas**.  
- Listado de **equipos** y **jugadores** asociados.  
- Uso de adaptadores personalizados para mostrar la información en **ListViews**.  
- Diseño de interfaz con **XML** siguiendo buenas prácticas de usabilidad.  

---

## Tecnologías Utilizadas

- **Lenguaje:** Java  
- **Entorno:** Android Studio  
- **Diseño de interfaz:** XML  
- **Gestión de listas:** Adaptadores personalizados (`Adapter`)  
- **Arquitectura:** Múltiples actividades conectadas mediante `Intent`  

---

## Estructura del Proyecto

```bash
app/
├── manifests/
│   └── AndroidManifest.xml
│
├── java/com/example/apiservices/
│   ├── Competition.java
│   ├── EquipoAdapter.java
│   ├── Jugador.java
│   ├── JugadorAdapter.java
│   ├── ListadoEquipos.java
│   ├── ListadoJugadores.java
│   ├── ListadoTeamsCompetencia.java
│   ├── MainActivity.java
│   ├── Teams.java
│   └── TeamsAdapter.java
│
└── res/
    ├── layout/
    │   ├── activity_main.xml
    │   ├── activity_listado_equipos.xml
    │   ├── activity_listado_jugadores.xml
    │   ├── activity_listado_teams_competencia.xml
    │   ├── item_equipos.xml
    │   ├── item_jugador.xml
    │   └── item_teams_competencia.xml
    └── drawable/
