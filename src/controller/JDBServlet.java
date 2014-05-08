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
import javax.servlet.http.HttpSession;

import beans.User;

import com.mysql.jdbc.Connection;

import database.ArticleTable;
import database.Form;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (session.getAttribute("user") == null) {
			request.setAttribute("message", "You need to login the system");
			request.getRequestDispatcher("/home.jsp")
					.forward(request, response);
		} else if (user.getRole().equals("reader")) {
			request.setAttribute("message",
					"You logged is as a reader. You do not have permission to open the page");
			request.getRequestDispatcher("/home.jsp")
					.forward(request, response);
		} else {

			String action = null;
			action = request.getParameter("action");
			Connection conn;

			if (action == null || action.equals("select_article")) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team107?user=team107&password=8b8ba518";
					conn = (Connection) DriverManager.getConnection(DB);
					// get article details
					ArticleTable articleTable = new ArticleTable(conn);
					ResultSet result = articleTable.getArticlesToSelect(user
							.getId());
					request.setAttribute("article", result);
					// get authors of article

					request.getRequestDispatcher("/views/article_list.jsp")
							.forward(request, response);
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					PrintWriter out = response.getWriter();
					out.println("Sorry the sql connection is failing");
				}

			} else if (action == null || action.equals("approved_article")) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team107?user=team107&password=8b8ba518";
					conn = (Connection) DriverManager.getConnection(DB);
					// get article details
					ArticleTable articleTable = new ArticleTable(conn);
					ResultSet result = articleTable.getApprovedArticles(user
							.getId());
					request.setAttribute("article", result);
					// get authors of article
					request.getRequestDispatcher(
							"/views/article_approved_forms.jsp").forward(
							request, response);
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					PrintWriter out = response.getWriter();
					out.println("Sorry the sql connection is failing");
				}
			} else if (action == null || action.equals("get_form")) {

				// to get article_id
				int article_id = Integer.parseInt(request
						.getParameter("article_id"));

				try {
					Class.forName("com.mysql.jdbc.Driver");
					String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team107?user=team107&password=8b8ba518";
					conn = (Connection) DriverManager.getConnection(DB);
					// get form details
					Form form = new Form(conn);
					System.out.println(article_id);
					ResultSet result = form.getReviewFormsByArticle_Reviewer(
							article_id, user.getId());
					result.next();
					// System.out.println(result.getString("id"));
					request.setAttribute("form", result);
					// get authors of article
					request.getRequestDispatcher(
							"/views/form.jsp?article_id=" + article_id)
							.forward(request, response);
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					PrintWriter out = response.getWriter();
					out.println("Sorry the sql connection is failing");
				}
			} else if (action == null || action.equals("await_selection")) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team107?user=team107&password=8b8ba518";
					conn = (Connection) DriverManager.getConnection(DB);
					ArticleTable articleTable = new ArticleTable(conn);
					ResultSet resultSet = articleTable.getAwaitingArticles(user
							.getId());
					request.setAttribute("article", resultSet);
					// get authors of article
					request.getRequestDispatcher(
							"/views/reviewer_await_select_list.jsp").forward(
							request, response);
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				System.out.println(action);

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

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (session.getAttribute("user") == null) {
			request.setAttribute("message", "You need to login the system");
			request.getRequestDispatcher("/home.jsp")
					.forward(request, response);
		} else if (user.getRole().equals("reader")) {
			request.setAttribute("message",
					"You logged is as a reader. You do not have permission to open the page");
			request.getRequestDispatcher("/home.jsp")
					.forward(request, response);
		} else {
			String action = null;
			action = request.getParameter("action");
			Connection conn;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team107?user=team107&password=8b8ba518";
				conn = (Connection) DriverManager.getConnection(DB);

				if (action == null || action.equals("download")) {

					// to get article_id
					int article_id = Integer.parseInt(request
							.getParameter("article_id"));
					// get form details
					Form form = new Form(conn);
					// change status to download
					form.downloadArticle(article_id, user.getId());

					// ResultSet result = form.getReviewFormsByArticle_Reviewer(
					// article_id, user.getId());
					// System.out.println(result.getString("id"));
					// request.setAttribute("form", result);
					// // get authors of article
					// request.getRequestDispatcher(
					// "/views/form.jsp?article_id=" + article_id)
					// .forward(request, response);
				} else if (action == null
						|| action.equals("delete_select_await")) {
					Form form = new Form(conn);
					int article_id = Integer.parseInt(request
							.getParameter("article_id"));
					form.cancelSelect(article_id, user.getId());
					ArticleTable articleTable = new ArticleTable(conn);
					ResultSet resultSet = articleTable.getAwaitingArticles(user
							.getId());
					request.setAttribute("article", resultSet);
					// get authors of article
					request.getRequestDispatcher(
							"/views/reviewer_await_select_list.jsp").forward(
							request, response);
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
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
}
