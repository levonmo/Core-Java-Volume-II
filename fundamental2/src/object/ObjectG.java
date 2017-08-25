package object;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//ServerSocket
public class ObjectG implements Runnable {
	
	Thread thread;

	public void run() {
		try {
			//����������Socket�������˿�8888
			ServerSocket serverSocket = new ServerSocket(8888);
			//���ܿͻ��˵�����
			Socket socket = serverSocket.accept();
			//ͨ��Socket�����ȡ���������
			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
			//InetAddress������������(��ַ)��IP��ַ���ɵ�
			InetAddress addr = socket.getInetAddress();
			System.out.println("���ܵ�����" + addr.getHostAddress() + "�����ӣ�" + "���Ƿ���ˡ���������");
			//���տͻ��˵���Ϣ
			String readUTF = dataInputStream.readUTF();
			System.out.println("���յ��ͻ��˵���Ϣ�ǣ�" + readUTF);
			//��ͻ��˷�����Ϣ
			dataOutputStream.writeUTF("�ͻ�����ð�");
			int num;
			while (true) {
				num = dataInputStream.readInt();
				System.out.println("�ͻ��˴����ĵ�num:" + num + "�����Ƿ����");
				dataOutputStream.writeInt(num + 1);
				thread.sleep(1000);
			}
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
		ObjectG objectG = new ObjectG();
		objectG.start();
	}
	
}
