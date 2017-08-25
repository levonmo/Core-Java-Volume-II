package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

import object.ObjectA;
import object.ObjectB;
import object.ObjectC;
import object.ObjectD;
import object.ObjectE;
import object.ObjectF;
import object.ObjectG;
import object.ObjectH;
import object.ObjectI;

import org.junit.Test;

//这是看《java程序设计》的笔记
public class Test1 {
	
	/*
	 * File类
	 * 		在java语言中有一个类是用来专门描述文件特征的，它描述文件的路径，名称，大小以及属性(如只读，隐藏)等文件属性
	 * 		并且提供了很多操作文件的方法。通过File类提供的一些方法和属性，可以完成对文件或目录的常用管理操作，如创建文件或目录，删除文件或目录，查看文件的有关信息
	 */
	@Test
	public void fileTest() throws IOException{
		String path = "test.txt";//根据相对路径创建File实例
		File file = new File(path);
		file.delete();//删除该文件(如果存在该文件删除成功返回true)
		file.createNewFile();//创建一个新的文件
		System.out.println("路径" + file.getParent());
		System.out.println("文件" + file.getName());
		System.out.println("绝对路径" + file.getAbsolutePath());
		System.out.println("文件大小" + file.length());
		System.out.println("是否为文件" + (file.isFile()?"是":"否"));
		System.out.println("文件的相对路径" + file.getPath());
		System.out.println("最后修改的时间是" + new Date(file.lastModified()));
	}
	
	
	/*
	 * 流
	 * 	·流的定义是指计算机的输入与输出之间的数据序列:从数据源串行地流向数据目的地。通过流能自由地控制包括文件，内存，IO设备等中的数据的流向
	 * 	·流就像水管里的水流，在水管的一端一点一点的供水，而在水管的另一端看到的是一股连续不断的水流。
	 * 	
	 * 	·流的特点
	 * 		1.有源端和目的性
	 * 			流的源端的目的端科简单地看成是流的生产者和消费者
	 * 		2.方向性
	 * 			根据流的方向性，可以把流分为两类:流入流和输出流。
	 * 				输入流是指从外设流入计算机的数据流。输出流是指计算机向外设的数据流。
	 * 	·流分为两种:
	 * 		·字节流 
	 * 			·InputStream(用来处理输入流)和OutputStream(用来处理输出流)，这两个类是字节流处理的基类。
	 * 			·字节流读取的最小单位是一个字节，主要用来操作字节和字节数组的。
	 * 			·在上面的两个基类的基础上，java.io包还提供了子类有FileInputStream和FileOutputStream类等
	 * 			
	 *		·字符流
	 * 			·是为了处理字符提出来的，字符流一次可以读取一个字符，专门用来操作字符 字符数组 字符串
	 * 			·java.io包提供了很多用于操作字符流的类和接口。
	 * 			·其中Reader和Writer两个类是字符流处理的基类，在这两个类的基础上还有很多子类FileReader丶FileWriter类
	 * 			·在针对字符串 文本文件进行输入与输出的操作时，应该首选字符流处理
	 */
	
/*-------------------------------------------------------------------------------------------------*/	
	
	/*
	 * 使用字节流进行文件读/写
	 * 	·InputStream和OutputStream都是抽象类，是其他所有字节操作类的基类。
	 * 		·InputStream类的部分方法
	 * 			·read():在输入流中读取数据的下一个字
	 * 			·read(byte[] b)从输入流中读取一定数量的字节，并将其存储在缓冲数组b中
	 * 			·close()：关闭流
	 * 		·OutputStream类的部分方法
	 * 			·flush():强制输出流中的字节到指定的输出装置
	 * 			·write(byte[] b):将指定数组中的字节写入到输出流中
	 * 			·write(int b):将指定的字节写入到输出流中
	 */

