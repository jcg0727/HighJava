package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListTest02 {
	public static void main(String[] args) {
		
	//문제) 5명의 사람 이름을 입력 받아서 ArrayList에 저장한 후에 이들 중 '김'씨 성의 이름을 모두 출력하시오.
	//		(단, 입력은 Scanner객체를 이용한다.)
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		
		for (int i = 1; i <=5; i++) {
			System.out.println(i + "번째 이름 : ");
			String name = scanner.next();
			list.add(name);
		}
		
		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i);
			
			if(name.substring(0,1).equals("김")) {
				System.out.println(name);
			}	
			if(name.charAt(0)=='김') {	
				System.out.println(name);
			}
			
			if(name.startsWith("김")==true) {
				System.out.println(name);
			}
			
			if(name.indexOf("김")==0) {
				System.out.println(name);
			}
			
		}
	}
}

		
//		String name1 = scanner.next();
//		String name2 = scanner.next();
//		String name3 = scanner.next();
//		String name4 = scanner.next();
//		String name5 = scanner.next();
//		
//		list.add(name1);
//		list.add(name2);
//		list.add(name3);
//		list.add(name4);
//		list.add(name5);
//		
//	
//		
//		for (int i = 0; i < list.size(); i++) {
//			if (list.get(i).charAt(0).equals("김")) {
//				System.out.println(list.get(i));
//			}
//		}
		
		


