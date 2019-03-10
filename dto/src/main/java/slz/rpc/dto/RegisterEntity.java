package slz.rpc.dto;

import java.io.Serializable;

public class RegisterEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private String interfaceName;
	private URL url;
	private String implClass;

	public RegisterEntity() {
		// TODO Auto-generated constructor stub
	}

	public RegisterEntity(String interfaceName, URL url, String implClass) {
		super();
		this.interfaceName = interfaceName;
		this.url = url;
		this.implClass = implClass;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getImplClass() {
		return implClass;
	}

	public void setImplClass(String implClass) {
		this.implClass = implClass;
	}

}
