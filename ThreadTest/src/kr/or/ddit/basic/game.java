package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class game {

	public static boolean inputCheck ; // 자동 초기화 되기 때문에 false로 초기화를 입력하지 않아도 된다. 
	
	public static void main(String[] args) {
		GameTimer timer = new GameTimer();
		
		
		// 난수를 이용해서 컴퓨터의 가위바위보 정하기
		String[]  data = {"가위", "바위", "보"};
		int index = (int)(Math.random() * data.length); // 0~2사이 난수 만들기
		String com = data[index]; // 컴퓨터의 가위 바위 보를 정한다.
		
		
		//사용자의 가위 바위 보 입력받기
		String user = null; // 사용자의 가위 바위 보가 저장될 변수 선언
		timer.start(); //카운트다운시작
		
		do {
		user = JOptionPane.showInputDialog("가위바위보를입력하세요.");
		}while (user ==null || !(user.equals("가위")||user.equals("바위")||user.equals("보")));
		
		inputCheck = true;
		
		// 결과 판정하기
		String result = "";
		if(user.equals(com)) {
			result = "비겼습니다.";
		}else if(user.equals("가위") && com.equals("보") ||
				 user.equals("바위") && com.equals("가위") ||
				 user.equals("보") && com.equals("바위")) {
			result = "당신이 이겼습니다.";
		}else{
			result = "당신이 졌습니다.";
		}
		//결과출력
		System.out.println(" --- 결  과 ---");
		System.out.println(" 컴퓨터 : " + com);
		System.out.println(" 사용자 : " + user);
		System.out.println(" 결  과 : " + result);
	}

}



class GameTimer extends Thread{
	@Override
	public void run() {
		System.out.println("카운트 다운 시작..");
		for (int i = 5; i>=1; i--) {
		if(game.inputCheck==true) { //  입력 완료 여부 검사
			return;
		}
		System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		} //for문끝
		System.out.println("시간이 초과되어 당신이 졌습니다.");
		System.exit(0);
	
	}
	
}