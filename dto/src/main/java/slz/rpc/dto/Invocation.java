package slz.rpc.dto;

import java.io.Serializable;

public class Invocation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String interfaceName;
	private String methodName;
	private Class[] paramTypes;
	private Object[] params;

	public Invocation(String interfaceName, String methodName, Class[] paramTypes, Object[] params) {
		super();
		this.interfaceName = interfaceName;
		this.methodName = methodName;
		this.paramTypes = paramTypes;
		this.params = params;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class[] getParamTypes() {
		return paramTypes;
	}

	public void setParamTypes(Class[] paramType) {
		this.paramTypes = paramType;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

}
