package kr.or.ddit.basic;

import java.util.Properties;

public class PropertiesTest {
	/*
	 	Properties 객체
	 ==> Map 보다 축소된 기능을 가진 객체라고 할 수 있다.
	 
	 ==> Map은 key값과 value값에 모든 형태의 객체를 사용할 수 있지만 
	 	 Properties는 key값과 value값에 String만 사용할 수 있다.
	 	 
	 ==> Map은 put() 메서드와 get()메서드를 이용해서 데이터를 입출력하지만 
		 Properties는 setProperty()메서드와 getProperty()메서드를 통해서 입출력한다.
		 
	 ==> Properties는 데이터를 파일로 입출력 할 수 있다.	 
	 */
	
	
	public static void main(String[] args) {
		// 객체 생성
		Properties prop = new Properties();
		
		//데이터 추가 =>setProperty()
		int iAge = 30;
		prop.setProperty("name", "홍길동");
		prop.setProperty("age", "20");
		//prop.setProperty("age" , String.valueOf(iAge));
		//prop.setProperty("age", " "+ iAge);
		prop.setProperty("tel", "010-1234-5678");
		prop.setProperty("addr", "대전시");
		
		// 데이터 읽기 =>getProperty()
		String name = prop.getProperty("name");
		String age = prop.getProperty("age");
		String tel = prop.getProperty("tel");
		String addr = prop.getProperty("addr");
		
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("연락처 : " + tel);
		System.out.println("주소 : " + addr);
		
	}
	
}
