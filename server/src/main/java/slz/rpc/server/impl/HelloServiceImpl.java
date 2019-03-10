package slz.rpc.server.impl;

import slz.rpc.server.service.HelloService;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		return "hello " + name;
	}

}
