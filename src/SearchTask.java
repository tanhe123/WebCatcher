import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;

public class SearchTask implements Runnable{
	/**
	 * constructs a SearchTask
	 * @param startUrl the start url to search
	 */
	public SearchTask(BlockingQueue<String> queue, String startUrl) {
		urlQueue = queue;
		this.startUrl = startUrl;
	}
	
	public void run() {
		search(startUrl);
	}
		
	public void search(String url) {
		System.out.println("正在检索的网页:" + url);
		try {
			if (!urlSet.contains(url)) {
				urlSet.add(url);
				
				String content = WebCatcher.getContent(new URL(url));
				
				// 将该url记入已访问集中
				urlSet.add(content);
				
				// 获取url里面的词
				this.catchCi(content);
				
				// 获取url里面的诗
				this.catchPoem(content);
				
				// 获取url里面的其它诗的链接列表
				List<String> urls = WebCatcher.getURL(content);
				for (String e : urls) {
					if (urlSet.contains(e)) continue;
					else {
						urlQueue.put(e);
				//		urlSet.add(e);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(urlQueue.size());
		
		// 从urlQueue中取元素递归遍历
		if (urlQueue.size() > 0) {
			try {
				String nurl = urlQueue.take();
				search(nurl);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void catchCi(String content) {
		try {
			//获取内容
			List<String> list = WebCatcher.getCi(content);
			// 未检索到内容
			if (list.size() == 0) {
				System.out.println("在该网页中未检索到词");
				return ;
			}
			
			String name = list.get(0);
			StringBuilder contents = new StringBuilder();
			for (String e : list) {
				contents.append(e + "\n");
			}
			System.out.println(contents);
			
			//存入数据库
			MySql sql = new MySql(MySql.myurl, "root", "");
			sql.Connect();
			sql.execute("insert into data(name,content) values(\""+name+"\"" +
					",\"" + contents+"\");");
		//	ResultSet rs = sql.executeQuery("select * from data");
//			while(rs.next()) {
//				System.out.println(rs.getObject(1) + " " + rs.getObject(4));
//			}
			sql.release();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void catchPoem(String content) {
		try {
			//获取内容
			List<String> list = WebCatcher.getPoem(content);
			// 未检索到内容
			if (list.size() == 0) {
				System.out.println("在该网页中未检索到诗");
				return ;
			}
			
			String name = list.get(0);
			StringBuilder contents = new StringBuilder();
			
			for (String e : list) {
				contents.append(e + "\n");
			}
			System.out.println(contents);
			
			//存入数据库
			MySql sql = new MySql(MySql.myurl, "root", "");
			sql.Connect();

			sql.execute("insert into data(name,content) values(\""+name+"\"" +
					",\"" + contents+"\");");
//			ResultSet rs = sql.executeQuery("select * from data");
//			while(rs.next()) {
//				System.out.println(rs.getObject(1) + " " + rs.getObject(4));
//			}
			sql.release();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	private static Set<String> urlSet = new HashSet<String>();
	private BlockingQueue<String> urlQueue;
	private String startUrl;
}
