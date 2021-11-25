package kr.or.ddit.basic.tcp;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JFileChooser;

public class TcpFileClient {
	private Socket socket;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	private DataOutputStream dos;
	
	
	
	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}
	//전송할 파일을 선택해서 반환하는 메서드
	private File getUploadFile() {
		JFileChooser chooser = new JFileChooser();
		
		chooser.setCurrentDirectory(new File("d:/d_other"));
		
		File selectedFile = null;
		
		int result = chooser.showOpenDialog(new Panel());
		
		if(result == JFileChooser.APPROVE_OPTION) {
			selectedFile = chooser.getSelectedFile();
					
		}
		return selectedFile;
	}
	
	
	
	
	
	private void clientStart() {
		//전송할 파일을 이용한 File객체 생성
		File file = getUploadFile();
		
		if(file==null) {
			System.out.println("전송할 파일이 선택되지 않았습니다.");
			System.out.println("전송 작업 취소");
			return;
			
		}
		
		
		String fileName = file.getName(); //파일 이름 구하기
		if(!file.exists()) { //전송할 파일이 없으면...
			System.out.println(fileName + " 파일이 없습니다.");
			System.out.println("전송 작업 취소...");
			return;
		}
		try {
			socket = new Socket("localhost" , 7777);
			System.out.println("파일 전송 시작...");
			OutputStream out = socket.getOutputStream();
			
			dos = new DataOutputStream(out);
			
			//서버에 접속하면 첫번째로 전송할 파일의 이름을 전송한다
			dos.writeUTF(fileName);
			
			//전송할 파일을  읽기 위한 입력용 스트림 객체 생성
			bis = new BufferedInputStream(new FileInputStream(file));
			
			//서버로 전송할 출력용 스트림 객체 생성 (소켓의 출력용 스트림 사용)
			bos = new BufferedOutputStream(out);
			
			byte[] temp = new byte[1024];
			
			int len = 0;
			
			while((len = bis.read(temp))>0) {
				bos.write(temp, 0, len);
			}
			bos.flush();
			System.out.println("파일 전송 완료...");
			
		} catch (Exception e) {
			System.out.println("파일 전송 실패 !! \n" + e.getMessage());
		}finally {
			//사용했떤 스트림과 소켓 닫기
			if(dos != null) try {dos.close();}catch(IOException e) {}
			if(bis != null) try {bis.close();}catch(IOException e) {}
			if(bos != null) try {bos.close();}catch(IOException e) {}
			if(socket != null) try {socket.close();}catch(IOException e) {}
		}
	}
}
