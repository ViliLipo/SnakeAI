# Toteutus
Snake-ai on toteutetty käyttäen JavaFX-canvasta ja itse kirjoitettuja
reitinhakualgoritmeja.
Ohjelma ei tällä hetkellä käytä ulkopuolisia tietorakenteita.
Ohjelman käyttöliittymästä voi seurata algoritmin suorituskykytietoja.

## Algoritmit
### BFS
Ohjelmassa on toteutettu kaksi reitinhakualgoritmiä. Ensimmäinen on
leveysuuntaiseen läpikäyntiin perustuva menetelmä. Se eroaa kuitenkin normaalista
leveyssuuntaisesta läpikäynnistä siten, että kaikki epäkelvot
polut hylätään heti ja niiden tutkimista ei jatketa. Tämä algoritmi löytää
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
A\* algoritmin avoin joukko on toteutettu yhdistelmänä taulukoita ja kekoa.
Keossa madot arvioidaan siis A* algoritmin F-scoren mukaan, joka on yhdistelmä
madon pään etäisyyttä alkuun, sekä arvioitua etäisyyttä maaliin.
Algoritmin aikavaativuus on O(|E| + |V|). Kuitenkin se saavuttaa parempia
tapauksia huomattavasti useammin kuin BFS.
#### Pseudokoodiesitys
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


## Tietorakenteet
Ohjelman toteuttamiseksi on toteutettu seuraavat tietorakenteet
### Linkitety lista LinkedList-luokka
Linkitetty lista on toteutettu kahteensuuntaa linkityksellä ja se toteuttaa
suurimman osan Javan list-rajapinnasta.
Sen loppuun ja alkuun lisäys operaatiot ovat vakioaikaisia, sekä alusta ja
lopusta poisto. Nämä ovat kriittisimmät operatiot mitä muissa algoritmeissa
käytetään.

## Binäärikeko BinaryHeap-luokka
Ohjelmaan on toteutetty tyypitetty binäärikeko, jolle voi asettaa haluamansa
java.util.comparator-rajapinnan toteuttavat vertailijan. Keko toimii normaalisti
minimikekona, mutta vertailijaa muuttamalla sen saa toimimaan haluamalla tavalla.
Binääri keko on rakennettu Introduction to Algorithms (Cormen et al) mukaan,
kuitenkin indeksointi on muutettu nolla-alkuiseksi.

## Pelilogiikka

### Luokka-GameArea
Tämä luokka vastaa pelialueeseen kohdistuvista operaatiosta. Luokan
metodeilla voi tarkistaa vakio-ajassa onko ruudussa omena, tai mato.
Pelialue on toteuettu kaksisuuntainsena taulukkona. Pelialueessa arvo 1 vastaa
matoa, 2 omenaa ja 0 tyhjää. Näiden arvojen perusteella pelialue myös
piirretään renderer-luokissa.

### Luokka-Snake eli mato
Mato on toteutettu käyttäen linkiettyälistaa sen osien sijaitien tallentamiseen.
Listaa käyetään jonona, josta ensimmäinen pala poistetaan, kun mato liikkuu,
jos mato ei ole syönyt omenaa. Jos mato on syönyt omenan viimeistä palaa
ei poisteta ja näin mato kasvaa. Madolla on tieto sen suunnasta jota
voidaan manipuloida turn-metodilla.

### Luokka-Apple
Apple-luokka mahdollistaa satunnaisen omenan asettamisen kartalle.
Omena asetetaan selvittämällä vapaat ruudut kartasta ja sen jälkeen listan sisällöstä
arvotaan sijainti johon omena asetetaan.

### Luokka-GameEngine
Tämä luokka tarjoaa metodin cycle(), jolla peliä edistetään yksi tikki.
Tämä on toteutettu näin, että ohjelmassa voidaan käyttää JavaFX AnimationTimer
objektia koko sovelluksen kellona. GameEngine-Objektille pitää antaa rakentajaan
GameRenderer-rajapinnan toteuttava olio, jonka tarkoitus on piirtää pelitilanne
joka tikillä. Myös ennen cycle-metodin kutsumista pitää oliolle asetta
Controller-rajapinnan toteuttava ohjain, joka antaa madolle suunnan
joka vuorolle. Tämä rajapinta mahdollistaa helposti muiden algoritmien tai
vaikka ihmisohjaamisen lisäämisen ohjelmaan.

## Sovellus
SnakeAI on JavaFX-app, jossa on ruutu johon peliä piirretään ja siihen
liittyviä asetuksia ja statistiikkaa. Pelialueen piirtäminen on toteutettu
luokassa CanvasGameRenderer. Sovelluksen kellona käytetään JavaFX animation
timeriä, joka kutsuu Luokan GameEngine metodia cycle() jokaisella tikillä,
jos peli ei ole pysäytettynä.

## Luokkakaavio

![kaavio](https://raw.githubusercontent.com/ViliLipo/SnakeAI/master/images/classdiagram.png)


## Puutteet
Algoritmit eivät takaa madon selviämistä omenan syömisen jälkeen. En löytänyt
laskennallisesti tehokasta tapaa varmistaa, että polku päätyy omenan syömisen
jälkeen sellaiseen tilaan josta mato voi selvitä. Ainoat ratkaisut
joita keksin olivat aikavaativuudeltaan selvittävien vuorojen määrä potenssiin
neljä. Matopeliä voisi helposti pelata algoritmilla, joka vain menee
järjestyksessä koko pelikentän läpi kuolematta ja näin täyttää koko
pelikentän madolla, mutta tämä tapa on epäinhimmillen ja tylsä.
