package kr.or.ddit.basic.stream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileIOTest04 {

	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일로 저장하기
		
		//InputStreamReader ==> 입력용 byte 기반의 스트림을 문자 기반의 스트림으로 변경해 주는 보조 스트림
		
		//OutputStreamWriter ==> 출력용 byte 기반의 스트림을 문자기반의 스트림으로 변경해 주는 보조 스트림
		try {
			// System.in ==> 콘솔(표준 입출력 장치)의 입력 장치를 이야기한다.
			InputStreamReader isr = new InputStreamReader(System.in);
			FileWriter fw = new FileWriter("d:/d_other/testchar.txt");
			System.out.println("아무거나 입력하세요.(입력의 끝은 Ctrl + Z 입니다.");
			
			int c;
			
			while((c=isr.read())!=-1) {
				fw.write(c); //콘솔로 입력받은 데이터를 파일에 출력하기
			}
			isr.close();
			fw.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
