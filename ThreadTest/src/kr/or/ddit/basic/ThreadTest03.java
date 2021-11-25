package kr.or.ddit.basic;

public class ThreadTest03 {

	public static void main(String[] args) {
		//쓰레드의 수행 시간을 체크해 보자.
		//MyRunner r = new MyRunner();
		//Thread th = new Thread(r); 위 두줄을 줄이면 아래 줄. 
		
		Thread th = new Thread(new MyRunner());
		
		
		//1970년 1월 1일 0시 0분 0초(표준시간)로 부터 경과한 시간을 밀리세컨드 단위로 반환한다.
		//밀리세컨드 ==>1/1000초 
		long startTime = System.currentTimeMillis();
		
		//시간을 체크할 내용
		th.start();
		try {
			th.join(); // 현재 실행중인 쓰레드에서 대상이 되는 쓰레드(지금은 th)가
						// 종료될 때 까지 기다린다.
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("경과 시간 : " + (endTime-startTime));
	}
}

class MyRunner implements Runnable{
	@Override
	public void run() {
		long sum = 0L;
		for(long i=1L; i<=1_000_000_000L; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
}