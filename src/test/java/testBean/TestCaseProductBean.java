package testBean;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ProductBean;

class TestCaseProductBean {
	ProductBean pb;
	
	
	@BeforeEach
	void setUp() throws Exception {
		pb= new ProductBean();
		pb.setCode(1);
		pb.setDescrizione("descrizione");
		pb.setImg("img");
		pb.setNome("nome");
		pb.setPrezzo(1);
		pb.setQuantita(1);
		pb.setStagione("stagione");
		pb.setTipo_prodotto("tipo");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test_get1() {
		pb.getCode();
		assertNotNull(pb.getCode());
	}
	@Test
	void test_set1() {
		pb.setCode(2);
		assertEquals(2, pb.getCode());
	}
	
	@Test
	void test_get2() {
		pb.getDescrizione();
		assertNotNull(pb.getDescrizione());
	}
	@Test
	void test_set2() {
		pb.setDescrizione("descr");
		assertEquals("descr", pb.getDescrizione());
	}
	
	@Test
	void test_get3() {
		pb.getImg();
		assertNotNull(pb.getImg());
	}
	@Test
	void test_set3() {
		pb.setImg("immagine");
		assertEquals("immagine", pb.getImg());
	}
	
	@Test
	void test_get4() {
		pb.getNome();
		assertNotNull(pb.getNome());
	}
	@Test
	void test_set4() {
		pb.setNome("nome2");
		assertEquals("nome2", pb.getNome());
	}
	
	@Test
	void test_get5() {
		pb.getPrezzo();
		assertNotNull(pb.getPrezzo());
	}
	@Test
	void test_set5() {
		pb.setPrezzo(2);
		assertEquals(2, pb.getPrezzo());
	}
	@Test
	void test_get6() {
		pb.getQuantita();
		assertNotNull(pb.getQuantita());
	}
	@Test
	void test_set6() {
		pb.setQuantita(2);
		assertEquals(2, pb.getQuantita());
	}
	
	@Test
	void test_get7() {
		pb.getStagione();
		assertNotNull(pb.getStagione());
	}
	
	@Test
	void test_set7() {
		pb.setStagione("stagio");
		assertEquals("stagio", pb.getStagione());
	}
	@Test
	void test_get8() {
		pb.getTipo_prodotto();
		assertNotNull(pb.getTipo_prodotto());
	}
	@Test
	void test_set8() {
		pb.setTipo_prodotto("tipo2");
		assertEquals("tipo2", pb.getTipo_prodotto());
	}
	
	@Test
	void test_toString() {
		String s= pb.toString();
		assertNotNull(s);
	}
	

}
