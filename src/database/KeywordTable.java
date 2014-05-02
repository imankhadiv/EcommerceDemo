package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Article;

public class KeywordTable {
	
	private Connection conn;

	public KeywordTable(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public KeywordTable() {
		
	}
	/**
	 * This method checks if the keyword exists in the keywords table
	 * 
	 * @param word
	 * @return
	 * @throws SQLException
	 */
	public boolean checkKeyword(String word) throws SQLException {
		String sql = "select count(*) as count from keywords where word=? ";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, word);
		ResultSet rs = stmt.executeQuery();
		int count = 0;
		if (rs.next()) {
			count = rs.getInt("count");
		}
		rs.close();
		if (count == 0) {
			return false;
		} else {

			return true;
		}
	}
	public void insertIntoKeywords(Article article) throws SQLException {
		String[] keyWords = article.getKeywords().split(",");
		for (String word : keyWords) {
			if (!checkKeyword(word)) {
				String sql = "insert into keywords(word) values(?) ";

				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, word);

				stmt.executeUpdate();
				stmt.close();
			}
			String sql = "insert into article_keywords(keyword_id,article_id) values(?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, String.valueOf(getKeywordId(word)));
			//stmt.setString(2, String.valueOf(getArticleId(article.getTitle())));
			stmt.setString(2, String.valueOf(article.getId()));
			stmt.executeUpdate();
			stmt.close();
		}
	}
	/**
	 * This method get a keyword and return the id of that word in the keywords table
	 * @param word
	 * @return
	 * @throws SQLException
	 */
	public int getKeywordId(String word) throws SQLException {
		String sql = "select * from keywords where word=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, word);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			int id = Integer.valueOf(rs.getString("id"));
			return id;
		}
		rs.close();
		return 0;

	}
	public ArrayList<Integer> getArticleIdsByKeywrod(String word) throws SQLException {
		ArrayList<Integer> articleIds = new ArrayList<Integer>();
		Statement stst = conn.createStatement();
		int id = getKeywordId(word);
		ResultSet rs = stst
				.executeQuery("select * from article_keywords where keyword_id = "+ id);
		while(rs.next()){
			articleIds.add(rs.getInt("article_id"));
		}
		rs.close();
		return articleIds;
						
	}
//	public static void main(String[] args) {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team107?user=team107&password=8b8ba518";
//			Connection conn = DriverManager.getConnection(DB);
//			KeywordTable words = new KeywordTable(conn);
//			System.out.println(words.getArticleIdsByKeywrod(""));
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}


}
