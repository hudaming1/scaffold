package org.hum.scaffold;

import java.util.List;
import java.util.Map.Entry;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

public class ZkClientTest {

	public static void main(String[] args) {
		ZkClient zkClient = new ZkClient("10.2.3.83:2181");
		List<String> children = zkClient.getChildren("/testshardingesjob/smartTestJob1/sharding/8");
		children.forEach(path -> {
			System.out.println(path);
		});
		
		Entry<List<ACL>, Stat> acl = zkClient.getAcl("/testshardingesjob/smartTestJob1/sharding/0");
		System.out.println(acl);
	}
}
