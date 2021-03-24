USE makemerun;

INSERT INTO `Klub`(`ID`,`Ime kluba`,`Adresa`,`Email`,`Broj telefona`)
VALUES ("1","ARK Gradiska","Gradiska","arkgradiska@yahoo.com","063621992");

INSERT INTO `Predsjednik kluba`(`ID`,`Ime`,`Prezime`,`JMBG`,`KLUB_ID`)
VALUES ("1","Aleksandar","Raca","2302976100220","1");

INSERT INTO `Clan kluba`(`ID`,`Ime`,`Prezime`,`Kontakt`,`Uloga`,`KLUB_ID`)
VALUES ("100","Aleksandar","Raca","066/740-213","Predsjednik kluba","1");

INSERT INTO `Racun`(`BrojRacuna`,`Banka`,`KLUB_ID`,`Stanje racuna`)
VALUES ("2423-100-434-40-00","Addiko","1","100");


