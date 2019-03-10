package slz.rpc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import slz.rpc.dto.RegisterEntity;
import slz.rpc.dto.URL;

@RestController
public class RegisterController {
	private static Map<String, Map<URL, String>> registerMap = new HashMap<>();

	@PostMapping("/register")
	public boolean register(@RequestBody RegisterEntity registerEntity) {

		URL url = registerEntity.getUrl();
		String implClass = registerEntity.getImplClass();
		String interfaceName = registerEntity.getInterfaceName();
		System.out.println(interfaceName + "  " + url.getIp() + "  " + implClass);
		Map<URL, String> map = new HashMap<>();
		map.put(registerEntity.getUrl(), registerEntity.getImplClass());
		registerMap.put(registerEntity.getInterfaceName(), map);

		System.out.println("注册成功");
		String json = new Gson().toJson(registerMap);
		System.out.println(json);
		return true;
	}

	@GetMapping("/className")
	public String getClassName(@RequestParam String interfaceName) {
		return registerMap.get(interfaceName).values().iterator().next();
	}

	@GetMapping("/show")
	public String show() {
		System.out.println(new Gson().toJson(registerMap));
		return new Gson().toJson(registerMap);
	}
}
