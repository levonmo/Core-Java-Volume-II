package object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

//һ�������ң�����ǿͻ���
public class ObjectH{
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		boolean isConti = true;
		//����һ��Socket���󣬽��������˵�����
		Socket socket = new Socket("localhost",8888);

		//��ȡ���������
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);//���������������
		BufferedReader bufferedReader = 
				new BufferedReader(new InputStreamReader(socket.getInputStream()));//����Դӷ�������ȡ��Ϣ
		
		//����һ������������
		BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(System.in));

		//����һ����ȡ��������Ϣ���߳�
		ReadServerThread readServerThread = new ReadServerThread(bufferedReader);
		readServerThread.start();
		
		//����һ�·���˵�"��ӭ��"
		System.out.println(bufferedReader.readLine());
		
		//����һ��������˷�����Ϣ��ѭ��
		while (isConti) {
			//���Ǽ��̵�����
			String mess = bufferedReader2.readLine();
			//������˷���Ϣ
			printWriter.println(mess);
			//����ͻ�������bye���˳��Ի�����
			if (mess.equals("byte")) {
				break;
			}
		}
		System.out.println("��ӭ�´�������");
		//������ͼ��ȡ����˵���Ϣ��
		readServerThread.isConti = false;
		bufferedReader2.close();
		bufferedReader.close();
		printWriter.close();
		socket.close();
	}
	
}

//����߳������Ҫ�����ǻ�ȡ����˵���Ϣ
class ReadServerThread extends Thread{
	
	BufferedReader bufferedReader;
	boolean isConti = true;
	
	public ReadServerThread(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}
	
	public void run() {
		while (isConti) {
			try {
				//��ȡ�˷���˵���Ϣ
				String mess = bufferedReader.readLine();
				System.out.println("����ˣ�" + mess);
				if(mess.equals("byte")){
					System.out.println("������˳���");
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		isConti = false;
	}
	
}