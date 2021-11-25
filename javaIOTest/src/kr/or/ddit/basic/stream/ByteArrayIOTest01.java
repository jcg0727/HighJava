package kr.or.ddit.basic.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

//바이트 기반의 스트림의 입출력 연습


public class ByteArrayIOTest01 {
	public static void main(String[] args) {
		byte[] insrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outsrc = null;
		
		// 입력용 스트림 객체 변수 선언 및 객체생성
		ByteArrayInputStream input = new ByteArrayInputStream(insrc);
		
		// 출력용 스트림 객체 변수 선언 및 객체 생성
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		int data; // 읽어온 자료가 저장될 변수 선언
		
		//read()메서드 ==> 더 이상 읽어올 데이터가 없으면 -1을 반환한다.
		
		while((data = input.read()) != -1 ) {
			//읽어온 데이터를 처리하는 내용을 작성하면 된다.
			output.write(data); // 일겅온 데이터를 출력용 스트림으로 출력하기
		}
		
		// 출력된 스트림값을 배열로 변환해서 저장하기
		outsrc = output.toByteArray();
		
		//입출력 스트림을 사용한 후에는 사용한 스트림을 모두 닫아 주어야 한다.
		//(일종의 자원 반납)
		try {
			input.close();
			output.close();
		} catch (IOException e) {
			
		}
		System.out.println("insrc => " + Arrays.toString(insrc));
		System.out.println("outsrc => " + Arrays.toString(outsrc));

	}
}
