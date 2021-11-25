package kr.or.ddit.basic;

public class ThreadTest21 {
	public static void main(String[] args) {
		DataBox box = new DataBox();
		
		ProducerThread th1 = new ProducerThread(box);
		ConsumerThread th2 = new ConsumerThread(box);
		
		th1.start();
		th2.start();
		
	}
}



//데이터를 공통으로 사용하는 클래스
class DataBox{
	private String data;
	
	//데이터를 공급하는 메서드
	// ==>data변수에 값이 있으면 data가 null이 될때까지 기다린다.
	// ==>data가 null이되면 새로운 데이터를 data변수에 저장한다.
	public synchronized void setData(String data) {
		if (this.data != null) {
			try {
				wait();
			} catch (InterruptedException  e) {
				// TODO: handle exception
			}
		} //if문 끝./
		this.data = data;  // 새로운 데이터 저장
		System.out.println("Thread에서 새로 저장한 데이터 : " + data);
		notify();
	}
	
	//데이터를 사용하는 메서드
	// ==> data 변수의 값이 null이면 data 변수에 새로운 데이터가 저장될 때 까지 기다린다.
	// ==> data 변수에 값이 잇으면 해당 데이터를 반환한다.
	//     반환 후에는 data변수 값을 null로 만든다.
	public synchronized String getData() {
		if(data == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		} //if문 끝
		String returnData = data;
		System.out.println("쓰레드가 읽은 데이터 : " + data);
		data = null;
		notify();
		return returnData;
	}

}//클래스끝

//데이터를 넣어주는 쓰레드
class ProducerThread extends Thread{
	private DataBox box;
	
	public ProducerThread(DataBox box) {
		this.box = box;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 3; i++) {
			box.setData("공급데이터-" + i);
		}
	}
}

// 데이터를 꺼내서 사용하는 쓰레드
class ConsumerThread extends Thread{
	private DataBox box; 
	
	public ConsumerThread(DataBox box) {
		this.box = box;
	}
	@Override
	public void run() {
		for (int i = 1; i <=3; i++) {
			String data = box.getData(); 
		}
	}
}


