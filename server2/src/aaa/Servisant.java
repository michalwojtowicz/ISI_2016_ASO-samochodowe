package aaa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.mysql.jdbc.Connection;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * Servlet implementation class Servisent
 */
public class Servisant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Connection con;
	ServerBaza bazaDanych;
	Cars car;

	private String s;
    public Servisant() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(){
	String baza = "jdbc:mysql://127.0.0.1:3306/serwis_aso_m4u";
	System.err.println("tworze sie");
	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = (Connection) DriverManager.getConnection(baza,"root","Haslo123");
		bazaDanych = new ServerBaza(con);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("sesja");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		String id;
		id = (String) request.getParameter("id");
		System.err.println("sesja" + id);
		
		System.err.println(id);
		if(session == null){
			return;
		}
		System.err.println("sesja2");
		
		
		String Nazwa = (String) request.getParameter("serwis");
		String miejsce = (String) session.getAttribute("miejsce");
		String kodP = (String) session.getAttribute("kodp");
		String ntel = (String) session.getAttribute("ntel");
		String email = (String) session.getAttribute("user");
		String PESEL = (String) session.getAttribute("PESEL");
		
		System.err.println(id);
		if(id.equals("1")){
			System.err.println("sesja3");
			session.setMaxInactiveInterval(2 * 60);
			if(session.getAttribute("status").equals("1")){
				try {
					System.err.println("sesja4");
					bazaDanych.createSerwis(Nazwa, miejsce, kodP, ntel, email);
					String idS = bazaDanych.getconectSerwi(Nazwa, email);
					bazaDanych.conect(PESEL, idS);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				out.println("brak uprawnien");
			}
		}else if(id.equals("2")){
			try {
				String ids = bazaDanych.getIDser((String) session.getAttribute("PESEL"));
				String text = bazaDanych.naprawy(ids);
				out.println(text);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(id.equals("3")){
			String VIN = request.getParameter("VIN");
			try {
				String S;
				S = bazaDanych.getHistoria(VIN);
				out.println(S);
			} catch (SQLException e) {
				out.println("ten Samochod nie ma histori");
				e.printStackTrace();
			}
		}else if(id.equals("4")){
			String data = request.getParameter("dodaj");
			try {
				System.err.println(PESEL);
				String ids = bazaDanych.getIDser(PESEL);
				bazaDanych.addwizyt(data, ids);
				out.println("dodano");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else if(id.equals("5")){
			String ids;
			System.err.println("jestm w 5");
			try {
				ids = bazaDanych.getIDser((String) session.getAttribute("PESEL"));
				String text = bazaDanych.wnaprw(ids);
				out.println(text);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		System.err.println("jest du post");
		if(session == null){
			return;
		}
		session.setMaxInactiveInterval(5*60);
		PrintWriter out = response.getWriter();
		setS(null);
		  StringBuffer jb = new StringBuffer();
		  String line = null;
		  String text;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }
		  text = jb.toString();
		  try {
		  } catch (ParseException e) {
		    // crash and burn
		    throw new IOException("Error parsing JSON request string");
		  }
		  JSONParser parse = new JSONParser();
		  Object obj;
		  String id = null;
		  String imie = null;
		  String nazwisko = null;
		  String PESEL = null;
		  String ntel = null;
		  String miejsce = null;
		  String Kodp = null;
		  String haslo = null;
		  String email = null;
		  String stanowisko = null;
		  String idn = null;
		  String koszt = null;
		  String typ = null;
		try {
			  obj = (Object) parse.parse(text);
			  JSONObject json = (JSONObject) obj;
			  idn = (String) json.get("ida");
			  typ = (String) json.get("typ");
			  koszt = (String) json.get("koszt");
			  id = (String) json.get("id");
			  imie = (String)json.get("imie");
			  nazwisko = (String) json.get("nazwisko");
			  PESEL = (String) json.get("PESEL");
			  ntel = (String)json.get("ntel");
			  miejsce = (String)json.get("miejscowos");
			  Kodp = (String)json.get("KodP");
			  haslo = (String)json.get("haslo");
			  email = (String)json.get("email");
			  stanowisko = (String) json.get("stan");
		} catch (org.json.simple.parser.ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 
			 
				  
				if(session.getAttribute("status").equals("1") && id.equals("1") == true ){
				  session.setMaxInactiveInterval(2*60);
				  out.println(text);
					  try {
						bazaDanych.sRejestracja(imie, nazwisko, PESEL, ntel, miejsce, Kodp, haslo, email,stanowisko);
						String ids = bazaDanych.getIDser( (String) session.getAttribute("PESEL"));
						bazaDanych.conect(PESEL, ids);
						out.println("dodany");
					  } catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();  
					  }
					}
				else if(id.equals("2")){
					try {
						bazaDanych.napraw(idn, typ, koszt);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
		  }
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
		

}