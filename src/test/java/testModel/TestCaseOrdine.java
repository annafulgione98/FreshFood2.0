package testModel;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.Cart;
import model.Ordine;
import model.OrdineModel;
import model.ProductBean;
import model.ProductModelDS;
import model.Utente;
import model.UtenteModel;
import model.ProductBean;
import model.ProductModelDS;

class TestCaseOrdine  {
	Cart cart;
	Ordine ordine;
	OrdineModel ord_model;
	ProductModelDS prodotto;
	ProductBean prod_bean;
	Utente u;
	UtenteModel um;

	@BeforeEach
	void setUp() throws Exception {
		
		cart=new Cart();
		prod_bean= new ProductBean();
		prod_bean.setNome("nome");
		prod_bean.setTipo_prodotto("tipoprod");
		prod_bean.setStagione("stagione");
		prod_bean.setDescrizione("descrizione");
		prod_bean.setPrezzo(1);
		prod_bean.setQuantita(1);
		prod_bean.setImg("img");
		prodotto= new ProductModelDS();
		ArrayList<ProductBean> prodotti = new ArrayList<ProductBean>();
	
		u= new Utente();
		u.setUsername("username");
		u.setPassword("pass");
		u.setTipo("cliente");
		
		um= new UtenteModel();
		
		ordine= new Ordine();
		ordine.setCodiceOrdine(0);
		ordine.setUtente("username");
		ordine.setProdottiOrdine(prodotti);
		ordine.setCodiceOrdine(1);
		ordine.setDataOrdine("dataOrdine");
		ordine.setDatiPagamento("datiPagamento");
		ordine.setDatiSpedizione("datiSpedizione");
		ordine.setSpesaTotale(33.2);
		
		ord_model= new OrdineModel();
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void test2() throws SQLException {
		//public synchronized void piazzaOrdine(String username,Cart cart,String infoSpedizione,String infoPagamento)
		ord_model.piazzaOrdine(ordine.getUtente(),cart, ordine.getDatiSpedizione(), ordine.getDatiPagamento());
		assertNotNull(ordine);
		
	}
	
	@Test
	void test3() throws SQLException {
		//public synchronized Cart caricaCarrelloDB(Cart cart,String nomeUtente)
		ord_model.caricaCarrelloDB(cart, u.getUsername());
		assertNotNull(ordine);
		
	}
	
	@Test
	void test4() throws SQLException {
		//public synchronized void aggiornaCarrelloDB(Cart cart,String nomeUtente)
		ord_model.aggiornaCarrelloDB(cart, u.getUsername());
		assertNotEquals( " ",  cart.toString());
		assertEquals("username", ordine.getUtente());
	}
	
	@Test
	void test5() throws SQLException {
		//public Ordine getOrdineByCodice(int codiceOrdine)
		ord_model.getOrdineByCodice(ordine.getCodiceOrdine());
		assertEquals(0, Ordine.getCodiceOrdine());
	}

}
