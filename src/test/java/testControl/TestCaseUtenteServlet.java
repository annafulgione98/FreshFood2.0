package testControl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import control.ProductControl;
import control.UtenteControl;
import model.Cart;
import model.Utente;
import model.UtenteModel;

class TestCaseUtenteServlet extends Mockito {
	
	
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	Cart cart;
	ServletContext ctx;
	
	
	UtenteControl servlet= new UtenteControl();
	Utente utente;

	@BeforeEach
	void setUp() throws Exception {
		

		request = (HttpServletRequest) Mockito.mock(HttpServletRequest.class);
		response = (HttpServletResponse) Mockito.mock(HttpServletResponse.class);
		session = (HttpSession) Mockito.mock(HttpSession.class);
		ctx= (ServletContext) Mockito.mock(ServletContext.class);
		
		servlet= new UtenteControl();
		utente=new Utente();
		utente.setUsername("annafulgione@alice.it");
		utente.setPassword("anna");
		utente.setTipo("cliente");
		
		cart = new Cart();
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void test() throws ServletException, IOException {
		servlet= new UtenteControl();
		String action="deleteUser";
		String username="annafulgione@alice.it";
		when(request.getSession()).thenReturn(session);
		when(request.getAttribute("CARRELLO")).thenReturn(cart);
		when(request.getParameter("action")).thenReturn(action);
		when(request.getParameter("username")).thenReturn(username);
		servlet.doGet(request, response);
		assertNotEquals("utente", null);
	}
	
	@Test
	void test1() throws ServletException, IOException {
		String action="Cancella-Utente";
		String username="annafulgione@alice.it";
		when(request.getSession()).thenReturn(session);
		when(request.getAttribute("CARRELLO")).thenReturn(cart);
		when(request.getParameter("action")).thenReturn(action);
		
		servlet.doGet(request, response);
		assertNotEquals("utente", null);
	}
	@Test
	void test2() throws ServletException, IOException {
		String action="Log-out";
		String username="annafulgione@alice.it";
		when(request.getSession()).thenReturn(session);
		when(request.getAttribute("CARRELLO")).thenReturn(cart);
		when(request.getParameter("action")).thenReturn(action);
		
		servlet.doGet(request, response);
		assertNotEquals("utente", null);
	}
}
