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

//这是看《java核心技术 卷2》的笔记
public class Test2 {
	
	/*
	 * XML
	 * 	·XML与HTML的区别
	 * 		·XML大小写是敏感的
	 * 		·XML的结束标签是绝对不能省略
	 * 		·XML中，属性值必须用引号括起来
	 * 		·只有单个标签必须以/结束，例如<img src="coffeecup.png"/>，这样，解析器就知道不需要查找</img>标签了
	 * 		·XML中，所有的属性必须有属性值
	 * 	
	 * 	·XML的文档结构
	 * 		·XML文档应当以一个文档头开始，例如
	 * 			<?xml version="1.0"?> 或者 <?xml version="1.0" encoding="UTF-8"?>
	 * 			严格来说，文档头饰可选的，但是强烈推荐你使用文档头
	 * 		·文档头之后通常是文档类型的定义，例如
	 * 			<!DOCTYPE web-app PUBLIC
	 * 				"-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN"
	 * 				"http://java.sun.com/j2ee/dtds/web-app_2_2.dtd">
	 * 			文档类型定义的是确保文档正确的一个重要的机制，但是它不是必须的
	 * 			这种定义下面会重点介绍(请看验证XML文档)			
	 * 
	 * 	·解析XML文档
	 * 		·像文档对象模型解析器这样的树形解析器，将读入的XML文档转换为树结构(Document Object Model,DOM)
	 * 			·DOM解析器对于实现我们的大多数目的来说都更容易一些，如果要处理很长的文档，用它生成树结构就会消耗大量内存
	 * 			·如果你对于某些元素感兴趣，而不关心上下文，那么在这些情况下你应该考虑使用流机制解析器
	 * 			·看下面第一个例子
	 * 		·像XML简单API解析器这样的流机制解析器，它们在读入XML文档时生成相应的事件(Simple API for XML,SAX)
	 * 		
	 * 
	 * 
	 */
	@Test
	public void documentObjectModel() throws ParserConfigurationException, SAXException, IOException{
		//要读入一个XML文档，首先需要一个DocumentBuilder对象，可以从DocumentBuilderFactory中得到这个对象
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		//从文件中读取某个文档
		File file = new File("testxml.xml");
		/*
		 * 这里还可以使用输入流作为输入源来建立一个文档树，例如
		 * 	·InputStream in = ...	
		 * 	·或者还可以使用一个URL：URL u = ...
		 */
		
		//建立文档树
		Document doc = builder.parse(file);
		
		//Document对象是XML文档的树形结构在内存中的表现，它是由实现了Node接口及其各种子接口的类的对象构成
		//可以通过调用getDocumentElement()方法来启动对文档内容的分析，它返回根元素
		Element root = doc.getDocumentElement();
		//调用getTagName()方法可以返回元素的标签名
		String tagName = root.getTagName();
		System.out.println(tagName);
		
		//如果想得到子元素，调用getChildNodes()方法，返回的一个类型为NodeList的集合，item(int)方法将等到指定索引值的项，getLength等到项数
		NodeList childrens = root.getChildNodes();
		for (int i = 0; i < childrens.getLength(); i++) {
			//这样就可以得到所有的子元素
			Node children = childrens.item(i);
			//但是有些空白字符它也算进子元素，所以要把它去掉
			if (children instanceof Element) {
				//在这里获取了font元素
				Element childrenElement = (Element)children;
				System.out.println(childrenElement.getTagName());
				//在 这里获取了font元素的所有子元素
				NodeList childrensB = childrenElement.getChildNodes();
				//遍历font元素的子元素
				for (int j = 0; j < childrensB.getLength(); j++) {
					Node childrenB = childrensB.item(j);
					//去掉空格或者是换行符(这些符号也会被认为是一个元素)
					if (childrenB instanceof Element) {
						//获取真正的子元素(name和size)
						Element childrenBElement = (Element)childrenB;
						//打印font子元素(name和size)的标签名和内容
						System.out.print(childrenBElement.getTagName() + ":" + childrenBElement.getTextContent());
						System.out.println();
					}
				}
			}
		}
	}

	/*
	 * ·验证XML文档
	 * 	·上面我们了解了如何遍历DOM文档树，但是使用这种方法需要大量的冗长的编程和错误的检查工作，你不但需要处理元素之间的换行键，
	 * 			还要检查该文档中是不是包含你想要的元素节点
	 * 	·所以，XML解析器的还有一个很大的好处就是它你自动验证某个文档是否具有正确的结构(就像spring的配置文件一样，需要按照它的规范去编写xml文件才能解析出)
	 * 	·如果要指定文件结构(必须按照这种规范来编写xml文档)，可以有两个方式:提供  文档类型定义(DTD) 或  一个XML Schema定义，例如:
	 * 		·文档类型定义(DTD) 
	 * 			·使用DTD中含有这样的一个规则:
	 * 				<!ELEMENT font(name,size)>
	 * 				这个规则表示:font元素必须总有两个子元素，分别是name和size
	 * 		·XML Schema定义
	 * 			·同样的约束用XML Schema表示如下：
	 * 				<xsd:element name="font">
	 * 					<xsd:sequence>
	 * 						<xsd:element name="name" type="xsd:string">	
	 * 						<xsd:element name="size" type="xsd:int">
	 * 					</xsd:sequence>
	 * 				</xsd:element>
	 * 
	 * 		·与DTD相比，XML Schema可以表达更加复杂的验证条件(比如size必须是一个整数)，XML Schema语言是设计用来代替DTD
	 * 			但DTD仍然具有旺盛的生命力，因为XML Schema的语法太复杂了。
	 * 	·文档类型定义
	 * 		·在XML文档内部定义DTD不是很普遍的，因为DTD会使文件的长度变得很长。所以一般情况都是把DTD单独出来，在XML文档中引入。
	 * 			使用SYSTEM可以实现这个目标，你可以指定一个包含DTD的URL,例如
	 * 			<!DOCTYPE configuration SYSTEM "config.dtd"> 注意:!DOCTYPE后面的是文档类型，必须匹配根元素
	 * 			又或者是<!DOCTYPE configuration SYSTEM "http://myserver.com/config.dtd">
	 * 		·有一个来源于SGML的用于识别"众所周知的"DTD机制，下面是要给例子
	 * 			<!DOCTYPE web-app
	 * 			  PUBLC "-Sun Microsystems, Inc.//DTD Web Application 2.2//EN"
	 * 			  "http://java.sun.com/j2ee/dtds/web-app_2_2.dtd"		
	 * 			>
	 */
	
	
	
	
	/*
	 * javaBean构件
	 * 	·布尔类型的属性应该使用is/set命名模式，例如：
	 * 		public boolean isRunning();
	 * 		public void setRunning();
	 * 	·
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