	/*
	 * 	上面是基类(抽象类)，下面是具体的实现类
	 * 
	 * 使用字节流进行文件的读写:FileInputStream和FileOutputStream类
	 * 
	 * 	·是InputStream和OutputStream的子类
	 * 	·拥有父类的变量和方法，并重写或者实现了部分方法
	 * 	·这两个类主要以字节的形式对文件进行读写，用于读取诸如图像数据之类的原始字节流
	 * 
	 * 	·FileInputStream的构造方法
	 * 		·FileInputStream(String name):用某文件名创建一个字节输入流对象
	 * 		·FileInputStream(File file):用File对象创建一个字节输入流对象
	 * 	
	 * 	·FileOutputStream的构造方法
	 * 		·FileOutputStream(String name):用某文件创建一个字节输出对象
	 * 		·FileOutputStream(String name,boolean append):用某个文件名name创建一个字节输出流对象，append为true时在文件的末尾处写入。
	 * 		·FileOutputStream(File file):用File对象创建一个字节输出流对象
	 * 		·FileOutputStream(File file,boolean append):用File对象创建一个字节输出流对象，append为true时在文件的末尾处写入。
	 * 
	 * 	·下面一个例子利用这两个类复制照片
	 */
	@Test
	public void useFileInputStreamAndFileOutputStreamCopyPhoto() throws IOException{
		/*
		 * 新建一个缓存数组，FileInputStream会将数据输入到这进行保存，然后FileOutputStream会从这里将数据输出到别的地方
		 */
		byte[] b = new byte[17 * 1024];
		File file = new File("bd_logo1.png");//新建一个File，存储这图片信息
		File file_copy = new File("bd_logo1_copy.png");//新建一个File，用于存储复制后的图片信息
		FileInputStream fis = new FileInputStream(file);//将File对象传人输入流，已经做好准备将图片信息传人输入流
		FileOutputStream fos = new FileOutputStream(file_copy);//将File对象传人输出流，已经做好准备将图片信息写出到输出流
		fis.read(b);//上面准备好的输入流已经将图片信息输入到输入流
		fos.write(b);//上面准备好的输出流已经将图片信息写入到输出流(已经将信息写入到File对象中)
		//关闭资源
		fis.close();
		fos.close();
	}
	
	
/*-------------------------------------------------------------------------------------------------*/
	
	/*
	 * 使用字符流进行文件的读写(这两个抽象类)
	 * 
	 * 	·Reader类和Writer类
	 * 		·这两个类是以"字符"为对象进行输入与输出。如果是对字符，字符串或者是文本文件进行输入，输出操作时，请尽量使用Reader类和Writer类
	 * 		·Reader类和Writer类是特别针对字符的I/O所设计的。
	 *		·很明显这两个类是一个抽象类  ，进行文本文件的字符读写时，真正使用的是其子类。
	 *	
	 *	·Reader类的方法
	 *		·read():读取单个字符
	 *		·read(char[] b):读取字符，并放在数组b中缓存。注意这里是字符数组而不是字节数组，因为这里是对字节进行操作的。
	 *		·close():关闭流
	 *	·Writer类的方法
	 *		·flush():刷新此流
	 *		·write(char[] b):将指定的字符数组写入到输出流(文件中)
	 *		·write(String str):将一个字符串写入到流中
	 *		·close():关闭流
	 *
	 */
	
	/*
	 * 上面是基类(抽象类)，下面是具体的实现类
	 * 	
	 * 使用字符流对文件进行读写
	 * 	·FileReader和FileWriter(这两个类在上面两个类的基础上进行扩充，间接继承了上面两个类)
	 * 		·是用来读取字符文件的便捷类，提供了一些字符流的处理方法
	 * 		·如果用户想操作一个文本文件，就可以使用这两个类。
	 * 		·这两个类都没有独特的成员方法，都是直接继承父类的
	 * 
	 * 	·FileReader类的构造方法
	 * 		·FileReader(File file):使用指定的文件对象(就是File实例)创建一个FileReader对象
	 * 		·FileReader(String name):使用指定的文件名创建一个FileReader对象
	 * 	·FileWriter类的构造方法
	 * 		·FileWriter(String name):使用指定的文件名创建一个FileWriter对象
	 * 		·FileWriter(String name,boolean append):使用指定的文件名创建一个FileWriter对象，并指示是否把字符追加到文件末尾
	 * 		·FileWriter(File file):使用指定的文件对象(就是File实例)创建一个FileWriter对象
	 * 		·FileWriter(File file,boolean append):使用指定的文件名创建一个FileWriter对象，并指示是否把字符追加到文件末尾
	 */
	@Test
	public void useFileReaderAndFileWriterCopy() throws IOException{
		File file = new File("a.txt");//创建一个File，将a.txt信息读取出来
		File file2 = new File("a_copy.txt");//创建一个File，用于存储复制a.txt的数据
		
		FileReader fileReader = new FileReader(file);//新建一个输入流，准备就绪将a.txt的信息输入到管道
		FileWriter fileWriter = new FileWriter(file2);//新建一个输出流，准备就绪就管道的信息写入到文件中
		
		int in = 0;//这是用来记录读取的字符串的(一个字符有一个独一无二的数字，将给数字传递给输出流就可以转换为一个字符了)，因为一次只能读取一个字符
		
		
		/*
		 * fileReader.read()进行读取下一个字符，如果是下一个没有了，就返回负一
		 */
		while((in = fileReader.read()) != -1){
			fileWriter.write(in);//输出流输出数据到文件
		}

		//关闭流
		fileReader.close();
		fileWriter.close();
		System.out.println("复制文件完成");
	}
	
	
	
/*-------------------------------------------------------------------------------------------------*/	
	
