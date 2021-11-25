package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;

public class 연습 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		
	list.add("고종");
	list.add("정조");
	list.add("태종");
	list.add("영조");
	list.add("태조");
	list.add("세종");
	
	System.out.println(list);
	
	Collections.sort(list);
	System.out.println(list);
	

	}
}


