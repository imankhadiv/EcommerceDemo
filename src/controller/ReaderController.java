package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String articleId = request.getParameter("id");
		String searchBy = request.getParameter("action");
		String text = request.getParameter("value");
		searchBy="author";
		text="iman";
		if(articleId != null){ 
			response.setContentType("application/pdf");
			 // ServletContext ctx = getServletContext();
			HttpSession session = request.getSession(false);
			@SuppressWarnings("unchecked")
			ArrayList<Article> articles = (ArrayList<Article>) session.getAttribute("readerArticles");
			String fileName = articles.get(Integer.valueOf(articleId)).getPdfPath();
			
			  String relativePath =  getServletContext().getRealPath("") + File.separator + "resources" + File.separator+ fileName;
			  System.out.println(relativePath);
			File nfsPDF = new File(relativePath);
		       FileInputStream fis = new FileInputStream(nfsPDF);
		       BufferedInputStream bis = new BufferedInputStream(fis);
		       ServletOutputStream sos = response.getOutputStream();
		       byte[] buffer = new byte[5120];
		       while (true) {
		         int bytesRead = bis.read(buffer, 0, buffer.length);
		         if (bytesRead < 0) {
		           break;
		         }
		       sos.write(buffer, 0, bytesRead);
		       sos.flush();
		       }
		       sos.flush();
		       bis.close();
			
		}else{
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team107?user=team107&password=8b8ba518";
			conn = DriverManager.getConnection(DB);
			ArticleTable articleTable = new ArticleTable(conn);

			if (searchBy.equals("author")) {
				ArrayList<Article> articles = articleTable.getArticlesByAuthorName(text);
				System.out.println(articles.size());
				HttpSession session = request.getSession() ;
				System.out.println(articles);
				//System.out.println(articles.get(0).get);
				session.setAttribute("readerArticles", articles);
				request.getRequestDispatcher("/views/reader.jsp").forward(request, response);

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
