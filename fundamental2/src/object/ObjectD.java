package object;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

//URLConnection类
public class ObjectD {
	
	public void display() throws IOException{
		
		//用来缓存用户输入的URL
		byte[] buf = new byte[100];
		
		//获取用户输入的URL
		System.out.println("请输入URL:");
		int count = System.in.read(buf);
		String urlPath = new String(buf,0,count);
		
		//创建一个URL对象(可以与File对象进行类比，就是一个URL相关的属性)
		URL url = new URL(urlPath);
		
		//创建URLConnection连接
		URLConnection c = url.openConnection();
		
		//建立连接
		c.connect();
		
		//打开一个输入流
		InputStream inputStream = c.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		//打开一个输出流
		FileOutputStream fileOutputStream = new FileOutputStream("sina.txt");
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
		
		//读取输入流中数据，并打印到控制台上
		String info = bufferedReader.readLine();
		while(info != null){
			System.out.println(info);
			//将信息输出到文本中
			bufferedWriter.write(info);
			info = bufferedReader.readLine();
		}
		
		
		//显示该连接的相关信息
		System.out.println("内容的类型：" + c.getContentType());
		System.out.println("内容的编码：" + c.getContentEncoding());
		System.out.println("内容的长度" + c.getContentLength());
		System.out.println("创建的日期：" + new Date(c.getDate()));
		System.out.println("最后修改的日期：" + new Date(c.getLastModified()));
		System.out.println("终止的日期：" + new Date(c.getExpiration()));
	}
	
}
