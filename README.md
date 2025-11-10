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

## Capturas de Pantalla

<p align="center">
  <b>Listado de Competencias</b> &nbsp;&nbsp;&nbsp;&nbsp;
  <b>Listado de Equipos</b> &nbsp;&nbsp;&nbsp;&nbsp;
  <b>Listado de Jugadores</b>
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/8a3d3c88-df21-43f3-9f9f-c34e1443bf15" alt="Listado de Competencias" width="200"/>
  <img src="https://github.com/user-attachments/assets/6215e679-5a8c-408e-bc52-2a0e397d23d6" alt="Listado de Equipos" width="200"/>
  <img src="https://github.com/user-attachments/assets/05c3971d-eec1-4c68-bd34-c62f7cb6dd79" alt="Listado de Jugadores" width="200"/>
</p>






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


