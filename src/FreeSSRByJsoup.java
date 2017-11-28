import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class FreeSSRByJsoup {
	
	/*
	 * base64解码
	 */
	public static String base64Decode(String string) throws UnsupportedEncodingException {
		byte[] asBytes = Base64.getUrlDecoder().decode(string);
		String result = new String(asBytes,"UTF-8");
		return result;
	}
	
	/*
	 * 根据正则表达式提取字符串
	 */
	public static List<String> getStringByRegex(String regex,String source){
		List<String> list = new ArrayList<>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		while(matcher.find()) {
			list.add(matcher.group());
		}
		return list;
	}
	
	/*
	 * 获取节点可用状态
	 */
	public static List<Boolean> getNodeStatus() throws IOException {
		//请求地址 http://sstz.toyoo.ml/json/stats.json
		Document doc = Jsoup.connect("http://sstz.toyoo.ml/json/stats.json")
				.ignoreContentType(true)
				.get();
		String json  = doc.select("body").text();
		
		//直接用正则表达式取状态数据
		List<String> statusList = new ArrayList<>();
		statusList = getStringByRegex("(\"status\": ){1}[a-z]*", json);
		//转换为布尔值
		List<Boolean> status = new ArrayList<>();
		for (String string : statusList) {
			string = string.substring(10);
			status.add(Boolean.valueOf(string));
		}
		return status;
	}
	
	/*
	 * 执行ping操作,判断节点是否可用,及延时时间
	 */
	public static String getPingTime(String ip) throws IOException {
		//执行ping命令
		Process p = Runtime.getRuntime().exec("ping "+ ip);
		//接受返回的数据
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),"GBK"));
		String line;
		StringBuilder sb = new StringBuilder();
		while((line=br.readLine())!=null) {
			//System.out.println(line);
			sb.append(line);
		}
		//使用正则表达式ping结果
		List<String> result = FreeSSRByJsoup.getStringByRegex("[0-9]*ms$", sb.toString());
		//长度为零,及结果中不包含"平均 = XXXms"的即ping不通
		if(result.size()==0) {
			return "请求超时";
		}else {
			return result.get(0);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		//开始时间
		long startTime = System.currentTimeMillis();
		
		
		System.out.println("开始连接......");
		
		Document doc = Jsoup.connect("https://doub.bid/sszhfx/").get();
		
		System.out.println("连接成功......");

		//更新日期
		Elements updateTimeEle = doc.select("span[style='color: #ff6464;']");
		String updateTime = updateTimeEle.eq(1).text();
		System.out.println("更新日期: "+updateTime);
		
		//账号列表元素
		Elements tableEle = doc.select("table[width=100%]");
		
		//System.out.println(tableEle.html());
		
		//将元素转换字符串
		String tableStr = tableEle.toString();
		
		//正则表达式匹配SSR链接规则
		String regex = "(ssr://){1}[a-zA-Z0-9_]{60,}";
		List<String> SSRList = new ArrayList<>();
		//提取SSR链接
		System.out.println("获取SSR地址......");
		SSRList = getStringByRegex(regex, tableStr);
		System.out.println("共获得"+ SSRList.size() +"个账号");
		
		System.out.println("对地址进行base64解码......");
		
		String urlString = "";
		String[] urlArray;
		//存储节点
		List<SSRNode> nodeList = new ArrayList<>();
		//获取节点名
		for(int i = 1;i<tableEle.select("tr").size();i++) {
			String serverName = tableEle.select("tr").get(i).select("td").get(0).text();
			//去除ssr://
			urlString = SSRList.get(i-1);
			urlString = urlString.substring(6);
			//BASE64解码
			urlString = base64Decode(urlString);
			urlArray = urlString.split(":");
			//SSRNode节点
			SSRNode ssrNode = new SSRNode();
			//如果数组长度为11的IP地址是IPV6,长度为6的是IPV4
			if(urlArray.length > 6) {
				String ip = urlArray[0] 
							+ ":" + urlArray[1] 
							+ ":" +urlArray[2] 
							+ ":" + urlArray[3]
							+ ":" + urlArray[4]
							+ ":" + urlArray[5];
				//备注
				ssrNode.setRemarks(serverName);
				//ip
				ssrNode.setServer(ip);
				//端口
				ssrNode.setServer_port(Integer.valueOf(urlArray[6]));
				//协议
				ssrNode.setProtocol(urlArray[7]);
				//加密方式
				ssrNode.setMethod(urlArray[8]);
				//混淆
				ssrNode.setObfs(urlArray[9]);
				//密码
				String pdStr = getStringByRegex("[a-zA-Z0-9]*", urlArray[10]).get(0);
				//对密码进行base64二次解码
				pdStr = base64Decode(pdStr);
				ssrNode.setPassword(pdStr);
				//remarks
				String remStr = getStringByRegex("(=){1}[a-zA-Z0-9-]*", urlArray[10]).get(0);
				remStr = remStr.substring(1);
				ssrNode.setRemarks_base64(remStr);
				nodeList.add(ssrNode);
			}else {
				String ip = urlArray[0];
				//备注
				ssrNode.setRemarks(serverName);
				//ip
				ssrNode.setServer(ip);
				//端口
				ssrNode.setServer_port(Integer.valueOf(urlArray[1]));
				//协议
				ssrNode.setProtocol(urlArray[2]);
				//加密方式
				ssrNode.setMethod(urlArray[3]);
				//混淆
				ssrNode.setObfs(urlArray[4]);
				//密码
				String pdStr = getStringByRegex("[a-zA-Z0-9]*", urlArray[5]).get(0);
				//对密码进行base64二次解码
				pdStr = base64Decode(pdStr);
				ssrNode.setPassword(pdStr);
				//remarks
				String remStr = getStringByRegex("(=){1}[a-zA-Z0-9-]*", urlArray[5]).get(0);
				remStr = remStr.substring(1);
				ssrNode.setRemarks_base64(remStr);
				nodeList.add(ssrNode);
			}
		
		}
		System.out.println("地址base64解码完成");	
		
		//查询节点状态
		List<Boolean> statusList = getNodeStatus();
	 	for (int i = 0; i < statusList.size();i++) {
			nodeList.get(i).setStatus(statusList.get(i));
		}
		
	 	//进行ping操作
	 	System.out.println("执行ping操作中......");
	 	for (SSRNode nl : nodeList) {
			if(nl.isStatus()) {
				String result = getPingTime(nl.getServer());
				nl.setAvgPingTime(result);
			}
		}
	 	
	 	//信息显示
		System.out.println("显示免费节点账号信息:");
		int nodeCount = 0;
		for (SSRNode sn : nodeList) {
			System.out.println("======== 第"+ ++nodeCount +"个节点 ========");
			System.out.println(sn.toString());
		}
		
		System.out.println();
		//结束时间
		long endTime = System.currentTimeMillis();
		System.out.println("耗时:"+ (endTime-startTime)*1.0/1000 + " 秒");
		
		//退出
		System.out.println("\n按回车键退出");
		Scanner input = new Scanner(System.in);
		String isExit = input.nextLine();
		if(isExit.length() == 0) {
			//关闭当前进程
			input.close();
			System.exit(0);
		}
	}
}

