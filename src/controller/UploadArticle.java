package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import beans.Article;
import database.Account;
import database.ArticleTable;
import database.Email;

/**
 * Servlet implementation class UploadArticle
 */
public class UploadArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "resources";
    
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
	private Connection conn;
	private String email;
	private String firstname;
	private String lastname;
	private String title;
	private String abst;
	private String keywords;
	private String file;
    
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadArticle() {
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
	}
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team107?user=team107&password=8b8ba518";
			conn = DriverManager.getConnection(DB);
			Account account = new Account(conn);
            
			uploadFile(request, response);
			Email mail = new Email();
			if (account.exists(email)) {
                //				System.out.println(email);
                //				request.setAttribute("message",
                //						"An account already exists with this email!");
                //				request.getRequestDispatcher("/views/upload-article.jsp")
                //						.forward(request, response);
				mail.sendEmailForUploadArticle(email, firstname);
				
			}
			else {
				String password = mail.generatePassword();
				account.create(email, password, firstname, lastname);
				mail.sendEmailForUploadArticle(email, firstname,password);
				
			}
            
			
			int userId = account.getUserId(email);
			account.changeRole("Author", userId);
			Article article = new Article(title, abst, String.valueOf(userId),
                                          keywords, file);
			ArticleTable articleTable = new ArticleTable(conn, article);
			articleTable.insertIntoArticles();
			
            
			request.setAttribute("message", "Your artilce was uploaded sucessfully. You will receive an email shortly");
			request.getRequestDispatcher("/home.jsp")
            .forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
    
	public void uploadFile(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        
		// configures upload settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// sets memory threshold - beyond which files are stored in disk
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// sets temporary location to store files
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        
		ServletFileUpload upload = new ServletFileUpload(factory);
        
		// sets maximum size of upload file
		upload.setFileSizeMax(MAX_FILE_SIZE);
        
		// sets maximum size of request (include file + form data)
		upload.setSizeMax(MAX_REQUEST_SIZE);
        
		// constructs the directory path toup store upload file
		// this path is relative to application's directory
        		String uploadPath = getServletContext().getRealPath("")
        				+ File.separator + UPLOAD_DIRECTORY;
		///String uploadPath = getServletContext().getRealPath("/WEB-INF/pdf"); FILES SHOULD BE SAVED ON WEBCONTENT ROOT INSIDE RESOURCES
		System.out.println(uploadPath);
        
		// creates the directory if it does not exist
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
        
		try {
			// parses the request's content to extract file data
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);
            
			if (formItems != null && formItems.size() > 0) {
				// iterates over form's fields
				for (FileItem item : formItems) {
					// processes only fields that are not form fields
					if (!item.isFormField()) {
                        
						String fileName = new File(item.getName()).getName();
						String filePath = uploadPath + File.separator
                        + fileName;
						System.out.println("full path:"+filePath);
						file = fileName;
						File storeFile = new File(filePath);
						// saves the file on disk
						item.write(storeFile);
						request.setAttribute("message",
                                             "Upload has been done successfully!");
					} else {
						if (item.getFieldName().equals("email"))
							this.email = setInputValue(item);
						if (item.getFieldName().equals("firstname"))
							this.firstname = setInputValue(item);
						if (item.getFieldName().equals("lastname"))
							this.lastname = setInputValue(item);
						if (item.getFieldName().equals("title"))
							this.title = setInputValue(item);
						if (item.getFieldName().equals("abstract"))
							this.abst = setInputValue(item);
						if (item.getFieldName().equals("keywords"))
							this.keywords = setInputValue(item);
                        
					}
                    
				}
			}
		} catch (Exception ex) {
			request.setAttribute("message",
                                 "There was an error: " + ex.getMessage());
		}
        
	}
    
	private String setInputValue(FileItem item) throws IOException {
        
		InputStream is = item.getInputStream();
		byte[] b = new byte[is.available()];
		is.read(b);
		String value = new String(b);
		return value;
        
	}
    
}
