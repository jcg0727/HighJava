
package kr.or.ddit.basic.stream;
/*
 한글이 저장될 파일 읽어오기(한글의 인코딩 방식을 지정해서 읽어온다)
*/

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest05 {

	public static void main(String[] args) {
		try {
		//	FileReader fr = new FileReader("d:/d_other/test_ansi.txt");
		//	FileReader fr = new FileReader("d:/d_other/test_utf8.txt");
			FileInputStream fis = new FileInputStream("d:/d_other/test_utf8.txt");
			
			//기본 인코딩 방식으로 읽어온다.
			//InputStreamReader isr = new InputStreamReader(fis);
			
			
			// 인코딩 방식을 지정해서 읽어오기
			// 인코딩 방식 예시
			// - MS949 ==> 윈도우의 기본 한글 인코딩 방식(ANSI와 같다.)
			// - UTF-8 ==> 유니코드 UTF-8 인코딩 방식
			// - US-ASCII ==> 영문 전용 인코딩 방식
			//InputStreamReader isr = new InputStreamReader(fis, "ms949");
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			

			int c;
			
			while((c=isr.read())!=-1) {
				System.out.print((char)c);
			}
			fis.close();
			isr.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
