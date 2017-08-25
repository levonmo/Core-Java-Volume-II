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

//URLConnection��
public class ObjectD {
	
	public void display() throws IOException{
		
		//���������û������URL
		byte[] buf = new byte[100];
		
		//��ȡ�û������URL
		System.out.println("������URL:");
		int count = System.in.read(buf);
		String urlPath = new String(buf,0,count);
		
		//����һ��URL����(������File���������ȣ�����һ��URL��ص�����)
		URL url = new URL(urlPath);
		
		//����URLConnection����
		URLConnection c = url.openConnection();
		
		//��������
		c.connect();
		
		//��һ��������
		InputStream inputStream = c.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		//��һ�������
		FileOutputStream fileOutputStream = new FileOutputStream("sina.txt");
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
		
		//��ȡ�����������ݣ�����ӡ������̨��
		String info = bufferedReader.readLine();
		while(info != null){
			System.out.println(info);
			//����Ϣ������ı���
			bufferedWriter.write(info);
			info = bufferedReader.readLine();
		}
		
		
		//��ʾ�����ӵ������Ϣ
		System.out.println("���ݵ����ͣ�" + c.getContentType());
		System.out.println("���ݵı��룺" + c.getContentEncoding());
		System.out.println("���ݵĳ���" + c.getContentLength());
		System.out.println("���������ڣ�" + new Date(c.getDate()));
		System.out.println("����޸ĵ����ڣ�" + new Date(c.getLastModified()));
		System.out.println("��ֹ�����ڣ�" + new Date(c.getExpiration()));
	}
	
}
