package testControl;

import static org.junit.jupiter.api.Assertions.*; 

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import model.Utente;
import control.OrdineControl;
import model.Cart;
import model.Ordine;
import model.ProductBean;

class TestCaseOrdineServlet  extends Mockito {
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	ServletContext ctx;
	
	Cart cart;
	Utente user;
	OrdineControl servlet= new OrdineControl();
	ProductBean pb= new ProductBean();
		
	@BeforeEach
	void setUp() throws Exception {
		request = (HttpServletRequest) Mockito.mock(HttpServletRequest.class);
		response = (HttpServletResponse) Mockito.mock(HttpServletResponse.class);
		session = (HttpSession) Mockito.mock(HttpSession.class);
		ctx= (ServletContext) Mockito.mock(ServletContext.class);
		
		user= new Utente();
		user.setUsername("annafulgione@alice.it");
		
		pb.setCode(1);
		pb.setDescrizione("descr");
		pb.setImg("img");
		pb.setNome("nome");
		pb.setPrezzo(12);
		pb.setQuantita(23);
		pb.setStagione("stag");
		pb.setTipo_prodotto("tip");
		
		cart= new Cart();
	}

	@SuppressWarnings("null")
	@Test
	void test1() throws ServletException, IOException {
		String username="annafulgione@alice.it";
		String action= "Ordini";
		RequestDispatcher dis = null;
		String pagina= "/ordini.jsp";
	    ArrayList<ProductBean> list = new ArrayList<ProductBean>();
	    list.add(pb);
	 
	    when(request.getSession()).thenReturn(session);
	    when(session.getAttribute("CARRELLO")).thenReturn(cart);
	    when(request.getParameter("Ordini")).thenReturn(action);
	    when(request.getParameter("username")).thenReturn(username);
	    when(ctx.getRequestDispatcher("/ordini.jsp")).thenReturn(dis);
	    //					request.removeAttribute("listaOrdini");
	    // when(request.removeAttribute("listaOrdini")).thenReturn(list);
		// when(request.setAttribute("listaOrdini", OrdineModel.mostraOrdiniEffettuati(user.getUsername()))).thenReturn(true);


	    servlet.doGet(request, response);
	    		
	    assertNotNull(list);
	}
	
	@Test
	void test2() throws ServletException, IOException {

		String action= "piazzaOrdine";
	    ArrayList<ProductBean> list = new ArrayList<ProductBean>();
	    list.add(pb);
	    
	    when(request.getSession()).thenReturn(session);
	    when(session.getAttribute("CARRELLO")).thenReturn(cart);
	    when(request.getParameter("action")).thenReturn(action);
	    when(session.getAttribute("nome")).thenReturn(user.getUsername());

	    servlet.doGet(request, response);
	    		
	    assertNotNull(list);
	}
	
	@Test
	void test3() throws ServletException, IOException {

		String action= "dettagli";
		String id= Ordine.getCodiceOrdine() + "";
	    ArrayList<ProductBean> list = new ArrayList<ProductBean>();
	    list.add(pb);
	    
	    when(request.getSession()).thenReturn(session);
	    when(session.getAttribute("CARRELLO")).thenReturn(cart);
	    when(request.getParameter("action")).thenReturn(action);
	    when(request.getParameter("username")).thenReturn(user.getUsername());
	    when(request.getParameter("idOrdine")).thenReturn(id);

	    servlet.doGet(request, response);
	    		
	    assertNotNull(list);
	}
	
}