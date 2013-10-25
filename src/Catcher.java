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
		List<String> list = new ArrayList<String>();
		list.add(strUrl);
		list.add(strUrl1);
		list.add(str);
		
		/*
		Catcher test = new Catcher();
		for (String e : list) {
			test.CatchCi(e);
			test.CatchPoem(e);	
		}*/
		
	/*	try {
			List<String> urls = WebCatcher.getURL(new URL(str));
			for (String e : urls) {
				System.out.println(e);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		/*Set<String> set = new HashSet<String>();
		set.add("haha");
		set.add("hehe");
		System.out.println(set.contains("haha"));*/

	/*	try {
			URL url = new URL("http://www.baidu.com");
			System.out.println(url.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		new Thread(new SearchTask("http://www.shicimingju.com/")).start();
	}
}
