# Testausdokumentti

Ohjelmiston yksikkötestit suoritetaan JUnit kirjaston avulla. Ne
voi suorittaa komennolla

```
mvn test
```
Myös integraatiotason testejä suoritetaan JUnitilla.
Ohjelman suorituskykyä voi valvoa sen graafisesta käyttöliittymästä.

Testikattavuuden visualisointi on saatavilla komenolla
```
  mvn test jacoco:report
```
Tällä myös selviää mitä on testattu.
Kun käyttöliittymä jätetään pois testikattavuudesta on projektin testien
rivikattavuus 93% ja haarauma kattavuus 87%.

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

|Testitapaus| Tulos| Huomioita|
|:---------| :-: | :---------:|
|Keko vastaanottaa alkiota| 1| Syöte:6|
|Alkiot palautuvat oikeassa järjestyksessä| 1| syöte:[-inf,3,0,1,inf]|
|Keko kasvattaa itseään|1| syöte:[0,1000]|
### Algoritmit
#### A* ja BFS
Molempia algoritmeja testataan pintapuolisesti, että ne löytävät jonkin lyhimmän
polun, kun madon pää on kohdassa (5,5) ja omena kohdassa (8,8).
Näitä algoritmeja testataan vielä monimutkaisemmalla syötteellä, jolla varmistetaan
ettei algoritmit törmää matoon ilman syytä. Tähän käytetään apuna
DeterministicApple-luokkaa, jolla voidaan asettaa aina samat omenat.
Integraatiotason testit suorituskykytestauksessa kuitenkin osoittavat
algoritmin toimivuuden yleisissä tapauksissa.

|Testitapaus| Tulos| Huomioita|
|:---------| :-: | :---------:|
|BFS Lyhin reitti löytyy| 1| syöte omena:(8,8)|
|A* Lyhin reitti löytyy| 1| syöte omena:(8,8)|
|BFS Ei törmäyksiä ja kaikki omenat löytyy|1| syöte omenat:([5-10],6), (10,[5=20]), (12,22), (7,21)|
|A* Ei törmäyksiä ja kaikki omenat löytyy|1| syöte omenat:([5-10],6), (10,[5=20]), (12,22), (7,21)|



## Suorituskykytestaus
Suorituskyky testaus suoritetaan luokassa GameEngineTest. Luokassa pelimoottorille
annetaan rakentajassa parametrina MockRenderer, joka ei tee render tai reset
kutsuilla mitään.
Luokassa suorituskykyä testataan molemmilla algoritmeilla ajamalla pelimoottoria
sata tuhatta kierrosta. Minimivaatimukset A\*-algoritmille on keskimääräinen
reitinlöytämisaika 17ms ja vähintään 1200 pistetulos. 1200 perustuu
siihen että, hieman virheellisesti toimivat algoritmit tuottavat usein 800-1000
pisteen tuloksia. Esim Jos A\* ei järjestä matoja uudestaan löysäämis tilanteessa
se ei läpäise 1200 pisterajaa, koska mato kuolee usein ilman syytä.
17ms perustuu siihen,
että silloin ohjelman ajaja ei huomaa merkittävää nykimistä uuden omenan
asettamisen kohdalla, koska animaatio on taajuudella 60hz.
BFS-algoritmi ei yllä tähän vaatimukseen, jolloin
sen keskimääräisen aikavaativuuden raja on 34ms eli pelikellon lähin monikerta.
BFS-algoritmin
tulee myös keskimäärin saavuttaa 1200 pisteen tulos. Huomio nämä testit
eivät välttämättä mene läpi hitailla kohdelaitteilla.
Seuraavissa testeissä suoritusta ei rajoita mikään pelikello vaan
pelin toimintaa ajetaan laitteiston maksiminopeudella. Reitin löytämiseen
käytettävä aika mitataan Controller-luokissa. Siihen sisältyy algoritmin
suorittaminen sekä polun käsittely
käännösmuotoon. Testaukseen kuluu aikaa laitteesta riippuen 1-5min.

|Testitapaus| Tulos| Huomioita|
|:---------| :-: | :---------:|
|A* aika alle 17ms| 1 | Suorittimella i7-6700 menee 2.5ms, syöte satunnainen|
|BFS aika alle 34ms| 1 | Suorittimella i7-6700 menee 13.5ms, syöte satunnainen|
| A*-tulos yli 1200| 1| Syöte satunnainen, 100 000 pelisykliä, tulos:1958|
|BFS-tulos yli 1200| 1 | Syöte satunnainen, 100 000 pelisykliä, tulos:2179|

Testauksen tavoitteena oli osoittaa, että A\* on keskimääräisessä tapauksessa
huomattavasti BFS-algoritmia nopeampi. Testaus osoittaa tämän, sillä
A\*:n viemä aika on vain 20% BFS:n keskimäärin viemästä ajasta.

Onnistumisen visualisointi


## Graafinen käyttöliittymä
Graafinen käyttöliittymä sekä rendreröinti on jätetty yksikkötestauksen ulkopuolelle.
Järjestelmätestaus suoritettiin käsin seuraavalla testipatteristolla. 1 onnistui
0 epäonnistui.
## Järjestelmätestaus
|Testitapaus| Tulos| Huomioita|
|:---------| :-:| :--------:|
|Suoritus käynnistyy run-painikkeella|1||
|Algoritmin vaihtaminen toimii| 1 ||
|Ajotiedot näkyvät ja päivittyvät|1 | |
| Pause toimii| 1 | |
| Nopeus-slider toimii| 1 | Toimii vain klikkauksella eikä raahamalla|
|Renderöinnissä ei ole artifakteja| 1| Vertical sync aiheuttaa ajoittain hidastumista|
Ohjelmassa on vähän toimintoja ja niiden testaamiseen ei tarvita monia testejä.
