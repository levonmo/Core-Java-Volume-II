package object;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

//一个简单的客户端
public class ObjectF implements Runnable  {
	
	Thread thread;

	public void run() {
		Socket socket;
		DataOutputStream dataOutputStream;
		DataInputStream dataInputStream;
		//连接到本地的8888端口
		try {
			//创建一个Socket对象,连接到本地的8888端口
			socket = new Socket("localhost",8888);
			//通过Socket对象获取输入输出流
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			dataInputStream = new DataInputStream(socket.getInputStream());
			//获取InetAddress对象(是由主机名与IP地址组成)
			InetAddress inetAddress = socket.getInetAddress();
			System.out.println("连接到服务器，这是客户端" + inetAddress.getHostAddress());
			//向服务器发送信息
			dataOutputStream.writeUTF("服务端你好");
			//读取服务端发来的信息
			String readUTF = dataInputStream.readUTF();
			System.out.println(readUTF);
			int num = 1;
			while (true) {
				//把数据穿给服务端
				dataOutputStream.writeInt(num);
				thread.sleep(1000);
				num = dataInputStream.readInt();
				System.out.println("服务端将num加一了：" + num + "，这是客户端");
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
