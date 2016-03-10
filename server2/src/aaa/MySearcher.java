package aaa;
import java.io.IOException;  
import java.io.PrintWriter;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
public class MySearcher extends HttpServlet {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
		PrintWriter out = response.getWriter();
		out.println("dupa");
		String haslo = request.getParameter("haslo");
        String name=request.getParameter("name");  
        response.sendRedirect("https://www.google.co.in/#q="+haslo);  
    }  
}  