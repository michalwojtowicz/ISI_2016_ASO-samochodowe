package aaa;

import sun.awt.windows.WToolkit;

public class Strona {

	public static String prasujStrone(String wejscie, String poprawneStrony) {

		String wyjscie = "blad";
		if (wejscie == null)
			wejscie = "glowna";

		if (poprawneStrony.length() > 0) {

			String[] strony = poprawneStrony.split(";");
			for (String strona : strony) {
				if (wejscie.equals(strona))
					wyjscie = wejscie;
			}
		}

		return wyjscie;
	}

	public static String getClientHtml(String tresc) {
		StringBuffer out = new StringBuffer();
		// sms
		// new SmsSender("Jestem w serwisie","505866720");
		// SmsSender a= new SmsSender();

		// sms.eventHappened(event);
		out.append("<!DOCTYPE HTML>\n");
		out.append("<html lang=\"pl\">\n");
		out.append("<head>\n");
		out.append("<meta charset=\"utf-8\" />\n");
		out.append("<title>Serwis ASO - M4U</title>\n");
		out.append("<meta name=\"description\" content=\"Serwis wspomagaj¹cy pracê serwisu\" />\n");
		out.append("<meta name=\"keywords\" content=\"najlepszy serwis aso, zobacz\" />\n");
		out.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />\n");
		out.append(
				"<link  type=\"text/css\"rel=\"stylesheet\" media=\"screen and (min-device-width: 481px)\" href=\"css/style.css\" />\n");
		out.append(
				"<link type=\"text/css\" rel=\"stylesheet\" media=\"only screen and (max-device-width: 480px)\" href=\"css/style-mobile.css\" />\n");
		out.append(
				"<link rel=\"stylesheet\" media=\"screen and (-webkit-device-pixel-ratio:0.75)\" href=\"css/style-mobile.css\" />\n");
		out.append("<link rel=\"stylesheet\" href=\"css/style-mobile.css\" media=\"handheld\" type=\"text/css\" />\n");
		out.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"jq/jquery-ui-1.11.4.custom/jquery-ui.css\">");
		out.append("<link href=\"../assets/styles.min.css\" rel=\"stylesheet\"> ");
		out.append(
				"<link href=\"//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/themes/ui-darkness/jquery-ui.min.css\" rel=\"stylesheet\"> ");
		out.append("<script src=\"//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>");
		out.append("<script src=\"//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js\"></script>");
		out.append(" <style>\n .dp-highlight .ui-state-default {\n background: #FF0000;\n color: #FFF;\n}\n</style>");

		out.append("</head>\n");

		out.append("<body background=\"tlo.jpg\" bgproperties=\"fixed\">\n");
		out.append("<font color = \"#EFEFEF\" size = 5 >\n");
		out.append("<div id=\"container\" top-margin=\"10px\">\n");
		out.append("<div id=\"logo\">\n");
		out.append("<img src=\"logo.png\">\n");
		out.append("</div>\n");
		out.append("<div id=\"con\">\n");
		out.append("<div id=\"nav\">\n");
		out.append("<center>\n");
		out.append("<br>\n");
		// mozliwosc dalszej modyfikacji

		// dodaæ if dla serwisanta ASO?strona=serwisant dla client
		out.append("<a href=\"ASO\" class=\"button\">Strona g³ówna</a><br><br><br>\n");

		out.append("<a href=\"ASO\" class=\"button\">Ekran logowania</a><br><br><br>\n");
		out.append("<a href=\"javascript:history.back()\" class=\"button\">Powrót</a><br><br><br>\n");
		out.append("</center>\n");
		out.append("</div>\n");
		out.append("<div id=\"news\">\n");
		out.append("<img src=\"reklama.png\">\n");
		out.append("</div>\n");
		out.append("<div id=\"content\">\n");
		out.append(tresc);
		out.append("</div>");
		out.append("<div id=\"footer\">\n");
		out.append("@In¿ynieria Systemów Informacyjnych 2016\n");
		out.append("</div>\n");
		out.append("</div>\n");
		out.append("</font>\n");
		out.append("</body>\n");
		out.append("</html>\n");

