package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import database.ArticleTable;

/**
 * Servlet implementation class JDBServlet
 */
public class JDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JDBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
				String action = null;
				System.out.println(request);
				action = request.getParameter("action");
				Connection conn;
				
				if (action==null||action.equals("select_article")) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team107?user=team107&password=8b8ba518";
						conn = (Connection) DriverManager.getConnection(DB);
						ArticleTable articleTable = new ArticleTable(conn);
						ResultSet result = articleTable.getPublishedArticles();
						request.setAttribute("article", result);
						request.getRequestDispatcher("/views/article_list.jsp")
								.forward(request, response);
						
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						PrintWriter out = response.getWriter();
						out.println("Sorry the sql connection is failing");
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
