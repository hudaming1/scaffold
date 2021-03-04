package org.hum.scaffold.splider;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class SpliderMain1 {

	private static final String URL = "https://sexygirl.cc/?p=%s&s=by_create";
	private static final String URL2 = "https://sexygirl.cc/";
	private static final int MAX_PAGE_SIZE = 478;
	private static final WebClient webClient = new WebClient(BrowserVersion.CHROME);// 新建一个模拟谷歌Chrome浏览器的浏览器客户端对象

	static {
		webClient.getOptions().setThrowExceptionOnScriptError(false);// 当JS执行出错的时候是否抛出异常, 这里选择不需要
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);// 当HTTP的状态非200时是否抛出异常, 这里选择不需要
		webClient.getOptions().setActiveXNative(false);
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setCssEnabled(false);// 是否启用CSS, 因为不需要展现页面, 所以不需要启用
		webClient.getOptions().setJavaScriptEnabled(true); // 很重要，启用JS
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());// 很重要，设置支持AJAX
	}

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 1; i <= MAX_PAGE_SIZE; i++) {
			executorService.execute(new FetchPage(i));
		}
	}
	
	private static class FetchPage implements Runnable {
		
		private int pageIndex;
		
		public FetchPage(int page) {
			this.pageIndex = page;
		}

		@Override
		public void run() {
			try {
				System.out.println("page " + pageIndex + " begin");
				HtmlPage page = null;
				synchronized (FetchPage.class) {
					page = webClient.getPage(String.format(URL, pageIndex));
				}
				String pageXml = page.asXml();
				Document document = Jsoup.parse(pageXml);
				List<Element> infoListEle = document.getElementsByAttributeValue("class", "col-6 col-md-3");// 获取元素节点等
				infoListEle.forEach(item -> {
					String title = !item.getElementsByTag("img").get(0).hasAttr("alt") ? item.getElementsByTag("figcaption").get(0).text() : item.getElementsByTag("img").get(0).attr("alt");
					String cover = item.getElementsByTag("img").get(0).attr("src");
					String ext = cover.substring(cover.lastIndexOf("."));
					String detailUrl = URL2 + item.getElementsByTag("a").get(0).attr("href");
					if (cover.contains(" ")) {
						cover = cover.split(" ")[0];
					}
					DownloadFactory.download(cover, pageIndex + "_" + title + ext);
				});
				System.out.println("page " + pageIndex + " over");
			} catch (FailingHttpStatusCodeException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
