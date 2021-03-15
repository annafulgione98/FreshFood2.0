CREATE SCHEMA negozio;
USE negozio;
CREATE TABLE utente (
username VARCHAR(45) PRIMARY KEY,
password VARCHAR(45) NOT NULL,
tipo VARCHAR(45) 
);

CREATE TABLE ordine(
codice  int auto_increment,
utente VARCHAR(45) NOT NULL,
spesaTotale double NOT NULL,
dataOrdine VARCHAR(45) NOT NULL,
datiSpedizione varchar(1000) NOT NULL,
datiPagamento varchar(1000) NOT NULL, 
PRIMARY KEY (codice), 
FOREIGN KEY (utente) REFERENCES utente (username)
);
CREATE TABLE product(
code int  AUTO_INCREMENT,
nome VARCHAR(45) NOT NULL,
tipoProdotto VARCHAR(45) NOT NULL,
stagione VARCHAR(45) NOT NULL,
descrizione VARCHAR(1000) NOT NULL,
quantita int NOT NULL,
prezzo int NOT NULL,
img varchar(150) NOT NULL,

PRIMARY KEY (code)
);
 CREATE TABLE composizione(
 prodotto int NOT NULL, 
 quantita int NOT NULL, 
 ordine int NOT NULL,
 PRIMARY KEY (prodotto),
 FOREIGN KEY (prodotto) REFERENCES product (code),
 FOREIGN key (ordine) REFERENCES ordine (codice)
 );
