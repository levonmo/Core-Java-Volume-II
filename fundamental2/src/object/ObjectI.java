package object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//一个聊天室：这个是服务端
public class ObjectI  {
	public static void main(String[] args) throws IOException {
		boolean isconti = true;
		//使用端口1234创建Socket对象(创建Socket对象，可以获取输入输出流，与客户端的Socket对象进行通信)
		ServerSocket serverSocket = new ServerSocket(8888);
		//通过accept()方法是服务器与客户端的Socket接口建立联系(这就是服务端与客户端通行的桥梁)
		Socket socket = serverSocket.accept();
		
		//通过Socket对象，获得输入输出流
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		//向客户端写入数据
		printWriter.println("你好，欢迎你！");
		
		//通过键盘输入获取一个流(这是一个缓冲流)
		BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(System.in));

		//创建读取客户端消息的线程(这里是读取客户端的信息)
		ReadClientThread readClientThread = new ReadClientThread(bufferedReader);
		readClientThread.start();
		
		/*
		 * 这里有两个循环
		 * 	·上面的线程里面有一个循环，是为了读取客户端的信息
		 * 	·下面有一个循环是用于键盘输入给客户端发送信息的
		 */
		
		//这里是向客户端发送信息
		while (isconti) {
			//获取键盘的输入
			String mess = bufferedReader2.readLine();
			//向客户端发送信息
			printWriter.println(mess);
			//如果服务端输入bye，退出对话程序
			if (mess.equals("byte")) {
				break;
			}
		}
		printWriter.println("欢迎下次再来！");
		//关闭读取客户端消息的线程
		readClientThread.isConti = false;
		//关闭打开的资源
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
				//读取出客户端发来的信息
				String mess = bufferedReader.readLine();
				if(mess.equals("byte")){
					System.out.println("客户端退出");
					//结束接收客户端信息的循环
					isConti = false;
					break;
				}
				System.out.println("客户端：" + mess);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}