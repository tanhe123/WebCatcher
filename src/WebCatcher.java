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
	 * using poemRegex to match the content of the url
	 * @param url the url to patch, such as http://www.shicimingju.com/1368.html
	 * @return list of the poems
	 */
	public static List<String> CatchPoem(URL url){
		BufferedReader reader = null;
		List<String> list = new ArrayList<String>();
		
		try {
			//BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "gbk"));
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
						
			StringBuilder str = new StringBuilder();
			String tmp;
			while((tmp = reader.readLine()) != null) {
				str.append(tmp);	
				str.append("\n");
			}
			
			reader.close();
			
			Pattern pattern = Pattern.compile(poemRegex);
			Matcher mat = pattern.matcher(str);
			while (mat.find()) {
				list.add(mat.group(1));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static final String poemRegex = "<p style=\\\"text-align: center;\">(.*?)</p>";
}
