package object;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

//һ���򵥵Ŀͻ���
public class ObjectF implements Runnable  {
	
	Thread thread;

	public void run() {
		Socket socket;
		DataOutputStream dataOutputStream;
		DataInputStream dataInputStream;
		//���ӵ����ص�8888�˿�
		try {
			//����һ��Socket����,���ӵ����ص�8888�˿�
			socket = new Socket("localhost",8888);
			//ͨ��Socket�����ȡ���������
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			dataInputStream = new DataInputStream(socket.getInputStream());
			//��ȡInetAddress����(������������IP��ַ���)
			InetAddress inetAddress = socket.getInetAddress();
			System.out.println("���ӵ������������ǿͻ���" + inetAddress.getHostAddress());
			//�������������Ϣ
			dataOutputStream.writeUTF("��������");
			//��ȡ����˷�������Ϣ
			String readUTF = dataInputStream.readUTF();
			System.out.println(readUTF);
			int num = 1;
			while (true) {
				//�����ݴ��������
				dataOutputStream.writeInt(num);
				thread.sleep(1000);
				num = dataInputStream.readInt();
				System.out.println("����˽�num��һ�ˣ�" + num + "�����ǿͻ���");
			} 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void start(){
		thread = new Thread(this);
		thread.start();
	}
	
	public static void main(String[] args) {
		ObjectF objectF = new ObjectF();
		objectF.start();
	}
	
}
