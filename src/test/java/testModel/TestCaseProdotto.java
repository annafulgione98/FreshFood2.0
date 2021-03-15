package testModel;

import static org.junit.jupiter.api.Assertions.*;  

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ProductBean;
import model.ProductModelDS;


class TestCaseProdotto  {

	ProductModelDS prodotto;
	ProductBean prod_bean;
	
	@BeforeEach
	void setUp() throws Exception {
		
		prod_bean= new ProductBean();
		prod_bean.setNome("mela");
		prod_bean.setTipo_prodotto("frutta");
		prod_bean.setStagione("inverno");
		prod_bean.setDescrizione("La mela � rossa");
		prod_bean.setPrezzo(1);
		prod_bean.setQuantita(100);
		prod_bean.setImg("https://www.mr-loto.it/wp-content/uploads/2018/12/mele-rosse.jpg");
	
		prodotto= new ProductModelDS();
	}

	@After
	void tearDown() throws SQLException {
		int code = prod_bean.getCode();
		prodotto.doDelete(code);
	}
	
	@Test
	void test1() throws SQLException {	
		//public synchronized void doSave(ProductBean product)
		prodotto.doSave(prod_bean);
		assertEquals(0, prod_bean.getCode());
	}
	
	@Test
	void test2() throws SQLException{
		boolean prod =prodotto.doDelete(prod_bean.getCode());
		assertEquals(true,prod);
	}
	
	@Test
	void test3() throws SQLException {
	//	public synchronized Collection<ProductBean>  doRetrieveByProd(String tipo) throws SQLException
		prodotto.doRetrieveByProd(prod_bean.getTipo_prodotto());
		assertEquals("frutta", prod_bean.getTipo_prodotto());
	}

	@Test
	void test4() throws SQLException {
		//public synchronized ArrayList<ProductBean> doRetrieveAll2() throws SQLException 
		prodotto.doRetrieveAll2();
		ArrayList<ProductBean> list= prodotto.doRetrieveAll2();
		assertNotNull(list);  
	}
	
	@Test
	void test5() throws SQLException {
	//	public synchronized ArrayList<ProductBean> cercaPerTipo(String tipo) throws SQLException
		
		ArrayList<ProductBean> list= prodotto.cercaPerTipo(prod_bean.getTipo_prodotto());
		list.add(prod_bean);
		assertNotNull(list);
	}
	
	@Test
	void test6() throws SQLException {
	// public synchronized boolean doDelete(int code) throws SQLException 
		boolean b;
		b= prodotto.doDelete(prod_bean.getCode());
		assertNotEquals((double) prod_bean.getCode(), null);
		assertTrue(b);
	}
	
	
	@Test
	void test7() throws SQLException {
	//	public synchronized Collection<ProductBean> doRetrieveAll(String order) throws SQLException
		String ordine="";
		Collection<ProductBean> list= prodotto.doRetrieveAll(ordine);
		assertNotEquals(" ", list);
	}
	
	@Test
	void test8() throws SQLException {
	//	public synchronized int getNewCode()
		int c= prodotto.getNewCode();
		assertNotNull(c);
	}
	
	@Test
	void test9() throws SQLException {
	//	public  synchronized void AddProduct(ProductBean bean) throws SQLException
		prodotto.AddProduct(prod_bean);
		assertNotNull(prod_bean);
	}
}

