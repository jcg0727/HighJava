package kr.or.ddit.basic;

//쓰레드에서 객체를 공통으로 사용하는 예제 프로그램

/*
 * 원주율을 계산하는 쓰레드와 계산된 원주율을 출력하는 쓰레드가 있다.
 * 
 * 원주율을 저장하는 객체가 필요하다.
 * 이 객체를 두 쓰레드가 공통으로 사용해서 처리한다.
 */

public class ThreadTest15 {

	public static void main(String[] args) {
		System.out.println("작업시작 ...");
		//공통으로 사용할 객체를 생성
		ShareData sd = new ShareData();
		
		//쓰레드 객체를 생성하고 공통으로 사용할 객체를 쓰레드에 주입한다.
		CalcPIThread cals = new CalcPIThread(sd);
		
		PrintPIThread prn = new PrintPIThread(sd);
		
		cals.start();
		prn.start();
		

	}

}



//-----------------------------------------------------------------------------------------------------------------------
//원주율을 관리하는 클래스(공통으로 사용할 클래스)
class ShareData{
	public double result;  // 계산된 원주율이 저장될 변수
	
	//volatile ==> CPU의 각 코어에는 캐쉬가 있는데 이 캐쉬를 사용하지 않고 직접메모리에서 데이터값을 입출력 한다.
	public volatile boolean isOk = false; //계산이 완료되었는지 여부를 나타내는 변수
}


//-----------------------------------------------------------------------------------------------------------------------
// 원주율을 계산하는 쓰레드 
class CalcPIThread extends Thread {
	private ShareData sd;
	
	
	//생성자
	public CalcPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		/*
		 * 원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 - .........)*4
		 */
		
		double sum = 0.0;
		for (int i = 1; i <=1000000000; i+=2) { //1부터 2씩 증가
			if((i/2) % 2 ==0) { //몫이 짝수일때
				sum += (1.0/i);		
			}else {//홀수일때
				sum -= (1.0/i); 
			}
		}
		sd.result = sum*4; // 완료된 계산 결과를 공통 객체에 저장한다.
		sd.isOk = true;
	}
}


//---------------------------------------------------------------------------------------------------------------------------
//계산된 원주율을 출력하는 쓰레드
class PrintPIThread extends Thread{
	private ShareData sd;
	
	//생성자
	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while(true) {
			if(sd.isOk==true) { // 계산이 완료되었는지 검사
				break;
			}
		}
		
		System.out.println();
		System.out.println("결과 : " + sd.result);
		System.out.println("PI : " + Math.PI);
	}
}