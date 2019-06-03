package org.hum.scaffold.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;

public class IwannaSee {
	
	private String zkAddr;
	
	private IwannaSee(String zkAddr) {
		this.zkAddr = zkAddr;
	}
	
	public static IwannaSee create(String zkAddr) {
		return new IwannaSee(zkAddr);
	}

	public List<String> consumers(Class<?> interfaceType) throws UnsupportedEncodingException {
		return consumers(interfaceType.getName());
	}

	public List<String> consumers(String interfaceTypeName) throws UnsupportedEncodingException {
		ZkClient zkClient = new ZkClient(zkAddr);
		List<String> list = new ArrayList<String>();
		List<String> children = zkClient.getChildren("/dubbo/" + interfaceTypeName + "/consumers");
		for (String child : children) {
			list.add(URLDecoder.decode(child, "utf-8"));
		}
		return list;
	}

	public List<String> providers(Class<?> interfaceType) throws UnsupportedEncodingException {
		return providers(interfaceType.getName());
	}

	public List<String> providers(String interfaceTypeName) throws UnsupportedEncodingException {
		ZkClient zkClient = new ZkClient(zkAddr);
		List<String> list = new ArrayList<String>();
		List<String> children = zkClient.getChildren("/dubbo/" + interfaceTypeName + "/providers");
		for (String child : children) {
			list.add(URLDecoder.decode(child, "utf-8"));
		}
		return list;
	}
	
	public static void printList(List<?> list) {
		for (Object obj : list) {
			System.out.println(obj);
		}
	}
}
