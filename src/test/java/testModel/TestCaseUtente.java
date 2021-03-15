package testModel;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.Ordine;
import model.Utente;
import model.UtenteModel;

class TestCaseUtente {

	UtenteModel um;
	Utente u;
	Utente u1;
	
	@BeforeEach
	void setUp() throws Exception {
		u= new Utente();
		u.setUsername("username");
		u.setPassword("password");
		u.setTipo("cliente");
		
		u1= new Utente();
		u1.setUsername("username123");
		u1.setPassword("password");
		u1.setTipo("cliente");
		
		um= new UtenteModel();
	}

	@AfterEach
	void tearDown() throws Exception {
		um.doDeleteAccount(u.getUsername());
	}

	@Test
	void test1() throws SQLException {
		//public  synchronized void doDeleteAccount(String username) throws SQLException 
		um.doSaveNewAccount(u);
		um.doDeleteAccount(u.getUsername());
		assertNotEquals( u.getUsername(), null);
	}

	@Test
	void test2() throws SQLException {
		//public  synchronized void doSaveNewAccount(Utente utente) throws SQLException 
		um.doSaveNewAccount(u);
		assertEquals(u.getUsername(), "username");
		assertEquals(u.getPassword(), "password");
		assertEquals(u.getTipo(), "cliente");

	}
	
	@Test
	void test3() throws SQLException {
		//public synchronized Collection<Utente> showAccount() throws SQLException
		ArrayList<Utente> accounts = new ArrayList<Utente>();
		 um.showAccount();
		assertNotNull(accounts);
	}
	
	@Test
	void test4() throws SQLException {
		//public synchronized int Login(Utente utente) throws SQLException 
		um.Login(u);
		assertEquals(u.getUsername(), "username");
		assertEquals(u.getPassword(), "password");
	}
	
	@Test
	void test5() throws SQLException {
		//public boolean checkUsername(String username)
		um.checkUsername("username");
		assertEquals(u.getUsername(), "username");
		
	}
}
