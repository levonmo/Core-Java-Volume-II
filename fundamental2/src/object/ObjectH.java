package object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

//一个聊天室：这个是客户端
public class ObjectH{
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		boolean isConti = true;
		//创建一个Socket对象，建立与服务端的连接
		Socket socket = new Socket("localhost",8888);

		//获取输入输出流
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);//这是输出给服务器
		BufferedReader bufferedReader = 
				new BufferedReader(new InputStreamReader(socket.getInputStream()));//这可以从服务器读取信息
		
		//建立一个键盘输入流
		BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(System.in));

		//创建一个读取服务器信息的线程
		ReadServerThread readServerThread = new ReadServerThread(bufferedReader);
		readServerThread.start();
		
		//接收一下服务端的"欢迎你"
		System.out.println(bufferedReader.readLine());
		
		//创建一个给服务端发送信息的循环
		while (isConti) {
			//这是键盘的输入
			String mess = bufferedReader2.readLine();
			//给服务端发信息
			printWriter.println(mess);
			//如果客户端输入bye，退出对话程序
			if (mess.equals("byte")) {
				break;
			}
		}
		System.out.println("欢迎下次再来！");
		//不在试图读取服务端的信息了
		readServerThread.isConti = false;
		bufferedReader2.close();
		bufferedReader.close();
		printWriter.close();
		socket.close();
	}
	
}

//这个线程类的主要功能是获取服务端的信息
class ReadServerThread extends Thread{
	
	BufferedReader bufferedReader;
	boolean isConti = true;
	
	public ReadServerThread(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}
	
	public void run() {
		while (isConti) {
			try {
				//获取了服务端的信息
				String mess = bufferedReader.readLine();
				System.out.println("服务端：" + mess);
				if(mess.equals("byte")){
					System.out.println("服务端退出了");
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		isConti = false;
	}
	
}