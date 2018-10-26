# SnakeAI Määrittely

## Esittely
SnakeAI on projekti, jossa matopeliä pelaavaa tekoälyä vertaillaan
perustuen eri algoritmeihin. Projekti toteutetaan Java-ohjelmointikielellä.
Projektinhallintaan käytetään Maven-työkalua. Sovellukseen tulee kaksi näkymää:
Normaali pelinäkymä ja algoritmin etenemisen visualisointi.
## Algoritmit
Matopelin pelaaminen on ongelma, jossa madon tulee päästä omenan luokse
osumatta itseensä tai pelialueen seiniin.
Ongelmaa ratkaistaan alustavasti leveysuuntainen
 läpikäynti-,ja A*-
algoritmeilla. Jokainen uusi omena asetetaan kartalle satunnaisesti.
Mato toteutetaan jono-tietorakenteella, joka pohjautuu linkitettylista
implementaatioon.
## Tietorakenteet
### Linkitettylista
Sovellukseen toteutetaan tyypitetty linkitettylista joka toteuttaa oleelliset osat
java.util.list rajapintaa. Listaa käytetään yleisesti ohjelmassa kun listoja
tarvitaan. Se toimii myös madon-logiikan kannalta keskeisessä jonossa ja
BFS-algoritmissa, koska se toteuttetaan iteratiivisella mallilla.
## Binäärikeko
Sovellukseen toteutetetaan A\*-algoritmin järjestämiseen keko. Keko on
tyypitetty ja voi vertailla olioita eri parametrein, Comparator-rajapinnan
täyttävien olioiden avulla.
## Syötteet
Syötteenä ohjelmalle annetaan algoritmin valinta ja simuloinnin nopeus.
## Aika- ja tilavaativuudet
Tavoitteena olisi jokaista omenaa saavuttaa O(|V| + |E|) molemmilla algoritmeilla.
AStar tulee saavuttamaan keskimäärin kuitenkin paljon tätä parempia aikoja.

# Lisäominaisuudet (jos jää aikaa)
- Sovelluksen käyttäjä saa päättää mihin seuraava omena putoaa
## Lähteet
- [OTM kevät 2018 kurssimateriaali](https://github.com/mluukkai/otm-2018/blob/master/web/materiaali.md) M. Luukkainen 2018 Helsinki
- [Tietorakenteet ja algoritmit kurssin kalvot](https://www.cs.helsinki.fi/u/saska/tira.pdf) J. Kivinen 2017
- Introduction to algorithms, Cormen Thomas H, MIT Press cop. 2009, 3rd ed (E-kirja)
