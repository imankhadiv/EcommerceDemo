package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Article;
import beans.User;

public class ArticleTable {

	private Connection conn;
	private Article article;

	public ArticleTable(Connection conn, Article article) {
		this.conn = conn;
		this.article = article;
	}

	public ArticleTable(Connection conn) {
		this.conn = conn;
	}

	public ArticleTable() {

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

	public void insertIntoKeywords() throws SQLException {
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
			stmt.setString(2, String.valueOf(getArticleId(article.getTitle())));
			stmt.executeUpdate();
			stmt.close();
		}
	}

	public int getKeywordId(String word) throws SQLException {
		String sql = "select * from keywords where word=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, word);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			int id = Integer.valueOf(rs.getString("id"));
			return id;
		}
		return 0;

	}

	public int getArticleId(String title) throws SQLException {
		String sql = "select * from articles where title=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, title);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			int id = Integer.valueOf(rs.getString("id"));
			return id;
		}
		return 0;

	}

	public void insertIntoArticles() throws SQLException {

		String sql = "insert into articles(title,abstract,user_id,pdf_path) values(?,?,?,?) ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, article.getTitle());
		stmt.setString(2, article.getAbst());
		stmt.setString(3, article.getUserId());
		stmt.setString(4, article.getPdfPath());
		stmt.executeUpdate();
		stmt.close();
		insertIntoKeywords();
		String articleId = String.valueOf(getMaxIdFromTable("articles"));
		insertIntoArticleAuthors(articleId, article.getUsers());
		

	}

	public ResultSet getUnpublishedArticles(int currentUserId,
			List<String> selectedIds) throws SQLException {
		Statement stst = conn.createStatement();
		ResultSet resultSet;
		if (selectedIds.size() > 0)
			resultSet = stst
					.executeQuery("select * from articles where status = 'unpublished' and user_id != "
							+ currentUserId
							+ " and id not in "
							+ getIds(selectedIds));
		else
			// resultSet =
			// stst.executeQuery("select * from articles where status = 'unpublished' and user_id != "+
			// currentUserId);
			resultSet = stst
					.executeQuery("select * from articles where status = 'unpublished' and user_id != " + 444);
		System.out.println(selectedIds + "......");
		return resultSet;

	}

	// public ResultSet getSelectedArticles(int currentUserId) throws
	// SQLException {
	// Statement stst = conn.createStatement();
	// ResultSet resultSet =
	// stst.executeQuery("select * from reviewer_status where status = 'unpublished' and user_id != "+
	// currentUserId);
	// resultSet.
	// return resultSet;
	//
	// }
	public void insertIntoReviewerStatus(String reviewerId, String articleId)
			throws SQLException {

		String sql = "insert into reviewer_status(reviewer_id,article_id) values(?,?) ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, reviewerId);
		stmt.setString(2, articleId);
		stmt.executeUpdate();
		stmt.close();

	}
	public void insertIntoArticleAuthors(String articleId,ArrayList<User> users) throws SQLException {
		
		String sql = "insert into article_authors(article_id,firstname,lastname,email) values(?,?,?,?) ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		for(User user:users){
		stmt.setString(1, articleId);
		stmt.setString(2, user.getFirstname());
		stmt.setString(3, user.getLastname());
		stmt.setString(4, user.getEmail());
		stmt.executeUpdate();
		}
		stmt.close();
	
	}

	public void removeFromReviewerStatus(String reviewerId, String articleId)
			throws SQLException {

		String sql = "delete from reviewer_status where reviewer_id = "
				+ reviewerId + " and article_id = " + articleId;
		Statement st = conn.createStatement();
		st.execute(sql);
		st.close();

	}

	public ResultSet getPublishedArticles() throws SQLException {
		Statement stst = conn.createStatement();
		ResultSet resultSet = stst.executeQuery("select * from articles");
		return resultSet;

	}

	public ResultSet getDownloadedArticles(List<String> downloadedIds)
			throws SQLException {
		Statement stst = conn.createStatement();
		ResultSet resultSet;
		if (downloadedIds.size() > 0)
			resultSet = stst
					.executeQuery("select * from articles where status = 'unpublished' and id in "
							+ getIds(downloadedIds));
		else
			resultSet = stst
					.executeQuery("select * from articles where status = 'p'");
		return resultSet;
	}

	public ResultSet getSelectedArticles(List<String> selectedIds)
			throws SQLException {
		Statement stst = conn.createStatement();
		ResultSet resultSet;
		if (selectedIds.size() > 0)
			resultSet = stst
					.executeQuery("select * from articles where status = 'unpublished' and id in "
							+ getIds(selectedIds));
		else
			resultSet = stst
					.executeQuery("select * from articles where status = 'p'");
		return resultSet;
	}

	/**
	 * This method implemented to
	 * 
	 * @param reviewerId
	 * @return
	 * @throws SQLException
	 *            
	 */
	public ResultSet getSelectedArticleIds(String reviewerId)
			throws SQLException {
		Statement stst = conn.createStatement();
		ResultSet resultSet = stst
				.executeQuery("select * from reviewer_status where status = 'Selected' and reviewer_id = "
						+ reviewerId);
		return resultSet;

	}

	public ResultSet getDownloadedArticleIds(String reviewerId)
			throws SQLException {
		Statement stst = conn.createStatement();
		ResultSet resultSet = stst
				.executeQuery("select * from reviewer_status where status = 'Downloaded' and reviewer_id = "
						+ reviewerId);
		return resultSet;
	}

	/**
	 * This method implemented to retrieves article ids from reviewer_status
	 * table
	 * 
	 * @param selected
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<String> getReviewerIds(ResultSet selected)
			throws SQLException {
		ArrayList<String> list = new ArrayList<String>();
		while (selected.next()) {
			list.add(selected.getString("article_id"));
		}
		return list;
	}

	public String getIds(List<String> ids) {
		int size = ids.size();
		String s = "(";

		for (int i = 0; i < size - 1; i++) {
			s += (ids.get(i) + ",");
		}
		s += (ids.get(size - 1) + ")");
		return s;
	}
//	public  Integer insertQueryGetId(String query) throws SQLException {
//	    Integer numero=0;
//	    Integer risultato=-1;
//	    
//	        Statement stmt = conn.createStatement();
//	        numero = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
//
//
//	        ResultSet rs = stmt.getGeneratedKeys();
//	        if (rs.next()){
//	            risultato=rs.getInt(1);
//	        }
//	        rs.close();
//
//	        stmt.close();
//	    
//	  return risultato;
//	}
	/**
	 * This method is implemented to get the table name and return the id of the last record;
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public  int getMaxIdFromTable(String tableName) throws SQLException {
		String sql = "select MAX(id) from "+tableName;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql) ;
		int id = 0 ;
		while(rs.next()){
			id = rs.getInt(1);
		}
		return id;
	}




}
