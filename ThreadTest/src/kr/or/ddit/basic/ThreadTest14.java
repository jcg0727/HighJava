package kr.or.ddit.basic;

/*
	Thread의 stop()메서드를 호출하면 쓰레드가 바로 멈춘다.
	이때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어 
	나중에 실행되는 프로그램에 영향을 줄 수 있다.
	그래서 stop()메서드는 비추천으로 되어 있다.
*/

//쓰레드를 멈추게 하는 연습용 프로그램
public class ThreadTest14 {
	public static void main(String[] args) {
//		StopThreadTest1 th1 = new StopThreadTest1();
//		th1.start();
//		try {
//			Thread.sleep(1000);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		//th1.stop(); // 비추천 메서드는 메서드명에 줄이 그려진 상태로 보여준다.
//		th1.setStop(true);
		
		
		//interrupt()메서드를 이용한 쓰레드 멈추기
		StopThreadTest2 th2 = new StopThreadTest2();
		th2.start();
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		th2.interrupt();
	}
}

// 쓰레드를 멈추게 하는 연습용 쓰레드
class StopThreadTest1 extends Thread{
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	@Override
	public void run() {
		while(!stop) {
			System.out.println("쓰레드 실행 중.....");
		}
		System.out.println("자원정리.........");
		System.out.println("쓰레드 종료..........");
	}
}

// interrupt()메서드를 이용해서 쓰레들르 멈추게 하는 쓰레드
class StopThreadTest2 extends Thread{
	
	@Override
	public void run() {
		 // 방법1==> interrupt()메서드와 sleep()메서드를 이용하는 방법
//		try {
//			while(true) {
//			System.out.println("실행중....");
//			Thread.sleep(1); //일시 정지 상태에서 interrupt() 메서드가 실행이 되면 
//						     // InterruptedException이 발생한다.
//			}
//		} catch (InterruptedException e) {
//			// TODO: handle exception
//		}
		
		//방법2
		while(true) {
			System.out.println("방법2 - interrupt() - 실행중 ................");
		
			//interrupt()메서드가 호출되었는지 여부를 검사한다.
			//검사방법1) Thread의 인스턴스 메서드인 isInterrupted()메서드 이용하기
			//			==>inInterrupted()메서드는 interrupt()메서드가 호출되면 true값을 반환한다.
			/*
			if (this.isInterrupted()) {
				break;
			}
			*/
			
			//검상방법2) Thread의 정적 메서드인 interrupted()메서드 이용하기
			//			==>interrupt()메서드가 호출되면 true 반환한다.	
			if (Thread.interrupted()) {
				break;
			}
		}
		
		
		
		System.out.println("자원정리...");
		System.out.println("쓰레드 종료..");
	}
	
}
