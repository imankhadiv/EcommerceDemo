package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MistakeDB {
	private Connection conn;

	public MistakeDB(Connection conn) {
		this.conn = conn;
	}

	public MistakeDB() {

	}
	
	public void insertIntoError(int article_id, int reviewer_id,String mistake) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "select count(*) as count from mistake where mistake='"+ mistake +"'";
		String sqlTemp = "";
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		if(rs.getInt("count")>0)
		{
			sql = "select * from mistake where mistake='"+mistake+"'";
			ResultSet resultSet = stmt.executeQuery(sql);
			resultSet.next();
			//TODO exist mistake
			int mistake_id = resultSet.getInt("id");
			sqlTemp = "select count(*) as count from mistake_list where mistake_id='"
					+ mistake_id
					+ "' and reviewer_id='"
					+ reviewer_id
					+ "' and article_id='" + article_id + "'";
			ResultSet reSet = stmt.executeQuery(sqlTemp);
			reSet.next();
			if (reSet.getInt("count") == 0) {
				sqlTemp = "insert into mistake_list (article_id,reviewer_id,mistake_id) values('"
						+ article_id
						+ "','"
						+ reviewer_id
						+ "','"
						+ mistake_id
						+ "')";
				stmt.execute(sqlTemp);
			}
		}
		else {
			//TODO insert new mistake
			sqlTemp = "insert into mistake (mistake) values ('"+mistake +"')";
			stmt.execute(sqlTemp);
			sqlTemp = "select * from mistake where mistake='"+mistake+"'";
			ResultSet rSet = stmt.executeQuery(sqlTemp);
			rSet.next();
			int mistake_id = rSet.getInt("id");
			sqlTemp = "insert into mistake_list (article_id,reviewer_id,mistake_id) values('"
					+ article_id
					+ "','"
					+ reviewer_id
					+ "','"
					+ mistake_id
					+ "')";
			stmt.execute(sqlTemp);
		}
		stmt.close();
	}
}
