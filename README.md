# Evidencija projekta
Ovo je mala web aplikacija za vodjenje evidencije za razvoj softvera. Moguce je pregledati postojece zadatke sa podacima o nazivu zadatka, kome je zadatak dodijeljen, na koji sprint se odnosi zadatak i u kojoj fazi se nalazi zadatak. Zadatke je moguce dodavati, editovati i brisati. Takodje za svaki zadatak je omoguceno menjanje stanja zadatka (Novi/U toku/Zavrsen).
Zadatke je moguce pretrazivati na osnovu naziva zadatka i/ili sprinta kome taj zadatak pripada.

## Napomene
Pre pokretanje aplikacije potrebno je pokrenuti ddl.sql skriptu iz foldera sql u programu MySQL kako bi se generisala baza podataka za aplikaciju. Projekat je radjen sa MySQL verzijom 8, i dependency za tu verziju je podesen u pom.xml fajlu, ukoliko imate instaliranu drugu verziju potrebno je da editujete pom.xml fajl i da unesete zeljenu verziju. Ukoliko zelite da radite sa postojecom bazom onda u applicaion.properties podesite parametre za zeljenu bazu. 

## Pokretanje aplikacije
U komandnom prozoru otvoriti root datoteku projekta i pokrenuti komandu: <br>
<b>mvn spring-boot:run</b>
<br>
Aplikacija se moze pokrenuti i iz Eclipse IDE.

## Koristene tehnologija
<li>Baza podataka: MySQL
<li>Backend: Spring Boot
<li>Perzistencija podataka: Hibernate
<li>Frontend: AngularJS


<i><b>Ova aplikacija je napravljena u sklopu zavrsnog testa kursa Java Web Development koji organizuje FTN Informatika doo.</b></i>