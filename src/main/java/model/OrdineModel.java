package model;
import control.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Classe che modella le operazioni che ? possibile effettuare sugli ordini

 */
@SuppressWarnings("unused")
public class OrdineModel {

	//variabili di istanza
	private static DataSource ds;
	private static final int SPESE_SPEDIZIONE = 10;


	//inizializzazione
	/*static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/negozio");

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	 */

	//metodi
	/**
	 * Restituisce una collezione di ordini che rappresentano gli ordini effettuati da un utente.
	 * */
	@SuppressWarnings("static-access")
	public synchronized Collection<Ordine> mostraOrdiniEffettuati(String username ) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Ordine> listaOrdini = new ArrayList<Ordine>();

		String ordiniUtenteSQL = "SELECT * FROM ORDINE WHERE utente = ? AND ordine.codice <> ? order by codice";

		try {
			connection = DriverMaagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(ordiniUtenteSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, username);

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				Ordine ordine = new Ordine();
				ordine.setCodiceOrdine(rs.getInt(1));
				ordine.setUtente(rs.getString(2));
				ordine.setSpesaTotale(rs.getDouble(3));
				ordine.setDataOrdine(rs.getString(4));
				ordine.setDatiSpedizione(rs.getString(5));
				ordine.setDatiPagamento(rs.getString(6));
				listaOrdini.add(ordine);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}

		String selectSQL = "SELECT * FROM ORDINE JOIN composizione JOIN product ON " 
				+ " composizione.utente = utente.username AND  product.code = composizione.prodotto" +
				" WHERE utente = ? AND ordine.utente <> ? order by ordine ";						

		try {
			connection = DriverMaagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, username);

			ResultSet rs = preparedStatement.executeQuery();		
			connection.commit();

