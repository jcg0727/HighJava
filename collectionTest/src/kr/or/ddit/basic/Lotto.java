package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Lotto {
	Scanner scan = new Scanner(System.in);
	
	
	
	public static void main(String[] args) {
		new Lotto().main();

	}
	
	//메인 메서드
	public void main() {
		System.out.println("===============================");
		System.out.println("Lotto 프로그램");
		System.out.println("---------------------");
		System.out.println("1. Lotto 구입 \n2. 프로그램 종료");
		System.out.println("===============================");
		System.out.print("메뉴선택 : ");
		int menu = scan.nextInt();
		
		switch(menu) {
		case 1:
			buy();
			break;
		case 2:
			System.out.println();
			System.out.println("감사합니다");
		}
		
	}
	
	public void buy() {
		System.out.println("Lotto 구입 시작");
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력 : ");
		int money = scan.nextInt();
		int count = money / 1000;
		System.out.println("행운의 로또 번호는 아래와 같습니다.");
		if(money >= 1000 && money <=100000) {
			for (int i = 1; i < count+1; i++) {
					
				System.out.print("로또번호"+ i + " :" );
				num();
			}
			System.out.println("받은 금액은 " + money + "원이고 거스름돈은 "+ (money % 1000) + "입니다.");
			main();
		}else if(money<1000){
			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
			main();
		}else{
			System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
			main();
		}	
	}

	// 번호 생성 메서드
	public void num() {
		HashSet<Integer> numSet = new HashSet<>();
		
		// 로또 번호 만들기
		while(numSet.size()<6) {
			numSet.add((int)(Math.random()*45+1));
		}
		
		ArrayList<Integer> numList = new ArrayList<>(numSet);
		
		Collections.sort(numList);
		System.out.println(numList);
	}
	
}
