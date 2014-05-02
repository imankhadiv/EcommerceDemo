package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Article;
import beans.ReviewForm;

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
	public ResultSet getSelectedArticles(int currentUserId) throws SQLException {
		Statement stst = conn.createStatement();
		ResultSet resultSet = stst
				.executeQuery("select * from forms where reviewer_id = "
						+ currentUserId);
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
	
	// for editor to approve the form
	public boolean setApproveToForms(int article_id, int reviewer_id) {
		Statement stst;
		try {
			stst = conn.createStatement();
			try {
				stst.executeQuery("update forms set form_approve=true where article_id = '"
						+ article_id
						+ "' and reviewer_id = '"
						+ article_id
						+ "' ");
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

}