			while(rs.next()){
				int codiceOrdine = rs.getInt(1);

				ProductBean p = new ProductBean();
				p.setCode(rs.getInt(10));
				p.setNome(rs.getString(11));
				p.setPrezzo(rs.getDouble(12));
				p.setStagione(rs.getString(14));
				p.setDescrizione(rs.getString(15));
				p.setImg(rs.getString(16));
				p.setQuantita(rs.getInt(9));
				p.setTipo_prodotto(rs.getString(18));

				for(int i=0 ; i < listaOrdini.size() ; i++){
					if(listaOrdini.get(i).getCodiceOrdine() == codiceOrdine){
						listaOrdini.get(i).aggiungiProdotto(p);
						break;
					}
				}
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
			
		}
		/*Lista di tutti gli  ordinii*/
		return listaOrdini;
	}

	/**
	 * Finalizza l'ordine, salvando un nuovo ordine sul database, legandolo all'utente,
	 * inoltre aggiorna le quantita di oggetti presenti nel database dopo l'ordine
	 */
	public synchronized void piazzaOrdine(String utente,Cart cart,String datiSpedizione,String datiPagamento){
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String getNuovoCodiceOrdineSQL = "SELECT codice,utente from ORDINE";

		try {
			connection = DriverMaagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(getNuovoCodiceOrdineSQL);

			ResultSet rs = preparedStatement.executeQuery();
			
			connection.commit();
			int idNuovoOrdine = 0;

			while (rs.next()) {
				String stringaCodice = rs.getString(1);
				String utente1 = rs.getString(2);
				if(stringaCodice.equals(utente1))
					continue;
				else{
					int id = Integer.parseInt(stringaCodice);
					if(id > idNuovoOrdine)
						idNuovoOrdine = id;
				}
			}
			idNuovoOrdine++;	//ho ottenuto un ID per un nuovo ordine in modo da non provocare conflitti

			//salvo nella tabella ORDINE il nuovo ordine
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String dataOdierna = sdf.format(new GregorianCalendar().getTime());
			double totale = cart.getTotale() + SPESE_SPEDIZIONE;

			String salvaOrdineSulDBSQL = "INSERT INTO ORDINE VALUES (?,?,?,?,?,?);\n";

			preparedStatement = connection.prepareStatement(salvaOrdineSulDBSQL);
			preparedStatement.setInt(1, idNuovoOrdine);
			preparedStatement.setString(2, utente);
			preparedStatement.setDouble(3, totale);
			preparedStatement.setString(4, dataOdierna);
			preparedStatement.setString(5, datiSpedizione);
			preparedStatement.setString(6, datiPagamento);
			preparedStatement.executeUpdate();

			//salvo nella tabella composizione ogni nuova relazione tra l'ordine appena inserito e i prodotti che lo compongono
			ArrayList<ProductBean> listaProdotti = new ArrayList<ProductBean>(cart.getProducts());
			String salvaProdottiSQL = "INSERT INTO composizione VALUES ";
			for(int i=0 ; i < listaProdotti.size() ; i++){
				salvaProdottiSQL = salvaProdottiSQL + "(" + listaProdotti.get(i).getCode()
						+ ",'" + idNuovoOrdine + "'," + listaProdotti.get(i).getQuantita() + "), ";
			}
			salvaProdottiSQL = salvaProdottiSQL.substring(0, salvaProdottiSQL.length()-2) + ";\n";

			preparedStatement = connection.prepareStatement(salvaProdottiSQL);
			preparedStatement.executeUpdate();
			//connection.commit();

			//svuola il carrello sul database
			String elminaOrdiniTemporanei = "DELETE FROM composizione WHERE utente = ?;";

			preparedStatement = connection.prepareStatement(elminaOrdiniTemporanei);
			preparedStatement.setString(1, utente);
			preparedStatement.executeUpdate();

			//modifica le quantita dei prodotti sul database (in magazzino).
			String getCodiceQuantitaProdottiSQL = "SELECT code,quantita FROM product";
			preparedStatement = connection.prepareStatement(getCodiceQuantitaProdottiSQL);
			rs = preparedStatement.executeQuery();

			while(rs.next() && listaProdotti.size() > 0){
				int codiceProdotto = rs.getInt(1);
				int quantita = rs.getInt(2);

				for(int i=0 ; i < listaProdotti.size() ; i++){
					if(listaProdotti.get(i).getCode() == codiceProdotto){
						quantita = quantita - listaProdotti.get(i).getQuantita();
						String modificaQuantitaProdotto = "UPDATE prodotto SET quantita = ? WHERE code = ?;";

						preparedStatement = connection.prepareStatement(modificaQuantitaProdotto);
						preparedStatement.setInt(1, quantita);
						preparedStatement.setInt(2, codiceProdotto);
						preparedStatement.executeUpdate();
						listaProdotti.remove(i);
						if(listaProdotti.size() == 0)
							break;
					}
				}
			}

		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 * In fase di login dell'utente, carica il carrello salvato sul database, 
	 * in questo modo l'utente pu? continuare ad acquistare dal punto in cui si era fermato
	 */
	public synchronized Cart caricaCarrelloDB(Cart cart,String nomeUtente){
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try{	//elimina il contenuto esistente del carrello dal database
			connection = DriverMaagerConnectionPool.getConnection();

			//devo verificare che la merce sia ancora disponibile quando eseguo il login
			String verificaQuantitaDisponibileSQL = "SELECT composizione.quantita, product.quantita, code "+
					" FROM composizione join product on composizione.prodotto = product.code where utente = ?";

			preparedStatement = connection.prepareStatement(verificaQuantitaDisponibileSQL);
			preparedStatement.setString(1, nomeUtente);
			ResultSet rs2 = preparedStatement.executeQuery();

			while(rs2.next()){
				int qta_carrello = rs2.getInt(1);
				int qta_magazzino = rs2.getInt(2);
				int codiceProdotto = rs2.getInt(3);

				if(qta_magazzino == 0){	//devo eliminare tale prodotto dal carrello
					String eliminaProdottoDalCarrello = "DELETE FROM composizione WHERE prodotto = ? " ;
					preparedStatement = connection.prepareStatement(eliminaProdottoDalCarrello);
					preparedStatement.setInt(1, codiceProdotto);
					preparedStatement.executeUpdate();	
				}
				else if(qta_magazzino < qta_carrello){	//devo modificare la quantita di prodotto nel carrello
					String modQTAProdottoCarrello = "UPDATE composizione SET quantita = ? WHERE utente = ? && prodotto = ?";

					preparedStatement = connection.prepareStatement(modQTAProdottoCarrello);
					preparedStatement.setInt(1 , qta_magazzino);
					preparedStatement.setString(2, nomeUtente);
					preparedStatement.setInt(3, codiceProdotto);
					preparedStatement.executeUpdate();	
				}

			}

			String caricaCarrelloSQL = "SELECT * FROM composizione join product on composizione.prodotto = product.code where utente = ?";

			preparedStatement = connection.prepareStatement(caricaCarrelloSQL);
			preparedStatement.setString(1, nomeUtente);
			ResultSet rs = preparedStatement.executeQuery();

			ProductModel model = new ProductModelDS();

			ArrayList<ProductBean> allProducts = new ArrayList<ProductBean>(model.doRetrieveAll(""));

			while(rs.next()){
				int codiceProdotto = rs.getInt(1);
				int quantita = rs.getInt(2);

				//cerca il prodotto tra quelli in lista nel database e lo aggiunge al carrello
				for(int i=0 ; i < allProducts.size() ; i++){
					ProductBean p = allProducts.get(i);

					if(p.getCode() == codiceProdotto){
						p.setQuantita(quantita);
						cart.addProduct(allProducts.get(i),allProducts.get(i).getQuantita());
					}
				}
			}	
		}catch(SQLException e){
			e.printStackTrace();
		}
		return cart;
	}
	/**
	 * Ad ogni inserimento o cancellazione di un prodotto dal carrello lo salva temporaneamente sul database
	 */
	public synchronized void aggiornaCarrelloDB(Cart cart,String nomeUtente){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<ProductBean> listaProdotti = (ArrayList<ProductBean>) cart.getProducts();

		try{	//elimina il contenuto esistente del carrello dal database
			String svuotaCarrelloSQL = "DELETE FROM composizione WHERE utente = ?";
			connection = DriverMaagerConnectionPool.getConnection();	

			preparedStatement = connection.prepareStatement(svuotaCarrelloSQL);
			preparedStatement.setString(1, nomeUtente);
			preparedStatement.executeUpdate();
			//connection.commit();		
			for(int i=0 ; i < listaProdotti.size() ; i++){
				String inserimento = "INSERT INTO composizione values (?,?,?)"; 

				preparedStatement = connection.prepareStatement(inserimento);
				preparedStatement.setInt(1, listaProdotti.get(i).getCode());
				preparedStatement.setInt(2, listaProdotti.get(i).getQuantita());
				preparedStatement.setString(3, nomeUtente );

				preparedStatement.executeUpdate();
				connection.commit();
			}

		}catch(SQLException e){
			e.printStackTrace();
		}


	}//chiude metodo aggiornaCarrello
	/**
	 * Cerca all'interno del database un ordine con uno specifico codice
	 * @param codiceOrdine il codice dell'ordine da cercare
	 * @return l'ordine trovato
	 */
	public Ordine getOrdineByCodice(int codiceOrdine){
		Ordine mioOrdine = new Ordine();
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try{	//elimina il contenuto esistente del carrello dal database
			String getOrdineSQL = "SELECT * from ORDINE where codice = ?";
			connection = DriverMaagerConnectionPool.getConnection();	

			preparedStatement = connection.prepareStatement(getOrdineSQL);
			preparedStatement.setInt(1, codiceOrdine);
			ResultSet rs = preparedStatement.executeQuery();	
			while(rs.next()){

				mioOrdine.setUtente(rs.getString(2));
				mioOrdine.setSpesaTotale(rs.getDouble(3));
				mioOrdine.setDataOrdine(rs.getString(4));
				mioOrdine.setDatiSpedizione(rs.getString(5));
				mioOrdine.setDatiPagamento(rs.getString(6));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mioOrdine;

	}

}
