import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Catcher {
	public static void main(String[] args) {
		String strUrl = "http://www.shicimingju.com/baidu/list/3733.html";
		String strUrl1 = "http://www.shicimingju.com/1368.html";
		String str = "http://www.shicimingju.com/baidu/list/35383.html";
	
		new Thread(new SearchTask(strUrl)).start();
	}
}
