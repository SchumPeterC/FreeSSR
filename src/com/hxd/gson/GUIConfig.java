package com.hxd.gson;

import java.util.List;

/**
 * SSR配置文件gui-config.josn所对应的实体类
 * @author HXD
 *
 */
public class GUIConfig {
	//根据配置文件定义的,并设定了默认值;
	public List<Server> configs;
	int index = 0;
	boolean random = false;
	int sysProxyMode = 2;
	boolean shareOverLan = false;
	int localPort = 1080;
	String localAuthPassword = "6wQdmZblOD-iaGLGOMWR";
	String dnsServer = "";
	int reconnectTimes = 2;
	int randomAlgorithm = 3;
	boolean randomInGroup = false;
	int TTL = 0;
	int connectTimeout = 5;
	int proxyRuleMode = 2;
	boolean proxyEnable = false;
	boolean pacDirectGoProxy = false;
	int proxyType = 0;
	String proxyHost = "";
	int proxyPort = 0;
	String proxyAuthUser = "";
	String proxyAuthPass = "";
	String proxyUserAgent = "";
	String authUser = "";
	String authPass = "";
	boolean autoBan = false;
	boolean sameHostForSameTarget = false;
	int keepVisitTime = 180;
	boolean isHideTips = false;
	boolean nodeFeedAutoUpdate = false;
	public List<ServerSubscribes> serverSubscribes;
	Token token;
	public class Token{
		
	}
	PortMap portMap;
	public class PortMap{
		
	}
	public List<Server> getConfigs() {
		return configs;
	}
	public void setConfigs(List<Server> configs) {
		this.configs = configs;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean isRandom() {
		return random;
	}
	public void setRandom(boolean random) {
		this.random = random;
	}
	public int getSysProxyMode() {
		return sysProxyMode;
	}
	public void setSysProxyMode(int sysProxyMode) {
		this.sysProxyMode = sysProxyMode;
	}
	public boolean isShareOverLan() {
		return shareOverLan;
	}
	public void setShareOverLan(boolean shareOverLan) {
		this.shareOverLan = shareOverLan;
	}
	public int getLocalPort() {
		return localPort;
	}
	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}
	public String getLocalAuthPassword() {
		return localAuthPassword;
	}
	public void setLocalAuthPassword(String localAuthPassword) {
		this.localAuthPassword = localAuthPassword;
	}
	public String getDnsServer() {
		return dnsServer;
	}
	public void setDnsServer(String dnsServer) {
		this.dnsServer = dnsServer;
	}
	public int getReconnectTimes() {
		return reconnectTimes;
	}
	public void setReconnectTimes(int reconnectTimes) {
		this.reconnectTimes = reconnectTimes;
	}
	public int getRandomAlgorithm() {
		return randomAlgorithm;
	}
	public void setRandomAlgorithm(int randomAlgorithm) {
		this.randomAlgorithm = randomAlgorithm;
	}
	public boolean isRandomInGroup() {
		return randomInGroup;
	}
	public void setRandomInGroup(boolean randomInGroup) {
		this.randomInGroup = randomInGroup;
	}
	public int getTTL() {
		return TTL;
	}
	public void setTTL(int tTL) {
		TTL = tTL;
	}
	public int getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public int getProxyRuleMode() {
		return proxyRuleMode;
	}
	public void setProxyRuleMode(int proxyRuleMode) {
		this.proxyRuleMode = proxyRuleMode;
	}
	public boolean isProxyEnable() {
		return proxyEnable;
	}
	public void setProxyEnable(boolean proxyEnable) {
		this.proxyEnable = proxyEnable;
	}
	public boolean isPacDirectGoProxy() {
		return pacDirectGoProxy;
	}
	public void setPacDirectGoProxy(boolean pacDirectGoProxy) {
		this.pacDirectGoProxy = pacDirectGoProxy;
	}
	public int getProxyType() {
		return proxyType;
	}
	public void setProxyType(int proxyType) {
		this.proxyType = proxyType;
	}
	public String getProxyHost() {
		return proxyHost;
	}
	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}
	public int getProxyPort() {
		return proxyPort;
	}
	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}
	public String getProxyAuthUser() {
		return proxyAuthUser;
	}
	public void setProxyAuthUser(String proxyAuthUser) {
		this.proxyAuthUser = proxyAuthUser;
	}
	public String getProxyAuthPass() {
		return proxyAuthPass;
	}
	public void setProxyAuthPass(String proxyAuthPass) {
		this.proxyAuthPass = proxyAuthPass;
	}
	public String getProxyUserAgent() {
		return proxyUserAgent;
	}
	public void setProxyUserAgent(String proxyUserAgent) {
		this.proxyUserAgent = proxyUserAgent;
	}
	public String getAuthUser() {
		return authUser;
	}
	public void setAuthUser(String authUser) {
		this.authUser = authUser;
	}
	public String getAuthPass() {
		return authPass;
	}
	public void setAuthPass(String authPass) {
		this.authPass = authPass;
	}
	public boolean isAutoBan() {
		return autoBan;
	}
	public void setAutoBan(boolean autoBan) {
		this.autoBan = autoBan;
	}
	public boolean isSameHostForSameTarget() {
		return sameHostForSameTarget;
	}
	public void setSameHostForSameTarget(boolean sameHostForSameTarget) {
		this.sameHostForSameTarget = sameHostForSameTarget;
	}
	public int getKeepVisitTime() {
		return keepVisitTime;
	}
	public void setKeepVisitTime(int keepVisitTime) {
		this.keepVisitTime = keepVisitTime;
	}
	public boolean isHideTips() {
		return isHideTips;
	}
	public void setHideTips(boolean isHideTips) {
		this.isHideTips = isHideTips;
	}
	public boolean isNodeFeedAutoUpdate() {
		return nodeFeedAutoUpdate;
	}
	public void setNodeFeedAutoUpdate(boolean nodeFeedAutoUpdate) {
		this.nodeFeedAutoUpdate = nodeFeedAutoUpdate;
	}
	public List<ServerSubscribes> getServerSubscribes() {
		return serverSubscribes;
	}
	public void setServerSubscribes(List<ServerSubscribes> serverSubscribes) {
		this.serverSubscribes = serverSubscribes;
	}
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
	public PortMap getPortMap() {
		return portMap;
	}
	public void setPortMap(PortMap portMap) {
		this.portMap = portMap;
	}
	
	
	
}
