# Testausdokumentti

Ohjelmiston yksikkötestit suoritetaan JUnit kirjaston avulla. Ne
voi suorittaa komennolla

```
mvn test
```
Myös integraatiotason testejä suoritetaan JUnitilla.
Ohjelman suorituskykyä voi valvoa sen graafisesta käyttöliittymästä.

Testitulosten visualisointi on saatavilla komenolla
```
  mvn test jacoco:report
```
Tällä myös selviää mitä on testattu.

## Suorituskykytestaus
Suorituskykyä voi seurata ohjelmaa ajamalla. Ohjelma ilmoittaa kuinka kauan
algoritmilla menee palauttaa polku omenalle. Keskimäärin AStar vie aikaa noin
8ms ja BFS vie aikaa noin 30ms. Tarkastelujakso molemmille oli noin 10min.
Tänä aikana peli ehti mennä loppuun useita kertoja. Molemmat algoritmit saa
noin 90 omenaa ennen kuolemistas.
