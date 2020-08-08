package org.hum.scaffold;

import cn.hutool.http.HttpUtil;

public class HttpClientTest {

	public static void main(String[] args) {
		System.out.println(HttpUtil.get("https://firefox.settings.services.mozilla.com/v1/buckets/main/collections/ms-language-packs/records/cfr-v1-zh-CN"));
	}
}
