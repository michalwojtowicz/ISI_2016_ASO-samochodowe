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
import org.json.simple.parser.ParseException;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Client
 */
public class Client extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	Connection con;
	ServerBaza bazaDanych;
	Cars car;
    public Client() {
        super();
        // TODO Auto-generated constructor stub
    }public void init(){
    	String baza = "jdbc:mysql://127.0.0.1:3306/serwis_aso_m4u";
    	try {
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    		con = (Connection) DriverManager.getConnection(baza,"root","dupa1234");
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
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String S = null;
		String PESEL;
		HttpSession session = request.getSession(false);
		if(session == null){
			return;
			
		}
		session.setMaxInactiveInterval(5 * 60);
		PESEL = (String) session.getAttribute("PESEL");
		if(id.equals("1")){
			try {
				session.setMaxInactiveInterval(60);
			car =  bazaDanych.checkYourCar( PESEL);
			} catch (SQLException e) {
				e.printStackTrace();
				out.println("brak pojazdow");
			}
			if(car.check == true ){
			out.println(car.wynik);
			}else{
				out.println("brak pojazdow");
			}
		}else if(id.equals("2")){
			session.setMaxInactiveInterval(60);
			try {
				S = bazaDanych.wolneWizyty();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println(S);
		}else if(id.equals("3")){
			System.err.println("Jestem w marka");
			String marka = request.getParameter("marka");
			String VIN = null;
			String wynik = null;
			session.setMaxInactiveInterval(60);
			try {
			VIN = bazaDanych.getVIN(marka, PESEL);
			wynik = bazaDanych.getHistoria(VIN);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println(wynik);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		System.err.println("jestem w post");
		if(session == null){
			return;
		}
		System.err.println("jestem w post");
		  session.setMaxInactiveInterval(5 * 60);
		  PrintWriter out = response.getWriter();
		  StringBuffer jb = new StringBuffer();
		  String line = null;
		  String text;
		  String PESEL = (String) session.getAttribute("PESEL");
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }
		  text = jb.toString();
		  JSONParser parse = new JSONParser();
		  Object obj;
		  out.println(text);
		try {
			obj = (Object) parse.parse(text);
			JSONObject json = (JSONObject) obj;
			String id = (String) json.get("id");
			String VIN = (String) json.get("VIN");
			String marka = (String) json.get("Marka");
			String model = (String) json.get("model");
			String rok = (String) json.get("rok");
			String paliwo = (String) json.get("paliwo");
			String pojemnosc = (String) json.get("pojemnosc");
			String moc = (String) json.get("moc");
			String kraj = (String) json.get("kraj");
			String przebieg = (String) json.get("pezebieg");
			String nazwa = (String) json.get("nazwa");
			String data = (String) json.get("data");
			if(id.equals("1")){
				System.err.println("jestem w post1");
				bazaDanych.addCar(VIN, marka, model, rok, paliwo, pojemnosc, moc, kraj, przebieg, PESEL);
			}else if(id.equals("2")){
				String idservis;
				String idwizyt = null;
				String przebiega;
				out.println(text);
				VIN = bazaDanych.getVIN(model, PESEL);
				idservis = bazaDanych.getIDservis(nazwa);
				out.println(idservis);
				idwizyt = bazaDanych.getIDwizyty(data, idservis);
				System.err.println("IDwizyty" + idwizyt);
				out.println(idwizyt);
				bazaDanych.rezerw(idwizyt);
				out.println("zarezerwowano");
				przebiega = bazaDanych.getPrzebieg(VIN);
				out.println(przebiega);
				bazaDanych.Dnaprawa(idservis, data, przebiega, VIN);
			}
		} catch (ParseException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
}
