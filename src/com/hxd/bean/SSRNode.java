package com.hxd.bean;
/**
 * SSR节点类
 * @author HXD
 *
 */
public class SSRNode implements Comparable<SSRNode>{
	
	/*
	 * 根据情况,设定了部分属性默认值
	 */
	
	/*
	 * 以下这部分属性可以从连接中得到
	 */
	//IP
	String server;
	//端口
	int server_port;
	//协议
	String protocol = "origin";
	//加密方式
	String method;
	//混淆
	String obfs = "plain";
	//密码
	String password;
	
	String remarks_base64;
	
	
	/*
	 * 以下是配置文件中其他属性,先定义上再说
	 */
	//id
	String id;
	
	//直接用服务器地址名做备注
	String remarks;
	
	int server_udp = 0;
	
	//混淆参数
	String protocolparam;
	
	//协议参数
	String obfsparam;
	
	//启用与否
	boolean enable = false;
	
	//状态,可用/不可用
	boolean status = true;

	//组别
	String group = "";
	
	boolean udp_over_tcp = false;
	
	String avgPingTime = "不可用";

	@Override
	public String toString() {
		return "节点信息: \n服务器名: " + remarks 
				+ "\nIP地址: " + server 
				+ "\n端口: " + server_port 
				+ "\n协议: " + protocol 
				+ "\n加密方式: "+ method 
				+ "\n混淆: " + obfs 
				+ "\n密码: " + password 
				+ "\n备注: " + remarks_base64
				+ "\n状态: " + status
				+ "\n平均延迟: " + avgPingTime;
	}
	
	

	public String getAvgPingTime() {
		return avgPingTime;
	}



	public void setAvgPingTime(String avgPingTime) {
		this.avgPingTime = avgPingTime;
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

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getObfs() {
		return obfs;
	}

	public void setObfs(String obfs) {
		this.obfs = obfs;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemarks_base64() {
		return remarks_base64;
	}

	public void setRemarks_base64(String remarks_base64) {
		this.remarks_base64 = remarks_base64;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getServer_udp() {
		return server_udp;
	}

	public void setServer_udp(int server_udp) {
		this.server_udp = server_udp;
	}

	public String getProtocolparam() {
		return protocolparam;
	}

	public void setProtocolparam(String protocolparam) {
		this.protocolparam = protocolparam;
	}

	public String getObfsparam() {
		return obfsparam;
	}

	public void setObfsparam(String obfsparam) {
		this.obfsparam = obfsparam;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public boolean isUdp_over_tcp() {
		return udp_over_tcp;
	}

	public void setUdp_over_tcp(boolean udp_over_tcp) {
		this.udp_over_tcp = udp_over_tcp;
	}


	//自定义排序顺序
	@Override
	public int compareTo(SSRNode o) {
		//去掉'ms'并转换为整数
		int p1 = Integer.valueOf(this.avgPingTime.substring(0, 3));
		int p2 = Integer.valueOf(o.getAvgPingTime().substring(0,3));
		if(p1>p2) {
			return 1;
		}else if(p1<p2) {
			return -1;
		}else {
			return 0;
		}
	}
	
	
}
