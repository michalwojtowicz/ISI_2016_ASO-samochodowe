package aaa;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import aaa.Strona;
import aaa.ClientDb;

/**
 * Servlet implementation class Html
 * @param <E>
 */
public class HtmlCreator<E> extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String wynik="";
	String naprawy="dupadia";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HtmlCreator() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// String url=(String) request.getParameter("strona");
		// if(url.equals("dodajSamochody")){
		//
		// }
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String strona = request.getParameter("strona");
		strona = Strona.prasujStrone(strona,
				"glowna;client;wyslanoSms;dodajSamochody;historia;uWizyta;wolneTerminy;serwisant;bramka;sRejestracja;sLogin;rejestracja;napraw;dodprac;tSerwis;cVIN;addwizy;historiaa;dodPracownika;wyswietlNaprawy");

		if (strona.equals("blad")) {

			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Nie znaleziono elemetu");
		} else if (strona.equals("dodajSamochody")) {
			// out.println(url);
			out.println(Strona.getClientHtml(Strona.dodajSamochody()));
		} else if (strona.equals("client")) {
			out.println(Strona.getClientHtml(Strona.client()));
		} else if (strona.equals("wyslanoSms")) {
			out.println(Strona.getClientHtml(Strona.wyslanoSms()));
		} else if (strona.equals("sRejestracja")) {
			out.println(Strona.getClientHtml(Strona.sRejestracja()));
		} else if (strona.equals("sLogin")) {
			out.println(Strona.getClientHtml(Strona.sLogin()));
		} else if (strona.equals("rejestracja")) {
			out.println(Strona.getClientHtml(Strona.rejestracja()));
		} else if (strona.equals("napraw")) {
			out.println(Strona.getClientHtml(Strona.napraw()));
		} else if (strona.equals("dodprac")) {
			out.println(Strona.getClientHtml(Strona.dodprac()));
		} else if (strona.equals("cVIN")) {
			out.println(Strona.getClientHtml(Strona.cVIN()));
		} else if (strona.equals("addwizy")) {
			out.println(Strona.getClientHtml(Strona.addwizy()));
		} else if (strona.equals("tSerwis")) {
			out.println(Strona.getClientHtml(Strona.tSerwis()));
		} else if (strona.equals("serwisant")) {
			out.println(Strona.getClientHtml(Strona.serwisant()));
		} else if (strona.equals("dodPracownika")) {
			out.println(Strona.getClientHtml(Strona.dodPracownika()));
		} else if (strona.equals("wyswietlNaprawy")) {
			ServerBaza a = new ServerBaza();
			try {
				naprawy = a.naprawyWys("1");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println(Strona.getClientHtml(Strona.wyswietlNaprawy(naprawy)));
		} else if (strona.equals("bramka2")) {
			String tekstsms = request.getParameter("bramka");
			out.println(tekstsms);
			ClientDb db = new ClientDb();
			db.init();
			String numery = "";

			try {
				numery = db.getNumerTelefonu();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (numery.length() > 0) {
				String[] numer = numery.split(";");
				for (String e : numer) {
					new SmsSender(tekstsms, e);
				}
			}
			response.sendRedirect("ASO?strona=serwisant");
		} else if (strona.equals("historia")) {
			out.println(Strona.getClientHtml(Strona.historia()));
			
		} else if (strona.equals("historiaa")) {
			out.println(Strona.getClientHtml(Strona.historiaa(wynik)));
			
		} else if (strona.equals("bramka")) {
			out.println(Strona.getClientHtml(Strona.bramka()));
		} else if (strona.equals("uWizyta")) {
			out.println(Strona.getClientHtml(Strona.uWizyta()));
		} else if (strona.equals("wolneTerminy")) {

			ClientDb db = new ClientDb();
			db.init();
			String dni[] = db.getWolneTerminy().split(";");
			
			ServerBaza a = new ServerBaza();
			int count;
			try {
				count = a.iloscRekordow();
				if(count==1){
					out.println(Strona.getClientHtml(Strona.wolneTerminy(dni[0], dni[0])));
					}else if(count==2){
						out.println(Strona.getClientHtml(Strona.wolneTerminy(dni[0], dni[1])));		
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (strona.equals("glowna")) {

			out.println(Strona.getClientHtml(Strona.glowna()));
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String strona = request.getParameter("strona");
		//out.print(strona);
		
		if(strona.equals("uWizyta")){
			
			ServerBaza baza = new ServerBaza();
			String data = request.getParameter("datepicker");
			String nazwa = request.getParameter("nazwa");
			String model = request.getParameter("model");
			
			try {
				baza.addwizyty(data, nazwa);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if(strona.equals("historia")){
			ServerBaza baza = new ServerBaza();
			
			String vin = request.getParameter("haslo");
			
			try {
				wynik=baza.getHistoria(vin);
				response.sendRedirect("ASO?strona=historiaa");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if(strona.equals("dodPracownika")) {
			String PESEL = request.getParameter("PESEL");
			String imie = request.getParameter("imie");
			String nazwisko = request.getParameter("nazwisko");
			String miejscowosc = request.getParameter("miejscowosc");
			String kodpocztowy = request.getParameter("kodp");
			String numer = request.getParameter("nrt");
			String email = request.getParameter("email");
			String haslo = request.getParameter("haslo");
			String stanowisko = request.getParameter("stan");
			
			ServerBaza a = new ServerBaza();
			
			try {
				a.sRejestracja(imie, nazwisko, PESEL, numer, miejscowosc, kodpocztowy, haslo, email, stanowisko);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		} else if(strona.equals("napraw")) {
			String Data_Naprawy = request.getParameter("Data_Naprawy");
			String Typ_Naprawy = request.getParameter("Typ_Naprawy");
			String Przebieg = request.getParameter("Przebieg");
			String Koszt = request.getParameter("Koszt");
			String VIN = request.getParameter("VIN");
			String ID_Serwis = request.getParameter("ID_Serwis");
			
			ServerBaza a = new ServerBaza();
			
			try {
				a.addnaprawy(Data_Naprawy, Typ_Naprawy, Przebieg, Koszt, VIN, ID_Serwis);
				a.updateStatus(VIN);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		doGet(request, response);
	}

}
