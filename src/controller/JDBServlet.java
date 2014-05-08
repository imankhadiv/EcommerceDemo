package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Comment;
import beans.User;

import com.mysql.jdbc.Connection;

import database.ArticleTable;
import database.CommentDB;
import database.Form;
import database.MistakeDB;

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
			Connection conn = null;
			try {
				if (action == null || action.equals("select_article")) {
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
				} else if (action == null || action.equals("approved_article")) {
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
				} else if (action == null || action.equals("get_form")) {
					// to get article_id
					int article_id = Integer.parseInt(request
							.getParameter("article_id"));
					Class.forName("com.mysql.jdbc.Driver");
					String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team107?user=team107&password=8b8ba518";
					conn = (Connection) DriverManager.getConnection(DB);
					// get form details
					Form form = new Form(conn);
					// get form details
					ResultSet result = form.getReviewFormsByArticle_Reviewer(
							article_id, user.getId());
					result.next();
					request.setAttribute("form", result);
					// get comment reason table
					CommentDB commentDB = new CommentDB(conn);
					int form_id = result.getInt("id");
					ResultSet rs = commentDB.getReasonCommentByFormID(form_id);
					request.setAttribute("reason", rs);
					// System.out.println(result.getString("id"));
					// get mistake table
					MistakeDB mistakeDB = new MistakeDB(conn);
					ResultSet rSet = mistakeDB.getMistakesByForm(user.getId(),
							article_id);
					request.setAttribute("mistake", rSet);
					request.getRequestDispatcher(
							"/views/form.jsp?article_id=" + article_id)
							.forward(request, response);
				} else if (action == null || action.equals("await_selection")) {
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
				} else if (action == null || action.equals("comment")) {
					Class.forName("com.mysql.jdbc.Driver");
					String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team107?user=team107&password=8b8ba518";
					conn = (Connection) DriverManager.getConnection(DB);
					CommentDB commentDB = new CommentDB(conn);
					int reason_id = Integer.parseInt(request
							.getParameter("reason_id"));
					List<Comment> commentList = new ArrayList<Comment>();
					commentList = commentDB.getFormReasonComments(reason_id);
					request.setAttribute("commentList", commentList);
					// get authors of article
					request.getRequestDispatcher("/views/comment.jsp").forward(
							request, response);
				} else {
					System.out.println(action);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				PrintWriter out = response.getWriter();
				out.println("Sorry the sql connection is failing");
			} finally {
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
			System.out.println(action);
			Connection conn = null;
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
				} else if (action == null
						|| action.equals("approve_comment_reason")) {
					System.out.println("jump");
					int reason_id = Integer.parseInt(request
							.getParameter("reason_id"));
					System.out.println("reason id is " + reason_id);
					CommentDB commentDB = new CommentDB(conn);
					commentDB.approveReason(reason_id);
				} else if (action == null || action.equals("approve_mistake")) {
					System.out.println("jump");
					MistakeDB mistakeDB = new MistakeDB(conn);
					int mistake_id = Integer.parseInt(request
							.getParameter("mistake_id"));
					int article_id = Integer.parseInt(request
							.getParameter("article_id"));
					System.out.println(article_id);
					mistakeDB.approveMistake(user.getId(), article_id,
							mistake_id);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

		}

	}
}
