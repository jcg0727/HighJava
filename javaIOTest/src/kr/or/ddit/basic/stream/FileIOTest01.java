package kr.or.ddit.basic.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {

	public static void main(String[] args) {
		//FileInputStream을 이용한 파일 내용 읽기
		try {
			//읽어올 파일을 인수값으로 받는 FileInputStream객체 생성
			//방법1 ==> 읽어올 파일 정보를 문자열로 지정하기
			FileInputStream fin = new FileInputStream("d:/d_other/test.txt");
			
			//방법2 ==> 읽어올 파일 정보를 File 객체로 지정하기
//			File file = new File("d:/d_other/test.txt");
//			FileInputStream fin = new FileInputStream(file);
			
			int c;
			while((c=fin.read())!=-1) {
				System.out.println((char)c);
			}
			fin.close(); //작업 완료후 스트림 닫기
		}catch(IOException e) {
			System.out.println("입출력 오류");
		}

	}

}
