package kr.or.ddit.basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		// 멀티 쓰레드 프로그램
		
		//방법1
		// ==> Thread클래스를 상속한 class의 인스턴스를 생성한후
		//	   이 인스턴스의 start()메서드를 호출해서 실행한다.
		MyThread1 th1 = new MyThread1();
		th1.start(); //run으로도 호출 가능 하지만 싱글 쓰레드처럼 작동함.
		
		//방법2
		//==> Runnable 인터페이스를 구현한 class의 인스턴스를 생성한 후
		//	  이 인스턴스를 Thread의 인스턴스를 생성할 때 생성자의 인자값으로 넘겨준다.
		//	  이 때 생성된 Thread의 인스턴스의 start()메서드를 호출해서 실행한다.
		MyThread2 r = new MyThread2();
		Thread th2 = new Thread(r);
		th2.start();
		
		//방법3 ==> 익명구현체를 이용하는 방법 ,,, Runnable를 구현한것과 똑같다
			Runnable r2 = new Runnable() {
				@Override
				public void run() {
					for (int j = 1; j <=200; j++) {
						System.out.print("@");
						try {
							// Thread.sleep(시간)메서드는 주어진 시간동안 작업을 잠시 멈춘다.
							// 시간은 밀리세컨드 단위를 사용한다.(즉, 1000 은 1초를 의미한다.)
							Thread.sleep(100);
						}catch (InterruptedException e) {	
						}
					}
				}
			};
			Thread th3 = new Thread(r2);
			th3.start();
			
			System.out.println("main메서드 끝");		
	}	
}





// 방법1 ==> Thread클래스를 상속받은 클래스 작성
class MyThread1 extends Thread{
	
	@Override
	public void run() {
		//이 run()메서드 안에 쓰레드에서 처리할 내용을 기술한다.
		for(int i=1; i<=200; i++) {
			System.out.print("*");
			try {
				// Thread.sleep(시간)메서드는 주어진 시간동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다.(즉, 1000 은 1초를 의미한다.)
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
		}
	}
}

//방법2 ==> Runnable 인터페이스를 구현한 클래스 작성
class MyThread2 implements Runnable{
	@Override
	public void run() {
		//이 run()메서드 안에 쓰레드에서 처리할 내용을 기술한다.
		for (int j = 1; j <=200; j++) {
			System.out.print("$");
			try {
				// Thread.sleep(시간)메서드는 주어진 시간동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다.(즉, 1000 은 1초를 의미한다.)
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
		}
	}
}