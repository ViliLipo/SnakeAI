# Viikkoraportti 4

## Työvaiheet

Tällä viikkolla on keskitytty BFS algoritmin testaamiseen ja hiomiseen.
BFS-algoritmille on kirjoitettu yksikkötestit. GUI:hin lisättiin
tapa seurata tietoja algoritmin menestyksestä pelissä ja siitä kuinka
kauan algoritmillä menee aikaa reitin löytämiseen. Lisäsin myös keko-pohjaisen
rakenteen, joka voi palauttaa aina maalia lähimmän sijainnin keosta.
Tätä käytän ensiviikolla A*/Djikstra tyylisen algoritmin implementoimiseen.

## Mihin haasteihin törmäsin?
Tällä viikolla haasteita on aiheuttanut madon hengissä pitäminen.
On ollut vaikeaa yhdistää algoritmiiin tapa validoida reittejä sen mukaan,
että selviääkö mato riittävän pitkään hengissä omenan syömisen jälkeen.
Mato voi nimittäin ajautua umpikujaan. Ongelman tämän hetkiset ratkaisut
ovat aikavaativuudeltaan eksponentiaalisia suhteessa selvittävien vuorojen
pituuteen ja täten epäkelpoja käytettäväksi reaaliajassa.

## Mitä teen seuraavaksi?
Seuraavaksi jatkan A*-tyylisen algoritin implementoimista, jonka
tulisi löytää reitit nopeammin kuin BFS-pohjaisen algoritmin.

### Käytin tällä viikolla harjoitustyöhön aikaa 6 tuntia.
Ensi viikolla siirrän työskentelyn painopistettä muilta kursseista tähän
työhön ja toivottavasti etenen nopeammin.
