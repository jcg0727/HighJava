package kr.or.ddit.basic;

public class 연습 {
	public static void main(String[] args) {
		tthread th = new tthread();
		thre tt = new thre();
		th.start();
		tt.start();
		
	}
}

class tthread extends Thread{
	@Override
	public void run() {

		for (int i = 0; i <100; i++) {
				System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

class thre extends Thread{
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("*");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
