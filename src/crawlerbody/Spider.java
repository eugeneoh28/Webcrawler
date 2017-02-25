package crawlerbody;

import java.util.*;

public class Spider {
	private static final int MAX_PAGES = 50;
	private Set<String> pagesVisited = new HashSet<String>();
	private List<String> pagesToVisit = new LinkedList<String>();
	
	private String getNextUrl() {
		String nextURL;
		do { 
			nextURL = this.pagesToVisit.remove(0);
		}while(this.pagesVisited.contains(nextURL));
		
		this.pagesVisited.add(nextURL);
		return nextURL;
	}
	
	public void search(String url, String searchWord) {
		while(this.pagesVisited.size()<MAX_PAGES) {
			String currUrl;
			SpiderLeg leg = new SpiderLeg();
			
			if(this.pagesToVisit.isEmpty()) {
				currUrl = url;
				this.pagesVisited.add(url);
			}else {
				currUrl = this.getNextUrl();
			}
			
			Boolean legCrawl = leg.crawl(currUrl);
			if(legCrawl) {
				Boolean check = leg.searchForWord(searchWord);
				if(check) {
					System.out.println(String.format("**Success** Word %s found at %s", searchWord, currUrl));
				}
				this.pagesToVisit.addAll(leg.getLinks());
			}else {
				System.out.println("Sorry, something went wrong");
			}
		}
		System.out.println(String.format("**Done** Visited %s web page(s)", this.pagesVisited.size()));
	}
	
}
