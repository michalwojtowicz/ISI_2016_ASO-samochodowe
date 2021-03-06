package aaa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.activation.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mysql.jdbc.Statement;
import com.sun.xml.internal.bind.CycleRecoverable.Context;

public class ServerBaza {
	private static Connection connect;

	public ServerBaza() {
		// TODO Auto-generated constructor stub
	}

	ServerBaza(Connection connect) {
		this.connect = connect;
	}

	Cars car = new Cars();
	Contener pack = new Contener();

	String login(String email, String haslo) throws SQLException {
		System.err.println("SB_baza");
		String sql = "";
		Statement stm = (Statement) connect.createStatement();
		ResultSet result;
		sql = "select * from klient where Email = '" + email + "'  and Haslo = '" + haslo + "';";
		result = stm.executeQuery(sql);
		if (!result.next()) {
			return "koniec";
		} else {

			return result.getString("PESEL");
		}
	}

	String rejestracja(String imie, String nazwisko, String PESEL, String numerTel, String miejscowosc,
			String KodPocztowy, String haslo, String StatusKlienta, String Email) throws SQLException {
		System.err.println("SB_rejestracja");
		String sql;
		Statement stm = (Statement) connect.createStatement();
		sql = "insert into klient (PESEL, Nazwisko, Imie, Miejscowosc, Kod_Pocztowy, Nr_Telefonu, Email, Haslo) values ('"
				+ PESEL + "', '" + nazwisko + "','" + imie + "', '" + miejscowosc + "', " + KodPocztowy + ", "
				+ numerTel + ", '" + Email + "', '" + haslo + "');";
		stm.executeUpdate(sql);
		return sql;
	}
	
	Cars checkYourCar(String PESEL) throws SQLException {
		System.err.println("SB_"+PESEL);
		String sql;
		String wynik = null;
		String S;
		Statement stm = (Statement) connect.createStatement();
		sql = "select VIN, Marka, Model, Rok_Produkcji, Paliwo, Moc_Silnika, Kraj_Pochodzenia, Przebieg from samochod where PESEL = '"
				+ PESEL + "';";
		ResultSet result = stm.executeQuery(sql);
		while (result.next()) {
			S = result.getString(1);
			car.addToList(S);
			wynik += "VIN " + S + "<br> ";
			wynik += "Marka " + result.getString(2) + "<br> ";
			wynik += "Model " + result.getString(3) + "<br> ";
			wynik += "rok produkcji " + result.getString(4) + "<br> ";
			wynik += "paliwo " + result.getString(5) + "<br> ";
			wynik += "moc silnika " + result.getString(6) + "<br> ";
			wynik += "kraj pochodzenia " + result.getString(7) + "<br> ";
			wynik += "przebieg " + result.getString(8) + "<br><br> ";

		}
		car.wynik(wynik);
		if (wynik == null) {
			car.check = false;
			return car;
		}
		car.check = true;
		return car;
	}

	String wolneWizyty() throws SQLException {
		String sql = "SELECT Data_Wizyty FROM kalendarz_wizyt WHERE MONTH(Data_Wizyty) = MONTH( now()) ";
		String wynik = "";
		System.err.println("halo");
		Statement stm = (Statement) connect.createStatement();
		ResultSet result = stm.executeQuery(sql);
		while (result.next()) {
			wynik += result.getString("Data_Wizyty") + " ";
			wynik += result.getString("Nazwa") + "<br>";
		}
		System.err.println(wynik);
		return wynik;

	}
	
	int iloscRekordow() throws SQLException {
		String sql = "SELECT * from kalendarz_wizyt";
		String wynik = "";
		Statement stm = (Statement) connect.createStatement();
		ResultSet result = stm.executeQuery(sql);
		int i = 0;
		while(result.next()){
			i++;
		}
		
		return i;
		
	}

	String getVIN(String model, String PESEL) throws SQLException {
		String sql = "select * from samochod where Model = '" + model + "'and PESEL = '" + PESEL + "' ;";
		String wynik = "";
		System.err.println("SB_getVIN");
		Statement stm = (Statement) connect.createStatement();
		ResultSet result = stm.executeQuery(sql);
		if (!result.next()) {
			return "blad";
		} else {
			wynik += result.getString(1);
			System.err.println(wynik);
			return wynik;
		}

	}

	String getHistoria(String VIN) throws SQLException {
		String sql = "select Data_Naprawy, Typ_Naprawy, Przebieg, Koszt from naprawa where VIN = '" + VIN + "';";
		String wynik = "";
		System.err.println("SB_getHistoria");
		Statement stm = (Statement) connect.createStatement();
		ResultSet result = stm.executeQuery(sql);
		while (result.next()) {
			wynik += "data naprawy " + result.getString("Data_Naprawy") + "<br>";
			wynik += "typ naprawy " + result.getString("Typ_Naprawy") + "<br>";
			wynik += "Przebieg " + result.getString("Przebieg") + "<br>";
			wynik += "koszt " + result.getString("Koszt") + "<br>";
		}
		System.err.println(wynik);
		return wynik;
	}

