package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class SetTest {
	public static void main(String[] args) {
		/*
		  -List와 Set의 차이점
		1. List
		   - 데이터의 순서(index)가 있다.
		   - 중복되는 데이터를 저장할 수 있다.
		2. Set
		   - 데이터의 순서(index)가 없다.
		   - 중복되는 데이터를 저장할 수 없다.
		*/
		
		HashSet hs1 = new HashSet();
		
		//set에 데이터를 추가할 때도 add()메서드를 사용한다.
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("Set 데이터 : " + hs1);
		System.out.println("Set의 개수 : " + hs1.size());
		
		//set에 중복되는 데이터를 추가하면 false를 반환하고 데이터가 추가되지 않는다.
		boolean isAdd = hs1.add("FF");
		System.out.println("중복되지 않을 때 : " + isAdd);
		System.out.println("Set 데이터 : " + hs1);

		isAdd = hs1.add("CC");
		System.out.println("중복될 때 :" + isAdd);
		System.out.println("Set 데이터 : " + hs1);
		
		//Set의 데이터를 수정하려면 수정하는 명령이 따로 없기 대문에 해당자료를 삭제한 후 추가해 주는 방법을 사용한다.
		
		// 삭제하는 메서드 remove(삭제할자료) => 반환값 : 성공(true), 실패(false)
		//					clear() ==> 전체 삭제
		
		//"FF"데이터를 "EE"로 변겯ㅇ하기
		hs1.remove("FF");
		System.out.println("삭제후" + hs1);
		
		hs1.add("EE");
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		
		/*
		    set의 데이터에는 순서가 없기 때문에 list처럼 인덱스로 데이터를 하나씩 불러 올 수 없다.
		    그래서 데이터를 하나씩 얻기 위해서는 Iterator형 객체로 변환하거나 '향상된for문'을 사용해서 처리한다.
		    
		    -set형의 데이터를 Iterator형의 객체로 변환하는 메서드 ==> iterator()
		*/
		
			Iterator it = hs1.iterator();  // set데이터를 Iterator형으로 변환하기
			
			//Iterator의 hasNext()메서드
			//    => Iterator 의 포인터가 가리키는 곳의 다음번째에 데이터가 있는지 검사한다. // 포인터는 첫번째 데이터를 가리키는 것이 아니라 첫번째 데이터 위를 가리킴.
			//		있으면 true 없으면 false
			
			while(it.hasNext()) {
				//Iterator의 next()메서드
				//    ==>Iterator의 포인터를 다음번째 위치로 이동한 후 그곳의 데이터를 반환한다.
				
				System.out.println(it.next());
			}
		
			// 우리반 학생들 중 번호를 이용하여 추첨하는 프로그램을 작성해 보자. 번호는 1번부터 25번까지 이고, 추첨할 인원은 5명이다.
			// 당첨자를 출력해 보시오.(중복 당첨자는 없는것으로 한다.)
			
			//최소값부터 최대갑 사이의 정수형 난수만들기
			// (int)(Math.random()*(최대값-최소값+1)+최소값)
			HashSet<Integer> testSet = new HashSet<>();
			while(testSet.size()<5) {
				int num = (int)(Math.random()*25 + 1 );
				testSet.add(num);
			}
			System.out.println("당첨자번호:" + testSet);
			System.out.println();
			//향상된 for문으로 데이터 꺼내오기
			System.out.println("향상된for문");
			for (Integer num : testSet) {
				System.out.print(num + " ");
			}
			System.out.println();
			System.out.println();
			
			
			//Set유형의 자료를 List형으로 변환하기
			ArrayList<Integer> testList = new ArrayList<>(testSet);
			
			System.out.println("List 데이터 출력");
			for (int i = 0; i < testList.size(); i++) {
				System.out.print(testList.get(i) + " ");
			}
			System.out.println();
			System.out.println();
		
		
			//로또번호 만들기
			
			HashSet<Integer> set = new HashSet<>();
			while(set.size()<6) {
				int lotto = (int)(Math.random()*45+1);
				set.add(lotto);
			}
			System.out.println("로또");
			System.out.println(set);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
