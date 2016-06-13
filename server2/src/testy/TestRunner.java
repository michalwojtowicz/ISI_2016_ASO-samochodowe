package testy;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	
	public static void main(String[] args) {
		
		Result result = JUnitCore.runClasses(TestNullPassword.class, TestNullLogin.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("TestNullPassword, TestNullLogin was sucessful = "+ result.wasSuccessful());

		result = JUnitCore.runClasses(TestLoginMain.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("TestLogin was sucessful = "+ result.wasSuccessful());
		
	}
}
