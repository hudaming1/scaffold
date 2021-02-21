package org.hum.scaffold;

import java.io.IOException;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;

public class DubboExportTests {

	public static void main(String[] args) throws IOException {
		ServiceConfig<DemoService> serviceBean = new ServiceConfig<DemoService>();
		serviceBean.setApplication(new ApplicationConfig("huming"));
		serviceBean.setInterface(DemoService.class);
		serviceBean.setRef(new DemoServiceImpl());
		serviceBean.setProtocol(new ProtocolConfig("dubbo", 20880));
		serviceBean.setRegistry(new RegistryConfig("N/A"));
		serviceBean.export();
		System.out.println("server start!");
		System.in.read();
	}
	
	public static interface DemoService {
		String sayHello(String name);
	}
	
	public static class DemoServiceImpl implements DemoService {

		@Override
		public String sayHello(String name) {
			return "hello " + name;
		}
	}
}
