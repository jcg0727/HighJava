package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/*
 * 문제> 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고, Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
 		  이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체출력 기능이 있다.
  	     (데이터는 Map에 저장하여 관리하는데 key값으로는 이름 을 사용하고 value 값으로는 'Phone클래스의 인스턴스'로 한다.)
  	      
  	      실행예시)
  	      ------------------------
  	      1. 전화번호 등록
  	      2. 전화번호 수정
  	      3. 전화번호 삭제
  	      4. 전화번호 검색
  	      5. 전화번호 전체 출력
  	      0. 프로그램 종료
  	      ==========================
  	      번호입력 >> 1
  	      새롭게 등록할 전화번호 정보를 입력하세요.
  	       이름 >> 홍길동
  	       전화번호 >> 010-1234-5678
  	       주 소 >>> 대전시
  	      '홍길동' 전화번호 등록 완료!! 
  	       
  	       
  	        ------------------------
  	      1. 전화번호 등록
  	      2. 전화번호 수정
  	      3. 전화번호 삭제
  	      4. 전화번호 검색
  	      5. 전화번호 전체 출력
  	      0. 프로그램 종료
  	      ==========================
  	      번호입력 >> 1
  	      
  	      새롭게 등록할 전화번호 정보를 입력하세요.
  	      이 름 >> 홍길동
  	      
  	      '홍길동'은 이미 등록된 사람입니다.
  	      
  	      ------------------------
  	      1. 전화번호 등록
  	      2. 전화번호 수정
  	      3. 전화번호 삭제
  	      4. 전화번호 검색
  	      5. 전화번호 전체 출력
  	      0. 프로그램 종료
  	      ==========================
  	      번호입력 >> 5
  	      
  	      -------------------------------------
  	      번호   이름   전화번호        주소
  	      ---------------------------------------
  	      1     홍길동  010-1234-5678   대전시 
  	      ~~~
  	      --------------------------------------
  	      출력완료...
  	      
  	      ------------------------
  	      1. 전화번호 등록
  	      2. 전화번호 수정
  	      3. 전화번호 삭제
  	      4. 전화번호 검색
  	      5. 전화번호 전체 출력
  	      0. 프로그램 종료
  	      ==========================
  	      번호입력 >>  0
  	      
  	      프로그램을 종료합니다.
 */


public class PhoneBookTest222 {
	HashMap<String, Phone> pb;
	Scanner scanner;
	
	
	public PhoneBookTest222() {
		scanner = new Scanner(System.in);
		pb = new HashMap<>();
	}
	
	public static void main(String[] args) {
		new  PhoneBookTest222().menu();

	}

	//메인메뉴
	public void menu() {
		System.out.println("----------------------");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("0. 프로그램 종료");
		System.out.println("========================");
		System.out.print("번호입력 >> " );
		int input = scanner.nextInt();
		
		
		switch(input) {
		case 1:
			insert();
			break;
		case 2:
			update();
			break;
		case 3:
			delete();
			break;
		case 4:
			select();
			break;
		case 5:
			print();
			break;
		case 0:
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
		}
	}
	
	
	// 전화번호 등록 메서드
	public void insert() {
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요");
		System.out.print("이름 >>");
		String name = scanner.next();
		
		if(pb.containsKey(name)) {
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			menu();
		}
		System.out.print("전화번호 >>");
		String tel = scanner.next();
		System.out.print("주소 >>");
		String addr = scanner.next();
		
		Phone p = new Phone(name, tel, addr);
		
		pb.put(name, p);
		
		System.out.println(name + "전화번호 등록 완료!!");
		menu();
	}
	
	// 전화번호 수정 메서드
	public void update() {
		System.out.println("수정할 전화번호 정보를 입력하세요");
		
		System.out.println("이름 >>> ");
		String name = scanner.next();
		
		System.out.println("새로운 전화번호>>");
		String nt = scanner.next();
		
		System.out.println("새로운 주소>>");
		String na = scanner.next();
		
		pb.put(name, new Phone(name, nt, na));
		System.out.println(name + " 수정완료");
		
		menu();
	}
	
	// 전화번호 삭제 메서드
	public void delete() {
		System.out.println("삭제할 전화번호 정보를 입력하세요");
		System.out.println("이름 >>");
		String name = scanner.next();
		
		pb.remove(name);
		System.out.println(name + " 삭제완료");
		
		menu();
	}
	
	// 전화번호 검색할 메서드
		public void select() {
			System.out.println("검색할 전화번호 정보를 입력하세요");
			System.out.println("이름 >> ");
			String name = scanner.next();

			Phone p = pb.get(name);
			System.out.println(name + "씨의 전화번호 정보");
			System.out.println("----------------------------");
			System.out.println("이  름 : " + p.getName());
			System.out.println("전화번호 : " + p.getTel());
			System.out.println("주  소 : " + p.getAddr());
			System.out.println("--------------------------");
			System.out.println("검색완료");
			menu();
		}
		
	// 전화번호 전체출력 메서드
		public void print() {
			Set<String> phoneKey = pb.keySet();
			System.out.println("------------------------------------");
			System.out.println("번호    이름     전화번호     주소    ");
			System.out.println("------------------------------------");
			
			int	cnt=0; 
			Iterator<String> phoneIt = phoneKey.iterator();
			while(phoneIt.hasNext()) {
				cnt++; 
				String name = phoneIt.next();
				Phone p = pb.get(name); 
				System.out.println(cnt + "\t" + p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());
			
			menu();
		}
	
}


class Phone1{
	private String name;
	private String tel;
	private String hp;
	public Phone1(String name, String tel, String hp) {
		super();
		this.name = name;
		this.tel = tel;
		this.hp = hp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	@Override
	public String toString() {
		return "Phone1 [name=" + name + ", tel=" + tel + ", hp=" + hp + "]";
	}
	
}
	
}