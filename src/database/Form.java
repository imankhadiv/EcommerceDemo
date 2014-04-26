package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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


}
