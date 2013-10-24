import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySql {
	
	private String url;
	public MySql(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public boolean Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			st = conn.createStatement();	
		} catch (SQLException | ClassNotFoundException e) {
			return false;
		}
		return true;
	}
	
	public boolean execute(String sql) throws SQLException {
		return st.execute(sql);
	}
	
	public ResultSet executeQuery(String sql) throws SQLException {
		return st.executeQuery(sql);
	}
	
	public void release() {
		try {
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Connection conn; //Connection
	private ResultSet rs; // ResultSet
	private Statement st; //statement
	private String user;
	private String password;
}
