package kr.or.ddit.basic;

// 쓰레드의 상태를 출력하는 프로그램 

public class ThreadTest12 {

	public static void main(String[] args) {
		StatePrintThread th = new StatePrintThread(new TargetThread());
		th.start();

	}

}


//쓰레드 상태의 검사 대상이 되는 쓰레드
class TargetThread extends Thread{
	
	@Override
	public void run() {
		for (long i = 1L; i <= 20_000_000_000L; i++) {} //시간 지연용
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				
			}
			for (long i = 1L; i <= 20_000_000_000L; i++) {} //시간 지연용
	}
}

//TargetThread의 상태를 출력하는 쓰레드
class StatePrintThread extends Thread{
	private TargetThread target;

	//생성자
	public StatePrintThread(TargetThread target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		while(true) {
			//쓰레드의 현재 상태 구하기
			Thread.State state = target.getState();
			System.out.println("TargetThread의 현재상태값 : " + state );
			if(state == Thread.State.NEW) { //쓰레드의 상태가 NEW상태인지 검사..
				target.start(); //TargetTHread를 실행한다.
			}
			if(state == Thread.State.TERMINATED) { //쓰레드의 상태가 종료 상태이면..
				break;
			}	
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
	}
}