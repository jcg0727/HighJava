package kr.or.ddit.basic;

import javax.swing.text.StyledEditorKit.ForegroundAction;

/*
 10마리의 말들이 경주하는 경마프로그램을 작성하시오.
 말은 Horse라는 이름의 쓰레드 클래스로 작성하는데 
 이클래스는 말이름(String), 현재위치(int), 등수(int)를 멤버변수로 갖는다.
 
  그리고, 이 클래스는 등수를 오름차순으로 처리할 수 잇는 내부정렬기준이 있다. (Comparable인터페이스 구현)
  
  경기구간은 1~50구간으로 되어 있다.
  
  경기 중 중간중간에 각 말들의 위치를 아래와 같이 나타내시오.
  예시)
  01번말
  02번말
  ...
  10번말
  
  경기가 끝나면 등수 순으로 출력한다.
*/

public class ThreadTest11 {

	public static void main(String[] args) {
		Horse[] hor = new Horse[] {

				new Horse("1"),
				new Horse("2"),
				new Horse("3"),
				new Horse("4"),
				new Horse("5"),
				new Horse("6"),
				new Horse("7"),
				new Horse("8"),
				new Horse("9"),
				new Horse("10"),			
		};
		for (Horse horse : hor) {
			horse.start();
		}
		for (Horse horse : hor) {
			try {
				horse.join();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}
}


// 1~50구간 경주마 쓰레드
class Horse1 extends Thread{
	public static String setRank = "";
	private String name;
	private int locate;
	private int rank;
	
	//생성자
	public Horse(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			System.out.println(name + "번말 ");
			for (int j = 1; j <=i; j++) {
				System.out.print("-");
			}
				System.out.println(">");
			try {
				Thread.sleep((int)(Math.random()*(301)+200));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println(name + "번말 경주 종료");
		setRank+=name+"";
	}
	
}
