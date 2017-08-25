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

//���ǿ���java������ơ��ıʼ�
public class Test1 {
	
	/*
	 * File��
	 * 		��java��������һ����������ר�������ļ������ģ��������ļ���·�������ƣ���С�Լ�����(��ֻ��������)���ļ�����
	 * 		�����ṩ�˺ܶ�����ļ��ķ�����ͨ��File���ṩ��һЩ���������ԣ�������ɶ��ļ���Ŀ¼�ĳ��ù���������紴���ļ���Ŀ¼��ɾ���ļ���Ŀ¼���鿴�ļ����й���Ϣ
	 */
	@Test
	public void fileTest() throws IOException{
		String path = "test.txt";//�������·������Fileʵ��
		File file = new File(path);
		file.delete();//ɾ�����ļ�(������ڸ��ļ�ɾ���ɹ�����true)
		file.createNewFile();//����һ���µ��ļ�
		System.out.println("·��" + file.getParent());
		System.out.println("�ļ�" + file.getName());
		System.out.println("����·��" + file.getAbsolutePath());
		System.out.println("�ļ���С" + file.length());
		System.out.println("�Ƿ�Ϊ�ļ�" + (file.isFile()?"��":"��"));
		System.out.println("�ļ������·��" + file.getPath());
		System.out.println("����޸ĵ�ʱ����" + new Date(file.lastModified()));
	}
	
	
	/*
	 * ��
	 * 	�����Ķ�����ָ����������������֮�����������:������Դ���е���������Ŀ�ĵء�ͨ���������ɵؿ��ư����ļ����ڴ棬IO�豸���е����ݵ�����
	 * 	��������ˮ�����ˮ������ˮ�ܵ�һ��һ��һ��Ĺ�ˮ������ˮ�ܵ���һ�˿�������һ���������ϵ�ˮ����
	 * 	
	 * 	�������ص�
	 * 		1.��Դ�˺�Ŀ����
	 * 			����Դ�˵�Ŀ�Ķ˿Ƽ򵥵ؿ��������������ߺ�������
	 * 		2.������
	 * 			�������ķ����ԣ����԰�����Ϊ����:���������������
	 * 				��������ָ�������������������������������ָ��������������������
	 * 	������Ϊ����:
	 * 		���ֽ��� 
	 * 			��InputStream(��������������)��OutputStream(�������������)�������������ֽ�������Ļ��ࡣ
	 * 			���ֽ�����ȡ����С��λ��һ���ֽڣ���Ҫ���������ֽں��ֽ�����ġ�
	 * 			�����������������Ļ����ϣ�java.io�����ṩ��������FileInputStream��FileOutputStream���
	 * 			
	 *		���ַ���
	 * 			����Ϊ�˴����ַ�������ģ��ַ���һ�ο��Զ�ȡһ���ַ���ר�����������ַ� �ַ����� �ַ���
	 * 			��java.io���ṩ�˺ܶ����ڲ����ַ�������ͽӿڡ�
	 * 			������Reader��Writer���������ַ�������Ļ��࣬����������Ļ����ϻ��кܶ�����FileReaderؼFileWriter��
	 * 			��������ַ��� �ı��ļ���������������Ĳ���ʱ��Ӧ����ѡ�ַ�������
	 */
	
/*-------------------------------------------------------------------------------------------------*/	
	
	/*
	 * ʹ���ֽ��������ļ���/д
	 * 	��InputStream��OutputStream���ǳ����࣬�����������ֽڲ�����Ļ��ࡣ
	 * 		��InputStream��Ĳ��ַ���
	 * 			��read():���������ж�ȡ���ݵ���һ����
	 * 			��read(byte[] b)���������ж�ȡһ���������ֽڣ�������洢�ڻ�������b��
	 * 			��close()���ر���
	 * 		��OutputStream��Ĳ��ַ���
	 * 			��flush():ǿ��������е��ֽڵ�ָ�������װ��
	 * 			��write(byte[] b):��ָ�������е��ֽ�д�뵽�������
	 * 			��write(int b):��ָ�����ֽ�д�뵽�������
	 */

