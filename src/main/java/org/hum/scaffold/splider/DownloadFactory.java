package org.hum.scaffold.splider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.HttpClients;

public class DownloadFactory implements Runnable {

	private static final String SAVE_DIR = "/Users/hudaming/Pictures/emoji/";

	private BlockingQueue<String> URLS;

	private final ExecutorService Consumers;

	public DownloadFactory(List<String> urls, int workers) {
		URLS = new ArrayBlockingQueue<String>(urls.size(), false, urls);
		Consumers = Executors.newFixedThreadPool(workers);
	}

	@Override
	public void run() {
		for (int i = 0; i < WORK_COUNT; i++) {
			Consumers.execute(new Runnable() {
				@Override
				public void run() {
					while (!URLS.isEmpty()) {
						String url = URLS.poll();
						try {
							if (url.length() == 0) {
								continue;
							} else if (!url.startsWith("http://img")) {
								continue;
							}
							String fileName = url.substring(url.lastIndexOf("/"));
							String dirName = url.substring(0, url.lastIndexOf("/"));
							dirName = dirName.substring(dirName.lastIndexOf("/"));
							fileName = fileName.replace("/", "") + "#" + dirName.replace("/", "") + "" + ".gif";
							if (!new File(SAVE_DIR + fileName).exists()) {
								download(url, fileName);
							} else {
								// System.out.println("###SKIP###" + fileName);
							}
						} catch (Exception e) {
							System.err.println(url);
						}
					}
				}
			});
		}
	}

	private static List<String> readFiles(String filepath) throws IOException {
		List<String> list = new ArrayList<String>();
		FileInputStream fileInputStream = new FileInputStream(new File(filepath));
		BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
		String line = null;
		while ((line = br.readLine()) != null) {
			list.add(line);
		}
		br.close();
		fileInputStream.close();
		return list;
	}

	private static final int WORK_COUNT = Runtime.getRuntime().availableProcessors() * 3;

	public static void main(String[] args) throws IOException {
//		List<String> urls = readFiles("/Users/hudaming/Workspace/GitHub/MyTest/src/main/java/org/hum/download.txt");
//		new DownloadFactory(urls, WORK_COUNT).run();
	}

	private static final HttpClient client = HttpClients.custom().setMaxConnPerRoute(Integer.MAX_VALUE).setSSLHostnameVerifier(new HostnameVerifier() {
		@Override
		public boolean verify(String arg0, SSLSession arg1) {
			return true;
		}
	}).setMaxConnTotal(Integer.MAX_VALUE).build();
	private static final RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000)
			.setConnectionRequestTimeout(60000).setSocketTimeout(60000).build();

	public static void download(String gifUrl, String fileName) {
		try {
			if (new File(SAVE_DIR + fileName).exists()) {
				return ;
			}
			HttpGet httpGet = new HttpGet(gifUrl);
			// 设置请求
			httpGet.setConfig(requestConfig);
			HttpResponse response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			
			InputStream is = entity.getContent();
			File file = new File(SAVE_DIR + fileName);
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
			// System.out.println("[" + Thread.currentThread().getName() + "]downloaded " + gifUrl);
		} catch (SocketException | ConnectTimeoutException ignore) {
			System.err.println("gifUrl:" + gifUrl + ", error:" + ignore.getMessage());
			// ignore
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(gifUrl);
		}
	}
}
