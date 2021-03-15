package testModel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.Cart;
import model.ProductBean;

class TestCaseCarrello {
	Cart cart;
	ProductBean prod_bean;

	@BeforeEach
	void setUp() throws Exception {
		cart= new Cart();
		prod_bean= new ProductBean();
		prod_bean.setCode(1);
		prod_bean.setNome("nome");
		prod_bean.setTipo_prodotto("tipoprod");
		prod_bean.setStagione("stagione");
		prod_bean.setDescrizione("descrizione");
		prod_bean.setPrezzo(1);
		prod_bean.setQuantita(1);
		prod_bean.setImg("img");
	}

	@AfterEach
	void tearDown() throws Exception {
		cart.svuotaCarrello();
	}

	@Test
	void test1() {
		//	public boolean addProduct(ProductBean product,int maxQuantitaDisponibile)
		Boolean bool= cart.addProduct(prod_bean, 1);
		assertTrue(bool);
	}
	
	@Test
	void test2() {
		//public int getNumeroOggettiCarrello()
		cart.addProduct(prod_bean, 1);
		cart.getNumeroOggettiCarrello();
		assertEquals(1, cart.getNumeroOggettiCarrello());
	}
	
	@Test
	void test3() {
		//public void deleteProduct(ProductBean product) 
		cart.deleteProduct(prod_bean);
		assertEquals(0, cart.getNumeroOggettiCarrello());
	}
	
	@Test
	void test4() {
		//List<ProductBean> getProducts()
		cart.addProduct(prod_bean, 1);
		List<ProductBean> list= cart.getProducts();
		assertNotNull(list);
	}
	
	@Test
	void test5() {
		//public String toString()
		cart.toString();
		assertNotNull(cart);
	}
	
	@Test
	void test6() {
		//public double getTotale()
		cart.getTotale();
		assertNotNull(cart.getTotale());
	}
	
	@Test
	void test7() {
		//public void svuotaCarrello()
		cart.svuotaCarrello();
		assertNotEquals( " ",  cart.toString());
	}
}
