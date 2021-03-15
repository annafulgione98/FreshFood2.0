INSERT INTO negozio.utente ( username, password, tipo) 
VALUES('annafulgione@alice.it', 'anna', 'cliente'),
('martina@alice.it', 'martina', 'cliente' ),
('root', 'root', 'amministratore');

        
INSERT INTO negozio.ordine( utente, spesaTotale, dataOrdine, datiSpedizione, datiPagamento)
VALUES('annafulgione@alice.it', 2.50, '98/06/2019', 'via ciao' , 'ciaoo'  );
	
    
INSERT INTO negozio.product( nome, tipoProdotto, descrizione,stagione, prezzo, quantita,  img)
VALUES( 'Anguria', 'frutta', 'L anguria o cocomero è un frutto estivo molto gustoso.product','Estate', 3, 100, 'http://www.ortomio.it/images/specie/anguria.jpg'),
		('Melanzane',  'verdura', 'La melanzana ha la proprietà di assorbire molto bene i grassi alimentari.', 'Estate', 2, 60, 'https://www.wine-food.it/wp-content/uploads/2018/05/melanzana-wine-food-300x300.jpg'),
		('Peperone',  'Verdura', 'Il peperone  aiuta a mantenere il peso corporeo e svolge un azione diuretica e antireumatica.', 'Estate',4, 20, 'http://www.italiafruit.net/Public/Upload/News/Images/37377/peperoni-generica-w.jpg'),
        ('Limone di Sorrento',  'Frutta', 'Questo frutto presenta caratteristiche esclusive, che lo rendono famoso nel mondo', 'Estate', 6, 70, 'http://tecnichef.it/wp-content/uploads/2015/04/limone-conservazione-300x300.jpg'),
('Lattuga', 'verdura', 'La lattuga possiede la capacità di reidratare il corpo umano ed è utile per l’apparato digerente.', 'Primavera/Estate', 1, 200, 'https://www.ilsagroup.com/upload/colture/immagini/lattuga-concimi-e-biostimolanti-per-ortaggi-da-foglia.jpg'),
('Pesca', 'frutta','La polpa è dolcissima e profumata e, secondo la varietà, può essere gialla o bianca con venature rosse ', 'estate', 2, 50, 'http://www.dietando.it/wp-content/uploads/2014/06/Pesche-quali-sono-quelle-migliori-per-la-dieta.jpg');
INSERT INTO negozio.composizione(prodotto, quantita, ordine)
VALUES( 1, 4, 1);
