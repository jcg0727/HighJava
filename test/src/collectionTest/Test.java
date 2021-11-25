package collectionTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Test {
	private Scanner scan;
	private Map<String, PPhone> pbMap;
	
	public Test() {
		scan = new Scanner(System.in);
		pbMap = new HashMap<>();
	}
	
	//시작하는 메서드
	public static void main(String[] args) {
	new Test().pbStart();
	}
	

	
	private void pbStart() {
		System.out.println("---------------------");
		System.out.println("전화 관리 시스템");
		System.out.println("---------------------");
		
		
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
				
			case 4:
				search();
				break;
				
			case 5:
				all();
				break;
				
			case 0:
				System.out.println("종료");
				break;
			default :
				System.out.println("번호 잘못 입력");
			
			
			}	
		}
	} //pbStart 메서드
	
	private void all() {
		System.out.println();
		Set<String> phKey = pbMap.keySet();
		System.out.println("-----------------------------------");
		System.out.println("번호    이름    전화번호     주소");
		System.out.println("-----------------------------------");
		
		if(phKey.size() == 0) {
			System.out.println("저장 x");
		}else {
			int cnt = 0;
			Iterator<String> phIt = phKey.iterator();
			while (phIt.hasNext()) {
				cnt++;
				String name = phIt.next();
				PPhone p = pbMap.get(name);
				System.out.println(cnt + p.getName() + p.getTel() + p.getAddr());
			}
			
		}
		
		
	}
	
	// 메뉴보기
	private int displayMenu() {
		System.out.println();
		System.out.println("------------------");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체출력");
		System.out.println("0. 종료");
		System.out.println("------------------");
		System.out.println("------------------");
		System.out.print("번호입력 >>");

		int num = scan.nextInt();
		return num;
		
	}
	
	//등록 
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 정보입력");
		System.out.print("이름>> ");
		String name = scan.next();
		
		if(pbMap.containsKey(name)) {
			System.out.println("이미 등록");
			return;
		}
		System.out.print("전화번호>> ");
		String tel = scan.next();
		
		scan.nextLine();
		System.out.print("주소>> ");
		String addr = scan.nextLine();
		
		PPhone p = new PPhone(name, tel, addr);
		
		pbMap.put(name, p);

		System.out.println(name + "등록완료");
		
	}
		
	//수정
	private void update() {
		System.out.println();
		System.out.println("수정할 정보 입력");
		System.out.print("이름>>");
		String name = scan.next();
		
		if(!pbMap.containsKey(name)) {
			System.out.println(name + "정보가 없습니다");
			return;
		}
		
		System.out.print("새로운 전화번호 >>");
		String newTel = scan.next();
		
		System.out.print("새로운 주소>>");
		String newAddr = scan.next();
		pbMap.put(name, new PPhone(name, newTel, newAddr));
		System.out.println(name + " 수정 완료");
	}
	
	//삭제
	private void delete() {
		System.out.println();
		System.out.println("삭제할 정보 입력");
		System.out.println("이름>>");
		String name = scan.next();
		
		if(!pbMap.containsKey(name)) {
			System.out.println("삭제할 정보가 없습니다");
			return;
		}
		
		pbMap.remove(name);
		System.out.println("삭제완료");
		
	}
	
	//검색
	private void search() {
		System.out.println();
		System.out.println("검색할 정보 입력");
		System.out.print("이름>>");
		String name = scan.next();
		
		if(!pbMap.containsKey(name)) {
			System.out.println("정보 없습니다.");
		}
		
		PPhone p = pbMap.get(name);
		System.out.println();
		System.out.println("==========================");
		System.out.println("이름 : " + p.getName());
		System.out.println("전화번호 : " + p.getTel());
		System.out.println("주소 : " + p.getAddr());
		System.out.println("==========================");
		System.out.println("검색완료");
	}
	
}//Test 메서드










class PPhone{
	private String name;
	private String tel;
	private String addr;
	

	public PPhone(String name, String tel, String addr) {
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