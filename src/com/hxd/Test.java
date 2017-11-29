package com.hxd;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.gson.Gson;
import com.hxd.gson.GUIConfig;
/**
 * 测试文件
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
		List<String> result = FreeSSRByJsoup.getStringByRegex("[0-9]*ms$", sb.toString());
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
	
	public static void main(String[] args) throws IOException {
		//System.out.println(getPingTime("45.35.52.194"));
		//System.out.println(getPingTime("192.168.1.1"));
		String result = readJSON();
		Gson gson = new Gson();
		GUIConfig  gc = gson.fromJson(result, GUIConfig.class);
		gc.getConfigs().get(0).setRemarks("测试");
		gc.getConfigs().get(0).setGroup("美国一");
		System.out.println(gc.getConfigs().get(0).getRemarks());
		String changedStr = gson.toJson(gc);
		System.out.println("修改");
		System.out.println(changedStr);
	}
}
	
