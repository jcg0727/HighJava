package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest2 {

	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<>();
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-2222-2222"));
		memList.add(new Member(9, "성춘향", "010-3333-3333"));
		memList.add(new Member(3, "강감찬", "010-4444-4444"));
		memList.add(new Member(6, "일지매", "010-5555-5555"));
		memList.add(new Member(2, "변학도", "010-6666-6666"));

		System.out.println("정렬전");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("----------------------------------------------");
	
		Collections.sort(memList);
		System.out.println("정렬후");
		for (Member mem : memList) {
			System.out.println(mem);
		}
	
		System.out.println("----------------------------------------------");
	
		
		Collections.shuffle(memList);
		
		Collections.sort(memList);
		System.out.println("섞기 후");
		for (Member mem : memList) {
			System.out.println(mem);
		}
	
		System.out.println("----------------------------------------------");
		
		Collections.sort(memList, new Member1());
		System.out.println("내림차순");
		for (Member mem : memList) {
			System.out.println(mem);
		}
	}
}




//Member 클래스 작성하기 
//==> 회원번호(int), 회원이름(String), 전화번호(String)로 구성
//==> 회원 이름을 기준으로 오름차순이 되도록 내부 정렬 기준을 넣어준다.
//==> Comparable 인터페이스를 구현해서 작성한다.

class Member implements Comparable<Member>{
	
	private int num;
	private String name;
	private String tel;
	
	//생성자
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	//getter, setter
	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	
	// 내부 정렬 기준을 처리하는 메서드
	// 회원 이름을 기준으로 오름차순이 되도록 내부 정렬 기준을 넣어준다.
	@Override
	public int compareTo(Member mem) {
		return name.compareTo(mem.getName());
	}
}


//회원번호의 내림차순으로 정렬하는 외부정렬기준클래스 작성하기
class Member1 implements Comparator<Member>{

	@Override
	public int compare(Member mem1, Member mem2) {
		if(mem1.getNum() > mem2.getNum()) {
			return -1;
		}else if(mem1.getNum() < mem2.getNum()) {
			return 1;
		}else {
			return 0;
		}
		
		//Wrapper 클래스를 이용하는 방법 1
	//	return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
		
		//Wrapper 클래스를 이용하는 방법 2
		//return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
	}
}