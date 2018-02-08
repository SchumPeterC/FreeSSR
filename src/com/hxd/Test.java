package com.hxd;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 测试类,用来测试小功能部分
 * @author HXD
 *
 */
public class Test {
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
			sb.append(line+"\n");
		}
		//使用正则表达式ping结果
		List<String> result = FreeSSR.getStringByRegex("[0-9]*ms$", sb.toString());
		//长度为零,及结果中不包含"平均 = XXXms"的即ping不通
		if(result.size()==0) {
			return "请求超时";
		}else {
			return result.get(0);
		}
	}
	
	/*
	 * 读取本地json文件
	 */
	public static String readJSON() throws IOException {
		//文件路径
		String filePath = "C:\\Users\\HXD\\Desktop\\gui-config.json";
		//文件编码
		String encoding = "UTF-8";
		//指定文件地址
	    File file = new File(filePath);
	    //存储文件内容
	    StringBuilder sb  = new StringBuilder();
	    //判断文件是否存在
	    if (file.isFile() && file.exists()) { 
	        InputStreamReader read = new InputStreamReader(
	                new FileInputStream(file), encoding);
	        BufferedReader bufferedReader = new BufferedReader(read);
	        String lineTxt = null;
	        while ((lineTxt = bufferedReader.readLine()) != null) {
	               sb.append(lineTxt+"\n");
	         }
	        read.close();
	    } else {
	        System.out.println("找不到指定的文件");
	    }
	    String result = sb.toString();
	    //System.out.println(result);
	    //输出文件
	    File file2 = new File("C:\\Users\\HXD\\Desktop\\config.json");
	    FileWriter fw = new FileWriter(file2);
	    fw.write(result);
	    fw.close();
	    return result;
	} 
	
	/*启动ShadowsocksR-dotnet4.0.exe
	 * 判断SSR程序是否运行,运行就杀掉进程
	 * cmd命令
	 * 杀掉程序:taskkill /f /im ShadowsocksR-dotnet4.0.exe
	 * 判断程序是否运行:tasklist|find /i "ShadowsocksR-dotnet4.0.exe"
	 * 启动程序:C:\GreenSoftware\ShadowsocksR-4.7.0\ShadowsocksR-dotnet4.0.exe
	 */
	public static void startSSR() throws IOException {
		//启动命令
		String startCommand = "C:\\GreenSoftware\\ShadowsocksR-4.7.0\\ShadowsocksR-dotnet4.0.exe";
		//判断,查找有没有ssr进程,不加"cmd /c"会报错
		String findCommand = "cmd /c tasklist|findstr /i \"ShadowsocksR-dotnet4.0\"";
		//杀死进程命令
		String killCommand = "taskkill /f /im ShadowsocksR-dotnet4.0.exe";
		
		Runtime run = Runtime.getRuntime();
		//先判断SSR是否已运行
		Process process = run.exec(findCommand);
		//接受返回信息
		BufferedReader  bufferedReader = new BufferedReader  
	            (new InputStreamReader(process.getInputStream()));
		String line = null;
		//存储返回信息
		StringBuilder sb = new StringBuilder();
		 while ((line = bufferedReader.readLine()) != null) {  
	            sb.append(line + "\n");  
		 }
		 System.out.println(sb);
		 //从反馈的信息中进行判断,包含"ShadowsocksR-dotnet4.0"代表SSR程序已启动
		 String regex = "ShadowsocksR-dotnet4.0";
		 int count = FreeSSR.getStringByRegex(regex, sb.toString()).size();
		 if(count == 2) {
			 //重启,先杀再启
			 System.out.println("SSR客户端已启动,将进行重启");
			 run.exec(killCommand);
			 //延时,等待程序退出再重新启动
			 try {
				 System.out.println("请稍候......");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 run.exec(startCommand);
			 System.out.println("重启完成");
		 }else {
			 //否则直接启动SSR客户端
			 System.out.println("启动SSR客户端");
			 run.exec(startCommand);
		 }
	}
	
	public static void freessSite() throws IOException {
		//获取当前时间戳
		String time = String.valueOf(System.currentTimeMillis());
		//以时间戳为请求参数
		String urlStr = "https://free-ss.site/ss.php?_=" + time;
		Document doc = Jsoup.connect(urlStr)
				.ignoreContentType(true)
				.get();
		String json = doc.select("body").text();
		System.out.println(json);
	}
	
	public static void main(String[] args) throws IOException {
		//System.out.println(getPingTime("45.35.52.194"));
		//System.out.println(getPingTime("192.168.1.1"));
//		String result = readJSON();
//		Gson gson = new Gson();
//		GUIConfig  gc = gson.fromJson(result, GUIConfig.class);
//		gc.getConfigs().get(0).setRemarks("测试");
//		gc.getConfigs().get(0).setGroup("美国一");
//		System.out.println(gc.getConfigs().get(0).getRemarks());
//		String changedStr = gson.toJson(gc);
//		System.out.println("修改");
//		System.out.println(changedStr);
//		startSSR();
		freessSite();
	}
}
	
