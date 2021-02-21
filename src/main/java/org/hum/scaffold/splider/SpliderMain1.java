package org.hum.scaffold.splider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class SpliderMain1 {

	private static final String URL = "https://sexygirl.cc/?p=%s&s=by_create";
	private static final String URL2 = "https://sexygirl.cc/";
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
		for (int i = 1; i <= 80; i++) {
			HtmlPage page = webClient.getPage(String.format(URL, i));
			String pageXml = page.asXml();
			Document document = Jsoup.parse(pageXml);
			int _p = i;
			List<Element> infoListEle = document.getElementsByAttributeValue("class", "col-6 col-md-3");// 获取元素节点等
			infoListEle.forEach(item -> {
				String title = item.getElementsByTag("figcaption").get(0).text();
				String url = URL2 + item.getElementsByTag("a").get(0).attr("href");
				if (title.contains("动态") || title.contains("动图")) {
					try {
						downloadPage(url, title);
						System.out.println(_p);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	public static void downloadPage(String pageUrl, String title) throws Exception {
		HtmlPage page = webClient.getPage(pageUrl);
		String pageXml = page.asXml();
		Document document = Jsoup.parse(pageXml);
		document.getElementsByTag("img").forEach(img -> {
			try {
				download(img.attr("src"), title);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private static final AtomicInteger fileCounter = new AtomicInteger();

	public static void download(String gifUrl, String fileName) throws IOException {
		try {
			HttpClient client = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(gifUrl);
			// 设置请求
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000).setSocketTimeout(3000).build();
			httpGet.setConfig(requestConfig);
			HttpResponse response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();

			InputStream is = entity.getContent();
			File file = new File("/Users/hudaming/Pictures/emoji/" + fileName + fileCounter.getAndIncrement() + ".gif");
			FileOutputStream fileout = new FileOutputStream(file);
			/**
			 * 根据实际运行效果 设置缓冲区大小
			 */
			byte[] buffer = new byte[4096];
			int ch = 0;
			while ((ch = is.read(buffer)) != -1) {
				fileout.write(buffer, 0, ch);
			}
			is.close();
			fileout.flush();
			fileout.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
