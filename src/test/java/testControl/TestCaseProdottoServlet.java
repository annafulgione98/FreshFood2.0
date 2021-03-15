package testControl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



import control.ProductControl;
import model.Cart;
import model.ProductBean;
import model.Utente;

class TestCaseProdottoServlet extends Mockito{
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	
	
	ProductControl servlet= new ProductControl();
	ProductBean prodotto= new ProductBean();
	Cart cart;
	Utente u;
	
	@BeforeEach
	void setUp() throws Exception {
		
		request = (HttpServletRequest) Mockito.mock(HttpServletRequest.class);
		response = (HttpServletResponse) mock(HttpServletResponse.class);
		session = (HttpSession) Mockito.mock(HttpSession.class);
		
		cart= new Cart();
		prodotto.setCode(0);
		prodotto.setNome("mela");
		prodotto.setStagione("inverno");
		prodotto.setTipo_prodotto("frutta");
		prodotto.setQuantita(50);
		prodotto.setDescrizione("la mela ? rossa");
		prodotto.setPrezzo(1.5);
		prodotto.setImg("");
		
		u= new Utente();
		u.setUsername("annafulgione@alice.it");
		servlet= new ProductControl();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void test1() throws ServletException, IOException {
		String action= "deleteC";
		String id= prodotto.getCode() + "";
		String username=u.getUsername();
		when(request.getSession()).thenReturn(session);
		when(request.getAttribute("CARRELLO")).thenReturn(cart);
		when(request.getParameter("action")).thenReturn(action);
		when(request.getParameter("id")).thenReturn(id);
		when(session.getAttribute("nome")).thenReturn(username);
		servlet.doGet(request, response);
		assertNotNull(prodotto);
	}
	
	@Test
	void test2() throws ServletException, IOException {
		String action= "addC";
		String id= prodotto.getCode() + "";
		String username=u.getUsername();
		when(request.getSession()).thenReturn(session);
		when(request.getAttribute("CARRELLO")).thenReturn(cart);
		when(request.getParameter("action")).thenReturn(action);
		when(request.getParameter("id")).thenReturn(id);
		when(session.getAttribute("nome")).thenReturn(username);
		servlet.doGet(request, response);
		assertNotNull(prodotto);
	}
}

