package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest04 {
	public static void main(String[] args) {
		//문제2) 문제1에서 별명의 길이가 같은것이 있을 경우를 처리하시오.
		//		(단, 새로운 클래스 (ArrayListTest04)에서 작업하시오.)
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		
		
		for (int i = 1; i <=5; i++) {
			String name = scanner.next();
			list.add(name);
		}
		
		// 제일 긴 별명의 길이가 저장될 변수 선언 ==> List의 첫번째 데이터의 길이로 초기화 한다. 
		int maxLength = list.get(0).length();
		for (int i = 1; i < list.size(); i++) {
			if(maxLength < list.get(i).length()) {
				maxLength = list.get(i).length();
			}
		}
		System.out.println("제일 긴 별명들...");
		for (String name : list) {
			if(maxLength == name.length()) {
				System.out.println(name);
			}
		}
		
	}
}