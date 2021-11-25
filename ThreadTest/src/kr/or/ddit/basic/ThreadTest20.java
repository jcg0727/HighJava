package kr.or.ddit.basic;

import org.omg.Messaging.SyncScopeHelper;

/*
 	wait(), notify()메서드를 이용한 두 쓰레드에서 번갈아 한번씩 실행하는 예제
 	
 	wait(), notify(), notifyAll()은 동기화 영역에서만 사용 가능하다.
*/

public class ThreadTest20 {

	public static void main(String[] args) {
		WorkObject work = new WorkObject();
		
		ThreadA th1 = new ThreadA(work);
		ThreadB th2 = new ThreadB(work);
		
		th1.start();
		th2.start();
		
	}
}



//공통으로 사용할 객체
class WorkObject{
	public synchronized void methodA() {
		System.out.println("methodA() 메서드 실행 중 ...");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {

		}
	}
	
	public synchronized void methodB() {
		System.out.println("methodB() 메서드 실행 중 ...");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {

		}
	}
}//클래스끝

//WorkObject의 methodA()메서드만 호출하는 쓰레드
class ThreadA extends Thread{
	private WorkObject work;
	
	public ThreadA(WorkObject work) {
		this.work = work;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			work.methodA();
		}
	}
}

//WorkObject의 methodB()메서드만 호출하는 쓰레드
class ThreadB extends Thread{
	private WorkObject work;
	
	public ThreadB(WorkObject work) {
		this.work = work;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			work.methodB();
		}
		
		//마지막에 waiting pool에있는 쓰레드를 깨워준다.
		synchronized(work) {
			work.notify();
		}
	}
}


