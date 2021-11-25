package kr.or.ddit.basic;

/*
	3개의 쓰레드가 각각 알파벳(A~Z)을 출력하는데 출력을 끝낸 순서대로 결과를 출력하는 프로그램 작성하시
*/


public class ThreadTest10 {

	public static void main(String[] args) {
		DisplayCharacter[] disp = new DisplayCharacter[] {
				new DisplayCharacter("홍길동"),
				new DisplayCharacter("이순신"),
				new DisplayCharacter("변학도"),
		};
			
		for(DisplayCharacter ds :disp) {
			ds.start();
		}
		//모든 쓰레드의 출력이 끝날때까지 기다린다.
		for (DisplayCharacter ds : disp) {
			try {
				ds.join();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println();
		System.out.println(" 경기결과");
		System.out.println("순위 : " + DisplayCharacter.setRank);
	}

}


//A~Z까지 출력하는 쓰레드
class DisplayCharacter extends Thread{
	public static String setRank = "";
	private String name;
	
	//생성자
	public DisplayCharacter(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(char c='A'; c<='Z'; c++) {
			System.out.println(name + "의 출력 문자 -- " + c);
			try {
				//200~500사이의 난수로 일시정지 시간을 설정한다.
				Thread.sleep((int)(Math.random()*(301)+200));
			} catch (Exception e) {
				
			}
		}
		System.out.println(name + "출력끝");
		
		//출력을 끝낸 순서대로 이름을 배치한다.
		setRank += name + " ";
	}
	
}