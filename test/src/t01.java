import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class t01 {

		private Map<String, Phonee> phoneBookMap;
		private Scanner scan;
		
		public t01() {
		scan = new Scanner(System.in);
		phoneBookMap = new HashMap<>();
		}
		
		public static void main(String[] args) {
			new t01().start();
		}
		
		
		
		private void start() {
			System.out.println("*&*&*&*&*&*&*&*&*&*&*&*&*");
			
			
			while(true) {
				int choice = displayMenu();
				switch(choice) {
				case 1:
					insert();
					break;
					
				case 2:
					update();
					break;
					
				case 3:
					delete();
					break;
					
				default:
					break;
				}
			}
		}
		
		private void delete() {
			System.out.println("삭제 가즈아");
			
			System.out.println("이름 입력>");
			String name = scan.next();
			
			if(phoneBookMap.containsKey(name)) {
				phoneBookMap.remove(name);
			}
			System.out.println(name + "삭제완료");
		}
		
		private void update() {
			
		}
		
		
		private void insert() {
			System.out.println("새롭게 등록할 정보 입력");
			System.out.print("이름입력");
			String name = scan.next();
			
			if(phoneBookMap.containsKey(name)) {
				System.out.println(name + "이미 등록됨");
				return;
			}
			System.out.print("전화번호");
			String tel = scan.next();
			
			System.out.print("주소");
			String addr = scan.next();
			
			Phonee p = new Phonee(name, tel, addr);
			phoneBookMap.put(name, p);
			System.out.println(name + "등록완료");
		
		
		
		}
		
		
		private int displayMenu() {
			System.out.println();
			System.out.println("-----------------");
			System.out.println(" 1. 전화번호 등록 ");
			System.out.println(" 2. 전화번호 수정 ");
			System.out.println(" 3. 전화번호 삭제 ");
			System.out.println(" 4. 전화번호 검색 ");
			System.out.println(" 5. 전화번호 전체 출력 ");
			System.out.println(" 0. 프로그램 종료 ");
			System.out.println("========================");
			System.out.print("번호입력 >>> ");
			int num = scan.nextInt();
			return num;
		}
		

	
}



class Phonee{
	private String name;
	private String tel;
	private String addr;
	public Phonee(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
