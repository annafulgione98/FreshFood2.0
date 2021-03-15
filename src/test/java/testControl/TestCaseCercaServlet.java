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

import control.Cerca;

class TestCaseCercaServlet extends Mockito {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	Cerca servlet;
	
	
	@BeforeEach
	void setUp() throws Exception {
		request = Mockito.mock(HttpServletRequest.class);
		response = Mockito.mock(HttpServletResponse.class);
		session= Mockito.mock(HttpSession.class);
		
		servlet= new Cerca();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCerca() throws ServletException, IOException {
		String cerca = "";
		request.setAttribute(cerca, "");
		cerca = request.getParameter("cerca");
		servlet.doGet(request, response);
		assertNotEquals("", request.getAttribute("cerca"));
		
	}

}
