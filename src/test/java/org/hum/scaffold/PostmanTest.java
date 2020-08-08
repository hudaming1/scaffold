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
	

	
	static String ZtoCallBack = "POST /poll/zto HTTP/1.1\r\n" + 
			"Host: express-api.missfresh.cn\r\n" + 
//			"Host: 39.96.83.46\r\n" + 
			"Connection: keep-alive\r\n" + 
			"Content-Length: " + "data=humingtest".getBytes().length + "\r\n" +  
			"Content-Type: application/x-www-form-urlencoded;charset=UTF-8\r\n\r\n" + 
			"data=humingtest" + "\r\n";
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
//		String param = "GET /sms/replenishment/receivingForecast/getlevel1CategoryList HTTP/1.1\n" + 
//				"Host: wuliu-ocean-gateway.b24.missfresh.net\n" + 
//				"Connection: keep-alive\n" + 
//				"deviceId: c3eff745d8f339f17748ce53ea03ec03\n" + 
//				"Accept: application/json, text/plain, */*\n" + 
//				"Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzbXNqeHEiLCJ1c2VyX25hbWUiOiJzbXNqeHEiLCJleHAiOjE1OTQyODU1OTcsImlhdCI6MTU5MTYwNzE5N30.1g1C4g6Q4sIynmace65rcMqGp1k-q34CBuOe3-iETzQ\n" + 
//				"mfsig: mfswVFZZhScrfd8UidRXcWogUEzvQVvOidljhC1OjBcrgEwahVgohdZTSw1oQwWajdMpRd+dVgdSidrOeVopid+nhd1UQv9MXSvVfP0yH3Q\n" + 
//				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36\n" + 
//				"deviceType: PC\n" + 
//				"Origin: http://56hub-web-staging.missfresh.net\n" + 
//				"Referer: http://56hub-web-staging.missfresh.net/\n" + 
//				"Accept-Language: zh-CN,zh;q=0.9" + 
//				"\n\n";
		
//		String body = "{\"caller\":\"LOGISTICS_SMS\",\"fetchSource\":[],\"index\":\"b5_regular_inventory\",\"page\":1,\"querys\":[{\"column\":\"create_time\",\"condition\":\"gte\",\"connection\":\"filter\",\"value\":[1595809330650]},{\"column\":\"create_time\",\"condition\":\"lte\",\"connection\":\"filter\",\"value\":[1595895730650]},{\"column\":\"warehouse_code\",\"condition\":\"in\",\"connection\":\"filter\",\"value\":[\"MRYX_TEST_1288253601908312\"]}],\"size\":10,\"sorts\":[{\"field\":\"id\",\"order\":\"desc\"}],\"type\":\"_doc\"}";
		String body = "{\"caller\":\"LOGISTICS_SMS\",\"fetchSource\":[],\"index\":\"b5_regular_inventory\",\"page\":1,\"querys\":[{\"column\":\"create_time\",\"condition\":\"lte\",\"connection\":\"filter\",\"value\":[\"2020-07-28 00:00:00\"]},{\"column\":\"warehouse_code\",\"condition\":\"in\",\"connection\":\"filter\",\"value\":[\"MRYX_TEST_1288253601908312\"]}],\"size\":10,\"sorts\":[{\"field\":\"id\",\"order\":\"desc\"}],\"type\":\"_doc\"}";
		
		String param = "POST /search/query HTTP/1.1\n" + 
				"Host: 10.2.39.13\n" + 
				"Connection: keep-alive\n" + 
				"Content-Length: " + body.length() + "\n" + 
				"Accept: application/json, text/plain, */*\n" + 
				"Content-Type: application/json;charset=UTF-8\n" + 
				"Accept-Language: zh-CN,zh;q=0.9\r\n\r\n"
				+ body;

		String resp = new HttpRequest("10.2.39.13", 30881).execute(param);
		System.out.println(resp);
	}
}
