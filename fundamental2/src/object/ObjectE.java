package object;

import java.io.IOException;
import java.net.InetAddress;

//InetAddress��(������(��ַ)+IP��ַ����һ��ʵ��)
public class ObjectE{

	public void display() throws IOException{
		byte[] buf = new byte[100];
		
		//��ȡ�û�����
		System.out.println("��������������");
		int count = System.in.read(buf);
		String hostName = new String(buf,0,count-2);
		
		//��ȡ������������IP��ַ
		InetAddress[] allByName = InetAddress.getAllByName(hostName);
		System.out.println();
		System.out.println("����" + allByName[0].getHostName() + "������IP��ַ");
		
		//��ʾ������������IP��ַ
		for (InetAddress inetAddress : allByName) {
			System.out.println(inetAddress.getHostAddress());
		}
	}
	
}
