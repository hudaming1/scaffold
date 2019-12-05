package org.hum.scaffold;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.hum.scaffold.tools.IwannaSee;

public class IwannaSeeTest {
	
	private static final ExecutorService ExecutorService = Executors.newFixedThreadPool(20);
	
	private static final String ZK_HOST = "10.2.4.14:30022,10.2.4.14:32148,10.2.4.14:31157";
	private static final String ESJOB_NS = "/ocean-wms-task";
	
	public static void main2(String[] args) throws UnsupportedEncodingException {
		// 10.2.4.14:30022,10.2.4.14:32148,10.2.4.14:31157
		List<String> children = IwannaSee.create("10.2.4.14:30022,10.2.4.14:32148,10.2.4.14:31157").getChildren("/ocean-wms-task/NJStockDistributeJob/servers");
		for (String child : children) {
			System.out.println(child);
		}
		
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		IwannaSee inst = IwannaSee.create(ZK_HOST);
		List<String> children = IwannaSee.create(ZK_HOST).getChildren(ESJOB_NS);
		int total = 0;
		for (String child : children) {
			try {
				List<String> servers = inst.getChildren(ESJOB_NS + "/" + child + "/servers");
				System.out.println(child + " = " + servers.size());
				total += servers.size();
			} catch(Exception ce) {
				
			}
		}
		System.out.println(total);
		
		final AtomicInteger deleted = new AtomicInteger(0);
		for (String child : children) {
			try {
				List<String> servers = inst.getChildren(ESJOB_NS + "/" + child + "/servers");
				for (final String server : servers) {
					final int _total = total;
					final IwannaSee zkClient = IwannaSee.create(ZK_HOST);
					ExecutorService.execute(new Runnable() {
						@Override
						public void run() {
							if (zkClient.delete(ESJOB_NS + "/" + child + "/servers/" + server)) {
								deleted.incrementAndGet();
								System.out.println("process : " + deleted + "/" + _total);
							} else {
								System.out.println("delete failed, server=" + ESJOB_NS + "/" + child + "/servers/" + server);
							}
							zkClient.close();
						}
					});
				}
			} catch(Exception ce) {
			}
		}
		
		System.out.println("over");
	}
}
