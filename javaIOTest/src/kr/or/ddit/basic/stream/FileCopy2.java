package kr.or.ddit.basic.stream;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/*
 * 복사할 원본 파일과 출력될 파일을 JFileChooser객체를 이용해서 선택하여 처리하도록 한다.
 */
public class FileCopy2 {

	public static void main(String[] args) {
		
		JFileChooser chooser = new JFileChooser();
		
		FileNameExtensionFilter img = new FileNameExtensionFilter("그림파일", "png", "jpg", "gif");
		chooser.addChoosableFileFilter(img);
		
		int result = chooser.showOpenDialog(new Panel());
		File selectedFile = chooser.getSelectedFile();
		System.out.println(selectedFile.getAbsolutePath());
		
		File file = new File("d:/d_other/펭귄.jpg");
		try {
			System.out.println("복사시작");
			//원본 데이터를 읽어올 입력용 스트림 객체 생성
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			//저장할 출력용 스트림 객체 생성
			FileOutputStream fout = new FileOutputStream("d:/d_other/연습용/복사본-펭귄.jpg");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			
			
			
			
			
			
			
			
			
			int data;

			while ((data=bis.read())!=-1) {
				bout.write(data);
			}
			bout.flush();
			
			//스트림 닫기
			bout.close();
			bis.close();

			System.out.println("복사 작업 완료");
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
