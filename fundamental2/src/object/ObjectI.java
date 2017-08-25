package object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//һ�������ң�����Ƿ����
public class ObjectI  {
	public static void main(String[] args) throws IOException {
		boolean isconti = true;
		//ʹ�ö˿�1234����Socket����(����Socket���󣬿��Ի�ȡ�������������ͻ��˵�Socket�������ͨ��)
		ServerSocket serverSocket = new ServerSocket(8888);
		//ͨ��accept()�����Ƿ�������ͻ��˵�Socket�ӿڽ�����ϵ(����Ƿ������ͻ���ͨ�е�����)
		Socket socket = serverSocket.accept();
		
		//ͨ��Socket���󣬻�����������
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		//��ͻ���д������
		printWriter.println("��ã���ӭ�㣡");
		
		//ͨ�����������ȡһ����(����һ��������)
		BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(System.in));

		//������ȡ�ͻ�����Ϣ���߳�(�����Ƕ�ȡ�ͻ��˵���Ϣ)
		ReadClientThread readClientThread = new ReadClientThread(bufferedReader);
		readClientThread.start();
		
		/*
		 * ����������ѭ��
		 * 	��������߳�������һ��ѭ������Ϊ�˶�ȡ�ͻ��˵���Ϣ
		 * 	��������һ��ѭ�������ڼ���������ͻ��˷�����Ϣ��
		 */
		
		//��������ͻ��˷�����Ϣ
		while (isconti) {
			//��ȡ���̵�����
			String mess = bufferedReader2.readLine();
			//��ͻ��˷�����Ϣ
			printWriter.println(mess);
			//������������bye���˳��Ի�����
			if (mess.equals("byte")) {
				break;
			}
		}
		printWriter.println("��ӭ�´�������");
		//�رն�ȡ�ͻ�����Ϣ���߳�
		readClientThread.isConti = false;
		//�رմ򿪵���Դ
		printWriter.close();
		bufferedReader.close();
		bufferedReader2.close();
		serverSocket.close();
		socket.close();
	}
}

class ReadClientThread extends Thread{
	
	BufferedReader bufferedReader;
	boolean isConti = true;

	ReadClientThread(BufferedReader bufferedReader){
		this.bufferedReader = bufferedReader;
	}

	public void run() {
		while (isConti) {
			try {
				//��ȡ���ͻ��˷�������Ϣ
				String mess = bufferedReader.readLine();
				if(mess.equals("byte")){
					System.out.println("�ͻ����˳�");
					//�������տͻ�����Ϣ��ѭ��
					isConti = false;
					break;
				}
				System.out.println("�ͻ��ˣ�" + mess);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}