package org.hum.scaffold;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.hum.scaffold.tools.IwannaSee;

public class IwannaSeeTest {

	public static void main(String[] args) throws UnsupportedEncodingException {
		List<String> consumers = IwannaSee.create("zk.qa.imrfresh.net:2181").consumers("com.mryx.grampus.ccs.rpc.CcsTokenService");
		IwannaSee.printList(consumers);
	}
}
