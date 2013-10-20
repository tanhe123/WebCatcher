import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WebCatcher {
	/**
	 * get the content of the url, the default charset is utf-8
	 * @param url the url of the content to get
	 * @return the content of the url
	 * @throws IOException
	 */
	public static String getContent(URL url) throws IOException {
		return getContent(url, "UTF-8");
	}
	
	/**
	 * get the content of the url
	 * @param url the url of the content to get
	 * @param charsetName 网页的编码, such as gbk
	 * @return the content of the url
	 * @throws IOException
	 */
	public static String getContent(URL url, String charsetName) throws IOException {;
		StringBuilder content = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), charsetName));
		
		String tmp;
		while((tmp = reader.readLine()) != null) {
			content.append(tmp);	
			content.append("\n");
		}
		
		reader.close();
		
		return content.toString();
	}
	
	/**
	 * using poemRegex to match the content of the url
	 * @param url the url to patch, such as http://www.shicimingju.com/1368.html
	 * @return list of the poems
	 * @throws IOException 
	 */
	public static List<String> getPoem(URL url) throws IOException{
		String content = getContent(url);
		return getPoem(content);
	}
	
	/**
	 * using poemRegex to match the content
	 * @param content content
	 * @return list of the result 
	 */
	public static List<String> getPoem(String content) {
		List<String> list = new ArrayList<String>();
		
		Pattern pattern = Pattern.compile(poemRegex);
		Matcher mat = pattern.matcher(content);
		while (mat.find()) {
			list.add(mat.group(1));
		}
		
		return list;
	}
	
	public static final String poemRegex = "<p style=\\\"text-align: center;\">(.*?)</p>";
}
