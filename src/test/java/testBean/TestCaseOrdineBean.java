package testBean;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Ordine;
import model.ProductBean;

class TestCaseOrdineBean {
	Ordine o;
	
	@BeforeEach
	void setUp() throws Exception {
		ArrayList<ProductBean> list= new ArrayList<ProductBean>();
		
		o= new Ordine();
		o.setCodiceOrdine(1);
		o.setDataOrdine("data");
		o.setDatiPagamento("datipa");
		o.setDatiSpedizione("datisp");
		o.setProdottiOrdine(list);
		o.setSpesaTotale(1);
		o.setUtente("user");
	}

	@Test
	void test_get1() {
		o.getCodiceOrdine();
		assertNotNull(o.getCodiceOrdine());
	}
	
	@Test
	void test_set1() {
		o.setCodiceOrdine(2);
		assertEquals(2, o.getCodiceOrdine());
	}
	
	@Test
	void test_get2() {
		o.getDataOrdine();
		assertNotNull(o.getDataOrdine());
	}
	
	@Test
	void test_set2() {
		o.setDataOrdine("data2");
		assertEquals("data2", o.getDataOrdine());
	}
	
	
	@Test
	void test_get3() {
		o.getDatiPagamento();
		assertNotNull(o.getDatiPagamento());
	}
	
	@Test
	void test_set3() {
		o.setDatiPagamento("dati");
		assertEquals("dati", o.getDatiPagamento());
	}
	
	
	@Test
	void test_get4() {
		o.getDatiSpedizione();
		assertNotNull(o.getDatiSpedizione());
	}
	
	@Test
	void test_set4() {
		o.setDatiSpedizione("datis");
		assertEquals("datis", o.getDatiSpedizione());
	}
	
	
	@Test
	void test_get5() {
		o.getProdottiOrdine();
		assertNotNull(o.getProdottiOrdine());
	}
	
	@Test
	void test_set5() {
		ArrayList<ProductBean> l= new ArrayList<ProductBean>();
		o.setProdottiOrdine(l);
		assertEquals(l, o.getProdottiOrdine());
	}
	
	
	@Test
	void test_get6() {
		o.getSpesaTotale();
		assertNotNull(o.getSpesaTotale());
	}
	
	@Test
	void test_set6() {
		o.setSpesaTotale(2);
		assertEquals(2, o.getSpesaTotale());
	}
	
	
	@Test
	void test_get7() {
		o.getUtente();
		assertNotNull(o.getUtente());
	}
	
	@Test
	void test_set7() {
		o.setUtente("u");
		assertEquals("u", o.getUtente());
	}
	
	@Test
	void test_toString() {
		String s= o.toString();
		assertNotNull(s);
	}
	

}
