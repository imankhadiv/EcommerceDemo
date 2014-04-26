package database;
/**
 * 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 * @author Iman Rastkhadiv
 *
 */
public class Account {
	private Connection conn;

	public Account(Connection conn) {
		this.conn = conn;
	}

	public boolean login(String email, String password) throws SQLException {
		String sql = "select count(*) as count from users where email=? and password=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);
		stmt.setString(2, password);
		ResultSet rs = stmt.executeQuery();

		int count = 0;
		if (rs.next()) {
			count = rs.getInt("count");
		}
		rs.close();
		if (count == 0) {
			return false;
		} else {
			// Statement stst = conn.createStatement();
			// stst.execute("UPDATE users SET last_sign_in_at = CURRENT_TIMESTAMP WHERE first_name= "+);
			return true;
		}
	}

	public void create(String email, String password, String firstname,
			String lastname) throws SQLException {
		String sql = "insert into users(email, password,first_name,last_name) values(?, ?, ?, ?) ";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);
		stmt.setString(2, password);
		stmt.setString(3, firstname);
		stmt.setString(4, lastname);
		stmt.executeUpdate();
		stmt.close();

	}

	public ResultSet getRecords() throws SQLException {
		Statement stst = conn.createStatement();
		ResultSet resultSet = stst.executeQuery("select * from users");
		return resultSet;

	}
	public int getUserId(String email) throws SQLException {
		
		String sql = "select * from users where email=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			int id = Integer.valueOf(rs.getString("id"));
			return id;
		}
		return 0;

	}
	public void changeRole(String role,int id) throws SQLException {
		String sql = "update users SET role = '"+ role +"' where id = "+ id;
		Statement stst = conn.createStatement();
		stst.executeUpdate(sql);
		

		//stst.addBatch("update bankAccount SET checkingBalance = checkingBalance - "+big+" where id = "+id);
	}


	public boolean exists(String email) throws SQLException {
		String sql = "select count(*) as count from users where email=? ";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);
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


}