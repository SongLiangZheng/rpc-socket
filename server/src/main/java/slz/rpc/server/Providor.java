package slz.rpc.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.codec.StringEncoderComparator;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;

import slz.rpc.dto.RegisterEntity;
import slz.rpc.dto.URL;
import slz.rpc.server.impl.HelloServiceImpl;
import slz.rpc.server.service.HelloService;

public class Providor {
	static ExecutorService exec = Executors.newFixedThreadPool(30);

	public static void start() throws IOException {
		ServerSocket serverSocket = new ServerSocket(8082);
		System.out.println("serverSocket start!");
		while (true) {
			Socket socket = serverSocket.accept();
			exec.execute(new SocketRunnble(socket));

		}
	}

	public static boolean register() throws URISyntaxException, ClientProtocolException, IOException {
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://localhost:8080/register");
		StringEntity entity = new StringEntity(new Gson().toJson(new RegisterEntity(HelloService.class.getName(),
				new URL("localhost", 8082), HelloServiceImpl.class.getName())), "utf-8");
		entity.setContentType("application/json");
		post.setEntity(entity);

		InputStream in = httpClient.execute(post).getEntity().getContent();
		boolean res = new DataInputStream(in).readBoolean();
		return res;
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
		boolean res = register();
		if (res)
			System.out.println("register service successfully!");
		else
			System.out.println("fail to register service.");
		start();
	}
}
