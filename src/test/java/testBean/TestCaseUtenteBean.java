package testBean;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Utente;

class TestCaseUtenteBean {
	Utente u;

	@BeforeEach
	void setUp() throws Exception {
		u= new Utente();
		u.setUsername("user");
		u.setPassword("pass");
		u.setTipo("tipo");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test_get1() {
		u.getUsername();
		assertNotNull(u.getUsername());
	}
	
	@Test
	void test_set1() {
		u.setUsername("name");
		assertEquals("name", u.getUsername());
	}
	
	@Test
	void test_get2() {
		u.getPassword();
		assertNotNull(u.getPassword());
	}

	@Test
	void test_set2() {
		u.setPassword("word");
		assertEquals("word", u.getPassword());
	}
	
	@Test
	void test_get3() {
		u.getTipo();
		assertNotNull(u.getTipo());
	}
	
	@Test
	void test_set3() {
		u.setTipo("tipo2");
		assertEquals("tipo2", u.getTipo());
	}
}

