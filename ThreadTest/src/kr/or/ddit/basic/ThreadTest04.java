package kr.or.ddit.basic;

/*
 	1~20억 까지의 합계를 구하는 프로그램을 하나의 쓰레드가 단독으로 처리할 때와
 	여러개의 쓰레드가 협력해서 처리할 때의 경과시간을 비교해 보자.
*/

public class ThreadTest04 {
	public static void main(String[] args) {
		// 단독으로 처리하는 쓰레드 객체 생성
		SumThread sm = new SumThread(1L, 2000000000L);
		
		// 여럿이 협력해서 처리하는 쓰레드 객체 생성
		SumThread[] sums = new SumThread[] {
			new SumThread(1L, 500_000_000L),
			new SumThread(500_000_001L,1_000_000_000L),
			new SumThread(1_000_000_001L, 1_500_000_000L),
			new SumThread(1_500_000_001L, 2_000_000_000L)
		};
		
		//단독으로 처리하기
		long startTime = System.currentTimeMillis();
		sm.start(); // 쓰레드 실행
		try {
			sm.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독으로 처리했을 때 경과 시간 : " + (endTime - startTime));
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();
		
		
		//여러 쓰레드가 협력해서 처리하기
		startTime = System.currentTimeMillis();
		
		//쓰레드 실행
		for (SumThread ss : sums) {
			ss.start();
		}
		//협력하는 모든 쓰레드가 종료될 때까지 기다린다.
		for (int i = 0; i < sums.length; i++) {
			try {
				sums[i].join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		endTime = System.currentTimeMillis();
		
		System.out.println("협력해서 처리한 경과 시간 : " + (endTime-startTime));
		
		
		
		
		
		
		
	}
}


// 시작값과 종료값을 이용하여 시작값과 종료값 사이의 합계를 구하는 쓰레드 클래스 작성
class SumThread extends Thread{
	private long min, max;
	
	public SumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}
	@Override
	public void run() {
		long sum = 0l;
		for (long i = min; i <= max; i++) {
			sum +=i;
		}
		System.out.println("합계(" + min + "~" + max + ") : " + sum);
	}
	
}
