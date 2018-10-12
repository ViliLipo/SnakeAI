# Käyttöohje

## Esivaatimukset
  Tämä ohje olettaa, että kohdelaitteen käyttöjärjestelmä on GNU/Linux-pohjainen
  ja että siihen on asennettu seuraavat ohjelmistot: openjdk-8, openjfx, Maven2
  ja git.
## Käyttöönotto
Käyttöön otto alkaa repositoryn kloonaamisella. Mene terminaalilla hakemistoon
johon haluat ladata ohjelman ja suorita:
```bash
  git clone https://github.com/ViliLipo/SnakeAI
```
Sitten lähdekoodin saa paketoitua ajettavaksi jar-tiedostoksi seuraavilla
komennoilla.
```bash
  cd SnakeAI/snake-ai
```
Tämä on siis git-repositorion sisällä oleva maven-projektikansio. Ja sitten
```bash
  mvn package
```
Nyt jar-tiedosto löytyy kansiosta /target. Ja sen voi ajaa komennolla
```
  java -jar target/snake-ai-1.0-SNAPSHOT.jar
```
Nyt testit voi ajaa komennolla
```
  mvn test
```
Jos testikattavuuden haluaa selvittää se onnistuu näin
```
  mvn test jacoco:report
```
Nyt sitä voi tarkastella esim. näin.

```
  firefox target/site/jacoco/index.html
```
Vaihda firefox käyttämäsi selaimen komentoon, jos et sitä käytä.

## Käyttö
Pelin simuloimisen voi aloittaa painamalla RUN-painiketta.
Tämän alapuolisesta valintalaatikosta voi valita käytettävän algoritmin.
Sen alapuolella on tietoja algoritmin toiminnasta.
Pause-nappulasta voi pysäyttää simuloinnin.
Liukukytkintä säätämällä voidaan muuttaa pelin nopeutta.