	boolean addCar(String VIN, String marka, String model, String rok, String paliwo, String pojemnosc, String moc,
			String kraj, String przebieg, String PESEL) throws SQLException {
		String sql = "insert into samochod ( VIN, Marka, Model, Rok_Produkcji, Paliwo, Pojemnosc,Moc_Silnika, Kraj_Pochodzenia, Przebieg, PESEL) values ('"
				+ VIN + "', '" + marka + "', '" + model + "','" + rok + "', '" + paliwo + "', '" + pojemnosc + "', '"
				+ moc + "', '" + kraj + "', '" + przebieg + "', '" + PESEL + "');";
		Statement stm = (Statement) connect.createStatement();
		stm.executeUpdate(sql);
		return true;
	}

	String getIDservis(String nazwa) throws SQLException {
		String sql = "select * from serwis where Nazwa = '" + nazwa + "';";
		String wynik = "";
		System.err.println("SB_getIDSerwis");
		Statement stm = (Statement) connect.createStatement();
		ResultSet result = stm.executeQuery(sql);
		while (result.next()) {
			wynik += result.getString(1);
		}
		System.err.println(wynik);
		return wynik;

	}

	String getIDwizyty(String data, String id) throws SQLException {
		String sql = "select * from kalendarz_wizyt where Data_Wizyty = '" + data + "' and ID_Serwis = '" + id
				+ "'and Status_Wizyty = '0';";
		String wynik = "";
		System.err.println("SB_getIDWizyty");
		Statement stm = (Statement) connect.createStatement();
		ResultSet result = stm.executeQuery(sql);
		System.err.println("halo5");
		if (!result.next()) {
			return "blad";
		} else {
			wynik += result.getString("ID_Wizyty");
			System.err.println(wynik);
			return wynik;
		}
	}

	String getPrzebieg(String id) throws SQLException {
		String sql = "select * from samochod where VIN = '" + id + "';";
		String wynik = "";
		System.err.println("SB_getPrzebieg");
		Statement stm = (Statement) connect.createStatement();
		ResultSet result = stm.executeQuery(sql);
		System.err.println("halo6");
		if (!result.next()) {
			return "blad";
		} else {
			wynik += result.getString("Przebieg");
			System.err.println(wynik);
			return wynik;
		}
	}

	boolean rezerw(String id) throws SQLException {
		System.err.println("SB_rezerw");
		String sql = "UPDATE kalendarz_wizyt SET Status_Wizyty='1' WHERE ID_Wizyty=" + id + ";";
		Statement stm = (Statement) connect.createStatement();
		stm.executeUpdate(sql);
		return true;
	}

	boolean Dnaprawa(String id, String data, String przebieg, String VIN) throws SQLException {
		System.err.println("SB_Dnaprawa");
		String sql = "insert into naprawa (Data_Naprawy, Typ_Naprawy, Przebieg, Koszt, VIN, ID_Serwis) values ('" + data
				+ "', 'czekaj na potwierdzenie naprawy', '" + przebieg + "', '0', '" + VIN + "', '" + id + "');";
		Statement stm = (Statement) connect.createStatement();
		stm.executeUpdate(sql);
		return true;
	}

	Contener logS(String login, String password) throws SQLException {
		System.err.println("SB_logS");
		String sql = "";
		Statement stm = (Statement) connect.createStatement();
		ResultSet result;
		sql = "select * from pracownik where Email = '" + login + "'and Haslo = '" + password + "';";
		result = stm.executeQuery(sql);
		if (!result.next()) {
			pack.add(0, "koniec");
			return pack;
		} else {
			pack.add(1, result.getString("Miejscowosc"));
			pack.add(2, result.getString("Kod_Pocztowy"));
			pack.add(3, result.getString("Nr_Telefonu"));
			pack.add(4, result.getString("Email"));
			pack.add(0, result.getString("Stanowisko"));
			pack.add(5, result.getString("PESEL"));
			return pack;
		}

	}

	boolean sRejestracja(String imie, String nazwisko, String PESEL, String numerTel, String miejscowosc,
			String KodPocztowy, String haslo, String Email, String stanowisko) throws SQLException {
		System.err.println("SB_sRejestracja");
		String sql;
		Statement stm = (Statement) connect.createStatement();
		sql = "insert into pracownik (PESEL, Nazwisko, Imie, Miejscowosc, Kod_Pocztowy, Nr_Telefonu, Email, Haslo, Stanowisko, Status_Pracownika3030) values ('"
				+ PESEL + "', '" + nazwisko + "','" + imie + "', '" + miejscowosc + "', '" + KodPocztowy + "', '"
				+ numerTel + "', '" + Email + "', '" + haslo + "', '" + stanowisko + "','1');";
		stm.executeUpdate(sql);
		return true;
	}

