package aaa;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Auth
 */
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection con;
    ServerBaza bazaDanych;
    Contener pack;
    public void init(){
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
		String login = request.getParameter("login");
		String password = request.getParameter("haslo");
		String login1 = request.getParameter("login1");
		String password1 = request.getParameter("haslo1");
		String odp = null;
		
		if(login != null){
			try {
				odp = bazaDanych.login(login, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!odp.equals("koniec")){
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(60);
				session.setAttribute("log", 1);
				session.setAttribute("user", login);
				session.setAttribute("PESEL", odp);
				out.println(odp);
			    response.sendRedirect("Client.html"); 
			}else{
				out.println("brak uzytkownika");
			}
		}else{
			try {
				pack = bazaDanych.logS(login1, password1);
				odp = pack.tab[0];
				if(!odp.equals("koniec")){
					HttpSession session = request.getSession();
					session.setAttribute("user", login1);
					session.setAttribute("miejsce",pack.tab[1]);
					session.setAttribute("ntel",pack.tab[3]);
					session.setAttribute("kodp",pack.tab[2]);
					session.setAttribute("PESEL",pack.tab[5]);
					if(odp.equals("szef")){
						session.setAttribute("status", "1");
						response.sendRedirect("serwisant.html");
					}else{
						session.setAttribute("status", "0");
						response.sendRedirect("pracownik.html");
					}
					out.println(odp);
					
				}else{
					out.println("brak uzytkownika");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
