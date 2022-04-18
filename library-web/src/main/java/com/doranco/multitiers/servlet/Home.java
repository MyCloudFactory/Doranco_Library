//Classe d'accés au servlet
package com.doranco.multitiers.servlet;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.doranco.multitiers.context.RemoteContext;
import com.doranco.multitiers.ejb.interfaces.Panier;
import com.doranco.multitiers.exceptions.LibraryException;

/**
 * Servlet implementation class Home
 */
//@WebServlet("/")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//@EJB 1ere méthode d'injection, la deuxiéme utilise jndi via initialcontext
	
	Panier panier; // injection de dépendance; via jndi (pas d'@EJB) car library-web et library-common ne seront pas sur la meme machine

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
				
		super();
		
		try {
			/*Version sans RemoteContext.java :InitialContext ic = new InitialContext(); //lit le contenu du fichier jndi.properties
			panier = (Panier) ic.lookup("java:global/library-ejb/PanierBean");*/
			/*Version avec RemoteContext.java*/
			panier= (Panier) RemoteContext.getInstance().getInitialContext().lookup("java:global/library-ear/library-ejb/PanierBean");
		} catch (NamingException e) {
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

		try {
			List<String> books = panier.getBooks();
			request.setAttribute("books", books);
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (LibraryException e) {

			System.out.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
