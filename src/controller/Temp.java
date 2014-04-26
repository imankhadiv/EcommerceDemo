//package controller;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.fileupload.FileItemIterator;
//import org.apache.commons.fileupload.FileItemStream;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//
//import database.FileUpload;
//
///**
// * Servlet implementation class Temp
// */
//public class Temp extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public Temp() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.setContentType("text/html");
//		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
//		
//		if(isMultiPart){
//			ServletFileUpload upload = new ServletFileUpload();
//			
//			try {
//				FileItemIterator itr = upload.getItemIterator(request);
//				while(itr.hasNext()) {
//					FileItemStream item = itr.next();
//					if(item.isFormField()){
//						//do field specific process
//						String fieldName = item.getFieldName();
//						InputStream is = item.openStream();
//						byte[] b = new byte[is.available()];
//						is.read(b);
//						String value = new String(b);
//						response.getWriter().println(fieldName+":"+value+"<br/>");
//					}else {
//						//do file upload specific process
//						String path = getServletContext().getRealPath("/");
//						//call a method
//						if(FileUpload.processFile(path,item)){
//							response.getWriter().println("file uploaded sucessfull");
//						}else{
//							response.getWriter().println("file uploaded failed");
//							
//						}
//							
//					}
//				}
//			} catch (FileUploadException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//}
