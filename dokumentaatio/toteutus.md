# Toteutus
Snake-ai on toteutetty käyttäen JavaFX-canvasta ja itse kirjoitettuja
reitinhaku algoritmeja.
Luokkakaavio, sekä muu yksityiskohtainen dokumentaatio lisätään myöhemmin.
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

### A*
Toinen reitinhakumenetelmä noudattelee A\* algoritmia. Algoritmin avointa
joukkoa on mallinnettu taulukoilla sekä binäärikeolla. A\* valittiin Djikstran
sijasta sillä, kartan eli verkon jokainen ruutujen välinen kaari on yhden
painoinen jos siitä voi kulkea ja äärettömän painoinen jos siitä ei voi kulkea.
