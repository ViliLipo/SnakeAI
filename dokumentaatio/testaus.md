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

## Yksikkötestaus
### Pelilogiikka
Pelilogiikkaa testataan pääosin luokissa GameAreaTest ja SnakeTest.
Luokasta GameArea testataan keskeiset metodet kuten törmäystarkistus, omenan
asettaminen sekä kulmatarkistus. Madon toiminnasta testataan liikkuminen ja
kasvaminen.

### Tietorakenteet
#### Linkitetty lista
Linkitetystä listasta testataan rajapinnasta ne osat jotka on toteutettu mm. iteraattorin perustoiminta,
elementin ottaminen indeksillä, koon palautus, tyhjyden tarkastaminen, elementin
etsiminen, taulukoksi muuttaminen, elementin lisääminen indeksillä ja ilman.
Nämä testit ovat luokassa LinkedListTest

#### Binäärikeko
Binäärikeosta testataan oleelliset julkiset metodit, jotka osaltaan käyttävät
kaikkia yksityisiä metodeja luokassa. Testissä binäärikeolle asetataan
tyypiksi Integer ja luodaan vertailija joka vertailee kokonaislukuja luonnollisesti.
Lisäämistä ja poistamista testataan testisyötteellä -inf, 3, 0, 1, inf ja sitten kokeillaan
että keko palauttaa nämä alkiot järjestyksessä -inf, 0, 1 , 3, inf.
Nämä testit ovat luokassa BinaryHeapTest.

### Algoritmit
#### A* ja BFS
Molempia algoritmeja testataan pintapuolisesti, että ne löytävät jonkin lyhimmän
polun, kun madon pää on kohdassa (5,5) ja omena kohdassa (8,8).
Integraatiotason testit suorituskykytestauksessa kuitenkin osoittavat
algoritmin toimivuuden yleisissä tapauksissa. Spesifien testisyötteiden
tekeminen algoritmille olisi erittäin työlästä.


## Suorituskykytestaus
Suorituskyky testaus suoritetaan luokassa GameEngineTest. Luokassa pelimoottorille
annetaan rakentajassa parametrina MockRenderer, joka ei tee render tai reset
kutsuilla mitään.
Luokassa suorituskykyä testataan molemmilla algoritmeilla ajamalla pelimoottoria
sata tuhatta kierrosta. Minimivaatimukset A*-algoritmille on keskimääräinen
reitinlöytämisaika 17ms ja vähintään 1400 pistetulos. 17ms perustuu siihen,
että silloin ohjelman ajaja ei huomaa merkittävää nykimistä uuden omenan
asettamisen kohdalla, koska animaatio on taajuudella 60hz.
BFS-algoritmi ei yllä tähän vaatimukseen, jolloin
sen keskimääräisen aikavaativuuden raja on 34ms eli pelikellon lähin monikerta.
BFS-algoritmin
tulee myös keskimäärin saavuttaa 1200 pisteen tulos. Huomio nämä testit
eivät välttämättä mene läpi hitailla kohdelaitteilla.


## Graafinen käyttöliittymä
Graafinen käyttöliittymä sekä rendreröinti on jätetty testauksen ulkopuolelle.
