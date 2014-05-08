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
	
	public void insertReason(int reviewer_id, int article_id,String title, String content) throws SQLException{
		Statement st = conn.createStatement();
		String sql1 = "select * from forms where reviewer_id='"+reviewer_id+"' and article_id='"+article_id+"'";
		ResultSet rs = st.executeQuery(sql1);
		rs.next();
		int form_id =  rs.getInt("id");
		// insert into reason list if not exist
		String sql = "insert into reviewer_reason_list (form_id,reviewer_id,title) "
				+ "select distinct "
				+ "'"
				+ form_id
				+ "','"
				+ reviewer_id
				+ "','"
				+ title
				+ "' from reviewer_reason_list"
				+ " where not exists (select * from reviewer_reason_list where form_id='"
				+ form_id
				+ "' and reviewer_id='"
				+ reviewer_id
				+ "' and title = '" + title + "')";
		System.out.println("reviewer_reason_list sql :"+sql);
		st.execute(sql);
		// get reason id
		sql = "select id from reviewer_reason_list where form_id = '"+form_id+"' and reviewer_id = '"+reviewer_id+"' and title ='"+title+"'";
		ResultSet rSet = st.executeQuery(sql);
		rSet.next();
		int reason_id = rSet.getInt("id");
		
		// insert into comment
		sql = "insert into comments (reason_id,content,parent_id) values ('"+reason_id+"','"+content+"','-1')";
		st.execute(sql);
		st.close();
	}

}
