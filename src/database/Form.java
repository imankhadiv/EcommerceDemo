package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.ReviewForm;
import beans.User;

public class Form {

	private Connection conn;

	public Form(Connection conn) {
		this.conn = conn;
	}

	public Form() {

	}

	/**
	 * This method implemented to find how many articles the current user has
	 * selected for peer-review
	 * 
	 * @param currentUserId
	 * @return
	 * @throws SQLException
	 */
	public ResultSet getApprovedArticles(int currentUserId) throws SQLException {
		Statement stst = conn.createStatement();
		ResultSet resultSet = stst
				.executeQuery("select * from forms where reviewer_id ='"
						+ currentUserId+"' and article_approve=true");
		return resultSet;

	}

	public void insertIntoForm(int article_id, int auther_id,
			int reviewer_id, String level,String summary, String secret, String overall) throws SQLException {

		String sql = "insert into forms(article_id,author_id,reviewer_id,level,summary,secret_message,overall) values(?,?,?,?,?,?,?) ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, article_id);
		stmt.setInt(2, auther_id);
		stmt.setInt(3, reviewer_id);
		stmt.setString(4, level);
		stmt.setString(5, summary);
		stmt.setString(6, secret);
		stmt.setString(7, overall);
		stmt.executeUpdate();
		stmt.close();
		// insertIntoKeywords();

	}
	
	public void updateForm(int article_id, int author_id,
			int reviewer_id, String level,String summary, String secret, String overall) throws SQLException {
		
		Statement st = conn.createStatement();
		String sql = "update forms set level='"+level
				+"', summary='"+summary+"', secrete='"+secret+"', overall='"+overall+"' where article_id = '"
				+ article_id
				+ "' and author_id='"
				+ author_id
				+ "' and reviewer_id='" + reviewer_id + "'";
		st.execute(sql);
		st.close();
	}
	
	public void createForm(int article_id, int author_id,
 int reviewer_id)
			throws SQLException {
		
		Statement st = conn.createStatement();
		String sql_validString = "select count(*) as count from forms where article_id = '"
				+ article_id
				+ "' and author_id='"
				+ author_id
				+ "' and reviewer_id='" + reviewer_id + "'";
		ResultSet resultSet = st
				.executeQuery(sql_validString);
		resultSet.next();
		if(resultSet.getInt("count")==0)
		{
			String sql = "insert into forms(article_id,author_id,reviewer_id) values(?,?,?) ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, article_id);
			stmt.setInt(2, author_id);
			stmt.setInt(3, reviewer_id);
			stmt.executeUpdate();
			stmt.close();
		}
		else{
			// nothing
		}
//		st.close();
	}
	
	public void downloadArticle(int article_id, int reviewer_id) throws SQLException
	{
		String sqlString ="update forms set status='download' where article_id='"+article_id+"' and reviewer_id='"+reviewer_id+"'";
		Statement st = conn.createStatement();
		st.execute(sqlString);
	}
	
	public void updateForm(int article_id, int reviewer_id, String level,
			String summary, String secret, String overall) throws SQLException {
		String sql = "update forms set level='" + level + "', summary='"
				+ summary + "', secret_message='" + secret + "', overall='"
				+ overall + "' where article_id ='" + article_id
				+ "' and reviewer_id='" + reviewer_id + "'";
		Statement st = conn.createStatement();
		st.execute(sql);
		st.close();
	}
	
	// for editor to approve the form
	public boolean setApproveToForms(int article_id, int reviewer_id) {
		Statement stst;
		try {
			stst = conn.createStatement();
			try {
				stst.executeUpdate("update forms set form_approve = '1' where article_id = "
						+ article_id
						+ " and reviewer_id = "
						+ article_id
						+ "");
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
				return false;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}

	public ResultSet getReviewFormsByArticle_Reviewer(int article_id,
			int reviewer_id) throws SQLException {
		Statement stst = conn.createStatement();
		ResultSet rs = stst
				.executeQuery("select * from forms where article_id = '"
						+ article_id + "' and reviewer_id='" + reviewer_id
						+ "'");
		return rs;
	}

	public ArrayList<ReviewForm> getAuthorReviewForms(int articleId) throws SQLException {
		Statement stst = conn.createStatement();
		ResultSet rs = stst
				.executeQuery("select * from forms where article_id = "
						+ articleId);
		ArrayList<ReviewForm> forms = new ArrayList<ReviewForm>();
		while(rs.next()){
			ReviewForm form = new ReviewForm();
			form.setId(rs.getInt("id"));
			form.setArticleId(rs.getInt("article_id"));
			form.setAutherId(rs.getInt("author_id"));
			form.setReviewerId(rs.getInt("reviewer_id"));
			form.setLevel(rs.getString("level"));
			form.setOverall(rs.getString("overall"));
			form.setSummary(rs.getString("summary"));
			form.setSecrete(rs.getString("secret_message"));
			form.setFormApproved(rs.getString("form_approve"));
			form.setArticleApproved(rs.getString("article_approve"));
			form.setReasons(new ReasonTable(conn).getFormReasons(rs.getInt("id")));
			forms.add(form);
			
//			form.setCommentList(commentList);
//			form.setErrors(errors);
		
		}
		return forms;
	}
	/**
	 * 
	 * @param articleId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<User> getReviewersOfTheArticle(int articleId) throws SQLException {
		Statement stst = conn.createStatement();
		ResultSet rs = stst
				.executeQuery("select * from forms where article_id = "
						+ articleId);
		ArrayList<User> authors = new ArrayList<User>();
		while(rs.next()){
			User author = new User();
			author = new Account(conn).getUserById(rs.getInt("reviewer_id"));
			authors.add(author);
		}
		return authors;
	}
	

	public void cancelSelect(int article_id, int reviewer_id) throws SQLException {
		String sql = "delete from forms where article_id='"+article_id+"' and reviewer_id='"+reviewer_id+"'";
		Statement st = conn.createStatement();
		st.execute(sql);
		st.close();
	}
	
	

	public void approveTheForm(int formId) throws SQLException {
		
		Statement st = conn.createStatement();
		st.executeUpdate("update forms set form_approve = true where id = "
				+ formId );
				
		st.close();
		
	}
	
	 public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
	
	 Class.forName("com.mysql.jdbc.Driver");
	 String DB =
	 "jdbc:mysql://stusql.dcs.shef.ac.uk/team107?user=team107&password=8b8ba518";
	 Connection conn = DriverManager.getConnection(DB);
	 Form form = new Form(conn);
	 form.approveTheForm(3);
	 
	
	 }


}
