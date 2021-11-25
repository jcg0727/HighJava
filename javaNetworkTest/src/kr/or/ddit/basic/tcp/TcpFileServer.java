package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 네트워크가 연결되면 클라이언트는 'd:/d_other'폴더에 있는 '펭귄.jpg' 파일을 서버로 전송한다.
 서버는 전송 받은 파일을 'd:/d_other/uploadFile'폴더에 같은 이름으로 저장한다.
 (클라이언트 : 파일을 읽어서 소켓으로 출력한다.
  서버 : 소켓에서 읽어서 파일로 출력한다.)
*/


public class TcpFileServer {
	private ServerSocket server;
	private Socket socket;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	private DataInputStream dis;
	
	public static void main(String[] args) {
		new TcpFileServer().serverStart();
	}
	
	
	private void serverStart() {
		File saveDir = new File("d:/d_other/uploadFile");
		if(!saveDir.exists()) { //저장할 폴더가 없으면 새로 생성한다.
			saveDir.mkdirs();
		}
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다.");
			socket = server.accept(); // 클라이언트의 요청을 기다린다.
			
			System.out.println("파일 다운로드 시작...");
			
			InputStream in = socket.getInputStream();
			
			//소켓을 이용한 입력용 스트림 객체 생성
			dis = new DataInputStream(in);
			
			//첫번째로 수신된 파일 이름을 읽어서 저장한다.
			String fileName = dis.readUTF();
			
			//저장할 파일 위치와 파일명을 지정하여 File객체 생성
			File saveFile = new File(saveDir, fileName);
			
			//소켓의 입력용 스트림을 이용한 버퍼 스트림 객체 생성
			bis = new BufferedInputStream(in);
			
			//파일 저장용 출력 스트림 객체 생성
			bos = new BufferedOutputStream(new FileOutputStream(saveFile));
			
			byte[] temp = new byte [1024];
			
			int len = 0;
			
			//소켓으로 받은 데이터를 파일로 저장한다.
			while ((len = bis.read(temp))>0) {
				bos.write(temp, 0, len);
			}
			bos.flush();
			System.out.println("파일 다운로드 완료...");
	
		} catch (Exception e) {
			System.out.println("파일 다운로드 실패 \n" + e.getMessage());
		}finally {
			if(dis != null) try {dis.close();}catch(IOException e) {}
			if(bis != null) try {bis.close();}catch(IOException e) {}
			if(bos != null) try {bos.close();}catch(IOException e) {}
			if(socket != null) try {socket.close();}catch(IOException e) {}
			if(server != null) try {server.close();}catch(IOException e) {}	
		}
	}
}
