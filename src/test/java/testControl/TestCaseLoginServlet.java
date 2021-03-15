package testControl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import control.Login;
import model.Utente;

class TestCaseLoginServlet extends Mockito{
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	
	Login servlet;
	Utente utente= new Utente();

	@BeforeEach
	void setUp() throws Exception {

		request = (HttpServletRequest) Mockito.mock(HttpServletRequest.class);
		response = (HttpServletResponse) mock(HttpServletResponse.class);
		session = (HttpSession) Mockito.mock(HttpSession.class);
		
		utente.setUsername("username");
		utente.setUsername("password");
		utente.setTipo("tipo");
		
		servlet= new Login();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testLogin1() throws ServletException, IOException {
		String user= "annafulgione@alice.it";
		String password= "anna";
		String tipo="cliente";
		when(request.getParameter("username")).thenReturn(user);
		when(request.getParameter("password")).thenReturn(password);
		when(request.getParameter("tipo")).thenReturn(tipo);
		when(request.getSession(true)).thenReturn(session);
		when(session.getAttribute("utente")).thenReturn(utente);
		servlet.doGet(request, response);
		assertNotNull(utente.getUsername());
		assertNotNull(utente.getPassword());
	}
	
	@Test
	void testLogin2() throws ServletException, IOException {
		String user= "root";
		String password= "123";
		String tipo="amministratore";
		when(request.getParameter("username")).thenReturn(user);
		when(request.getParameter("password")).thenReturn(password);
		when(request.getParameter("tipo")).thenReturn(tipo);
		when(request.getSession(true)).thenReturn(session);
		when(session.getAttribute("utente")).thenReturn(utente);
		servlet.doGet(request, response);
		assertNotNull(utente.getUsername());
		assertNotNull(utente.getPassword());
	}
	
}