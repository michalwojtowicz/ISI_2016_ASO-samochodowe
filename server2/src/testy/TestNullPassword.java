package testy;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import aaa.ClientDb;

import java.sql.SQLException;

public class TestNullPassword {
	
	String login = "testCase";
	String password = "";
	
	ClientDb test = new ClientDb();
	
	@Test
	public void testNullPassword() throws SQLException{

		System.out.println("TestNullPassword run...");
		try {
			test.init();
			test.rejestracja("cos1","cos2" , "cos3", "3456215", "login", "34-600","1", "cos8", "testCase");
			assertEquals(true,test.servletLogic(true, login, password));
		}finally{
			
			test.removeFromDataBase("cos3");
		}			
	}

}
