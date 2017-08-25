package object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

//URL编程
public class ObjectC  {
	
	public void display() throws IOException{
		
		//用数组进行缓存用户输入的URL
		byte[] buf = new byte[100];
		
		//获取用户输入的URL
		System.out.print("请输入URL:");
		int count = System.in.read(buf);
		
		//创建一个URL
		String addr = new String(buf,0,count);
		URL url = new URL(addr);
		
		//打开一个输入流
		InputStream openStream = url.openStream();//打开该URL的输入流
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openStream));
		
		//读取数据
		String info = bufferedReader.readLine();
		
		while (info != null) {
			System.out.println(info);
			info = bufferedReader.readLine();
		}
		
		System.out.println("该URL的主机名为：" + url.getHost());
		System.out.println("该URL的文件名为：" + url.getFile());
		System.out.println("该URL的协议为：" + url.getProtocol());
		System.out.println("该URL的端口号为：" + url.getPort());		
	}
	
}
