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
 läpikäynti-, Djikstran-, ja A*-
algoritmeilla. Jokainen uusi omena asetetaan kartalle satunnaisesti.
Mato toteutetaan jono-tietorakenteella, joka pohjautuu linkitettylista
implementaatioon. Ja Djikstraa varten toteutetaan keko-
tietorakenne. Pelitila tallennetaan taulukkoon, josta johdetaan painotettu verkko.
## Syötteet
Syötteenä ohjelmalle annetaan haluttu algoritmi, sekä pelivuorojen määrä,
jota algoritmi käyttää pelin tilan arviointiin omenan syömisen jälkeen.
## Aika- ja tilavaativuudet
Tavoitteena olisi jokaista omenaa kohden saavuttaa Djikstran-algoritmin
ominainen tila ja aikavaativuus O((E + V )log(V)). Leveysuuntainen läpikäynti
vie tietysti O(|V| + |E|).

## Lähteet
- [OTM kevät 2018 kurssimateriaali](https://github.com/mluukkai/otm-2018/blob/master/web/materiaali.md) M. Luukkainen 2018 Helsinki
- [Tietorakenteet ja algoritmit kurssin kalvot](https://www.cs.helsinki.fi/u/saska/tira.pdf) J. Kivinen 2017
- Introduction to algorithms, Cormen Thomas H, MIT Press cop. 2009, 3rd ed (E-kirja)