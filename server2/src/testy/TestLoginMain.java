package testy;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import aaa.ClientDb;

public class TestLoginMain {
	
	String login = "testCase";
	String password = "1";
	
	ClientDb test = new ClientDb();
	
	@Test
	public void testLogin() throws SQLException{
		
		System.out.println("TestLogin run...");
		try {
			
			test.init();
			test.rejestracja("cos1","cos2" , "cos3", "3456215", "login", "34-600","1", "cos8", "testCase");
			assertEquals(true, test.servletLogic(true, login, password));
		} finally{
			
			test.removeFromDataBase("cos3");
		}	
	}
}
