package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
/*
  문제) set을 이용하여 숫자야구게임 프로그램을 작성하시오.
  	   컴퓨터의 숫자는 난수를 이용하여 구한다.
  	   (스트라이크는 S, 볼은 B 로 나타낸다.)
  	   
  	   예시)
  	   	   컴퓨터의 난수 ==> 9 5 7
  	   	실행예시)
  	   	  숫자입력 => 3 5 6
  	   	  3 5 6 ==> 1S 0B
  	   	  숫자입력 => 7 8 9
  	   	  7 8 9 ==> 0S 2B  
*/
public class BaseBallTest {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		HashSet<Integer> hash = new HashSet<>();
		while(hash.size()<3) {
			int com = (int)(Math.random()*9+1);
			hash.add(com);
		}
		ArrayList<Integer> list = new ArrayList<>(hash);
		for (int i = 0; i < list.size(); i++) {
		}
		Collections.shuffle(list);
		System.out.println(list);
		
		while(true) {
			ArrayList<Integer> player = new ArrayList<>();
			int strike = 0;
			int ball = 0;
			
			for (int i = 1; i <= 3; i++) {
				int input = scanner.nextInt();
				player.add(input);
			}
			System.out.print("player : " + player);
			
			
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < player.size(); j++) {
					if (list.get(i)==player.get(j) && i==j) {
						strike++;
					}else if (list.get(i)==player.get(j) && i !=j) {
						ball++;
					}
				}
			}
			System.out.println(strike+"S"+ball+"B");
			
			if(strike == 3) {
				break;
			}
		}
	}
}
