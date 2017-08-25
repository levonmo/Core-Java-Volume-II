package object;

import java.io.Serializable;
//�������л�
public class ObjectB implements Serializable  {
	
	private static final long serialVersionUID = 1L;//�����л���Ĳ�ͬ�汾�����л�������
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
