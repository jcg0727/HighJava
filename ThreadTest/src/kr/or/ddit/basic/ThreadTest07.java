package kr.or.ddit.basic;

import java.util.Random;

import javax.swing.JOptionPane;

/*
 문제) 컴퓨터와 가위바위보를 진행하는 피로그램을 작성하시오.
 
 컴퓨터의 가위 바위 보는 난수를 이용해서 구하고,
 사용자의 입력 showInputDialot()메서드를 이용해서 입력받는다.
 
 입력시간은 5초로 제한하고 카운트 다운을 한다.
 5초 안에 입력이 없으면 게임에 진것으로 처리한다.
 
 5초 안에 입력이 있으면 입력된 값과 컴퓨터의 승패를 구해서 출력한다.
 
 결과 예시 
 -- 결과 --
 컴퓨터 : 가위
 사용자 : 바위
 결과 : 당신이 이겼습니다.

5초안에 입력이 없을때 예시)
시간이 초과되어 당신이 졌습니다.
 */


public class ThreadTest07 {

	public static void main(String[] args) {
		Input input = new Input();
		Count count = new Count();
		
		input.start();
		count.start();
		
	}
}

// 데이터를 입력받는 쓰레드
class Input extends Thread{
	
	//입력 여부를 확인하기 위한 변수 선언
	public static boolean inputCheck = false;
	
	@Override
	public void run() {
		Random random = new Random();
		int com = random.nextInt(3);
		String player = JOptionPane.showInputDialog("가위 바위 보를 입력해 주세요");
		inputCheck = true;
		System.out.println("--결과--");
		if(com==0) {
			System.out.println("컴퓨터 : 가위");
			if(player.equals("가위")) {
				System.out.println("사용자 : 가위");
				System.out.println("결과 : 무승부 입니다.");
			}else if(player.equals("바위")) {
				System.out.println("사용자 : 보");
				System.out.println("결과 : 당신이 졌습니다.");
			}else {
				System.out.println("사용자 : 바위");
				System.out.println("결과 : 당신이 이겻습니다.");
			}	
		}else if(com==1) {
			System.out.println("컴퓨터 : 바위");
			if(player.equals("바위")) {
				System.out.println("사용자 : 바위");
				System.out.println("결과 : 무승부 입니다.");
			}else if(player.equals("가위")) {
				System.out.println("사용자 : 가위");
				System.out.println("결과 : 당신이 졌습니다.");	
			}else {
				System.out.println("사용자 : 보");
				System.out.println("결과 : 당신이 이겼습니다.");
			}	
		}else{
			System.out.println("컴퓨터 : 보");
			if(player.equals("보")) {
				System.out.println("사용자 : 보");
				System.out.println("결과 : 무승부 입니다.");
			}else if(player.equals("가위")) {
				System.out.println("사용자 : 가위");
				System.out.println("결과 : 당신이 이겼습니다.");	
			}else {
				System.out.println("사용자 : 바위");
				System.out.println("결과 : 당신이 졌습니다.");
			}	
		}
	}
}

class Count extends Thread{
	@Override
	public void run() {
		
		for (int i = 5; i >=1; i--) {
			//입력이 완료되었는지 여부를 검사해서 입력이 완료되면 쓰레드를 종료시킨다.
			if (Input.inputCheck == true) {
				return; //run()메서드가 종료되면 쓰레드도 종료된다.
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
		
			}
		}
		System.out.println("시간이 초과되어 당신이 졌습니다.");
		System.exit(0);
	}
}