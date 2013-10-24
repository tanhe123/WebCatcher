import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		try {
			String strUrl = "http://www.shicimingju.com/baidu/list/3733.html";
			String strUrl1 = "http://www.shicimingju.com/1368.html";
			String str = "http://www.shicimingju.com/baidu/list/35383.html";
			//获取内容
			List<String> list = WebCatcher.getCi(new URL(strUrl));
			StringBuilder content = new StringBuilder();
			for (String e : list) {
				content.append(e + "\n");
			}
			System.out.println(content);
			
			//存入数据库
			MySql sql = new MySql(MySql.myurl, "root", "622");
			sql.Connect();
			sql.execute("insert into data(content) values(\""+content+"\");");
			System.out.println("haha");
			ResultSet rs = sql.executeQuery("select * from data");
			while(rs.next()) {
				System.out.println(rs.getObject(1) + " " + rs.getObject(4));
			}
			sql.release();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
