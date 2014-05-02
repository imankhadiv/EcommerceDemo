package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Article;
import database.ArticleTable;

/**
 * Servlet implementation class ReaderController
 */
public class ReaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReaderController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String searchBy = request.getParameter("action");
		String text = request.getParameter("value");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team107?user=team107&password=8b8ba518";
			conn = DriverManager.getConnection(DB);
			ArticleTable articleTable = new ArticleTable(conn);

			if (searchBy.equals("author")) {
				ArrayList<Article> articles = articleTable.getArticlesByAuthorName(text);

			} else if (searchBy.equals("keyword")) {

			} else if (searchBy.equals("date")) {
				

			} else if (searchBy.equals("title")) {
				
				ArrayList<Article> articles = articleTable.getArticlesByTitle(text);
				System.out.println(articles.get(0).getTitle());

			} else {
				request.getRequestDispatcher("/home.jsp").forward(request,
						response);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