	boolean createSerwis(String Nazwa, String miejscowsc, String kodP, String ntel, String email) throws SQLException {
		System.err.println("SB_createserwis");
		String sql;
		Statement stm = (Statement) connect.createStatement();
		sql = "insert into serwis (Nazwa, Miejscowosc, Kod_Pocztowy, Nr_Telefonu, Email) values ('" + Nazwa + "', '"
				+ miejscowsc + "', '" + kodP + "', '" + ntel + "', '" + email + "');";
		stm.executeUpdate(sql);
		return true;
	}

	String getconectSerwi(String Nazwa, String email) throws SQLException {
		String sql = "select * from serwis where Email = '" + email + "' and Nazwa = '" + Nazwa + "';";
		String wynik = "";
		System.err.println("SB_getconnectSerwi");
		Statement stm = (Statement) connect.createStatement();
		ResultSet result = stm.executeQuery(sql);
		System.err.println("halo5");
		if (!result.next()) {
			return "blad";
		} else {
			wynik += result.getString("ID_Serwis");
			System.err.println(wynik);
			return wynik;
		}

	}

	void conect(String PESEL, String id) throws SQLException {
		System.err.println("SB_connect");
		String sql;
		Statement stm = (Statement) connect.createStatement();
		sql = "insert into serwis_pracownik (PESEL, ID_Serwis) values ('" + PESEL + "', '" + id + "');";
		stm.executeUpdate(sql);

	}

	String getIDser(String PESEL) throws SQLException {
		String sql = "select * from serwis_pracownik where PESEL = '" + PESEL + "';";
		String wynik = "";
		System.err.println("SB_getIDser");
		Statement stm = (Statement) connect.createStatement();
		ResultSet result = stm.executeQuery(sql);
		System.err.println("halo5");
		if (!result.next()) {
			return "blad";
		} else { 
			wynik += result.getString("ID_Serwis");
			System.err.println(wynik);
			return wynik;
		}

	}

	String naprawy(String id) throws SQLException {
		String sql = "select * from naprawa where ID_Serwis = '" + id + "';";
		String wynik = "";
		System.err.println("SB_naprawy");
		Statement stm = (Statement) connect.createStatement();
		ResultSet result = stm.executeQuery(sql);
		while (result.next()) {
			wynik += "data naprawy " + result.getString(1) + "<br>";
			wynik += "typ naprawy " + result.getString(2) + "<br>";
			wynik += "Przebieg " + result.getString(3) + "<br>";
			wynik += "koszt " + result.getString(4) + "<br>";
		}
		System.err.println(wynik);
		return wynik;

	}
	
	void dodajNaprawe(String data, String typ, String przebieg, String koszt, String VIN, String ID_Serwis) throws SQLException {
		String sql = "INSERT INTO `naprawa` (`Data_Naprawy`, `Typ_Naprawy`, `Przebieg`, `Koszt`, `VIN`, `ID_Serwis`) VALUES ('"+data+"', '"+typ+"', '"+przebieg+"', '"+koszt+"', '"+VIN+"', '"+ID_Serwis+"');";
		String wynik = "";
		System.err.println("SB_naprawy");
		Statement stm = (Statement) connect.createStatement();
		ResultSet result = stm.executeQuery(sql);
	}
	
	String naprawyWys(String id) throws SQLException {
		String sql = "select * from kalendarz_wizyt where ID_Serwis = '" + id + "' and Status_Wizyty='0';";
		String wynik = "";
		System.err.println("SB_naprawy");
		Statement stm = (Statement) connect.createStatement();
		ResultSet result = stm.executeQuery(sql);
		while (result.next()) {
			wynik += "Planowana data naprawy: " + result.getString("Data_Wizyty") + "<br>";
			wynik += "VIN:  "+pobierzVIN(result.getString("ID_Wizyty"))+"<br><br>";
			//wynik += "VIN: " + result.getString("VIN") + "<br><br><br>";
		}
		System.err.println(wynik);
		return wynik;

	}
	
	String pobierzVIN(String id) throws SQLException {
		String sql = "select VIN from kalendarz_wizyt_samochod where ID_Wizyty = '" + id + "';";
		String wynik = "";
		System.err.println("SB_naprawy");
		Statement stm = (Statement) connect.createStatement();
		ResultSet result = stm.executeQuery(sql);
		while (result.next()) {

			wynik += result.getString("VIN");
		}
		System.err.println(wynik);
		return wynik;

	}
	
