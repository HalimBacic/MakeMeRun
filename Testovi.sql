use makemerun;

SELECT * FROM `Kontakt`;

SELECT * FROM `TRKAiAIMS`;

SELECT * FROM `Proizvodjac opreme`;

SELECT * FROM `Medijski saradnik`;

SELECT * FROM `Trkac`;
SELECT NOW();
SELECT * FROM `Izvod`;

SELECT * FROM `Ugovor`;

SELECT * FROM `Trkac` WHERE `TRKA_Naziv`="Gradiski Nocni Cener";

UPDATE `Trkac`
SET `Trkac`.`Vrijeme`="01:01:23:00"
WHERE `Trkac`.`Broj`=100;

SELECT 
		`Predsjednik kluba`.`Ime`,`Predsjednik kluba`.`Prezime`,`SPONZOR`.`Ime`,`UGOVOR`.`Broj`
FROM `Predsjednik kluba`,`SPONZOR`,`UGOVOR`,`Potpisuju`;

SELECT * FROM `Startni paket`;
SELECT * FROM `Sadrzaj paketa`;

DELETE FROM `Sadrzaj paketa`;
DELETE FROM `Startni paket`;

SELECT * FROM `Kontakt`;
DELETE FROM `Kontakt` WHERE ID="5";

INSERT INTO `Proizvodjac opreme`(`Tip proizvoda`,`Kontakt`,`Ime`,`KLUB_ID`)
VALUES ("Majice","some kontakt","Halim","1");

INSERT INTO `SADRZAJ PAKETA`(`Carape`,`Znojnica`,`Torba`,`Karte za muzej`,`Majica`,`Energetski gel`,`Rucak`,`Bon za kupovinu`,`Vrecica za patike`,`Bandana`,`Pokloni Sponzora`,`STARTNI PAKET_ID`)
VALUES("TRUE","false","false","false","true","false","false","false","false","false","true","2")
