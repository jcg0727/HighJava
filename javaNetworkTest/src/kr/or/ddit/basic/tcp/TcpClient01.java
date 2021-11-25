package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient01 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//현재 자신 컴퓨터를 나타내는 방법
		// 1) 원래의 IP주소 : 예 )192.168.45.40
		// 2) 지정된 IP주소 : 127.0.0.1
		// 3) 원래의 컴퓨터 이름 : 예)SEM
		// 4) 지정된 컴퓨터 이름 : localhost  // 내컴퓨터 라는 뜻
		
		
		System.out.println("서버에 요청 신호를 보냅니다...");
		//서버의 IP주소와  Port번호를 지정해서 sOCKET객체를 생성한다.
		Socket socket = new Socket("localhost", 7777);
				
		//Socket객체를 생성하는 명령 이후에는 서버와 연결이 완료된 후의 작업을 기술하면 된다.
		System.out.println("서버에 연결되었습니다...");
		
		//서버에서 보내온 메시지를 받아서 출력하기
		
		// InputStream객체 생성 
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		//서버가 보낸 자료 받아서 출력하기
		System.out.println("서버에서 보내온 메시지 : " + dis.readUTF());
		System.out.println();
		
		System.out.println("연결을 종료합니다...");
		
		
		//스트림과 소켓 닫기
		dis.close();
		socket.close();
		
		
		
		
		
		
		
	}

}
