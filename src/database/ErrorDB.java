package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.ReviewForm;

public class ErrorDB {
	private Connection conn;

	public ErrorDB(Connection conn) {
		this.conn = conn;
	}

	public ErrorDB() {

	}
	
	public void insertIntoError(int article_id, int auther_id,
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
