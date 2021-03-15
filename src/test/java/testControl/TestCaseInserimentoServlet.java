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

import control.InsertProduct;
import model.ProductBean;

class TestCaseInserimentoServlet extends Mockito {
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	
	InsertProduct servlet;
	ProductBean prodotto= new ProductBean();
	
	
	@BeforeEach
	void setUp() throws Exception {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);
		
		prodotto.setNome("pomodoro");
		prodotto.setDescrizione("� rossissimo");
		prodotto.setImg("img");
		prodotto.setPrezzo(12);
		prodotto.setQuantita(2);
		prodotto.setStagione("estate");
		prodotto.setTipo_prodotto("ortaggio");
		
		servlet= new InsertProduct();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInserimentoProdotto() throws ServletException, IOException {
		
		//request.getSession().setAttribute("tipo", "amministratore");
		
		request.setAttribute("code", prodotto.getCode());
		request.setAttribute("nome", prodotto.getNome());
		request.setAttribute("descrizione", prodotto.getDescrizione());
		request.setAttribute("img", prodotto.getImg());
		request.setAttribute("stagione", prodotto.getStagione());
		request.setAttribute("prezzo", prodotto.getPrezzo());
		request.setAttribute("quantita", prodotto.getQuantita());
		request.setAttribute("tipoProdotto", prodotto.getTipo_prodotto());
		
		servlet.doGet(request, response);
		assertNotNull(prodotto);
		
	}

}