	/*
	 * 	�����ǻ���(������)�������Ǿ����ʵ����
	 * 
	 * ʹ���ֽ��������ļ��Ķ�д:FileInputStream��FileOutputStream��
	 * 
	 * 	����InputStream��OutputStream������
	 * 	��ӵ�и���ı����ͷ���������д����ʵ���˲��ַ���
	 * 	������������Ҫ���ֽڵ���ʽ���ļ����ж�д�����ڶ�ȡ����ͼ������֮���ԭʼ�ֽ���
	 * 
	 * 	��FileInputStream�Ĺ��췽��
	 * 		��FileInputStream(String name):��ĳ�ļ�������һ���ֽ�����������
	 * 		��FileInputStream(File file):��File���󴴽�һ���ֽ�����������
	 * 	
	 * 	��FileOutputStream�Ĺ��췽��
	 * 		��FileOutputStream(String name):��ĳ�ļ�����һ���ֽ��������
	 * 		��FileOutputStream(String name,boolean append):��ĳ���ļ���name����һ���ֽ����������appendΪtrueʱ���ļ���ĩβ��д�롣
	 * 		��FileOutputStream(File file):��File���󴴽�һ���ֽ����������
	 * 		��FileOutputStream(File file,boolean append):��File���󴴽�һ���ֽ����������appendΪtrueʱ���ļ���ĩβ��д�롣
	 * 
	 * 	������һ�����������������ิ����Ƭ
	 */
	@Test
	public void useFileInputStreamAndFileOutputStreamCopyPhoto() throws IOException{
		/*
		 * �½�һ���������飬FileInputStream�Ὣ�������뵽����б��棬Ȼ��FileOutputStream������ｫ�����������ĵط�
		 */
		byte[] b = new byte[17 * 1024];
		File file = new File("bd_logo1.png");//�½�һ��File���洢��ͼƬ��Ϣ
		File file_copy = new File("bd_logo1_copy.png");//�½�һ��File�����ڴ洢���ƺ��ͼƬ��Ϣ
		FileInputStream fis = new FileInputStream(file);//��File���������������Ѿ�����׼����ͼƬ��Ϣ����������
		FileOutputStream fos = new FileOutputStream(file_copy);//��File��������������Ѿ�����׼����ͼƬ��Ϣд���������
		fis.read(b);//����׼���õ��������Ѿ���ͼƬ��Ϣ���뵽������
		fos.write(b);//����׼���õ�������Ѿ���ͼƬ��Ϣд�뵽�����(�Ѿ�����Ϣд�뵽File������)
		//�ر���Դ
		fis.close();
		fos.close();
	}
	
	
/*-------------------------------------------------------------------------------------------------*/
	
	/*
	 * ʹ���ַ��������ļ��Ķ�д(������������)
	 * 
	 * 	��Reader���Writer��
	 * 		��������������"�ַ�"Ϊ����������������������Ƕ��ַ����ַ����������ı��ļ��������룬�������ʱ���뾡��ʹ��Reader���Writer��
	 * 		��Reader���Writer�����ر�����ַ���I/O����Ƶġ�
	 *		������������������һ��������  �������ı��ļ����ַ���дʱ������ʹ�õ��������ࡣ
	 *	
	 *	��Reader��ķ���
	 *		��read():��ȡ�����ַ�
	 *		��read(char[] b):��ȡ�ַ�������������b�л��档ע���������ַ�����������ֽ����飬��Ϊ�����Ƕ��ֽڽ��в����ġ�
	 *		��close():�ر���
	 *	��Writer��ķ���
	 *		��flush():ˢ�´���
	 *		��write(char[] b):��ָ�����ַ�����д�뵽�����(�ļ���)
	 *		��write(String str):��һ���ַ���д�뵽����
	 *		��close():�ر���
	 *
	 */
	
