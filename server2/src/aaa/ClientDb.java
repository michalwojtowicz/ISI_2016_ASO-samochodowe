package aaa;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import junit.framework.TestCase;

public class ClientDb extends TestCase{
	Connection con;
	ServerBaza bazaDanych;
	
	public void init(){
		String baza = "jdbc:mysql://127.0.0.1/aso";
    	try {
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    		con = (Connection) DriverManager.getConnection(baza,"root","");
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
	
	public void rejestracja(String imie,String nazwisko, String PESEL, String numerTel, String miejscowosc, String KodPocztowy,String haslo,String StatusKlienta,String Email) throws SQLException{
		System.err.println("rejestracja");
		String sql;
		Statement stm = (Statement) con.createStatement();
		sql = "insert into klient (PESEL, Nazwisko, Imie, Miejscowosc, Kod_Pocztowy, Nr_Telefonu, Email, Haslo) values ('" + PESEL + "', '" + nazwisko +"','"+imie + "', '" + miejscowosc + "', " + KodPocztowy + ", "+numerTel + ", '" + Email+"', '"+ haslo + "');";
		stm.executeUpdate(sql);	
	}
	
	public String getWolneTerminy(){
		
		String sql = "SELECT DAY(Data_Wizyty) as Dzien_Wizyty FROM kalendarz_wizyt WHERE MONTH(Data_Wizyty) = MONTH( now())";
		String wynik = "";
		Statement stm;
		try {
			stm = (Statement) con.createStatement();
			ResultSet result = stm.executeQuery(sql);
			while(result.next()){
				wynik += result.getString("Dzien_Wizyty") + ";";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wynik;
	}
	
	public boolean servletLogic(boolean user, String login, String password){
		
		String odp;
		Contener pack;
		if(user){
			try {
				odp = bazaDanych.login(login, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("blad bazy");
				return false;
			}
			if(!odp.equals("koniec")){
				return true;
			}else{
				System.out.println("brak uzytkownika");
				return false;
			}
		}else {
			try {
				pack = bazaDanych.logS(login, password);
				odp = pack.tab[0];
				if(!odp.equals("koniec")){
					if(odp.equals("szef")){
						System.out.println("serwisant");
						return  true;
					}else{
						System.out.println("pracownik");
						return true;
					}
				}else{
					System.out.println("brak uzytkownika");
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("blad bazy");
				return false;
			}
		}
	}
	
	public void removeFromDataBase(String nick){
		String sql;
		Statement stm;
		try {
			stm = (Statement) con.createStatement();
			sql = "DELETE FROM klient WHERE PESEL = '"+nick+"';";
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//	public static void main(String[] arg) throws SQLException{
//		
//		ClientDb proba = new ClientDb();
//		proba.init();
//		proba.rejestracja("cos1","cos2" , "cos3", "3456215", "login", "34-600","1", "cos8", "cos9");
//		System.out.println(proba.servletLogic("cos9", "1"));
//	}
}
