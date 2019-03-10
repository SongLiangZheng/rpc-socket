package slz.rpc.server;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) throws ClientProtocolException, URISyntaxException, IOException {
		SpringApplication.run(ServerApplication.class, args);
	}

	
}
