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

import control.RegisterAccount;
import model.Utente;

class TestCaseRegistrazioneServlet extends Mockito{
	
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	
	RegisterAccount servlet;
	Utente utente= new Utente();


	@BeforeEach
	void setUp() throws Exception {
		request = (HttpServletRequest) Mockito.mock(HttpServletRequest.class);
		response = (HttpServletResponse) Mockito.mock(HttpServletResponse.class);
		session = (HttpSession) Mockito.mock(HttpSession.class);
		
		utente.setUsername("username");
		utente.setUsername("password");
		utente.setTipo("tipo");
		
		servlet= new RegisterAccount();
	}


	@Test
	void testRegistrazione() throws ServletException, IOException {
		//request.setSession(session);
		
		when(request.getSession(true)).thenReturn(session);
		when(request.getParameter("username")).thenReturn(utente.getUsername());
		when(request.getParameter("password")).thenReturn(utente.getPassword());
		when(request.getParameter("tipo")).thenReturn(utente.getTipo());
		when(session.getAttribute("utente")).thenReturn(utente);
		
		servlet.doGet(request, response);
		assertNotNull(utente.getUsername());
		assertNotNull(utente.getPassword());
	}

}