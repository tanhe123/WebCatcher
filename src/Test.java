import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class Test {
	public static void main(String[] args) {
		try {
			List<String> list = WebCatcher.getPoem(new URL("http://www.shicimingju.com/1368.html"));
			for (String e : list) {
				System.out.println(e);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
