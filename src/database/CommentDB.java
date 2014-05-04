package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Comment;

public class CommentDB {
	
	private Connection conn;

	public CommentDB(Connection conn) {
		super();
		this.conn = conn;
	}
	public CommentDB() {
		
	}
	/**
	 * This method implemented to get all the comments from reviewer_reason_list table
	 * @param reasonId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Comment> getFormReasonComments(int reasonId) throws SQLException {
		Statement stst = conn.createStatement();
		ResultSet rs = stst
				.executeQuery("select * from comments where reason_id= "
						+ reasonId);
		ArrayList<Comment> comments = new ArrayList<Comment>();
		while(rs.next()){
			Comment comment = new Comment();
			comment.setId(rs.getInt("id"));
			comment.setReasonId(rs.getInt("reason_id"));
			comment.setContent(rs.getString("content"));
			comment.setCreatedAt(String.valueOf(rs.getDate("created_at")));
			comments.add(comment);
		}
		return comments;
		
		
	}
	public void insertIntoComments(int reasonId,String comment) throws SQLException {
		String sql = "insert into comments(reason_id,content) values(?,?) ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, reasonId);
		stmt.setString(2, comment);
		stmt.executeUpdate();
		//stmt.addBatch();
		stmt.close();
	}

}
