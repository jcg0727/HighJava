package kr.or.ddit.basic.args;

public class ArgsTest {
	
	// 매개변수로 받은 정수들의 합계를 구하는 메서드
	// (이 정수들의 개수는 상황에 따라 변할 수 있다.)
	public int sumArr(int[] data) {
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	/*
	  	가변형 인수 ==> 메서드의 매개변수의 개수가 실행될 때 마다 다를 수 있을 때 사용한다.
	  				==> 가변형 인수는 메서드 안에서 배열로 처리된다.
	 */
	// 가변형 인수를 이용하는 메서드 만들기
	public int sumArg(int...data) {
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
		
	//가변형 인수와 일반적인 변수를 같이 사용할 경우에는 가변형 변수를 제일 뒤쪽에 배치해야 한다.
	public String sumArg2(String name, int...data) {
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return name + "씨의 합계 : " + sum;
	}
	
	
	
	
	public static void main(String[] args) {
		ArgsTest test = new ArgsTest();
		int[] nums = {100, 200, 300};
		System.out.println(test.sumArr(nums));
		System.out.println(test.sumArr(new int[] {1,2,3,4,5,6}));
//		int k = 100;
//		test.aaa(k);
//		test.aaa(200);
		System.out.println();
		System.out.println(test.sumArg(100,200,300));
		System.out.println(test.sumArg(1,2,3,4,5,6));
		
		System.out.println();
		System.out.println(test.sumArg2("홍길동", 70, 80, 90));
	}
}
