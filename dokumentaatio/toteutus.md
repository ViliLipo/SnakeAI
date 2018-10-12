# Toteutus
Snake-ai on toteutetty käyttäen JavaFX-canvasta ja itse kirjoitettuja
reitinhaku algoritmeja.
Ohjelma ei tällä hetkellä käytä ulkopuolisia tietorakenteita.
Ohjelman käyttöliittymästä voi seurata algoritmin suorituskykytietoja.

## Algoritmit
### BFS
Ohjelmassa on toteutettu kaksi reitinhaku algoritmiä. Ensimmäinen on
leveysuuntaiseen läpikäyntiin perustuva menetelmä. Se eroaa kuitenkin normaalista
leveyssuuntaisesta läpikäynnistä siten, että kaikki epäkelvot
polut hylätään heti ja niiden tutkimista ei jatketa.Tämä algoritmi löytää
aina varmasti lyhimmän reitin, mutta on aikavaativuudeltaan raskas. Algoritmi
ei myöskään voi varmistaa sitä, ettei mato jossain kohtaa kuole, sillä se
ei arvio pelitilannetta omenan syömisen jälkeen

```
  bfs(start, goal):
    visited = boolean[|V|]
    cameFrom = [|V|]
    queue = []
    queue.add(start)
    visited[start] = true
    while queue is not empty:
    // Pahin tapaus on että, kaikki solmut päätyy jonoon
      current = que.poll()
      if current is goal :
         return buildPath(cameFrom, current)
      for each neighbor of current :
      // Pahin tapaus on että, lopulta kaikki kaaret on käyty läpi
        if not visited[neighbor]:
          visited[neighbor] = true
          cameFrom[neighbor] = current
          queue.add(neighbor)

```

### A*
Toinen reitinhakumenetelmä noudattelee A\* algoritmia. Algoritmin avointa
joukkoa on mallinnettu taulukoilla sekä binäärikeolla. A\* valittiin Djikstran
sijasta sillä, kartan eli verkon jokainen ruutujen välinen kaari on yhden
painoinen jos siitä voi kulkea ja äärettömän painoinen jos siitä ei voi kulkea.
A* algritmin avoin joukko on toteutettu yhdistelmänä taulukoita ja kekoa.
Keossa madot arvioidaan siis A* algoritmin F-scoren mukaan, joka on yhdistelmä
madon pään etäisyyttä alkuun, sekä arvioitua etäisyyttä maaliin.
Algoritmin aikavaativuus on O(|E| + |V|).
toiminta
```
  aStar(start, goal):
              closedSet = {}
              openSet = {start}
              cameFrom = an empty map
              gScores = map with default value of infinity
              fScores = map with default value of infinity
              gScores[start] = 0
              fScores[start] = distance_estimate(start, goal)
              while(openSet is not empty):
              // Pahin tapaus kaikki solmut päätyvät jossain kohtaa joukkoon
                if current = goal:
                    return buildPath(cameFrom, current)
                openSet.remove(current)
                closedSet.add(current)
                for each getNeighbours(current):
                // Huonoin tapaus kaikki kaaret yhteensä
                  if neighbour not in closed set :
                    gScore = gScore[current] + 1
                    if neighbour not in openset
                       openSet.add(neighbour)
                    if gScore < gScores[neighbour]
                       cameFrom[neighbour] = current
                       gScores[neighbour] = gScore
                       fScores[neighbour] = gScore + distance_estimate(neighbour, goal)
```
Testauksessa on huomattu tämän algoritmin aiheuttavan, joskus väistettäviä
törmäyksiä. Tilanteet näyttävät liittyvän relaksointiin.

## Tietorakenteet
Ohjelman toteuttamiseksi on toteutettu seuraavat tietorakenteet
### Linkitety lista LinkedList-luokka
Linkitetty lista on toteutettu kahteensuuntaa linkityksellä ja se toteuttaa
suurimman osan Javan list-rajapinnasta.
Sen loppuun ja alkuun lisäys operaatiot ovat vakioaikaisia, sekä alusta ja
lopusta poisto. Nämä ovat kriittisimmät operatiot mitä muissa algoritmeissa
käytetään.
## Sovellus
SnakeAI on JavaFX-app, jossa on ruutu johon peliä piirretään ja siihen
liittyviä asetuksia ja statistiikkaa. Pelialueen piirtäminen on toteutettu
luokassa CanvasGameRenderer. Sovelluksen kellona käytetään JavaFX animation
timeriä, joka kutsuu Luokan GameEngine metodia cycle() jokaisella tikillä,
jos peli ei ole pysäytettynä.

## Luokkakaavio

![kaavio](https://github.com/ViliLipo/SnakeAI/blob/master/images/class_diagram.svg)
