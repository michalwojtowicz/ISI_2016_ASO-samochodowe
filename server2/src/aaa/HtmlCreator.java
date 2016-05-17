package aaa;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import aaa.Strona;
import aaa.ClientDb;
/**
 * Servlet implementation class Html
 */
public class HtmlCreator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HtmlCreator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String strona = request.getParameter("strona");
		strona = Strona.prasujStrone(strona, "glowna;dodajSamochody;historia;uWizyta;wolneTerminy");
		
		if (strona.equals("blad")){
			
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Nie znaleziono elemetu");
		} else if (strona.equals("dodajSamochody")){
			
			out.println(Strona.getClientHtml(Strona.dodajSamochody()));
		} else if (strona.equals("historia")){
			
			out.println(Strona.getClientHtml(Strona.historia()));
		} else if (strona.equals("uWizyta")){
			
			out.println(Strona.getClientHtml(Strona.uWizyta(),"<link rel=\"stylesheet\" type=\"text/css\" href=\"jq/jquery-ui-1.11.4.custom/jquery-ui.css\">"));
		} else if (strona.equals("wolneTerminy")){
			
			ClientDb db = new ClientDb();
			db.init();
			String dni[] = db.getWolneTerminy().split(";");
			
			out.println(Strona.getClientHtml(Strona.wolneTerminy(dni[0],dni[1]), "	<link href=\"../assets/styles.min.css\" rel=\"stylesheet\"> "
					+ "<link href=\"//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/themes/ui-darkness/jquery-ui.min.css\" rel=\"stylesheet\"> "
					+ "<script src=\"//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>"
					+ "<script src=\"//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js\"></script>"
					+ " <style>\n .dp-highlight .ui-state-default {\n background: #FF0000;\n color: #FFF;\n}\n</style>"));
		} else if (strona.equals("glowna")){
			
			out.println(Strona.getClientHtml(Strona.glowna()));
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
