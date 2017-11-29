package com.hxd.gson;
/*
 * 服务器部分对应的实体类
 *
 */
public class Server {
	String remarks;
	//String id;
	String server;
	int server_port;
	//int server_udp_port = 0;
	String password;
	String method;
	String protocol;
	//String protocolparam = "";
	String obfs;
	//String obfsparam ="";
	String remarks_base64;
	String group ="";
	//boolean enable = true;
	//boolean udp_over_tcp = false;
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public int getServer_port() {
		return server_port;
	}
	public void setServer_port(int server_port) {
		this.server_port = server_port;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getObfs() {
		return obfs;
	}
	public void setObfs(String obfs) {
		this.obfs = obfs;
	}
	public String getRemarks_base64() {
		return remarks_base64;
	}
	public void setRemarks_base64(String remarks_base64) {
		this.remarks_base64 = remarks_base64;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	
	
}
