package object;

import java.io.Serializable;
//对象序列化
public class ObjectB implements Serializable  {
	
	private static final long serialVersionUID = 1L;//可序列化类的不同版本的序列化兼容性
	private String name;
	private int age;
	
	
	public ObjectB(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public static long getSeri() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ObjectB [name=" + name + ", age=" + age + "]";
	}
	
	
	
}
