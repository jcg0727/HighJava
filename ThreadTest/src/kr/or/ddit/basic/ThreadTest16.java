package kr.or.ddit.basic;

public class ThreadTest16 {

	public static void main(String[] args) {
		ShareObject obj= new ShareObject();
		
		TestThread th1 = new TestThread("1번쓰레드", obj);
		TestThread th2 = new TestThread("2번쓰레드", obj);
	
		th1.start();
		th2.start();
		
	}

}

//연습용 쓰레드
class TestThread extends Thread{
	ShareObject sobj;
	
	public TestThread(String name, ShareObject sobj) {
		super(name);
		this.sobj = sobj;
	}
	
	@Override
	public void run() {
		for (int i = 0; i <10; i++) {
			sobj.add();
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

//공통으로 사용할 클래스
class ShareObject{
	private int sum = 0;
	
	//동기화 처리하기
	//public synchronized void add(){ // 방법 1 : 메서드 자체에 동기화 처리를 한다.
	public void add() {  //방법2 : 동기화 블럭으로 설정한다.
		synchronized(this) {
		int n = sum;
		n += 10;
		sum = n;
		System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
	}
}
}
