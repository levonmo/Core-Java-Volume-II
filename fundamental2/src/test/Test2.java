package test;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//���ǿ���java���ļ��� ��2���ıʼ�
public class Test2 {
	
	/*
	 * XML
	 * 	��XML��HTML������
	 * 		��XML��Сд�����е�
	 * 		��XML�Ľ�����ǩ�Ǿ��Բ���ʡ��
	 * 		��XML�У�����ֵ����������������
	 * 		��ֻ�е�����ǩ������/����������<img src="coffeecup.png"/>����������������֪������Ҫ����</img>��ǩ��
	 * 		��XML�У����е����Ա���������ֵ
	 * 	
	 * 	��XML���ĵ��ṹ
	 * 		��XML�ĵ�Ӧ����һ���ĵ�ͷ��ʼ������
	 * 			<?xml version="1.0"?> ���� <?xml version="1.0" encoding="UTF-8"?>
	 * 			�ϸ���˵���ĵ�ͷ�ο�ѡ�ģ�����ǿ���Ƽ���ʹ���ĵ�ͷ
	 * 		���ĵ�ͷ֮��ͨ�����ĵ����͵Ķ��壬����
	 * 			<!DOCTYPE web-app PUBLIC
	 * 				"-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN"
	 * 				"http://java.sun.com/j2ee/dtds/web-app_2_2.dtd">
	 * 			�ĵ����Ͷ������ȷ���ĵ���ȷ��һ����Ҫ�Ļ��ƣ����������Ǳ����
	 * 			���ֶ���������ص����(�뿴��֤XML�ĵ�)			
	 * 
	 * 	������XML�ĵ�
	 * 		�����ĵ�����ģ�ͽ��������������ν��������������XML�ĵ�ת��Ϊ���ṹ(Document Object Model,DOM)
	 * 			��DOM����������ʵ�����ǵĴ����Ŀ����˵��������һЩ�����Ҫ����ܳ����ĵ��������������ṹ�ͻ����Ĵ����ڴ�
	 * 			����������ĳЩԪ�ظ���Ȥ���������������ģ���ô����Щ�������Ӧ�ÿ���ʹ�������ƽ�����
	 * 			���������һ������
	 * 		����XML��API�����������������ƽ������������ڶ���XML�ĵ�ʱ������Ӧ���¼�(Simple API for XML,SAX)
	 * 		
	 * 
	 * 
	 */
	@Test
	public void documentObjectModel() throws ParserConfigurationException, SAXException, IOException{
		//Ҫ����һ��XML�ĵ���������Ҫһ��DocumentBuilder���󣬿��Դ�DocumentBuilderFactory�еõ��������
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		//���ļ��ж�ȡĳ���ĵ�
		File file = new File("testxml.xml");
		/*
		 * ���ﻹ����ʹ����������Ϊ����Դ������һ���ĵ���������
		 * 	��InputStream in = ...	
		 * 	�����߻�����ʹ��һ��URL��URL u = ...
		 */
		
		//�����ĵ���
		Document doc = builder.parse(file);
		
		//Document������XML�ĵ������νṹ���ڴ��еı��֣�������ʵ����Node�ӿڼ�������ӽӿڵ���Ķ��󹹳�
		//����ͨ������getDocumentElement()�������������ĵ����ݵķ����������ظ�Ԫ��
		Element root = doc.getDocumentElement();
		//����getTagName()�������Է���Ԫ�صı�ǩ��
		String tagName = root.getTagName();
		System.out.println(tagName);
		
		//�����õ���Ԫ�أ�����getChildNodes()���������ص�һ������ΪNodeList�ļ��ϣ�item(int)�������ȵ�ָ������ֵ���getLength�ȵ�����
		NodeList childrens = root.getChildNodes();
		for (int i = 0; i < childrens.getLength(); i++) {
			//�����Ϳ��Եõ����е���Ԫ��
			Node children = childrens.item(i);
			//������Щ�հ��ַ���Ҳ�����Ԫ�أ�����Ҫ����ȥ��
			if (children instanceof Element) {
				//�������ȡ��fontԪ��
				Element childrenElement = (Element)children;
				System.out.println(childrenElement.getTagName());
				//�� �����ȡ��fontԪ�ص�������Ԫ��
				NodeList childrensB = childrenElement.getChildNodes();
				//����fontԪ�ص���Ԫ��
				for (int j = 0; j < childrensB.getLength(); j++) {
					Node childrenB = childrensB.item(j);
					//ȥ���ո�����ǻ��з�(��Щ����Ҳ�ᱻ��Ϊ��һ��Ԫ��)
					if (childrenB instanceof Element) {
						//��ȡ��������Ԫ��(name��size)
						Element childrenBElement = (Element)childrenB;
						//��ӡfont��Ԫ��(name��size)�ı�ǩ��������
						System.out.print(childrenBElement.getTagName() + ":" + childrenBElement.getTextContent());
						System.out.println();
					}
				}
			}
		}
	}

