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
			//创建服务器Socket并监听端口8888
			ServerSocket serverSocket = new ServerSocket(8888);
			//接受客户端的连接
			Socket socket = serverSocket.accept();
			//通过Socket对象获取输入输出流
			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
			//InetAddress类是由主机名(网址)与IP地址构成的
			InetAddress addr = socket.getInetAddress();
			System.out.println("接受到来自" + addr.getHostAddress() + "的连接，" + "这是服务端。。。。。");
			//接收客户端的信息
			String readUTF = dataInputStream.readUTF();
			System.out.println("接收到客户端的信息是：" + readUTF);
			//向客户端发送信息
			dataOutputStream.writeUTF("客户端你好啊");
			int num;
			while (true) {
				num = dataInputStream.readInt();
				System.out.println("客户端传来的的num:" + num + "，这是服务端");
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