	/*
	 * InputStreamReader和OutputStreamWriter类
	 * 	·在IO包中，除了可以相互转化的字节流和字符流之外，还存在一组字节流与字符流进行转换的类:InputStreamReader和OutputStreamWriter类。
	 * 
	 * 	·InputStreamReader是Reader的子类，是输入的字节流 通向 字符流的桥梁，它将一个字节流输入对象变为字符流输入对象。
	 * 	·OutputStreamWriter是Writer的子类，是输出的字符流 通行 字节流的桥梁，它将一个字符流转化为字节流，将一个字符流的输出对象变为字节流的输出对象。
	 * 
	 * 	·下面使用这两个类进行文件文本的读取
	 * 		·这个例子是以字节流的形式去读取数据(数据的内容是字符)，再将字节符输出
	 */
	@Test
	public void useInputStreamReaderAndOutputStreamWriterCopy() throws IOException{
		String path = "a.txt";//使用相对路径名
		File file = new File(path);
		FileInputStream fileInputStream = new FileInputStream(file);//用File对象构造一个输入流，但是后面可以将该流转换为字符流
		//为这个流加上字符处理能力，把字节输入流加到里面，就变成了字符输入流了，下面就可以使用字符输出流进行读取了
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		
		//新建一个File用来存储输入流传过来的数据
		String path2 = "a_copy_b.txt";
		File file2 = new File(path2);
		FileOutputStream fileOutputStream = new FileOutputStream(file2);//用File对象构造一个输出流对象
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);//为这个输出流加上字符处理能力
		
		int ch = 0;
		