	/*
	 * ����֤XML�ĵ�
	 * 	�����������˽�����α���DOM�ĵ���������ʹ�����ַ�����Ҫ�������߳��ı�̺ʹ���ļ�鹤�����㲻����Ҫ����Ԫ��֮��Ļ��м���
	 * 			��Ҫ�����ĵ����ǲ��ǰ�������Ҫ��Ԫ�ؽڵ�
	 * 	�����ԣ�XML�������Ļ���һ���ܴ�ĺô����������Զ���֤ĳ���ĵ��Ƿ������ȷ�Ľṹ(����spring�������ļ�һ������Ҫ�������Ĺ淶ȥ��дxml�ļ����ܽ�����)
	 * 	�����Ҫָ���ļ��ṹ(���밴�����ֹ淶����дxml�ĵ�)��������������ʽ:�ṩ  �ĵ����Ͷ���(DTD) ��  һ��XML Schema���壬����:
	 * 		���ĵ����Ͷ���(DTD) 
	 * 			��ʹ��DTD�к���������һ������:
	 * 				<!ELEMENT font(name,size)>
	 * 				��������ʾ:fontԪ�ر�������������Ԫ�أ��ֱ���name��size
	 * 		��XML Schema����
	 * 			��ͬ����Լ����XML Schema��ʾ���£�
	 * 				<xsd:element name="font">
	 * 					<xsd:sequence>
	 * 						<xsd:element name="name" type="xsd:string">	
	 * 						<xsd:element name="size" type="xsd:int">
	 * 					</xsd:sequence>
	 * 				</xsd:element>
	 * 
	 * 		����DTD��ȣ�XML Schema���Ա����Ӹ��ӵ���֤����(����size������һ������)��XML Schema�����������������DTD
	 * 			��DTD��Ȼ������ʢ������������ΪXML Schema���﷨̫�����ˡ�
	 * 	���ĵ����Ͷ���
	 * 		����XML�ĵ��ڲ�����DTD���Ǻ��ձ�ģ���ΪDTD��ʹ�ļ��ĳ��ȱ�úܳ�������һ��������ǰ�DTD������������XML�ĵ������롣
	 * 			ʹ��SYSTEM����ʵ�����Ŀ�꣬�����ָ��һ������DTD��URL,����
	 * 			<!DOCTYPE configuration SYSTEM "config.dtd"> ע��:!DOCTYPE��������ĵ����ͣ�����ƥ���Ԫ��
	 * 			�ֻ�����<!DOCTYPE configuration SYSTEM "http://myserver.com/config.dtd">
	 * 		����һ����Դ��SGML������ʶ��"������֪��"DTD���ƣ�������Ҫ������
	 * 			<!DOCTYPE web-app
	 * 			  PUBLC "-Sun Microsystems, Inc.//DTD Web Application 2.2//EN"
	 * 			  "http://java.sun.com/j2ee/dtds/web-app_2_2.dtd"		
	 * 			>
	 */
	
	
	
	
	/*
	 * javaBean����
	 * 	���������͵�����Ӧ��ʹ��is/set����ģʽ�����磺
	 * 		public boolean isRunning();
	 * 		public void setRunning();
	 * 	��
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
