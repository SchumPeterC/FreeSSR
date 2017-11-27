import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
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
	
	
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("开始连接......");
		
		Document doc = Jsoup.connect("https://doub.bid/sszhfx/").get();
		
		System.out.println("连接成功......");
//		String page = doc.html();
//		System.out.println(page);
		//更新日期
		Elements updateTimeEle = doc.select("span[style='color: #ff6464;']");
		String updateTime = updateTimeEle.eq(1).text();
		//账号列表
		Elements table = doc.select("table[width=100%] tr");
		//账号数,减去表头
		String test2 = table.toString();
		//System.out.println(test2);
		
		//正则表达式匹配SSR链接规则
		String regex = "(ssr://){1}[a-zA-Z0-9_]{60,}";
		List<String> SSRList = new ArrayList<>();
		
		System.out.println("获取SSR地址......");
		
		SSRList = getStringByRegex(regex, test2);
		
		System.out.println("共获得"+ SSRList.size() +"个账号");
		
		System.out.println("对地址进行base64解码......");
		
		List<String> passwordList = new ArrayList<>();
		for (String string : SSRList) {
			//去除ssr://
			string = string.substring(6);
			//BASE64解码
			string = base64Decode(string);
			//提取密码字符串
			String regex2 = "(auth:){1}[a-zA-Z0-9]*";
			passwordList.add(getStringByRegex(regex2, string).toString());
			
			System.out.println(string);
		}
		System.out.println("地址base64解码完成");
		
//		String res = "MTA0LjE2MC4xNzMuMTQxOjM0NDI6YXV0aF9hZXMxMjhfc2hhMTpjaGFjaGEyMDp0bHMxLjJfdGlja2V0X2F1dGg6Wkc5MVlpNXBieTl6YzNwb1puZ3ZLbVJ2ZFdJdVltbGtMM056ZW1obWVDOHFNelEwTWcvP3JlbWFya3M9NXB5czZMU201WS0zNXAybDZJZXFPbVJ2ZFdJdWFXOHZjM042YUdaNEwtbVZuT1dEai1XZm4tV1FqVHBrYjNWaUxtSnBaQzl6YzNwb1puZ3Y";
//		byte[] asBytes2 = Base64.getUrlDecoder().decode(res);  
//		res = new String(asBytes2,"UTF-8");
//		System.out.println(res);
		
//		int accountCount = table.size()-1;
//		String test = table.select(".dl1").html();
//		System.out.println(test);
		//正则表达式1 (ssr://){1}[a-zA-Z0-9_]*("){1}
		//正则表达式2 (ssr://){1}[a-zA-Z0-9_]{60,}
//		List<String> accoutList = new ArrayList<>();
//		for(int i = 0; i < accountCount + 1; i++) {
//			Element  tr = accountEle.get(i);
//			String[] account = null;
//			for(int j=0;j<6;j++) {
//				account[j] = tr.child(j).text();
//			}
//			account[6] = tr.child(6).select("a[href~=http://doub.pw/qr/qr.php?text=*]").toString();
//			System.out.println(account.toString());
//		}
//		for (Node i : test) {
//			System.out.println(i.text());
//		}
		
		//System.out.println("更新日期: "+updateTime);
		//System.out.println("共有"+accountCount+"个账号");
		//System.out.println("账号列表:\n" + account);
		
	}
}
