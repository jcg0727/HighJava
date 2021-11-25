package kr.or.ddit.test;

import java.util.Scanner;

public class TestController {
	private Scanner scan = new Scanner(System.in);
	private ITestService service;
	
	public TestController() {
		service = TestServiceImpl.getInstance();
	}
	
	public static void main(String[] args) {
		new TestController().testStart();
	}
	
	
	private void testStart() {
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1:
				insertTest();
				break;
				
			default :
				System.out.println("잘못입력");
			}
			
			
		}
		
	}//
	
	private void insertTest() {
		System.out.println();
		System.out.println("정보 입력");
		System.out.print("번호>>");
		String num = scan.next();
			
		System.out.print("아이디>>");
		String id = scan.next();
		
		System.out.print("이름>>");
		String nm = scan.next();
		
		TestVO vo = new TestVO();
		vo.setTestNum(num);
		vo.setTestId(id);
		vo.setTestNm(nm);
		int cnt = service.insertTest(vo);
	}
	
	private int displayMenu() {
		System.out.println("Test");
		System.out.println("1.추가");
		System.out.println("============");
		System.out.print("작업선택>>");
		return scan.nextInt();
	}
	
	
	
	

}
