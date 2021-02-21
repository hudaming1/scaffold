package org.hum.scaffold;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

public class HttpClientTest {

	public static void main(String[] args) {
		testPost();
	}
	
	public static void testGet() {
		System.out.println(HttpUtil.get("https://firefox.settings.services.mozilla.com/v1/buckets/main/collections/ms-language-packs/records/cfr-v1-zh-CN"));
	}
	
	public static void testPost() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-User-Id", "1");
		HttpResponse resp = HttpUtil.createPost("http://sms-basic.b35.missfresh.net/sms/basic/area/query").addHeaders(headers).body("{\"warehouseCode\":\"MRYXBJN-XIANDAICHENG\",\"size\":\"small\",\"userId\":1}").execute();
		System.out.println(resp);
	}
}
