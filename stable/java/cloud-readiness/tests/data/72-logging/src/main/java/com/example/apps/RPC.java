package com.example.apps;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;
import org.apache.xmlrpc.client.XmlRpcTransport;
import org.apache.xmlrpc.client.XmlRpcClient;
import redstone.xmlrpc.XmlRpcInvocationInterceptor;
import redstone.xmlrpc.XmlRpcParser;

import java.net.URL;
import java.util.Properties;

public class RPC {
	
	public interface MyCall extends Call {}
	
	public interface MyXmlRpcTransport extends XmlRpcTransport {}
	
	public interface MyXmlRpcInvocationInterceptor extends XmlRpcInvocationInterceptor {}
	
	public static void main(String argv[]) {
		ServiceFactory sf = new ServiceFactory() {
			@Override
			public Service createService(URL url, QName qName) throws ServiceException {
				return null;
			}

			@Override
			public Service createService(QName qName) throws ServiceException {
				return null;
			}

			@Override
			public Service loadService(Class aClass) throws ServiceException {
				return null;
			}

			@Override
			public Service loadService(URL url, Class aClass, Properties properties) throws ServiceException {
				return null;
			}

			@Override
			public Service loadService(URL url, QName qName, Properties properties) throws ServiceException {
				return null;
			}
		};
		XmlRpcClient client = new XmlRpcClient();
		XmlRpcParser parse = new XmlRpcParser() {
			@Override
			protected void handleParsedValue(Object o) {

			}
		};
	}

}
