package kr.or.ddit.basic;

import java.util.HashSet;

public class EqualsHashcodeTest {
	public static void main(String[] args) {
		Person p1 = new Person(1, "홍길동");
		Person p2 = new Person(1, "홍길동");
		Person p3 = p1;
		
		System.out.println("p1 = " + p1);
		System.out.println("p2 = " + p2);
		System.out.println("p3 = " + p3);
		System.out.println();
		System.out.println(p1 == p2);
		System.out.println(p1 == p3);
		System.out.println(p1.equals(p2)); //상속 안하면 Object 클래스 상속함. equals는 ==으로 비교하게 되어있음.
		System.out.println("-----------------------------------------------------------------");
		
		System.out.println("p1 hashcode = " + p1.hashCode());
		System.out.println("p2 hashcode = " + p2.hashCode());
		System.out.println("-----------------------------------------------------------------");
		HashSet<Person> testSet = new HashSet<>();
		testSet.add(p1);
		testSet.add(p2);
		System.out.println(testSet.size());
		
		/*
		 - equals()메서드 ==> 두 객체의 내용이 같은지 검사하는 연산자
		 - hashCode()메서드 ==> 두 객체의 동일성을 검사하는데 사용하는 메서드
		 
		  HashSet, Hashtable, HashMap 과 같이 Hash 로 시작하는 컬렉션 객체들은 객체의 의미상의 동일성을 위해 equals()와 hashCode()메서드를 호출하여 비교한다.
		  그러므로, 객체가 같은지 여부를 결정하려면 두 메서드 모두를 재정의 해야 한다.
		  
		  - hashCode() 메서드에서 사용하는 '해싱 알고리즘'은 서로 다른 객체들에 대해서 같은 hashCode 를 나타낼 수 있다.
		*/
		
	}
}

class Person{
	private int id;
	private String name;
	
	//생성자----------------------------------
	public Person() {}
	
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	//-------------------------------------------
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
	
	
	
	
	
	
//	@Override
//	public boolean equals(Object obj) {
//		if(obj==null) {
//			return false;
//		}
//		if (this.getClass() != obj.getClass()) { //같은 유형의 클래스인지 검사
//			return false;
//		}
//		if (this == obj) { //참조값(주소값)이 같은지 검사
//			return true;
//		}
//		
//		Person that = (Person) obj; //매개변수값을 현재의 객체 유형으로 형변환 한다.
//	
//	if(this.id==that.id) {
//		if(this.name==null) {
//			if(that.name==null) {
//				return true;
//			}else {
//				return false;
//			}
//		}else {
//			if(this.name.equals(that.name)) {
//				return true;
//			}else {
//				return false;
//			}
//		}
//	}
//		return false;
//	}


