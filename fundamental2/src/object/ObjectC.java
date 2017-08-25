package object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

//URL���
public class ObjectC  {
	
	public void display() throws IOException{
		
		//��������л����û������URL
		byte[] buf = new byte[100];
		
		//��ȡ�û������URL
		System.out.print("������URL:");
		int count = System.in.read(buf);
		
		//����һ��URL
		String addr = new String(buf,0,count);
		URL url = new URL(addr);
		
		//��һ��������
		InputStream openStream = url.openStream();//�򿪸�URL��������
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openStream));
		
		//��ȡ����
		String info = bufferedReader.readLine();
		
		while (info != null) {
			System.out.println(info);
			info = bufferedReader.readLine();
		}
		
		System.out.println("��URL��������Ϊ��" + url.getHost());
		System.out.println("��URL���ļ���Ϊ��" + url.getFile());
		System.out.println("��URL��Э��Ϊ��" + url.getProtocol());
		System.out.println("��URL�Ķ˿ں�Ϊ��" + url.getPort());		
	}
	
}