	/*
	 * �����ǻ���(������)�������Ǿ����ʵ����
	 * 	
	 * ʹ���ַ������ļ����ж�д
	 * 	��FileReader��FileWriter(��������������������Ļ����Ͻ������䣬��Ӽ̳�������������)
	 * 		����������ȡ�ַ��ļ��ı���࣬�ṩ��һЩ�ַ����Ĵ�����
	 * 		������û������һ���ı��ļ����Ϳ���ʹ���������ࡣ
	 * 		���������඼û�ж��صĳ�Ա����������ֱ�Ӽ̳и����
	 * 
	 * 	��FileReader��Ĺ��췽��
	 * 		��FileReader(File file):ʹ��ָ�����ļ�����(����Fileʵ��)����һ��FileReader����
	 * 		��FileReader(String name):ʹ��ָ�����ļ�������һ��FileReader����
	 * 	��FileWriter��Ĺ��췽��
	 * 		��FileWriter(String name):ʹ��ָ�����ļ�������һ��FileWriter����
	 * 		��FileWriter(String name,boolean append):ʹ��ָ�����ļ�������һ��FileWriter���󣬲�ָʾ�Ƿ���ַ�׷�ӵ��ļ�ĩβ
	 * 		��FileWriter(File file):ʹ��ָ�����ļ�����(����Fileʵ��)����һ��FileWriter����
	 * 		��FileWriter(File file,boolean append):ʹ��ָ�����ļ�������һ��FileWriter���󣬲�ָʾ�Ƿ���ַ�׷�ӵ��ļ�ĩβ
	 */
	@Test
	public void useFileReaderAndFileWriterCopy() throws IOException{
		File file = new File("a.txt");//����һ��File����a.txt��Ϣ��ȡ����
		File file2 = new File("a_copy.txt");//����һ��File�����ڴ洢����a.txt������
		
		FileReader fileReader = new FileReader(file);//�½�һ����������׼��������a.txt����Ϣ���뵽�ܵ�
		FileWriter fileWriter = new FileWriter(file2);//�½�һ���������׼�������͹ܵ�����Ϣд�뵽�ļ���
		
		int in = 0;//����������¼��ȡ���ַ�����(һ���ַ���һ����һ�޶������֣��������ִ��ݸ�������Ϳ���ת��Ϊһ���ַ���)����Ϊһ��ֻ�ܶ�ȡһ���ַ�
		
		
		/*
		 * fileReader.read()���ж�ȡ��һ���ַ����������һ��û���ˣ��ͷ��ظ�һ
		 */
		while((in = fileReader.read()) != -1){
			fileWriter.write(in);//�����������ݵ��ļ�
		}

		//�ر���
		fileReader.close();
		fileWriter.close();
		System.out.println("�����ļ����");
	}
	
	
	
/*-------------------------------------------------------------------------------------------------*/	
	
	/*
	 * InputStreamReader��OutputStreamWriter��
	 * 	����IO���У����˿����໥ת�����ֽ������ַ���֮�⣬������һ���ֽ������ַ�������ת������:InputStreamReader��OutputStreamWriter�ࡣ
	 * 
	 * 	��InputStreamReader��Reader�����࣬��������ֽ��� ͨ�� �ַ���������������һ���ֽ�����������Ϊ�ַ����������
	 * 	��OutputStreamWriter��Writer�����࣬��������ַ��� ͨ�� �ֽ���������������һ���ַ���ת��Ϊ�ֽ�������һ���ַ�������������Ϊ�ֽ������������
	 * 
	 * 	������ʹ��������������ļ��ı��Ķ�ȡ
	 * 		��������������ֽ�������ʽȥ��ȡ����(���ݵ��������ַ�)���ٽ��ֽڷ����
	 */
	@Test
	public void useInputStreamReaderAndOutputStreamWriterCopy() throws IOException{
		String path = "a.txt";//ʹ�����·����
		File file = new File(path);
		FileInputStream fileInputStream = new FileInputStream(file);//��File������һ�������������Ǻ�����Խ�����ת��Ϊ�ַ���
		//Ϊ����������ַ��������������ֽ��������ӵ����棬�ͱ�����ַ��������ˣ�����Ϳ���ʹ���ַ���������ж�ȡ��
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		
		//�½�һ��File�����洢������������������
		String path2 = "a_copy_b.txt";
		File file2 = new File(path2);
		FileOutputStream fileOutputStream = new FileOutputStream(file2);//��File������һ�����������
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);//Ϊ�������������ַ���������
		
		int ch = 0;
		
