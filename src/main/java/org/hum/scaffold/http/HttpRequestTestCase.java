package org.hum.scaffold.http;

public class HttpRequestTestCase {


	public final static String param = "POST /api/signIn HTTP/1.1\n" + 
			"Host: domain.com\n" + 
			"Connection: keep-alive\n" + 
			"Content-Length: 43\n" + 
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
			+ "\n" 
			+ "{\"username\":\"\",\"password\":\"\"}\n";
}
