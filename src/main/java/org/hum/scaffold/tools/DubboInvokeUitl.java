package org.hum.scaffold.tools;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.ReferenceBean;

public class DubboInvokeUitl {
	
	public static <T> T getDubboReference(Class<T> classType, Server serverEnum, String version) {
		String url = "dubbo://" + serverEnum.getServer() + "/" + classType.getName() + ":" + version;
		return getDubboReference(classType, url);
	}
	
	public static <T> T getDubboReference(Class<T> classType, Server serverEnum) {
		String url = "dubbo://" + serverEnum.getServer() + "/" + classType.getName();
		return getDubboReference(classType, url);
	}
	
	public static <T> T getDubboReference(Class<T> classType, String ip, int port) {
		String url = "dubbo://" + ip + ":" + port + "/" + classType.getName();
		return getDubboReference(classType, url);
	}

	public static <T> T getDubboReference(Class<T> classType, String url) {
        return getDubboReference(classType.getName(), url);
	}

	public static <T> T getDubboReference(String classType, String url) {
		ReferenceBean<T> ref = new ReferenceBean<T>();
        ref.setApplication(new ApplicationConfig("HumingTest"));
        if (url.startsWith("zookeeper://")) {
        	ref.setRegistry(new RegistryConfig(url, "zookeeper"));
        } else {
        	ref.setUrl(url);
        }
        ref.setProtocol("dubbo");
        ref.setInterface(classType);
        // 如果服务端还有其他参数，例如group或version，则需要给ref赋值
        ref.setVersion(null);
        ref.setGroup(null);
        
        return ref.get();
	}
	
	public static enum Server {
		
		Dev("10.2.39.16:30883"),
		;
		
		private String server;
		
		Server(String server) {
			this.server = server;
		}
		
		public String getServer() {
			return server;
		}
	}
}
