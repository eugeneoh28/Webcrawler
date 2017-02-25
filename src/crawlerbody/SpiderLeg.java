package crawlerbody;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class SpiderLeg {
	private List<String> links = new LinkedList<String>();
	private Document htmlDocument;
	private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	
	public Boolean crawl(String url) {
		try {
			
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
			Document html = connection.get();
			if(connection.response().contentType().contains("text/html")) {
				this.htmlDocument = html;
				Elements linksFound = htmlDocument.select("a[href]");
				for(Element link : linksFound) {
					links.add(link.absUrl("href"));
				}
				return true;
			}else {
				return false;
			}
		}
		catch (IOException ioe) {
			System.out.println("Error in HTTP request + ioe");
			return false;
		}
	}
	public boolean searchForWord(String word) {
		if(this.htmlDocument == null) {
			throw new IllegalStateException();
		}
		
		String body = this.htmlDocument.body().text();
		return body.toLowerCase().contains(word.toLowerCase());
	}
	public List<String> getLinks() {
		return this.links;
	}
}