		return out.toString();
	}

	public static String glowna() {
		StringBuffer out = new StringBuffer();

		out.append("<font color = \"#fff\" size = 15 > Logowanie </font>\n");
		out.append("<form action=\"Auth?akcja=zaloguj\">  \n");
		out.append("E-mail<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" name=\"login\"> <br>\n");
		out.append("Has³o<br>\n");
		out.append("<input type=\"password\"  class =\"tekst\" name=\"haslo\">\n");
		out.append("<br /><input type=\"submit\" class =\"button\"  value=\"Zaloguj\">\n");
		out.append("</form>  \n");
		out.append("<a class=\"button\" href=\"ASO?strona=rejestracja\">Zarejestruj siê</a><br><br>\n");
		out.append("<a class=\"button\" href=\"ASO?strona=sLogin\">Logowanie pracownika</a>\n");

		out.append("<script>\n");
		out.append("function clearDoc() {\n");
		out.append("document.getElementById(\"demo\").innerHTML = \"<h2>Nothing</h2>\";\n");
		out.append("}\n");
		out.append("function myFunction(arr) {\n");
		out.append("var out = \"\";\n");
		out.append("var i;\n");
		out.append("out = arr.imie;\n");
		out.append("document.getElementById(\"demo\").innerHTML = out;\n");
		out.append("}\n");
		out.append("function btntest_onclick()\n");
		out.append("{\n");
		out.append("window.location.href = \"ASO?strona=rejestracja\";\n");
		out.append("}\n");
		out.append("function client(){\n");
		out.append("window.location.href = \"Client.html\";\n");
		out.append("function serwisant(){\n");
		out.append("window.location.href = \"ASO?strona=sLogin\";\n");
		out.append("}\n");

		out.append("</script>\n");

		return out.toString();
	}

	public static String sRejestracja() {
		StringBuffer out = new StringBuffer();

		// out.append("<div id=\"content\">\n");
		out.append("<font color =\"#EFEFEF\">\n");
		out.append("<h1>Serwisant Rejestracja</h1><br><br>\n");
		out.append("PESEL<br>\n");
		out.append("<input type=\"text\" id=\"PESEL\" class =\"tekst\"  value=\"\"><br><br>\n");
		out.append("Imiê<br>\n");
		out.append("<input type=\"text\" id=\"imie\" class =\"tekst\"  value=\"\"><br><br>\n");
		out.append("Nazwisko<br>\n");
		out.append("<input type=\"text\" id=\"nazwisko\"  class =\"tekst\" value=\"\"><br><br>\n");
		out.append("Miejscowoœæ<br>\n");
		out.append("<input type=\"text\" id=\"miejscowosc\"  class =\"tekst\" value=\"\"><br><br>\n");
		out.append("Kod pocztowy<br>\n");
		out.append("<input type=\"text\" id=\"kodp\" class =\"tekst\"  value= \"\"><br><br>\n");
		out.append("Numer telefonu<br>\n");
		out.append("<input type=\"text\" id=\"nrt\" class =\"tekst\"  value=\"\"><br><br>\n");
		out.append("E-mail<br>\n");
		out.append("<input type=\"text\" id=\"email\" class =\"tekst\"  value=\"\"><br><br>\n");
		out.append("Has³o<br>\n");
		out.append("<input type=\"text\" id=\"haslo\" class =\"tekst\"  value=\"\"><br><br><br>\n");

		out.append("<button type=\"button\" class=\"button\" onclick=\"loadDoc()\">Zarejestruj</button><br><br>\n");
		out.append("<input id=\"btntest\"  class=\"button\" type=\"button\" value=\"Logowanie\"\n");
		out.append("onclick=\"return btntest_onclick()\" />\n");

		out.append("<script>\n");
		out.append("function loadDoc() {\n");
		out.append("var haslo = document.getElementById(\"haslo\").value;\n");
		out.append("var email = document.getElementById(\"email\").value\n");
		out.append("var kodp = document.getElementById(\"kodp\").value;\n");
		out.append("var PESEL = document.getElementById(\"PESEL\").value;\n");
		out.append("var imie = document.getElementById(\"imie\").value;\n");
		out.append("var nazwisko = document.getElementById(\"nazwisko\").value;\n");
		out.append("var nrt = document.getElementById(\"nrt\").value;\n");
		out.append("var miejscow = document.getElementById(\"miejscowosc\").value;\n");

		out.append("var xhttp = new XMLHttpRequest();\n");
		out.append("xhttp.onreadystatechange = function() {\n");
		out.append("if (xhttp.status == 200) {\n");
		out.append("document.getElementById(\"demo\").innerHTML = xhttp.responseText;\n");
		out.append("}\n");
		out.append("};\n");
		out.append("xhttp.open(\"POST\", \"users/rejestr\", true);\n");
		out.append("xhttp.setRequestHeader(\"Content-type\", \"application/json\");\n");
		out.append(
				"var data = JSON.stringify({\"id\":\"2\",\"email\":email,\"haslo\":haslo,\"KodP\":kodp,\"PESEL\":PESEL,\"imie\":imie,\"nazwisko\":nazwisko,\"ntel\":nrt,\"miejscowos\":miejscow,\"stan\":\"szef\"});\n");
		out.append("xhttp.send(data);\n");
		out.append("}\n");

		out.append("function clearDoc() {\n");
		out.append("document.getElementById(\"demo\").innerHTML = \"<h2>Nothing</h2>\";\n");
		out.append("}\n");
		out.append("function myFunction(arr) {\n");
		out.append("var out = \"\";\n");
		out.append("var i;\n");
		out.append("out = arr.imie;\n");
		out.append("document.getElementById(\"demo\").innerHTML = out;\n");
		out.append("}\n");
		out.append("function btntest_onclick()\n");
		out.append("{\n");
		out.append("window.location.href = \"sLogin.html\";\n");
		out.append("}\n");
		out.append("</script>\n");

		return out.toString();

	}

	public static String sLogin() {
		StringBuffer out = new StringBuffer();

		out.append("<center>\n");

		out.append("<font color =\"#EFEFEF\"  size = 15 >Logowanie Pracownika</font>\n");
		out.append("<font color =\"#EFEFEF\">\n");
		out.append("<form action=\"Auth?akcja=zaloguj\">\n");
		out.append("E-mail<br>\n");
		out.append("<input type=\"text\" class =\"tekst\"  name=\"login1\"> <br>\n");
		out.append("Haslo<br>\n");
		out.append("<input type=\"password\"  class =\"tekst\" name=\"haslo1\">\n");
		out.append("<br><br>\n");
		out.append("<input class=\"button\" type=\"submit\" value=\"Zaloguj\">\n");
		out.append("</form>  <br>\n");
		out.append("<a class=\"button\" href=\"ASO?strona=sRejestracja\">Zarejestruj siê</a><br>\n");
		// out.append("<a class=\"button\"
		// href=\"ASO?strona=dodajSamochody\">Dodaj Samochod</a><br>\n");

		out.append("		</font>\n");
		out.append("</center>\n");

		out.append("<script>\n");
		out.append("function clearDoc() {\n");
		out.append("out.append(\"document.getElementById(\"demo\").innerHTML = \"<h2>Nothing</h2>\";\n");
		out.append("}\n");
		out.append("function myFunction(arr) {\n");
		out.append("var out = \"\";\n");
		out.append("var i;\n");
		out.append("out = arr.imie;\n");
		out.append("document.getElementById(\"demo\").innerHTML = out;\n");
		out.append("}\n");
		out.append("function btntest_onclick()\n");
		out.append("{\n");
		out.append("window.location.href = \"ASO?strona=sRejestracja\";\n");
		out.append("}\n");
		out.append("function client(){\n");
		out.append("window.location.href = \"Client.html\";\n");
		out.append("}\n");

		out.append("</script>\n");

		return out.toString();
	}

	public static String rejestracja() {
		StringBuffer out = new StringBuffer();
		
		out.append("<center>\n");
		out.append("<font color = \"#EFEFEF\">\n");
		out.append("<h2>Rejestracja nowego klienta</h2>\n");
		out.append("<h4>\n");
		out.append("PESEL<br>\n");
		out.append("<input type=\"text\" class =\"tekst\"  id=\"PESEL\" value=\"\"> <br><br>\n");
		out.append("Imiê<br>\n");
		out.append("<input type=\"text\" class =\"tekst\"  id=\"imie\" value=\"\"> <br><br>\n");
		out.append("Nazwisko<br>\n");
		out.append("<input type=\"text\" class =\"tekst\"  id=\"nazwisko\" value=\"\"><br><br>\n");
		out.append("Miejscowoœæ<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" id=\"miejscowosc\" value=\"\"><br><br>\n");
		out.append("Kod pocztowy<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" id=\"kodp\" value= \"\"><br><br>\n");
		out.append("Numer telefonu<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" id=\"nrt\" value=\"\"><br><br>\n");
		out.append("E-mail<br>\n");
		out.append("<input type=\"text\" class =\"tekst\"  id=\"email\" value=\"\"><br><br>\n");
		out.append("Has³o<br>\n");
		out.append("<input type=\"text\" class =\"tekst\"  id=\"haslo\" value=\"\"><br>\n");
		out.append("</h4>\n");
		out.append("</font>\n");
		out.append("<br>\n");
		out.append("<button type=\"button\" class=\"button\" onclick=\"loadDoc()\">Zarejestruj</button><br><br>\n");
		out.append("<input id=\"btntest\" class=\"button\" type=\"button\" value=\"Logowanie\"\n");
		out.append("onclick=\"return btntest_onclick()\" />\n");

		out.append("<script>\n");
		out.append("function loadDoc() {\n");
		out.append("var haslo = document.getElementById(\"haslo\").value;\n");
		out.append("var email = document.getElementById(\"email\").value\n");
		out.append("var kodp = document.getElementById(\"kodp\").value;\n");
		out.append("var PESEL = document.getElementById(\"PESEL\").value;\n");
		out.append("var imie = document.getElementById(\"imie\").value;\n");
		out.append("var nazwisko = document.getElementById(\"nazwisko\").value;\n");
		out.append("var nrt = document.getElementById(\"nrt\").value;\n");
		out.append("var miejscow = document.getElementById(\"miejscowosc\").value;\n");

		out.append("var xhttp = new XMLHttpRequest();\n");
		out.append("xhttp.onreadystatechange = function() {\n");
		out.append("if (xhttp.status == 200) {\n");
		out.append("document.getElementById(\"demo\").innerHTML = xhttp.responseText;\n");
		out.append("}\n");
		out.append("};\n");
		out.append("xhttp.open(\"POST\", \"users/rejestr\", true);\n");
		out.append("xhttp.setRequestHeader(\"Content-type\", \"application/json\");\n");
		out.append(
				"var data = JSON.stringify({\"id\":\"1\",\"email\":email,\"haslo\":haslo,\"KodP\":kodp,\"PESEL\":PESEL,\"imie\":imie,\"nazwisko\":nazwisko,\"ntel\":nrt,\"miejscowos\":miejscow});\n");
		out.append("xhttp.send(data);\n");
		out.append("}\n");

		out.append("function clearDoc() {\n");
		out.append("document.getElementById(\"demo\").innerHTML = \"<h2>Nothing</h2>\";\n");
		out.append("}\n");
		out.append("function myFunction(arr) {\n");
		out.append("var out = \"\";\n");
		out.append("var i;\n");
		out.append("out = arr.imie;\n");
		out.append("document.getElementById(\"demo\").innerHTML = out;\n");
		out.append("}\n");
		out.append("function btntest_onclick()\n");
		out.append("{\n");
		out.append("window.location.href = \"index.html\";\n");
		out.append("}\n");
		out.append("</script>\n");

		return out.toString();
	}

	public static String client() {

		StringBuffer out = new StringBuffer();

		// zmodyfikowac!

		out.append("<center>\n");
		out.append("<h2>Klient</h2>\n");

		out.append("<a class=\"button\" onclick=\"loadDoc()\">Wyswietl moje samochody</a><br><br>\n");

		out.append("<a class=\"button\" href=\"ASO?strona=dodajSamochody\">Dodaj Samochod</a><br><br>\n");
		out.append(
				"<a type=\"button\" class=\"button\" href=\"ASO?strona=wolneTerminy\">Sprawdz wolne terminy</a><br><br>\n");
		out.append("<a type=\"button\" class=\"button\" href=\"ASO?strona=historia\">Historia samochodu</a><br><br>\n");
		out.append("<a type=\"button\" class=\"button\" href=\"ASO?strona=uWizyta\">Umów sie na wizyte</a>\n");
		out.append("</center>\n");
		out.append("</div>\n");
		out.append("</div>\n");

		out.append("<script>\n");
		out.append("function loadDoc() {\n");
		out.append("	  var xhttp = new XMLHttpRequest();\n");
		out.append(" xhttp.onreadystatechange = function() {\n");
		out.append("   if (xhttp.status == 200) {\n");
		out.append("document.getElementById(\"content\").innerHTML = xhttp.responseText;\n");
		out.append("  }\n");
		out.append(" };\n");

		out.append(" xhttp.open(\"GET\", \"users/client?id=1\", true);\n");
		out.append(" xhttp.send();\n");
		out.append("}\n");
		out.append("</script>\n");
		return out.toString();
	}

	public static String dodajSamochody() {

		StringBuffer out = new StringBuffer();

		out.append("<font color = \"#EFEFEF\">");
		out.append("<h2>WprowadŸ dane samochodu</h2>\n");
		out.append("<center>");
		out.append("VIN<br><input type=\"text\"  class =\"tekst\" id=\"VIN\"><br><br>\n");
		out.append("Marka<br><input type=\"text\"  class =\"tekst\" id=\"Marka\"><br><br>\n");
		out.append("Model<br><input type=\"text\" class =\"tekst\"  id=\"model\"><br><br>\n");
		out.append(
				"Rok Produkcji<br><input type=\"text\"  class =\"tekst\" id=\"rok\" value=\"2016-02-05\"><br><br>\n");
		out.append("Paliwo<br><input type=\"text\" class =\"tekst\"  id=\"paliwo\"><br><br>\n");
		out.append("Pojemnoœæ<br><input type=\"text\" class =\"tekst\"  id=\"pojemnosc\"><br><br>\n");
		out.append("Moc Silnika<br><input type=\"text\"  class =\"tekst\" id=\"moc\"><br><br>\n");
		out.append("Kraj pochodzenia<br><input type=\"text\"  class =\"tekst\" id=\"kraj\"><br><br>\n");
		out.append("Przebieg<br><input type=\"text\"  class =\"tekst\" id=\"przebieg\"><br><br>\n");
		out.append("<button type=\"button\"  class=\"button\" onclick=\"loadDoc()\">Dodaj</button>\n");
		out.append("</font>\n");
		out.append("</center>");
		out.append("<script>\n");
		out.append("function loadDoc() {\n");
		out.append("var VIN = document.getElementById(\"VIN\").value;\n");
		out.append("var Marka = document.getElementById(\"Marka\").value\n");
		out.append("var model = document.getElementById(\"model\").value;\n");
		out.append("var rok = document.getElementById(\"rok\").value;\n");
		out.append("var paliwo = document.getElementById(\"paliwo\").value;\n");
		out.append("var pojemnosc = document.getElementById(\"pojemnosc\").value;\n");
		out.append("var moc = document.getElementById(\"moc\").value;\n");
		out.append("var kraj = document.getElementById(\"kraj\").value;\n");
		out.append("var przebieg = document.getElementById(\"przebieg\").value;\n");

		out.append("var xhttp = new XMLHttpRequest();\n");
		out.append("xhttp.onreadystatechange = function() {\n");
		out.append("if (xhttp.status == 200) {\n");
		// out.append("document.getElementById(\"demo\").innerHTML =
		// xhttp.responseText;\n");
		out.append("}\n");
		out.append("};\n");
		out.append("xhttp.open(\"POST\", \"users/client\", true);\n");
		out.append("xhttp.setRequestHeader(\"Content-type\", \"application/json\");\n");
		out.append(
				" var data = JSON.stringify({\"id\":\"1\",\"VIN\":VIN,\"Marka\":Marka,\"model\":model,\"rok\":rok,\"paliwo\":paliwo,\"pojemnosc\":pojemnosc,\"moc\":moc,\"kraj\":kraj,\"pezebieg\":przebieg}\n");
		out.append(" ); \n");
		out.append("xhttp.send(data);\n");
		out.append("}\n");
		out.append("</script>\n");

		return out.toString();
	}
	
	public static String historiaa(String a) {
		StringBuffer out = new StringBuffer();

		out.append("<font color = \"#EFEFEF\">\n");
		out.append("<center>\n");
		out.append(a);
		out.append("</font>\n");
		out.append("</center>\n");
	
		return out.toString();
	}

	public static String historia() {
		StringBuffer out = new StringBuffer();

		out.append("<font color = \"#EFEFEF\">\n");
		out.append("<center>\n");
		out.append("<h3>Podaj VIN samochodu</h3>\n");
		
		
		out.append("<form method=\"post\">");
		out.append("<input type=\"text\" class =\"tekst\" name=\"haslo\" id=\"haslo\"><br />\n");
		out.append("<input class=\"button\" type=\"submit\" value=\"Wyœwietl\">");
		out.append("</form>");
		
		
		//out.append("<button type=\"button\"  class =\"button\" onclick=\"loadDoc()\">SprawdŸ</button>\n");
		// out.append("<div id =\"content\"> </div>\n");
		out.append("</font>\n");
		out.append("</center>\n");
		out.append("<script>\n");
		out.append("function loadDoc() {\n");
		out.append("var xhttp = new XMLHttpRequest()\n");
		out.append("var marka = document.getElementById(\"haslo\").value;\n");
		out.append("xhttp.onreadystatechange = function() {\n");
		out.append("if (xhttp.status == 200) {\n");
		out.append("document.getElementById(\"content\").innerHTML = xhttp.responseText;\n");
		out.append("}\n");
		out.append("};\n");
		out.append("xhttp.open(\"GET\", \"users/client?id=3&marka=\" + marka, true);\n");
		out.append("xhttp.send();\n");
		out.append("}\n");
		out.append("</script>\n");

		return out.toString();
	}

	public static String serwisant() {
		StringBuffer out = new StringBuffer();

		out.append("<center>\n");
		out.append("<font color = \"#EFEFEF\">\n");
		out.append("<h1>Serwisant</h1>\n");

		out.append(
				"<a href=\"ASO?strona=tSerwis\"><button type=\"button\" class=button>Stworz nowy serwis</button></a><br><br>\n");
		out.append(
				"<a href=\"ASO?strona=dodPracownika\"><button type=\"button\" class=\"button\" >Dodaj nowego pracownika</button></a><br><br>\n");
		out.append(
				"<a href=\"ASO?strona=cVIN\"><button type=\"button\" class=\"button\" >Sprawdz samochod po VIN</button></a><br><br>\n");
		out.append(
				"<a href=\"ASO?strona=addwizy\"><button type=\"button\" class=\"button\" >Dodaj nowa wizyte</button></a><br><br>\n");
		out.append(
				"<a href=\"ASO?strona=napraw\"><button type=\"button\" class=\"button\" >Naprawa</button></a><br><br>\n");
		out.append(
				"<a href=\"ASO?strona=bramka\"><button type=\"button\" class=\"button\" >Bramka sms</button></a><br><br>\n");

		out.append("<script>\n");


		out.append("function loadDoc() {\n");
		out.append("  var xhttp = new XMLHttpRequest();\n");
		out.append("  xhttp.onreadystatechange =\n");

		out.append("	function() {\n");
		out.append("  if (xhttp.status == 200) {\n");
		out.append("  	document.getElementById(\"content\").innerHTML = xhttp.responseText;\n");
		out.append(" }\n");
		out.append(" };\n");

		out.append("	xhttp.open(\"GET\",\"Servisant?id=2\",true);xhttp.send();\n");

		out.append("}\n");

		out.append("function loadDoc2() {\n");
		out.append("	  var xhttp = new XMLHttpRequest();\n");
		out.append("	  xhttp.onreadystatechange =\n");

		out.append("function() {\n");
		out.append("    if (xhttp.status == 200) {\n");
		out.append("    	document.getElementById(\"content\").innerHTML = xhttp.responseText;\n");
		out.append("    }\n");
		out.append("  };\n");

		out.append("xhttp.open(\"GET\",\"users/client?id=2\",true);xhttp.send();\n");

		out.append("}\n");

		out.append("function newbuton() {\n");
		out.append("	var btn = document.createElement(\"BUTTON\");\n");
		out.append("	document.body.appendChild(btn);\n");
		out.append("	}1\n");

		out.append("	function clearDoc() {\n");
		out.append("		document.getElementById(\"demo\").innerHTML = \"<h2>Nothing</h2>\";\n");
		out.append("	}\n");

		out.append("	function myFunction(arr) {\n");
		out.append("		var out=\"\";var i;out=arr.imie;document.getElementById(\"demo\").innerHTML=out;\n");
		out.append("	}\n");

		out.append("	function btntest_onclick() {\n");
		out.append("		window.location.href = \"rejestracja.html\";\n");
		out.append("	}\n");
		out.append("	function client() {\n");
		out.append("		window.location.href = \"Client.html\";\n");
		out.append("	}\n");
		out.append("	</script>\n");

		return out.toString();

	}

	public static String uWizyta() {
		StringBuffer out = new StringBuffer();

		out.append("<font color = \"#EFEFEF\">\n");
		out.append("<h2>Umów siê na wizytê</h2>\n");
		
		out.append("<form method=\"post\">");
		out.append("Podaj date: <br><input type=\"text\"  class =\"tekst\" name=\"datepicker\" id=\"datepicker\" autofocus=\"0\"><br><br>");
		out.append("Nazwa serwisu: <br><input type=\"text\" class =\"tekst\" id=\"nazwa\" name=\"nazwa\"><br><br>");
		out.append("Model samochodu: <br><input type=\"text\" class =\"tekst\" id=\"model\" name=\"model\"><br><br>");
		out.append("<input class=\"button\"type=\"submit\" value=\"ZatwierdŸ\">");
		out.append("</form>");
		
		out.append("<script src=\"jq/jquery-ui-1.11.4.custom/external/jquery/jquery.js\"></script>\n");
		out.append("<script src=\"jq/jquery-ui-1.11.4.custom/jquery-ui.js\"></script>\n");
		out.append("<script>\n");
		out.append("$(function() {\n");
		out.append("$( \"#datepicker\" ).datepicker();\n");
		out.append("});\n");
		out.append("function loadDoc() {\n");
		out.append("var Data = document.getElementById(\"data\").value;\n");
		out.append("var nazwa = document.getElementById(\"nazwa\").value\n");
		out.append("var model = document.getElementById(\"model\").value;\n");
		out.append("var xhttp = new XMLHttpRequest();\n");
		out.append(" xhttp.onreadystatechange = function() {\n");
		out.append("if (xhttp.status == 200) {\n");

		out.append("}\n");
		out.append("};\n");
		out.append("xhttp.open(\"POST\", \"users/client\", true);\n");
		out.append("xhttp.setRequestHeader(\"Content-type\", \"application/json\");\n");
		out.append("var data = JSON.stringify({\"id\":\"2\",\"data\":Data,\"nazwa\":nazwa,\"model\":model});\n");
		out.append("xhttp.send(data);\n");
		out.append("}\n");
		out.append("</script>\n");

		return out.toString();
	}

	public static String wolneTerminy(String dzien, String dzien2) {
		StringBuffer out = new StringBuffer();

		out.append("<center><div id=\"datepicker\" style=\"margin-left: 400px;\"></div>\n");
		out.append("<script>\n");
		out.append("$(function() {\n");
		out.append("$(\"#datepicker\").datepicker({\n");
		out.append("beforeShowDay: function(date) {\n");
		out.append("var date1 = new Date()\n");
		out.append("date1.setDate('" + dzien + "')\n");
		out.append("var date2 = new Date()\n");
		out.append("date2.setDate('" + dzien2 + "')\n");
		out.append(
				"return [true, date1 && ((date.getTime() == date1.getTime()) || (date.getDate() == date2.getDate() || date.getDate() == date1.getDate())) ? \"dp-highlight\" : \"\"];\n");
		out.append("}\n");
		out.append("});\n");
		out.append("});\n");
		out.append("</script></center>\n");

		return out.toString();
	}

	public static String napraw() {

		StringBuffer out = new StringBuffer();
		
		
		out.append("<form method=\"post\">");
		out.append("<font color = \"#EFEFEF\">\n");
		out.append("<center>\n");
		out.append("<h3>Naprawa</h3>\n");
		out.append("Data naprawy<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" name=\"Data_Naprawy\" id=\"Data_Naprawy\" value=\"\"><br><br>\n");
		out.append("Typ naprawy<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" name=\"Typ_Naprawy\" id=\"Typ_Naprawy\" value=\"\"><br><br>\n");
		out.append("Przebieg<br>\n");
		out.append("<input type=\"text\" class =\"tekst\" name=\"Przebieg\" id=\"Przebieg\" value=\"\"><br>\n");
		out.append("Koszt<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" name=\"Koszt\" id=\"Koszt\" value=\"\"><br><br>\n");
		out.append("VIN<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" name=\"VIN\" id=\"VIN\" value=\"\"><br><br>\n");
		out.append("ID Serwis<br>\n");
		out.append("<input type=\"text\" class =\"tekst\" name=\"ID_Serwis\" id=\"ID_Serwis\" value=\"\"><br>\n");
		out.append("<center>\n");
		out.append("<input  class=\"button\" type=\"submit\" value=\"Zatwierdz\"><br><br>");
		out.append("<a href=\"ASO?strona=wyswietlNaprawy\"><button type=\"button\" class=\"button\" >SprawdŸ planowane naprawy</button></a><br>");
		out.append("</font>\n");
		out.append("</form>");
		out.append("</center>\n");

		out.append("<script>\n");
		out.append("function myFunction() {\n");

		out.append("var xhttp = new XMLHttpRequest();\n");
		out.append("xhttp.onreadystatechange = function() {\n");
		out.append("if (xhttp.status == 200) {\n");
		out.append("document.getElementById(\"content\").innerHTML = xhttp.responseText;\n");
		out.append("}\n");
		out.append("};\n");

		out.append("xhttp.open(\"GET\", \"Servisant?id=5\", true);\n");
		out.append("xhttp.send();\n");
		out.append("}\n");
		out.append("function myFunction2() {\n");
		out.append("var id = document.getElementById(\"id\").value;\n");
		out.append("var typ = document.getElementById(\"typ\").value;\n");
		out.append("var koszt = document.getElementById(\"koszt\").value;\n");
		out.append("var xhttp = new XMLHttpRequest();\n");
		out.append("xhttp.onreadystatechange = function() {\n");
		out.append("if (xhttp.status == 200) {\n");

		out.append("document.getElementById(\"demo\").innerHTML = xhttp.responseText;\n");
		out.append("}\n");
		out.append("};\n");
		out.append("xhttp.open(\"POST\", \"Servisant\", true);\n");
		out.append("xhttp.setRequestHeader(\"Content-type\", \"application/json\");\n");
		out.append("var data = JSON.stringify({\"id\":\"2\",\"ida\":id,\"typ\":typ,\"koszt\":koszt});\n");
		out.append("xhttp.send(data);\n");
		out.append("}\n");
		out.append("</script>\n");

		return out.toString();
	}
	
	public static String dodPracownika() {
		StringBuffer out = new StringBuffer();

		out.append("<center>\n");
		out.append("<form method=\"post\">");
		out.append("<h2>Dodaj nowego pracownika</h2>\n");
		out.append("<h4>PESEL<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" id=\"PESEL\" name=\"PESEL\" value=\"\"><br><br>\n");
		out.append("Imiê<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" id=\"imie\" name=\"imie\"value=\"\"><br><br>\n");
		out.append("Nazwisko<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" id=\"nazwisko\" name=\"nazwisko\"value=\"\"><br><br>\n");
		out.append("Miejscowoœæ<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" id=\"miejscowosc\" name=\"miejscowosc\"value=\"\"><br><br>\n");
		out.append("Kod pocztowy<br>\n");
		out.append("<input type=\"text\" class =\"tekst\"  id=\"kodp\" name=\"kodp\"value= \"\"><br><br>\n");
		out.append("Numer telefonu<br>\n");
		out.append("<input type=\"text\" class =\"tekst\"  id=\"nrt\" name=\"nrt\"value=\"\"><br><br>\n");
		out.append("E-mail<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" id=\"email\" name=\"email\"value=\"\"><br><br>\n");
		out.append("Has³o<br>\n");
		out.append("<input type=\"password\"  class =\"tekst\" id=\"haslo\" name=\"haslo\"value=\"\"><br><br>\n");
		out.append("Stanowisko<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" id=\"stan\" name=\"stan\"value=\"\"><br><br>\n");
		out.append("</h4>\n");
		out.append("<input class=\"button\" type=\"submit\" value=\"Zatwierdz\">");
		out.append("</form>");
		out.append("</font>\n");
		out.append("</center>\n");
	
		return out.toString();
	}

	public static String dodprac() {
		StringBuffer out = new StringBuffer();

		// out.append("<div id=\"content\">\n");
		out.append("<center>\n");
		out.append("<h2>Dodaj nowego pracownika</h2>\n");
		out.append("<h4>PESEL<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" id=\"PESEL\" value=\"\"><br><br>\n");
		out.append("Imiê<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" id=\"imie\" value=\"\"><br><br>\n");
		out.append("Nazwisko<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" id=\"nazwisko\" value=\"\"><br><br>\n");
		out.append("Miejscowoœæ<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" id=\"miejscowosc\" value=\"\"><br><br>\n");
		out.append("Kod pocztowy<br>\n");
		out.append("<input type=\"text\" class =\"tekst\"  id=\"kodp\" value= \"\"><br><br>\n");
		out.append("Numer telefonu<br>\n");
		out.append("<input type=\"text\" class =\"tekst\"  id=\"nrt\" value=\"\"><br><br>\n");
		out.append("E-mail<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" id=\"email\" value=\"\"><br><br>\n");
		out.append("Has³o<br>\n");
		out.append("<input type=\"password\"  class =\"tekst\" id=\"haslo\" value=\"\"><br><br>\n");
		out.append("Stanowisko<br>\n");
		out.append("<input type=\"text\"  class =\"tekst\" id=\"stan\" value=\"\"><br><br>\n");
		out.append("</h4>\n");
		out.append("<button type=\"button\" class=\"button\" onclick=\"loadDoc()\">Zarejestruj</button><br><br>\n");
		out.append("<input id=\"btntest\" type=\"button\" class=\"button\" value=\"Zaloguj\" \n");
		out.append("onclick=\"return btntest_onclick()\" />\n");
		out.append("</font>\n");
		out.append("</center>\n");

		out.append("<script>\n");
		out.append("function loadDoc() {\n");
		out.append("var haslo = document.getElementById(\"haslo\").value;\n");
		out.append("var email = document.getElementById(\"email\").value\n");
		out.append("var kodp = document.getElementById(\"kodp\").value;\n");
		out.append("var PESEL = document.getElementById(\"PESEL\").value;\n");
		out.append("var imie = document.getElementById(\"imie\").value;\n");
		out.append("var nazwisko = document.getElementById(\"nazwisko\").value;\n");
		out.append("var nrt = document.getElementById(\"nrt\").value;\n");
		out.append("var miejscow = document.getElementById(\"miejscowosc\").value;\n");
		out.append("var stan = document.getElementById(\"stan\").value;\n");

		out.append("var xhttp = new XMLHttpRequest();\n");
		out.append("xhttp.onreadystatechange = function() {\n");
		out.append("if (xhttp.status == 200) {\n");
		out.append("document.getElementById(\"demo\").innerHTML = xhttp.responseText;\n");
		out.append("}\n");
		out.append("};\n");
		out.append("xhttp.open(\"POST\", \"Servisant\", true);\n");
		out.append("xhttp.setRequestHeader(\"Content-type\", \"application/json\");\n");

		out.append(
				"var data = JSON.stringify({\"id\":\"1\",\"email\":email,\"haslo\":haslo,\"KodP\":kodp,\"PESEL\":PESEL,\"imie\":imie,\"nazwisko\":nazwisko,\"ntel\":nrt,\"miejscowos\":miejscow,\"stan\":stan});\n");
		out.append("xhttp.send(data);\n");
		out.append("}\n");

		out.append("function clearDoc() {\n");
		out.append("document.getElementById(\"demo\").innerHTML = \"<h2>Nothing</h2>\";\n");
		out.append("}\n");
		out.append("function myFunction(arr) {\n");
		out.append("var out = \"\";\n");
		out.append("var i;\n");
		out.append("out = arr.imie;\n");
		out.append("document.getElementById(\"demo\").innerHTML = out;\n");
		out.append("}\n");
		out.append("function btntest_onclick()\n");
		out.append("{\n");
		out.append("window.location.href = \"sLogin.html\";\n");
		out.append("}\n");
		out.append("</script>\n");
		return out.toString();
	}

	public static String bramka() {
		StringBuilder out = new StringBuilder();

		// out.append("<button type=\"button\" class=\"button\"
		// onclick=\"\">Wyœlij</button><br><br>\n");
		// out.append("<script>\n");
		//
		// out.append("var tekstbramkisms =
		// document.getElementById(\"bramka\").value;\n");

		// out.append("<div id=\"content\">\n");
		out.append("<center>\n");
		out.append("<font color = \"#fff\" size = 15 > Bramka SMS </font>\n");
		out.append("<form action=\"Auth?akcja=zaloguj\">  \n");
		out.append("<input type=\"text\"  class =\"tekst\" name=\"bramka\" value=\"\"><br><br>\n");

		out.append("<input  class =\"button\" type=\"submit\" value=\"Wyslij sms\">\n");
		out.append("</form>  \n");
		out.append("</center>\n");

		return out.toString();
	}

	public static String wyslanoSms() {
		StringBuilder out = new StringBuilder();

		// out.append("<button type=\"button\" class=\"button\"
		// onclick=\"\">Wyœlij</button><br><br>\n");
		// out.append("<script>\n");
		//
		// out.append("var tekstbramkisms =
		// document.getElementById(\"bramka\").value;\n");

		// out.append("<div id=\"content\">\n");
		out.append("<center>\n");
		out.append("<font color = \"#fff\" size = 20 > Wyslano sms </font>\n");

		out.append("</center>\n");

		return out.toString();
	}

	////////////
	////////////
	// NOWY KOD//
	////////////
	////////////
	public static String tSerwis() {
		StringBuilder out = new StringBuilder();
		out.append("<font color = \"#EFEFEF\">\n");
		out.append("<center>\n");
		out.append("<h3>Tworzenie serwisu</h3>\n");
		out.append("<input class=\"tekst\" type=\"text\" id=\"nazwa\" value=\"\">\n");
		out.append("<br /><button class=\"button\" type=\"button\" onclick=\"loadDoc()\">Stwórz</button>\n");
		out.append("</font>\n");
		out.append("</center>\n");

		out.append("<script>\n");
		out.append("function loadDoc() {\n");
		out.append("var xhttp = new XMLHttpRequest();\n");
		out.append("var nazwa = document.getElementById(\"nazwa\").value;\n");
		out.append("xhttp.onreadystatechange = function() {\n");
		out.append("if (xhttp.status == 200) {\n");
		out.append("document.getElementById(\"content\").innerHTML = \"Stworzono serwis!\";\n");
		out.append("}\n");
		out.append("};\n");
		out.append("xhttp.open(\"GET\", \"Servisant?id=1&serwis=\" + nazwa, true);\n");
		out.append("xhttp.send();\n");
		out.append("}\n");
		out.append("</script>\n");

		return out.toString();
	}

	// Serwisant
	public static String cVIN() {
		StringBuilder out = new StringBuilder();

		out.append("<font color = \"#EFEFEF\">\n");
		out.append("<center>\n");
		out.append("<h3>Podaj VIN</h3>\n");
		out.append("<input type=\"text\" class=\"tekst\" id=\"haslo\" value=\"\">\n");
		out.append("<br /><button type=\"button\" class=\"button\" onclick=\"loadDoc()\">SprawdŸ</button>\n");
		out.append("</font>\n");
		out.append("</center>\n");
		out.append("<script>\n");
		out.append("function loadDoc() {\n");
		out.append("var xhttp = new XMLHttpRequest();\n");
		out.append("var VIN = document.getElementById(\"haslo\").value;\n");
		out.append("xhttp.onreadystatechange = function() {\n");
		out.append("if (xhttp.status == 200) {\n");
		out.append("document.getElementById(\"content\").innerHTML = xhttp.responseText;\n");
		out.append("}\n");
		out.append("};\n");
		out.append("xhttp.open(\"GET\", \"Servisant?id=3&VIN=\" + VIN, true);\n");
		out.append("xhttp.send();\n");
		out.append("}\n");
		out.append("</script>\n");

		return out.toString();

	}

	// Serwisant
	public static String addwizy() {
		StringBuilder out = new StringBuilder();

		out.append("<font color = \"#EFEFEF\">\n");
		out.append("<center>\n");
		out.append("<h3>Podaj date w formacie </h3>\n");
		out.append("<input type=\"text\" class=tekst id=\"haslo\" value=\"2016-06-07\">\n");
		out.append("<br /><button typtype=\"button\" class=\"button\" onclick=\"loadDoc2()\">Dodaj</button>\n");
		// out.append("<div id =\"content\"> </div>\n");
		out.append("</font>\n");
		out.append("</center>\n");
		out.append("<script>\n");
		out.append("function loadDoc2() {\n");
		out.append("var xhttp = new XMLHttpRequest();\n");
		out.append("var dodaj = document.getElementById(\"haslo\").value;\n");
		out.append("xhttp.onreadystatechange = function() {\n");
		out.append("if (xhttp.status == 200) {\n");
		out.append("document.getElementById(\"content\").innerHTML = xhttp.responseText;\n");
		out.append("}\n");
		out.append("};\n");
		out.append("xhttp.open(\"GET\", \"Servisant?id=4&dodaj=\" + dodaj, true);\n");
		out.append("xhttp.send();\n");
		out.append("}\n");
		out.append("</script>\n");
		return out.toString();
	}
	
	public static String wyswietlNaprawy(String naprawy) {
		StringBuilder out = new StringBuilder();
		out.append("<font color = \"#EFEFEF\">\n");
		out.append("<center>\n");
		out.append(naprawy);
		out.append("</font>\n");
		out.append("</center>\n");
		return out.toString();
	}

}