		//一字符的方式显示文件内容
		while((ch = inputStreamReader.read()) != -1){
			//当输入流读取的数据不是-1，证明已经读取了一个字符出来，下面就用输出流进行写出一个字符
			outputStreamWriter.write(ch);
		}
		inputStreamReader.close();
		outputStreamWriter.close();
	}
	
	
	
	/*
	 * 缓冲流
	 * 
	 * BufferedReader类和BufferedWriter
	 * 	·这两个类在读取/写入增加了缓存功能(上面都是读一个再写一个，效率比较低，这里是将读一部分出来，在写进去)，提高读写的效率
	 * 	·是Reader和Writer的子类，提供了缓存方式文本的读取/写入
	 * 	·使用BufferedReader读取文本的时候，会先从文件中读入字符数据到缓冲区，之后若使用read()方法，会先从缓冲区读取。如果缓冲区的数据不足，才会从文件中读取。
	 * 	·使用BufferedWriter写入数据是，写入的数据不会先输出到目的地，而是先存储到缓存区。如果缓存区满了，才会一次对目的地进行写出。
	 * 
	 * 	·BufferedReader的构造方法和方法
	 * 		·BufferedReader(Reader in):创建一个使用默认缓存区的缓冲字符输入流
	 * 		·BufferedReader(Reader in,int sz):使用一个指定了输入缓冲区范围的字符输入流
	 * 		·readLine():读取一行，返回读取的字符串
	 * 
	 * 	·BufferedWriter差不多
	 * 
	 * 	·下面例子使用这两个类进行文件文本的复制
	 */
	@Test
	public void useBufferedCopy() throws IOException{
		//存放读取出来的字符串
		String str;
		
		//新建一个输入流
		String path = "a.txt";
		File file = new File(path);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		//新建一个输出流
		String path2 = "a_copy_buffered.txt";
		File file2 = new File(path2);
		FileWriter fileWriter = new FileWriter(file2);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		//输入流读取数据，输出流写入数据
		while((str = bufferedReader.readLine()) != null){
			bufferedWriter.write(str);
		}

		bufferedReader.close();
		bufferedWriter.close();
	}
	
	
	
	/*
	 * PrintWriter类
	 * 	·这个类因其简单，灵活而强大的格式化输出能力从而在 字符流 输出方面得到了越来越多的使用，提供了很多的方法来格式化输出。
	 * 
	 * 	·PrintWriter的构造方法和方法
	 * 		·PrintWriter(File file)
	 * 		·PrintWriter(OutpurStream out)
	 * 		·PrintWriter(Writer out)
	 * 	
	 * 	·下面例子演示PrintWriter类读写文件的基本使用方法
	 */
	@Test
	public void printWriter() throws FileNotFoundException{
		
		//新建一个File对象
		String path = "printWriter.txt";
		File file = new File(path);
		
		//新建一个PrintWriter输出流，可以直接将字符串输出
		PrintWriter printWriter = new PrintWriter(file);
		printWriter.println("PrintWriter可以直接将字符串输出");
		printWriter.write("write方法也可以将信息写入到文本中");
		printWriter.write(34);
		printWriter.close();
	}
	

	
	/*
	 * 序列化和反序列化
	 * 
	 * 	·Serialiable接口与Externalizable
	 * 	·用来处理处理对象类型的，可以以对象为单位进行数据的存储和传输
	 * 	·序列化是指将对象的状态数据以字节流的形式进行处理，在文件中长久保存
	 * 	·反序列化是指在需要时从文件中获取该对象的信息以重新获得一个完全的对象
	 *	·对象所属的类必须实现Serialiable接口与Externalizable接口才能被序列化
	 *
	 * 	·Serialiable接口
	 * 		·如果一个类能被序列化，那么它的子类都是可序列化的
	 * 		·在反序列化的过程中，将使用该类的公用或受保护的无参数构造方法初始化不可序列化的字段
	 * 		   	可序列化的子类必须能访问无参数的构造方法，可序列化子类的字段将从该流中恢复
	 * 		·序列化时，类的所有数据成员可序列化(被输出)，除了声明为transient，static的成员
	 * 		·将数据成员声明为transient后，序列化就无法将其成员的信息加进到对象字节流中
	 * 			但在后面数据的反序列化时，要重建数据成员(因为它也是类的一部分)，但不包含任何数据，因为这个数据成员不向流中写入任何数据。
	 * 		·声明成transient，static的数据成员不被序列化工具存储
	 * 
	 * 	·Externalizable接口
	 * 		·继承了Serialiable接口，并提供了一下方法
	 * 		·序列化与反序列化的进程可以人为的控制。比如，使用压缩和加密技术。
	 * 		·要提供不带参数的构造方法(并且为public)
	 * 		·两个主要的方法
	 * 			·readExternal(ObjectInput in):在序列化被调用，恢复其内容，通过调用DataInput的方法来恢复其基础类型，调用readObject来恢复对象，字符串和数组
	 * 			·writeExternal(ObjectOutput out):在反序列化被调用，保持其内容，通过调用DataOut的方法来保存其基础类型，调用readObject来保存对象，字符串和数组
	 * 			·注意:这两个方法为public，因此可以这些方法读取和写入对象数据，如果序列化对象包含敏感信息，则更要格外小心
	 */
	
	//将对象序列化
	@Test
	public void serialiable() throws IOException{
		//创建一个输出流，将在程序中的对象直接写出到文本就可以，并不需要在其他文件中读取，所以这里不用使用输入流
		FileOutputStream fileOutputStream = new FileOutputStream("objectB.txt");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		//创建一个对象
		ObjectB objectB = new ObjectB("mo",22);
		//将对象序列化(写入文本中)
		objectOutputStream.writeObject(objectB);
		
		objectOutputStream.close();
		fileOutputStream.close();
	}
	//对象反序列化
	@Test
	public void serialiable2() throws IOException, ClassNotFoundException{
		//创建一个输入流，将文本中的数据直接读到程序中就可以了，不需要再保存到文本中，所以是不需要输出流的
		FileInputStream fileInputStream = new FileInputStream("objectB.txt");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		
		//使用ObjectInputStream对象的readObject方法进行反序列化
		ObjectB objectB = (ObjectB)objectInputStream.readObject();
		System.out.println(objectB);
		
		objectInputStream.close();
		fileInputStream.close();
	}
	
	/*
	 * 对象序列化和反序列化的使用规则简单整理如下:
	 * 	·不是所有的对象都可以被序列化，只有实现了Serialiable接口的类的对象才可以
	 * 	·不仅对象可以序列化和反序列化，对象的数组也可以序列化和反序列化
	 * 	·如果一个父类实现了Serialiable接口，那么所有的子类都实现了Serialiable接口
	 * 	·static的属性和方法是不可以序列化的，因为static的属性成员与对象无关，而序列化和反序列化是针对对象而言
	 * 	·对于不希望被序列化的非static属性(实例变量)，可以在该属性声明的使用transient关键字进行声明
	 */
	
	/*
	 *可序列化类的不同版本的序列化兼容性(serialVersionUID)
	 *	·凡是实现了 Serialiable接口的类都有一个表示序列化版本的静态变量
	 *		private static fianl long serialVersionUID;
	 *	·该变量serialVersionUID的取值是java的运行环境根据类内部细节生产的，如果对类文件进行修改，在重新编译，值也会不一样的
	 *	·不同的编译器会导致不同的值，为了值的独立性和确定性，强力建议在要给可序列化类中显示定义的serialVersionUID，为它赋于明确的值
	 *	·serialVersionUID可以确保类的版本
	 *	·显式地定义serialVersionUID有以下两种用途
	 *		·在某些场合，希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有相同的serialVersionUID
	 *		·在某些场合，不希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有不用的serialVersionUID
	 */
	
	
	
	
	/*
	 * 网络编程
	 * 	·URL编程
	 * 		·URL类
	 * 			·URL是用来描述如何在Internet上进行资源定位的字符串。一个完整的URL是由协议，主机名，端口号，文件名与引用组成。
	 * 				例如:http://www.sina.com:80/new/index,html就是一个完整的URL例子。
	 * 					它使用的协议是HTTP，主机名为www.sina.com,端口是80，访问的资源名是news/index.html
	 * 		·URLConnection
	 * 			·URLConnection类代表对URL所标识的资源的一个连接。
	 * 			·它是一个抽象类，可以通过URL对象openConnection()方法实例化。
	 * 			·URLConnection方法
	 * 				·connect():建立URL连接
	 * 				·getInputStream():获取链接 的输入流
	 * 				·getOutputStream():获取链接的输出流
	 */
	@Test
	public void url() throws IOException{
		ObjectC objectC = new ObjectC();
		objectC.display();
	}
	@Test
	public void urlConnection() throws IOException{
		ObjectD objectD = new ObjectD();
		objectD.display();
	}
	
	
	
	
	/*
	 * TCP套接字编程
	 * 	·TCP套接字编程涉及的类主要有:InetAddress类，Socket类，ServerSocket类
	 * 		·InetAddress类
	 * 			·InetAddress类用于表示Internet上的主机地址，InetAddress类的一个实例是由主机名(就是网站)与IP地址组成。
	 * 				因为要让用户记住IP地址是比较困难的，所以就有一个主机名的概念。最终，主机名还是要转换为IP地址，因为在Internet的每一台电脑依靠IP地址来识。
	 * 		·Socket类
	 * 			·实现了程序之间的双向通讯，Socket是一个通讯端点，当一个Socket连接建立之后，用户就可以从该Socket类的对象中获取输入输出流了。
	 * 		·ServerSocket类
	 * 			·是用来监听所有来自指定端口的连接，并为每一个新的连接创建一个Socket对象(Socket对象获取输入输出流)
	 * 			·完成后客户端与服务器端就可以进行通信了
	 * 			·ServerSocket的构造函数和方法
	 * 				·ServerSocket(int port)
	 * 				·ServerSocket(int port,int backlog)
	 * 				·ServerSocket(int port,int backlog,InetAddress bindAddr)
	 * 				·port是指服务器监听的端口，backlog是指最大的连接数，bindAddr是指服务器所绑定的地址
	 * 				·Socket accept():接受该连接并返回该连接的Socket对象
	 * 				·close():关闭此服务器
	 * 
	 */				
	@Test
	public void inetAddress() throws IOException{
		ObjectE objectE = new ObjectE();
		objectE.display();
	}
	public void socketAndSocketServer(){
		//点进去这两个类，分别启动它们的main方法(先启动服务端的，再启动客户端的，因为服务端会一直监听着客户端)
		new ObjectF();
		new ObjectG();
	}
	public void serverAndClient(){
		//这里模拟了一个聊天室
		new ObjectI();//这是服务端
		new ObjectH();//这是客户端
	}
	
}

