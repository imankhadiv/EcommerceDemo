package controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;

import com.mysql.jdbc.Connection;

import database.Form;

/**
 * Servlet implementation class ArticleToSelectServlet
 */
public class ArticleToSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleToSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (session.getAttribute("user") == null) {
			request.setAttribute("message",
					"You need to login to view your articles");
			request.getRequestDispatcher("/home.jsp")
					.forward(request, response);
		} else if (user.getRole().equals("reader")) {
			request.setAttribute("message",
					"You logged is as a reader. You do not have permission to open the page");
			request.getRequestDispatcher("/home.jsp")
					.forward(request, response);
		}
		
		System.out.println("jump success");
		String[] article_id_list = request.getParameterValues("article_id");
		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team107?user=team107&password=8b8ba518";
			try {
				conn = (Connection) DriverManager.getConnection(DB);
				Form form = new Form(conn);
				
				for(int i=0;i<article_id_list.length;i++)
				{
					int article_id = Integer.parseInt(article_id_list[i]);
					//TODO create form
					//form.setApproveToForms(article_id, user.getId());
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
