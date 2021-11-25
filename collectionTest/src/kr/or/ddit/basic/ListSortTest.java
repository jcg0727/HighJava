package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/*
 	정렬과 관련된 interface는 Comparable, Comparator 이렇게 두가지가 있다.
 	
 	Comparable은 Collection에 추가되는 데이터 자체에 정렬 기준을 넣고 싶을 때(내부 정렬 기준)
 	구현하는 인터페이스이고,
 	
 	Comparator는 외부에 별도로 정렬 기준을 구현하고 싶을 때(외부 정렬 기준) 구현하는 인터페이스 이다.
 	
 	Comparable에서는 compareTo()메서드를 재정의 하고, 
 	Comparator에서는 compare()메서드를 재정의해야 한다.
 	
 	-String클래스, Wrapper클래스, Date클래스, File 클래스에는 내부 정렬 기준이 구현되어 있다.(내부 정렬 기준은 오름차순으로 처리되도록 구현되어 있다.)
 */

public class ListSortTest {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬전 : " + list);
		//정렬은Collections.sort() 메서드를 이용하여 정렬한다.
		//Collections.sort()메서더는 기본적으로 저장된 데이터의 내부 정렬 기준으로 정렬한다.
		//대부분의 데이터들의 내부 정렬 기준은 오름차순을 기준으로 정렬하도록 구성되어 있다.
		Collections.sort(list);
		
		System.out.println("정렬후 : " + list);
		
		Collections.shuffle(list); // 자료 섞기
		System.out.println("자료섞기후 : " + list);
		
		//외부 정렬 기준 클래스를 지정해서 정렬하기
		Collections.sort(list,new Desc());
		System.out.println("내림차순 정렬 후 : " + list);
	
	}
}


// 정렬 방식을 정해주는 class 만들기(외부 정렬 기준 클래스라고 한다.)
// ==>comparator 인터페이스를 구현해 작성한다.
class Desc implements Comparator<String>{
	// compare()메서드를 이용해서 정렬하고자 하는 기준을 정해준다.

	// compare()메서드의 반환값
	// 두 값이 같으면 0을 반환한다.
	// 양수를 반환하면 앞, 뒤의 순서를 바꾼다.
	// 음수를 반환하면 순서를 바꾸지 않는다.
	
	//예) 오름차순일 경우 ==> 앞의 값이 크면 양수, 같으면 0
	//						  앞의 값이 작으면 음수를 반환하도록 구현
	@Override
	public int compare(String str1, String str2) {
		// 내림차순으로 구현
		if(str1.compareTo(str2)>0) {
			return -1;	
		}else if (str1.compareTo(str2)<0) {
			return 1;
		}else {
			return 0;			
		}
	}
}





