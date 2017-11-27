import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FreeSSR {
	
	public static void main(String[] args) {
		//定义爬取地址
		String url = "https://doub.bid/sszhfx/";
		//存储网页源代码
		String result = "";
		//缓冲输入流
		BufferedReader in = null;
		
		try {
			//将String转换成Url对象
			URL realUrl = new URL(url);
			//初始化连接
			URLConnection connection = realUrl.openConnection();
			//开始连接
			connection.connect();
			//初始化输入流
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			//用来临时存储抓取到的每一行数据
			String line = "";
			while((line = in.readLine())!= null){
				//遍历抓取到的每一行数据将其存储到result里面
				result += line;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("请求异常");
			e.printStackTrace();
		}finally {
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("结果:\n"+result);
		
	}
	
}
