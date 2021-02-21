package org.hum.scaffold;

import java.net.UnknownHostException;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;

public class ZkClientTest {

	// ssh -o ServerAliveInterval=60 -o ServerAliveCountMax=360 -NfR  0.0.0.0:52996:10.20.0.155:32267 root@129.28.193.172
	public static void main(String[] args) throws UnknownHostException {
		
		// ,10.20.0.155:32271,10.20.0.155:31445
		long start = System.currentTimeMillis();
		ZkClient zkClient = new ZkClient("10.20.0.155:32267");
		System.out.println("connect cost : " + (System.currentTimeMillis() - start) / 1000 + "s");
		start = System.currentTimeMillis();
		List<String> children = zkClient.getChildren("/");
		children.forEach(path -> {
			System.out.println(path);
		});
		System.out.println("get node " + (System.currentTimeMillis() - start) / 1000 + "s");
	}
}
	