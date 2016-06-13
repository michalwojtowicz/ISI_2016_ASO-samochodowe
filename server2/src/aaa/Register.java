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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mysql.jdbc.Connection;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	Connection con;
	ServerBaza bazaDanych;

	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		String baza = "jdbc:mysql://127.0.0.1:3306/serwis_aso_m4u";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = (Connection) DriverManager.getConnection(baza, "root", "Haslo123");
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			String S = bazaDanych.login("email", "haslo");
			out.println(S);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		System.err.println("rejestracja");
		String S = null;
		StringBuffer jb = new StringBuffer();
		String line = null;
		String text;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
			/* report an error */ }
		text = jb.toString();
		try {
		} catch (ParseException e) {
			// crash and burn
			throw new IOException("Error parsing JSON request string");
		}

		JSONParser parse = new JSONParser();

		try {
			Object obj = (Object) parse.parse(text);
			JSONObject json = (JSONObject) obj;
			String id = (String) json.get("id");
			String imie = (String) json.get("imie");
			String nazwisko = (String) json.get("nazwisko");
			String PESEL = (String) json.get("PESEL");
			String ntel = (String) json.get("ntel");
			String miejsce = (String) json.get("miejscowos");
			String Kodp = (String) json.get("KodP");
			String haslo = (String) json.get("haslo");
			String email = (String) json.get("email");
			String stanowisko = (String) json.get("stan");
			if (id.equals("1")) {
				S = bazaDanych.rejestracja(imie, nazwisko, PESEL, ntel, miejsce, Kodp, haslo, "1", email);
				out.println("zarejstrowany");
			} else if (id.equals("2")) {
				bazaDanych.sRejestracja(imie, nazwisko, PESEL, ntel, miejsce, Kodp, haslo, email, stanowisko);
				out.println("zarejstrowany");
			}
		} catch (org.json.simple.parser.ParseException e) {
			out.println("duap");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("wprowadzi porprawne dane");
		}

	}

}
