package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayLIstTest03 {
	public static void main(String[] args) {
		//문제) 5명의 별명을 입력받아 ArraryList에 저장하고 이들 중 별명의 길이가 제일 긴 별명을 출력하시오.
		//		(단, 각 별명의 길이는 모두 다르게 입력한다.)
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		
		
		for (int i = 1; i <=5; i++) {
			String name = scanner.next();
			list.add(name);
		}
		
		
		String word = "";
		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i);
			if(name.length() > word.length()) {
				word = name;
			}
		}
		System.out.println(word);
	}
}
