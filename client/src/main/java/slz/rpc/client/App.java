package slz.rpc.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.io.IOUtils;

import slz.rpc.dto.Invocation;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 8082);
		Invocation invocation = new Invocation("slz.rpc.server.service.HelloService", "sayHello",
				new Class[] { String.class }, new String[] { "张三" });
		ObjectOutputStream objOutputStream = new ObjectOutputStream(socket.getOutputStream());
		objOutputStream.writeObject(invocation);

		InputStream in = socket.getInputStream();
		String res = IOUtils.toString(in);
		System.out.println(res);
		socket.close();
	}
}
