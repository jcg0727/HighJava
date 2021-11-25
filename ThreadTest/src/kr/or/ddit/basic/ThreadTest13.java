package kr.or.ddit.basic;


// yield()메서드 연습
public class ThreadTest13 {

	public static void main(String[] args) {
		YieldThread th1 = new YieldThread("1번쓰레드");
		YieldThread th2 = new YieldThread("2번쓰레드");
		
		th1.start();
		th2.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("1111111111111111111-----------------------------");
		
		th1.work = false;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("22222222222222222222-----------------------------");
		
		th1.work = true;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("3333333333333-----------------------------");
		
		th1.stop = true;
		th2.stop = true;
	}
}


//yield()메서드 연습용 쓰레드
class YieldThread extends Thread {
	public boolean stop = false; // 쓰레드의 종료 여부를 결정하는 변수
	public boolean work = true;  // 쓰레드가 작동 중 특정 일처리 여부를 결정하는 변수
	
	
	public YieldThread(String name) {
		super(name);  // 쓰레드의 이름 설정
	}
	
	@Override
	public void run() {
		while(!stop) {
			if(work) {
				System.out.println(getName() + "- 작업중...");
			}else {
				System.out.println(getName() + "-양보...");
				Thread.yield();
			}
		}
	}
	
	
}