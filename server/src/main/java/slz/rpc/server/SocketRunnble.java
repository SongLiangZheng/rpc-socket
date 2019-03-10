package slz.rpc.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;

import slz.rpc.dto.Invocation;
import slz.rpc.dto.URL;

public class SocketRunnble implements Runnable {
	private Socket socket;

	public SocketRunnble(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			Object obj = in.readObject();
			if (obj instanceof Invocation) {
				Invocation invocation = (Invocation) obj;

				Class clazz = Class.forName(invocation.getInterfaceName());
				Method method = clazz.getMethod(invocation.getMethodName(), invocation.getParamTypes());
				CloseableHttpClient httpCilent = HttpClients.createDefault();
				URI uri = new URIBuilder("http://localhost:8080/className")
						.addParameter("interfaceName", invocation.getInterfaceName()).build();
				HttpGet httpGet = new HttpGet(uri);
				CloseableHttpResponse response = httpCilent.execute(httpGet);
				InputStream inputStream = response.getEntity().getContent();
				StringBuilder sb = new StringBuilder();
				byte[] buffer = new byte[inputStream.available()];
				inputStream.read(buffer);
				String implClazzString = new String(buffer);
//				String implClazzString = IOUtils.toString(inputStream);

				Class implClazz = Class.forName(implClazzString);

				String res = (String) method.invoke(implClazz.newInstance(), invocation.getParams());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				out.write(res.getBytes());

				in.close();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
