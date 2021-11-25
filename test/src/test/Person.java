package test;


//xml을 파싱하여 값을 저장할 Person 클래스 작성

public class Person {
	private String name;
	private String hp;
	private String addr;
	
	public Person() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Person [이름 : " + name + ", 전화번호 : " + hp + ", 주소 : " + addr + "]";
	}
	
	
	
	
}