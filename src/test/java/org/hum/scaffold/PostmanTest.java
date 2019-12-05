package org.hum.scaffold;

import java.io.IOException;
import java.net.UnknownHostException;

import org.hum.scaffold.http.Postman.HttpRequest;

public class PostmanTest {
	
	String data = "{\"username\":\"\",\"password\":\"\"}";
	
	String demo = "POST /api/signIn HTTP/1.1\n" + 
			"Host: domain.com\n" + 
			"Connection: keep-alive\n" + 
			"Content-Length: " + data.getBytes().length + "\r\n" +  
			"Origin: http://domain.com\n" + 
			"X-Requested-With: XMLHttpRequest\n" + 
			"User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36\n" + 
			"clusterName:\n" + 
			"Content-Type: application/json;charset=UTF-8\n" + 
			"Accept: */*\n" + 
			"Referer: http://domain.com/index\n" + 
			"Accept-Encoding: gzip, deflate\n" + 
			"Accept-Language: zh-CN,zh;q=0.9\n" + 
			"Cookie: JSESSIONID=f0680788-ff48-4cc4-94dc-3eac4a3ed088\n"
			+ "\n" + data + "\n";


	String changeAppDeptRequestData = "id=1212&deptId=163";
	String changeAppDeptRequest = "POST /system/app/update HTTP/1.1\r\n" + 
			"Host: appcenter.missfresh.net\r\n" + 
			"Connection: keep-alive\r\n" + 
			"Content-Length: " + data.getBytes().length + "\r\n" +  
			"Accept: */*\r\n" + 
			"Origin: http://appcenter.missfresh.net\r\n" + 
			"X-Requested-With: XMLHttpRequest\r\n" + 
			"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36\r\n" + 
			"Content-Type: application/x-www-form-urlencoded; charset=UTF-8\r\n" + 
			"Referer: http://appcenter.missfresh.net/system/app/edit/530\r\n" + 
			"Accept-Encoding: gzip, deflate\r\n" + 
			"Accept-Language: zh-CN,zh;q=0.9\r\n" + 
			"Cookie: JSESSIONID=7515bc11-7c51-42c7-b04c-5a811ec842e2; ccs-token=9PTRimdQhn+m7HUyv/pRndHS/J8OH4im3ZRozehv9sjFbgEPqQ+o/w==; accessToken=1b1b0f10-fc5c-4316-bb4a-27256dc3139b\r\n"
					+ "\r\n" + data + "\r\n";
	
	static String getPersonal = "GET /api/menu/personal HTTP/1.1\r\n" + 
			"Host: taotie.missfresh.net\r\n" + 
			"Connection: keep-alive\r\n" + 
			"Cache-Control: max-age=0\r\n" + 
			"Upgrade-Insecure-Requests: 1\r\n" + 
			"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36\r\n" + 
			"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3\r\n" + 
			"Accept-Encoding: gzip, deflate\r\n" + 
			"Accept-Language: zh-CN,zh;q=0.9\r\n" + 
			"Cookie: accessToken=767fd299-13a3-4101-abdc-536de866b1e8\r\n" +
			"\r\n";
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		String param = "GET /api/registry-center HTTP/1.1\r\n" + 
				"Host: esjob-test.missfresh.net\r\n" + 
				"Connection: keep-alive\r\n" + 
				"Cache-Control: max-age=0\r\n" + 
				"Authorization: Basic bWlzc2ZyZXNoUm9vdDEyMzptaXNzZnJlc2hSb290MTIz\r\n" + 
				"Upgrade-Insecure-Requests: 1\r\n" + 
				"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36\r\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3\r\n" + 
				"Accept-Encoding: gzip, deflate\r\n" + 
				"Accept-Language: zh-CN,zh;q=0.9\r\n" + 
				"Cookie: JSESSIONID=17b9qrbe8i91yb6viwhtvaiwv\r\n"
				+ "\r\n\r\n";
		

		
		String resp = new HttpRequest("esjob-test.missfresh.net").execute(param);
		System.out.println(resp);
	}
}
