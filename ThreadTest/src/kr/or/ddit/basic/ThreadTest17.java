package kr.or.ddit.basic;

//은행의 입출금을 쓰레드로 처리하는 예제
//(동기화 처리 예제)



public class ThreadTest17 {
	private int balance; //잔액이 저장될 변수	
	
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	//입금을 처리하는 메서드
	public void deposit(int money) {
		balance += money;
	}
	
	//출금을 처리하는 메서드 ( 반환값 : 출금성공(true), 출금실패(false))
	public boolean withdraw(int money) {
		synchronized (this) { // 동기화 블럭으로 처맇마	
			if(balance >= money) { //잔액이 출금액 이상인지 여부 검사
				for(int i=1; i<=100000000; i++) {} //시간지연용
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance());
				return true;
			}else {
				return false;
			}
		}
	}
	
	
	
	
	public static void main(String[] args) {
		
		//입출금을 처리하는 객체 생성
		ThreadTest17 acount = new ThreadTest17();
		acount.setBalance(10000); // 잔액을 만원으로 설정
		
		//익명 구현체로 쓰레드 구성하기
		Runnable test = new Runnable() {
			
			@Override
			public void run() {
				boolean result = acount.withdraw(6000); // 6000원 출금하기
				System.out.println("쓰레드에서 result = " + result + ", balance = " + acount.getBalance());
				
			}
		};
		//-----------------------------------------------------
		
		Thread th1 = new Thread(test);
		Thread th2 = new Thread(test);
		
		th1.start();
		th2.start();
	}
}
