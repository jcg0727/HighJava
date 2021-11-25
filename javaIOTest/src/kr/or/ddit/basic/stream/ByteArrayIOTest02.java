package kr.or.ddit.basic.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {
		byte[] insrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outsrc = null;
		
		byte[] temp = new byte[4]; //4개 짜리 배열 생성 ->읽어온 데이터가 저장될 배열
		
		ByteArrayInputStream input = new ByteArrayInputStream(insrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			// input.available() ==> 읽어올 데이터의 개수 반환한다.
			// 일어올 데이터가 있는지 여부를 검사
			while(input.available() > 0) {
				/*
				input.read(temp); //temp배열 개수만큼 읽어와 temp배열에 저장한다.
				output.write(temp);
				*/
				
				int len = input.read(temp); // 실제 읽어온 byte수를 반환한다.
				output.write(temp, 0, len);
			}
			outsrc = output.toByteArray();
			System.out.println();
			System.out.println("insrc = " + Arrays.toString(insrc));
			System.out.println("outsrc = " + Arrays.toString(outsrc));
			
			input.close();
			output.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}

}
