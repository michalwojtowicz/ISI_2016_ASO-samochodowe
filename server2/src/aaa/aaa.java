package aaa;
//Gruby test
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.activation.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mysql.jdbc.Connection;
import com.sun.xml.internal.bind.CycleRecoverable.Context;

/**
 * Servlet implementation class aaa
 */
/*lalala
 
 */
public class aaa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	String mesage;
	ServerBaza a;
	DataSource dataSource;
    public aaa() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() {
    	//connect
    	    String baza = "jdbc:mysql://127.0.0.1:3306/m4u";
    		 Context initContext;
			try {
				initContext = (Context) new InitialContext();
				Context envContext  = (Context)((InitialContext) initContext).lookup("java:/comp/env");
	             dataSource = (DataSource)((InitialContext) envContext).lookup("jdbc/jdbcConect");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
		
    }
    
    public void destroy() {
    	//disconnect
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		if(idStr == null) {
			return;
		}
		PrintWriter out = response.getWriter();
		String S = idStr;
		int id = 0;
		Connection connection;
		try {
			connection = (Connection) ((Statement) dataSource).getConnection();
			 a = new ServerBaza(connection);
		     
		} catch (SQLException e) {
			out.println("dupa");
		}
      try {
		S =  a.login("dupa7","dupa6");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		response.setContentType("text/json");
		out.println(getStudentInfo(id,S));
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject getStudentInfo(int id,String S) {
		JSONObject obj = new JSONObject();
		
		if(id == 0) {
			obj.put("imie", S);
			obj.put("nazwisko", "Sikora");
			JSONArray array = new JSONArray();
			array.add(5);
			array.add(4);
			array.add(5);
			obj.put("oceny", array);
		} else {
			obj.put("imie", "Pindel");
			obj.put("nazwisko", "Pindel");
			JSONArray array = new JSONArray();
			array.add(2);
			array.add(2);
			array.add(2);
			obj.put("oceny", array);
		}
		
		return obj;
	}

}
