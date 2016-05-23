package aaa;

import java.util.ArrayList;

public class Cars {
	String wynik;
	boolean check;
	ArrayList<String> list = new ArrayList<String>();
  Cars(){
	  
  }
  void addToList(String S){
	  list.add(S);
  }
  void wynik(String S){
	  wynik = S;
  }
}
