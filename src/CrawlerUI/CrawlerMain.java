package CrawlerUI;

import crawlerbody.Spider;

public class CrawlerMain {
	public static void main (String args[]) {
		Spider spider = new Spider();
		spider.search("http://arstechnica.com/", "computer");
		
	}
}
