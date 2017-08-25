package object;

import java.io.IOException;
import java.net.InetAddress;

//InetAddress类(主机名(网址)+IP地址构成一个实例)
public class ObjectE{

	public void display() throws IOException{
		byte[] buf = new byte[100];
		
		//获取用户输入
		System.out.println("请输入主机名：");
		int count = System.in.read(buf);
		String hostName = new String(buf,0,count-2);
		
		//获取该主机的所有IP地址
		InetAddress[] allByName = InetAddress.getAllByName(hostName);
		System.out.println();
		System.out.println("主机" + allByName[0].getHostName() + "有以下IP地址");
		
		//显示该主机的所有IP地址
		for (InetAddress inetAddress : allByName) {
			System.out.println(inetAddress.getHostAddress());
		}
	}
	
}
