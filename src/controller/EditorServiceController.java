package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Article;
import beans.User;

/**
 * Servlet implementation class EditorServiceController
 */
public class EditorServiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditorServiceController() {
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

		if (articleId != null) {
			response.setContentType("application/pdf");
			// ServletContext ctx = getServletContext();
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if (user == null) {
				request.setAttribute("message",
						"You need to login to continue");
				request.getRequestDispatcher("/home.jsp")
						.forward(request, response);
			} else {
				try {
					System.out.println("inside try");
					@SuppressWarnings("unchecked")
					ArrayList<Article> articles = (ArrayList<Article>) session
							.getAttribute("editorArticles");
					String fileName = articles.get(Integer.valueOf(articleId))
							.getPdfPath();

					String relativePath = getServletContext().getRealPath("")
							+ File.separator + "resources" + File.separator
							+ fileName;
					System.out.println(relativePath);
					File nfsPDF = new File(relativePath);
					FileInputStream fis;

					fis = new FileInputStream(nfsPDF);
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
				} catch (Exception e) {
					request.setAttribute("message", "pdf not found");
					try {
						request.getRequestDispatcher("/home.jsp").forward(
								request, response);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						request.setAttribute("message", "pdf not found");
						request.getRequestDispatcher("/home.jsp").forward(
								request, response);

					}
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
	}

}
