package org.hum.scaffold.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;

public class IwannaSee {
	
	private ZkClient zkClient;
	
	private IwannaSee(String zkAddr) {
		this.zkClient = new ZkClient(zkAddr);
	}
	
	public static IwannaSee create(String zkAddr) {
		return new IwannaSee(zkAddr);
	}

	public List<String> consumers(Class<?> interfaceType) throws UnsupportedEncodingException {
		return consumers(interfaceType.getName());
	}

	public List<String> consumers(String interfaceTypeName) throws UnsupportedEncodingException {
		return getChildren("/dubbo/" + interfaceTypeName + "/consumers");
	}

	public List<String> providers(Class<?> interfaceType) throws UnsupportedEncodingException {
		return providers(interfaceType.getName());
	}

	public List<String> providers(String interfaceTypeName) throws UnsupportedEncodingException {
		return getChildren("/dubbo/" + interfaceTypeName + "/providers");
	}
	
	public List<String> getChildren(String path) throws UnsupportedEncodingException {
		List<String> list = new ArrayList<String>();
		List<String> children = zkClient.getChildren(path);
		for (String child : children) {
			list.add(URLDecoder.decode(child, "utf-8"));
		}
		return list;
	}
	
	public boolean delete(String path) {
		return zkClient.delete(path);
	}
	
	public static void printList(List<?> list) {
		for (Object obj : list) {
			System.out.println(obj);
		}
	}
	
	public void close() {
		this.zkClient.close();
	}
}
