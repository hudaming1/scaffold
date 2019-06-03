package org.hum.scaffold;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.hum.scaffold.tools.IwannaSee;

public class IwannaSeeTest {

	public static void main(String[] args) throws UnsupportedEncodingException {
		List<String> consumers = IwannaSee.create("10.2.39.16:2181").consumers("interfaceName");
		IwannaSee.printList(consumers);
	}
}
