package kr.or.ddit.basic.stream;

import java.io.File;
import java.io.FileOutputStream;

public class FileIOTest02 {

	public static void main(String[] args) {
		//FileOutputStream을 이용해서 파일에 출력하기
		try {
			File file = new File("d:/d_other/out.txt");
			FileOutputStream fout = new FileOutputStream(file);
			
			for (char ch = 'A'; ch <= 'Z'; ch++) {
				fout.write(ch); //ch변수의 값을 파일로 출력
			}
			System.out.println("출력완료");
			fout.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