	String naprawyPlanowane(String id) throws SQLException {
		String sql = "select * from kalendarz_wizyt where ID_Serwis = '" + id + "' and Status_wizyty = '0';";
		String wynik = "";
		System.err.println("SB_naprawy");
		Statement stm = (Statement) connect.createStatement();
		ResultSet result = stm.executeQuery(sql);
		while (result.next()) {
			wynik += "Data naprawy: " + result.getString("Data_Wizyty") + "<br>";
			wynik += "VIN: " + result.getString("VIN") + "<br>";
		}
		System.err.println(wynik);
		return wynik;

	}
	
	boolean addnaprawy(String Data_Naprawy, String Typ_Naprawy, String Przebieg, String Koszt, String VIN, String ID_Serwis) throws SQLException {
		System.err.println("SB_addwizyty");
		String sql = "insert into naprawa (Data_Naprawy, Typ_Naprawy, Przebieg, Koszt, VIN, ID_Serwis) values('"+Data_Naprawy+"', '"+Typ_Naprawy+"', '"+Przebieg+"', '"+Koszt+"', '"+VIN+"', '"+ID_Serwis+"');";
		Statement stm = (Statement) connect.createStatement();
		stm.executeUpdate(sql);
		return true;

	}
	
	boolean updateStatus(String VIN) throws SQLException {
		System.err.println("SB_addwizyty");
		String sql = "update kalendarz_wizyt set Status_Wizyty = '1' where ID_Wizyty IN (SELECT ID_Wizyty from kalendarz_wizyt_samochod where VIN in (select VIN from naprawa) and VIN like '"+VIN+"')";
		Statement stm = (Statement) connect.createStatement();
		stm.executeUpdate(sql);
		return true;

	}
	
	
	
	

	boolean addwizyt(String data, String id) throws SQLException {
		System.err.println("SB_addwizyty");
		String sql = "insert into kalendarz_wizyt (Data_Wizyty, Status_Wizyty, ID_Serwis) values('" + data + "', '0', '"
				+ id + "');";
		Statement stm = (Statement) connect.createStatement();
		stm.executeUpdate(sql);
		return true;

	}
	
	boolean addwizyty(String data, String nazwa) throws SQLException {
		System.err.println("SB_addwizyty");
		String[] datas = data.split("/");
		String wynik = datas[2]+"-"+datas[0]+"-"+datas[1];
		
		String sql = "insert into kalendarz_wizyt (Data_Wizyty, Status_Wizyty, ID_Serwis) values('"+wynik+"', '0', (select ID_Serwis from serwis where nazwa like '"+nazwa+"'))";
		Statement stm = (Statement) connect.createStatement();
		stm.executeUpdate(sql);
		return true;

	}
	

	String wnaprw(String id) throws SQLException {
		String sql = "select * from naprawa where ID_Serwis = '" + id + "' and Koszt = '0';";
		String wynik = "";
		System.err.println("SB_wnapraw");
		Statement stm = (Statement) connect.createStatement();
		ResultSet result = stm.executeQuery(sql);
		while (result.next()) {
			wynik += "ID_Naprawy " + result.getString("ID_Naprawy") + "<br>";
			wynik += "data " + result.getString(2) + "<br>";
			wynik += "Przebieg " + result.getString("Przebieg") + "<br>";
			wynik += "VIN " + result.getString("VIN") + "<br>";
		}
		System.err.println(wynik);
		return wynik;

	}

	void napraw(String id, String typ, String koszt) throws SQLException {
		System.err.println("SB_napraw");
		String sql = "UPDATE naprawa SET Typ_Naprawy='" + typ + "'WHERE ID_Naprawy='" + id + "';";
		Statement stm = (Statement) connect.createStatement();
		stm.executeUpdate(sql);
		sql = "UPDATE naprawa SET Koszt='" + koszt + "'WHERE ID_Naprawy='" + id + "';";
		stm.executeUpdate(sql);
	}

	public static int getCountNumerTelefonu() throws SQLException {
		String sql = "select count(Nr_Telefonu) from klient;";
		int wynik = 0;
		Statement stm = (Statement) connect.createStatement();
		ResultSet result = stm.executeQuery(sql);
		if (!result.next()) {
			return 0;
		} else {
			wynik = result.getInt(1);
			return wynik;
		}
	}

	public static String getNumerTelefonu() throws SQLException {
		String sql = "select Nr_Telefonu from klient;";
		String wynik = "";
		Statement stm = (Statement) connect.createStatement();
		ResultSet result = stm.executeQuery(sql);
		if (!result.next()) {
			return "blad";
		} else {
			for (int i = 1; i <= 1; i++) {
				wynik += result.getString(i) + ";";
			}
			System.err.println(wynik);
			return wynik;
		}
	}
}