		//һ�ַ��ķ�ʽ��ʾ�ļ�����
		while((ch = inputStreamReader.read()) != -1){
			//����������ȡ�����ݲ���-1��֤���Ѿ���ȡ��һ���ַ�����������������������д��һ���ַ�
			outputStreamWriter.write(ch);
		}
		inputStreamReader.close();
		outputStreamWriter.close();
	}
	
	
	
	/*
	 * ������
	 * 
	 * BufferedReader���BufferedWriter
	 * 	�����������ڶ�ȡ/д�������˻��湦��(���涼�Ƕ�һ����дһ����Ч�ʱȽϵͣ������ǽ���һ���ֳ�������д��ȥ)����߶�д��Ч��
	 * 	����Reader��Writer�����࣬�ṩ�˻��淽ʽ�ı��Ķ�ȡ/д��
	 * 	��ʹ��BufferedReader��ȡ�ı���ʱ�򣬻��ȴ��ļ��ж����ַ����ݵ���������֮����ʹ��read()���������ȴӻ�������ȡ����������������ݲ��㣬�Ż���ļ��ж�ȡ��
	 * 	��ʹ��BufferedWriterд�������ǣ�д������ݲ����������Ŀ�ĵأ������ȴ洢����������������������ˣ��Ż�һ�ζ�Ŀ�ĵؽ���д����
	 * 
	 * 	��BufferedReader�Ĺ��췽���ͷ���
	 * 		��BufferedReader(Reader in):����һ��ʹ��Ĭ�ϻ������Ļ����ַ�������
	 * 		��BufferedReader(Reader in,int sz):ʹ��һ��ָ�������뻺������Χ���ַ�������
	 * 		��readLine():��ȡһ�У����ض�ȡ���ַ���
	 * 
	 * 	��BufferedWriter���
	 * 
	 * 	����������ʹ��������������ļ��ı��ĸ���
	 */
	@Test
	public void useBufferedCopy() throws IOException{
		//��Ŷ�ȡ�������ַ���
		String str;
		
		//�½�һ��������
		String path = "a.txt";
		File file = new File(path);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		//�½�һ�������
		String path2 = "a_copy_buffered.txt";
		File file2 = new File(path2);
		FileWriter fileWriter = new FileWriter(file2);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		//��������ȡ���ݣ������д������
		while((str = bufferedReader.readLine()) != null){
			bufferedWriter.write(str);
		}

		bufferedReader.close();
		bufferedWriter.close();
	}
	
	
	
	/*
	 * PrintWriter��
	 * 	�����������򵥣�����ǿ��ĸ�ʽ����������Ӷ��� �ַ��� �������õ���Խ��Խ���ʹ�ã��ṩ�˺ܶ�ķ�������ʽ�������
	 * 
	 * 	��PrintWriter�Ĺ��췽���ͷ���
	 * 		��PrintWriter(File file)
	 * 		��PrintWriter(OutpurStream out)
	 * 		��PrintWriter(Writer out)
	 * 	
	 * 	������������ʾPrintWriter���д�ļ��Ļ���ʹ�÷���
	 */
	@Test
	public void printWriter() throws FileNotFoundException{
		
		//�½�һ��File����
		String path = "printWriter.txt";
		File file = new File(path);
		
		//�½�һ��PrintWriter�����������ֱ�ӽ��ַ������
		PrintWriter printWriter = new PrintWriter(file);
		printWriter.println("PrintWriter����ֱ�ӽ��ַ������");
		printWriter.write("write����Ҳ���Խ���Ϣд�뵽�ı���");
		printWriter.write(34);
		printWriter.close();
	}
	

	
	/*
	 * ���л��ͷ����л�
	 * 
	 * 	��Serialiable�ӿ���Externalizable
	 * 	������������������͵ģ������Զ���Ϊ��λ�������ݵĴ洢�ʹ���
	 * 	�����л���ָ�������״̬�������ֽ�������ʽ���д������ļ��г��ñ���
	 * 	�������л���ָ����Ҫʱ���ļ��л�ȡ�ö������Ϣ�����»��һ����ȫ�Ķ���
	 *	�����������������ʵ��Serialiable�ӿ���Externalizable�ӿڲ��ܱ����л�
	 *
	 * 	��Serialiable�ӿ�
	 * 		�����һ�����ܱ����л�����ô�������඼�ǿ����л���
	 * 		���ڷ����л��Ĺ����У���ʹ�ø���Ĺ��û��ܱ������޲������췽����ʼ���������л����ֶ�
	 * 		   	�����л�����������ܷ����޲����Ĺ��췽���������л�������ֶν��Ӹ����лָ�
	 * 		�����л�ʱ������������ݳ�Ա�����л�(�����)����������Ϊtransient��static�ĳ�Ա
	 * 		�������ݳ�Ա����Ϊtransient�����л����޷������Ա����Ϣ�ӽ��������ֽ�����
	 * 			���ں������ݵķ����л�ʱ��Ҫ�ؽ����ݳ�Ա(��Ϊ��Ҳ�����һ����)�����������κ����ݣ���Ϊ������ݳ�Ա��������д���κ����ݡ�
	 * 		��������transient��static�����ݳ�Ա�������л����ߴ洢
	 * 
	 * 	��Externalizable�ӿ�
	 * 		���̳���Serialiable�ӿڣ����ṩ��һ�·���
	 * 		�����л��뷴���л��Ľ��̿�����Ϊ�Ŀ��ơ����磬ʹ��ѹ���ͼ��ܼ�����
	 * 		��Ҫ�ṩ���������Ĺ��췽��(����Ϊpublic)
	 * 		��������Ҫ�ķ���
	 * 			��readExternal(ObjectInput in):�����л������ã��ָ������ݣ�ͨ������DataInput�ķ������ָ���������ͣ�����readObject���ָ������ַ���������
	 * 			��writeExternal(ObjectOutput out):�ڷ����л������ã����������ݣ�ͨ������DataOut�ķ�����������������ͣ�����readObject����������ַ���������
	 * 			��ע��:����������Ϊpublic����˿�����Щ������ȡ��д��������ݣ�������л��������������Ϣ�����Ҫ����С��
	 */
	
	//���������л�
	@Test
	public void serialiable() throws IOException{
		//����һ������������ڳ����еĶ���ֱ��д�����ı��Ϳ��ԣ�������Ҫ�������ļ��ж�ȡ���������ﲻ��ʹ��������
		FileOutputStream fileOutputStream = new FileOutputStream("objectB.txt");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		//����һ������
		ObjectB objectB = new ObjectB("mo",22);
		//���������л�(д���ı���)
		objectOutputStream.writeObject(objectB);
		
		objectOutputStream.close();
		fileOutputStream.close();
	}
	//�������л�
	@Test
	public void serialiable2() throws IOException, ClassNotFoundException{
		//����һ�������������ı��е�����ֱ�Ӷ��������оͿ����ˣ�����Ҫ�ٱ��浽�ı��У������ǲ���Ҫ�������
		FileInputStream fileInputStream = new FileInputStream("objectB.txt");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		
		//ʹ��ObjectInputStream�����readObject�������з����л�
		ObjectB objectB = (ObjectB)objectInputStream.readObject();
		System.out.println(objectB);
		
		objectInputStream.close();
		fileInputStream.close();
	}
	
	/*
	 * �������л��ͷ����л���ʹ�ù������������:
	 * 	���������еĶ��󶼿��Ա����л���ֻ��ʵ����Serialiable�ӿڵ���Ķ���ſ���
	 * 	����������������л��ͷ����л������������Ҳ�������л��ͷ����л�
	 * 	�����һ������ʵ����Serialiable�ӿڣ���ô���е����඼ʵ����Serialiable�ӿ�
	 * 	��static�����Ժͷ����ǲ��������л��ģ���Ϊstatic�����Գ�Ա������޹أ������л��ͷ����л�����Զ������
	 * 	�����ڲ�ϣ�������л��ķ�static����(ʵ������)�������ڸ�����������ʹ��transient�ؼ��ֽ�������
	 */
	
	/*
	 *�����л���Ĳ�ͬ�汾�����л�������(serialVersionUID)
	 *	������ʵ���� Serialiable�ӿڵ��඼��һ����ʾ���л��汾�ľ�̬����
	 *		private static fianl long serialVersionUID;
	 *	���ñ���serialVersionUID��ȡֵ��java�����л����������ڲ�ϸ�������ģ���������ļ������޸ģ������±��룬ֵҲ�᲻һ����
	 *	����ͬ�ı������ᵼ�²�ͬ��ֵ��Ϊ��ֵ�Ķ����Ժ�ȷ���ԣ�ǿ��������Ҫ�������л�������ʾ�����serialVersionUID��Ϊ��������ȷ��ֵ
	 *	��serialVersionUID����ȷ����İ汾
	 *	����ʽ�ض���serialVersionUID������������;
	 *		����ĳЩ���ϣ�ϣ����Ĳ�ͬ�汾�����л����ݣ������Ҫȷ����Ĳ�ͬ�汾������ͬ��serialVersionUID
	 *		����ĳЩ���ϣ���ϣ����Ĳ�ͬ�汾�����л����ݣ������Ҫȷ����Ĳ�ͬ�汾���в��õ�serialVersionUID
	 */
	
	
	
	
	/*
	 * ������
	 * 	��URL���
	 * 		��URL��
	 * 			��URL���������������Internet�Ͻ�����Դ��λ���ַ�����һ��������URL����Э�飬���������˿ںţ��ļ�����������ɡ�
	 * 				����:http://www.sina.com:80/new/index,html����һ��������URL���ӡ�
	 * 					��ʹ�õ�Э����HTTP��������Ϊwww.sina.com,�˿���80�����ʵ���Դ����news/index.html
	 * 		��URLConnection
	 * 			��URLConnection������URL����ʶ����Դ��һ�����ӡ�
	 * 			������һ�������࣬����ͨ��URL����openConnection()����ʵ������
	 * 			��URLConnection����
	 * 				��connect():����URL����
	 * 				��getInputStream():��ȡ���� ��������
	 * 				��getOutputStream():��ȡ���ӵ������
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
	 * TCP�׽��ֱ��
	 * 	��TCP�׽��ֱ���漰������Ҫ��:InetAddress�࣬Socket�࣬ServerSocket��
	 * 		��InetAddress��
	 * 			��InetAddress�����ڱ�ʾInternet�ϵ�������ַ��InetAddress���һ��ʵ������������(������վ)��IP��ַ��ɡ�
	 * 				��ΪҪ���û���סIP��ַ�ǱȽ����ѵģ����Ծ���һ���������ĸ�����գ�����������Ҫת��ΪIP��ַ����Ϊ��Internet��ÿһ̨��������IP��ַ��ʶ��
	 * 		��Socket��
	 * 			��ʵ���˳���֮���˫��ͨѶ��Socket��һ��ͨѶ�˵㣬��һ��Socket���ӽ���֮���û��Ϳ��ԴӸ�Socket��Ķ����л�ȡ����������ˡ�
	 * 		��ServerSocket��
	 * 			��������������������ָ���˿ڵ����ӣ���Ϊÿһ���µ����Ӵ���һ��Socket����(Socket�����ȡ���������)
	 * 			����ɺ�ͻ�����������˾Ϳ��Խ���ͨ����
	 * 			��ServerSocket�Ĺ��캯���ͷ���
	 * 				��ServerSocket(int port)
	 * 				��ServerSocket(int port,int backlog)
	 * 				��ServerSocket(int port,int backlog,InetAddress bindAddr)
	 * 				��port��ָ�����������Ķ˿ڣ�backlog��ָ������������bindAddr��ָ���������󶨵ĵ�ַ
	 * 				��Socket accept():���ܸ����Ӳ����ظ����ӵ�Socket����
	 * 				��close():�رմ˷�����
	 * 
	 */				
	@Test
	public void inetAddress() throws IOException{
		ObjectE objectE = new ObjectE();
		objectE.display();
	}
	public void socketAndSocketServer(){
		//���ȥ�������࣬�ֱ��������ǵ�main����(����������˵ģ��������ͻ��˵ģ���Ϊ����˻�һֱ�����ſͻ���)
		new ObjectF();
		new ObjectG();
	}
	public void serverAndClient(){
		//����ģ����һ��������
		new ObjectI();//���Ƿ����
		new ObjectH();//���ǿͻ���
	}
	
}

