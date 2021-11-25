package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
// 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 student 클래스를 만든다. 
// 이 클래스의 생성자에서는 학번, 이름,국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화 처리를 한다.  
// 이 student 객체는 List에 저장하여 관리한다.
// List 에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 잇는 내부 정렬 기준을 구현하고 
//총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬되는 외부의 정렬기준클래스를 작성하여 정렬된 결과를 출력하시오.
//(단, 등수는 List에 전체 데이터가 추가된 후에 구하도록 한다.)

public class StudentTest {
	//등수를 구하는 메서드
	public void setRanking(List<Student> stdList) {
		for (Student stu1 : stdList) { //기준 데이터를 구하기 위한 반복문
			int rank = 1; // 처음에는 1등으로 설정해 놓고 시작한다.
			for (Student stu2 : stdList) { //비교 대상을 나타내는 반복문
				//기준보다 큰 값을 만나면 rank 값을 증가시킨다.
				if(stu1.getTotalScore() < stu2.getTotalScore()) {
					rank++;
				}
			} // 비교 대상 반복문 끝
			stu1.setRank(rank);//구해진 등수를 Student객체의 rank변수에 저장한다.
		}
	}
	

	public static void main(String[] args) {
		StudentTest studentTest = new StudentTest();
		List<Student> list = new ArrayList<Student>();
		
		list.add(new Student("1111", "정조", 80, 70, 60));
		list.add(new Student("3333", "고종", 60, 40, 20));
		list.add(new Student("2222", "태종", 80, 100, 80));
		list.add(new Student("4444", "세조", 50, 40, 50));
		list.add(new Student("5555", "단종", 50, 40, 50));
		
		// 등수 구하는 메서드 호출하기
		studentTest.setRanking(list);
		System.out.println("정렬전");
		for (Student std : list) {
			System.out.println(std);
		}
		
		
		
		
		System.out.println("정렬 전");
		for (Student student : list) {
			System.out.println(student);
		}
		System.out.println("-----------------------------------");
		
		System.out.println("학번의 오름차순");
		Collections.sort(list);
		for (Student student : list) {
			System.out.println(student);
		}
		System.out.println("-----------------------------------");
		
		System.out.println("총점의 내림차순");
		Collections.sort(list, new stu());
		for (Student student : list) {
			System.out.println(student);
		}
	}
}


class Student implements Comparable<Student>{
	private String num;
	private String name;
	private int koreanScore;
	private int englishScore;
	private int mathScore;
	private int totalScore;
	private int rank;
	
	public Student(String num, String name, int koreanScore, int englishScore, int mathScore) {
		super();
		this.num = num;
		this.name = name;
		this.koreanScore = koreanScore;
		this.englishScore = englishScore;
		this.mathScore = mathScore;
		this.totalScore = koreanScore + englishScore + mathScore;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKoreanScore() {
		return koreanScore;
	}

	public void setKoreanScore(int koreanScore) {
		this.koreanScore = koreanScore;
	}

	public int getEnglishScore() {
		return englishScore;
	}

	public void setEnglishScore(int englishScore) {
		this.englishScore = englishScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", koreanScore=" + koreanScore + ", englishScore="
				+ englishScore + ", mathScore=" + mathScore + ", totalScore=" + totalScore + ", rank=" + rank + "]";
	}


	//학번의 오름차순
	@Override
	public  int compareTo(Student s) {
		return num.compareTo(s.getNum());
	}
	
}

//총점의 내림차순
class stu implements Comparator<Student>{
	@Override
	public int compare(Student stu1, Student stu2) {
		if(stu1.getTotalScore() == stu2.getTotalScore()){
			return stu1.getName().compareTo(stu2.getName());
		}else if(stu1.getTotalScore() > stu2.getTotalScore()) {
			return -1;
		}else if(stu1.getTotalScore()<stu2.getTotalScore()) {
			return 1;
		}else {
			return 0;
		}
		
//		if(stu1.getTotalScore() == stu2.getTotalScore()) {
//			return stu1.getTotalScore().compareTo(stu2.getTotalScore());
//		}else {
//			return Integer.compare(stu1.getTotalScore(), stu2.getTotalScore()) * 1-;
//		}
		
		
	}
}
	
	
	
	
	
	
